
#### HashMap 学习总结

##### 重要方法的源码分析（JDK 1.8）

包含的常量：
```
 // 默认的初始容量（必须是2的幂值）
 static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;  // 16
 
 // 最大容量
 static final int MAXIMUM_CAPACITY = 1 << 30;   // 1073741824
 
 // 默认的加载因子（扩容因子）
 static final float DEFAULT_LOAD_FACTOR = 0.75f;
 
 // 添加一个元素时，如果新元素预插入的链表长度已等于此值且容量大于等于 MIN_TREEIFY_CAPACITY
 // 那么链表将转转为红黑存储
 static final int TREEIFY_THRESHOLD = 8;
 
 // 转换链表的临界值，当链表长度小于此值时，会将红黑树结构转换成链表结构
 static final int UNTREEIFY_THRESHOLD = 6;
 
 // 最小树容量
 static final int MIN_TREEIFY_CAPACITY = 64;
```

###### 插入：put()
```
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 如果哈希表为空则先创建表
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        // 由 key 的 hash 值算出要插入的数组索引 i
        if ((p = tab[i = (n - 1) & hash]) == null)
            // 如果 table[i] 等于 null，则直接插入
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            // 如果 key 已经存在，则覆盖它的原 value 值
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            // 如果存储结构为红黑树
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                // 如果是链表结构，循环链表节点准备插入
                for (int binCount = 0; ; ++binCount) {
                    // 找到下一个指针为空的节点
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        // 检查是否需要将链表转为红黑树（这里满足了链表转为红黑树的第一个条件，即新元素预插入的链表长度已达到阈值）
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            // treeifyBin() 方法会判断第二个条件（即容量是否大于等于阈值，如果是则转为红黑树，否则做扩容处理）
                            treeifyBin(tab, hash);
                        break;
                    }
                    // key 已经存在直接覆盖 value
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        // 如果容量超过阈值，做扩容处理
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }

```

###### 查询：get()
```
    public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        // 非空校验
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            // 总是先检查第一个节点是否是要查询的目标元素
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            // 对下一个节点的非空校验
            if ((e = first.next) != null) {
                // 如果为红黑树结构，则检索树中的节点
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                // 链表结构，则在这里循环遍历各节点
                do {
                    // 返回 hash 和 key 都相等的节点
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
```

###### 扩容：resize()
```
    final Node<K,V>[] resize() {
        // 备份扩容前的数组
        Node<K,V>[] oldTab = table;
        // 扩容前数组的大小
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        // 扩容前数组的容量阈值
        int oldThr = threshold;
        // 新数组的大小和阈值
        int newCap, newThr = 0;
        if (oldCap > 0) {
            // 如果容量已经超过最大阈值则不再进行扩容，返回原数组
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            // 将容量阈值设为当前容量的两倍，但不能超过容量的最大阈值
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        // 如果原数组没有数据，那么新数组大小设为原数组的容量阈值
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            // 如果初始化的值为 0，则使用默认的初始化容量
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        // 如果新的容量阈值为 0
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        // --开始扩容--//
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        // table 先置为新数组大小的空数组
        table = newTab;
        // 如果原数组数据不为空，将原数组复制到新 table 中
        if (oldTab != null) {
            // 循环原数组
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                // 将非空元素进行复制
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    // 如果只有一个节点，直接赋值
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    // 红黑树结构的复制
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    // 链表结构的复制
                    else { // preserve order
                        // JDK 1.8 扩容优化部分
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            // 通过高位运算来判断元素是否需要移动
                            // 这里不需要移动，元素在新数组中的下标 = 原下标位置（原索引）
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            // 这里需要移动，元素在新数组中的下标 = 原索引 + 原数组长度（oldCap）
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        // 将原索引放到哈希桶中
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        // 将 原索引 + oldCap 放到哈希桶中
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
```

##### HashMap 相关的几个重要知识点

##### 什么是加载因子？为什么默认的加载因子为 0.75？

> &emsp;加载因子也叫扩容因子或负载因子，用来判断什么时候进行扩容的，假如加载因子是 0.5，HashMap 的初始化容量是 16，那么当 HashMap 中有 16*0.5=8 个元素时，HashMap 就会进行扩容。
<br>&emsp;那加载因子为什么是 0.75 而不是 0.5 或者 1.0 呢？
<br>&emsp;这其实是出于容量和性能之间平衡的结果：
<br>&emsp;①当加载因子设置比较大的时候，扩容的门槛就被提高了，扩容发生的频率比较低，占用的空间会比较小，但此时发生 Hash 冲突的几率就会提升，因此需要更复杂的数据结构来存储元素，这样对元素的操作时间就会增加，运行效率也会因此降低；
<br>&emsp;②而当加载因子值比较小的时候，扩容的门槛会比较低，因此会占用更多的空间，此时元素的存储就比较稀疏，发生哈希冲突的可能性就比较小，因此操作性能会比较高。
<br>&emsp;除了上述情况外，HashMap的容量大小有一个硬性的要求，就是必须为2的幂值，那么和3/4的乘积就可以是一个整数，所以就取了一个 0.5 到 1.0 的平均数 0.75 作为加载因子。

##### JDK 1.8 HashMap 扩容时做了哪些优化？

> &emsp;JDK 1.8 在扩容时并没有像 JDK 1.7 那样，重新计算每个元素的哈希值，而是通过高位运算（e.hash & oldCap）来确定元素是否需要移动
<br> &emsp;使用 e.hash & oldCap 得到的结果,如果高一位为 0，表示元素在扩容时位置不会发生任何变化，即元素的新下标等于原下标；如果高一位为 1，表示元素在扩容时位置发生了变化，新的下标位置等于原下标位置 + 原数组长度。

##### 为什么多节点时要转为红黑树结构来存储？

> &emsp;如果采用链表存储，那么每次遍历一个链表，平均查找的时间复杂度是 O(n)，n 是链表的长度。
<br>&emsp;红黑树有和链表不一样的查找性能，由于红黑树有自平衡的特点，可以防止不平衡情况的发生，所以可以始终将查找的时间复杂度控制在 O(log(n))。
<br>&emsp;最初链表还不是很长，所以可能 O(n) 和 O(log(n)) 的区别不大，但是如果链表越来越长，那么这种区别便会有所体现。所以为了提升查找性能，需要在特定条件下把链表转化为红黑树的形式。

##### 为什么不一开始就用红黑树？

> &emsp;单个 TreeNode 需要占用的空间大约是普通 Node 的两倍，所以只有当包含足够多的 Nodes 时才会转成 TreeNodes，而是否足够多就是由 TREEIFY_THRESHOLD 的值决定的。而当桶中节点数由于移除或者 resize 变少后，又会变回普通的链表的形式，以便节省空间。

##### 为什么默认设置哈希桶中的节点超过 8 个才转为红黑树？

&emsp;源码中对选择 8 的注释说明：
```
     * In usages with well-distributed user hashCodes, tree bins are
     * rarely used.  Ideally, under random hashCodes, the frequency of
     * nodes in bins follows a Poisson distribution
     * (http://en.wikipedia.org/wiki/Poisson_distribution) with a
     * parameter of about 0.5 on average for the default resizing
     * threshold of 0.75, although with a large variance because of
     * resizing granularity. Ignoring variance, the expected
     * occurrences of list size k are (exp(-0.5) * pow(0.5, k) /
     * factorial(k)). The first values are:
     *
     * 0:    0.60653066
     * 1:    0.30326533
     * 2:    0.07581633
     * 3:    0.01263606
     * 4:    0.00157952
     * 5:    0.00015795
     * 6:    0.00001316
     * 7:    0.00000094
     * 8:    0.00000006
     * more: less than 1 in ten million
```

> &emsp;如果 hashCode 分布良好，也就是 hash 计算的结果离散好的话，那么红黑树这种形式是很少会被用到的，因为各个值都均匀分布，很少出现链表很长的情况。在理想情况下，链表长度符合泊松分布，各个长度的命中概率依次递减，当长度为 8 的时候，概率仅为 0.00000006。这是一个小于千万分之一的概率。
<br>&emsp;通常如果我们的hash算法正常的话，那么链表的长度也不会很长，且 Map 里面一般是不会存储这么多的数据的，所以通常情况下，并不会发生从链表向红黑树的转换。所以就选择了概率非常小，小于千万分之一概率，也就是长度为 8 的概率，把长度 8 作为转化的默认阈值。

&emsp;**注意**：如果平时开发中发现 HashMap 或是 ConcurrentHashMap 内部出现了红黑树的结构，这个时候往往就说明我们的哈希算法出了问题，需要留意是不是我们实现了效果不好的 hashCode 方法，并对此进行改进，以便减少冲突。


##### 为什么说 HashMap 是线程不安全的？

> &emsp;在 HashMap 的 put() 方法中，有一行 "modCount++" 代码，这属于典型的 "i++" 操作，从表面上看 i++ 只是一行代码，但实际上它并不是一个原子操作，它的执行步骤主要分为三步（读取+增加+保存），而且在每步操作之间都有可能被打断。所以如果存在多个线程同时进行 i++ 操作，那么就有可能产生线程安全问题，导致数据结果错误。
<br>&emsp;除了上述情况外，HashMap 还有许多种线程不安全的情况：
<br>&emsp;① 同时 put 碰撞导致数据丢失
<br>&emsp;② 可见性问题无法保证
<br>&emsp;③ 死循环造成 CPU 100%

&emsp;**注意**：在多线程使用场景中如果需要使用 Map，应该尽量避免使用线程不安全的 HashMap。同时，虽然 Collections.synchronizedMap(new HashMap()) 是线程安全的，但是效率低下，因为内部用了很多的 synchronized，多个线程不能同时操作。推荐使用线程安全同时性能比较好的 **ConcurrentHashMap**。

##### 什么情况下会导致死循环？

> &emsp;产生死循环最主要的原因就是在扩容的时候，也就是内部新建新的 HashMap 的时候，扩容的逻辑会反转散列桶中的节点顺序，当有多个线程同时进行扩容的时候，由于 HashMap 并非线程安全的，所以如果两个线程同时反转的话，便可能形成一个循环，并且这种循环是链表的循环，相当于 A 节点指向 B 节点，B 节点又指回到 A 节点，这样一来，在下一次想要获取该 key 所对应的 value 的时候，便会在遍历链表的时候发生永远无法遍历结束的情况，也就发生了死循环，死循环会造成 CPU 100% 的情况。
