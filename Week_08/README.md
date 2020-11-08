#### 位运算

##### 位运算符

|含义|运算符|示例|
|:--|--|--|
|左移|<<|0011 << 1 = 0110|
|右移|>>|0110 >> 1 = 0011|
|按位或|\||0011 \| 1011 = 1011|
|按位与|&|0011 & 1011 = 0011|
|按位取反|~|~0011 = 1100|
|按位异或|^|0011 ^ 1011 = 1000|

##### 指定位置的位运算

- 将 x 最右边的 n 位清零

> x & (~0 << n)


- 获取 x 的第 n 位值（0 或者 1）

> (x >> n) & 1

- 获取 x 的第 n 位的幂值

> x & (1 << n)

- 仅将第 n 位置为 1

> x | (1 << n)

- 仅将第 n 位置为 0

> x & (~ (1 << n))

- 将 x 最高位至第 n 位（含）清零

> x & ((1 << n) - 1)

##### 实战位运算要点

- 判断奇偶

> x % 2 == 1 —> (x & 1) == 1
<br>x % 2 == 0 —> (x & 1) == 0

- 除以2

> x >> 1
<br>mid = (left + right) / 2; —> mid = (left + right) >> 1;

- 将最低位的1清零

> x & (x-1)

- 得到最低位的1

> x & -x


#### 布隆过滤器

##### 定义

> 一个很长的二进制向量和一系列随机映射函数。布隆过滤器可以用于检索一个元素是否在一个集合中。

##### 优缺点

> 优点：空间效率和查询时间都远远超过一般的算法

> 缺点：有一定的误识别率和删除困难

##### 案例

- 比特币网络
- 分布式系统（Map-Reduce） — Hadoop、search engine
- Redis 缓存
- 垃圾邮件、评论等的过滤

#### LRU缓存

##### LRU Cache 代码实现

```
public class LRUCache {
    private Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        map = new LinkedCappedHashMap<>(capacity);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        return map.get(key);
    }

    public void put(int key, int value) {
        map.put(key, value);
    }

    private static class LinkedCappedHashMap<K, V> extends LinkedHashMap<K, V> {
        int maximumCapacity;

        LinkedCappedHashMap(int maximumCapacity) {
            super(16, 0.75f, true);
            this.maximumCapacity = maximumCapacity;
        }

        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > maximumCapacity;
        }
    }
}
```

#### 排序算法

##### 分类

###### 比较类排序

> &emsp;通过比较来决定元素间的相对次序，由于其时间复杂度不能突破
O(nlogn)，因此也称为非线性时间比较类排序。

###### 非比较类排序

> &emsp;不通过比较来决定元素间的相对次序，它可以突破基于比较排序的时
间下界，以线性时间运行，因此也称为线性时间非比较类排序。

![image](AC6BB520A0CB402AA348ABE209D6436F)

##### 时间复杂度

![image](43CD890D0080467EB50187BE0426688B)


##### 初级排序 - O(n^2)

###### 选择排序

- 算法原理

>  &emsp;每次找最小值，然后放到已排好序数组的末尾。

- 代码实现

```
    public int[] selectionSort(int[] arr) {
        int len = arr.length;
        int minIndex, temp;
        for (int i = 0; i < len - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                // 找到最小数，记录其索引
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 将当前最小数加入已排序末尾
            temp = arr[i]; arr[i] = arr[minIndex]; arr[minIndex] = temp;
        }
        return arr;
    }
```

###### 插入排序

- 算法原理

>  &emsp;从前到后逐步构建有序序列；对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。

- 代码实现

```
    public int[] insertionSort(int[] arr) {
        int len = arr.length;
        int preIndex, current;
        for (int i = 1; i < len; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
        return arr;
    }
```

###### 冒泡排序

- 算法原理

>  &emsp;嵌套循环，每次查看相邻的元素如果逆序，则交换。

- 代码实现

```
    public int[] bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                // 将较大元素冒泡
                if (arr[j] > arr[j+1]) {
                    // 元素交换
                    int temp = arr[j + 1]; arr[j + 1] = arr[j]; arr[j] = temp;
                }
            }
        }
        return arr;
    }
```



##### 高级排序 - O(N*LogN)

###### 快速排序

- 算法原理

> &emsp;数组取标杆 pivot，将小元素放 pivot左边，大元素放右侧，然后依次对右边和右边的子数组继续快排；以达到整个序列有序。

- 代码实现

```
    public static int[] quickSort(int[] arr, int begin, int end) {
        if (end <= begin) return arr;
        int pivot = partition(arr, begin, end);
        quickSort(arr, begin, pivot - 1);
        quickSort(arr, pivot + 1, end);
        return arr;
    }
    
    public static int partition(int[] a, int begin, int end) {
        // pivot: 标杆位置，counter: 小于pivot的元素的个数
        int pivot = end, counter = begin;
        for (int i = begin; i < end; i++) {
            if (a[i] < a[pivot]) {
                int temp = a[counter]; a[counter] = a[i]; a[i] = temp;
                counter++;
            }
        }
        int temp = a[pivot]; a[pivot] = a[counter]; a[counter] = temp;
        return counter;
    }
```
###### 归并排序

- 算法原理

> &emsp;1. 把长度为n的输入序列分成两个长度为n/2的子序列；
<br> &emsp;2. 对这两个子序列分别采用归并排序；
<br> &emsp;3. 将两个排序好的子序列合并成一个最终的排序序列。

- 代码实现

```
    public static int[] mergeSort(int[] arr, int left, int right) {
        if (right <= left) return arr;
        int mid = (left + right) >> 1; // (left + right) / 2
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        return merge(arr, left, mid, right);
    }

    public static int[] merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 中间数组
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
        // 也可以用 System.arraycopy(a, start1, b, start2, length)
        return arr;
    }
```
###### 堆排序

- 算法原理

> &emsp;1. 数组元素依次建立小顶堆
<br> &emsp;2. 依次取堆顶元素，并删除

- 代码实现

```
    public static int[] heapSort(int[] arr) {
        if (arr.length == 0) return arr;
        int length = arr.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(arr, length, i);
        }

        for (int i = length - 1; i >= 0; i--) {
            int temp = arr[0]; arr[0] = arr[i]; arr[i] = temp;
            heapify(arr, i, 0);
        }
        return arr;
    }

    public static void heapify(int[] arr, int length, int i) {
        int left = 2 * i + 1, right = 2 * i + 2;
        int largest = i;
        if (left < length && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < length && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = arr[i]; arr[i] = arr[largest]; arr[largest] = temp;
            heapify(arr, length, largest);
        }
    }
```

##### 特殊排序

###### 计数排序

> &emsp;计数排序要求输入的数据必须是有确定范围的整数。将输入的数据值转化为键存
储在额外开辟的数组空间中；然后依次把计数大于 1 的填充回原数组。

###### 桶排序

> &emsp;桶排序的工作的原理：假设输入数据服从均匀分布，将数据分到
有限数量的桶里，每个桶再分别排序（有可能再使用别的排序算法或是以递归方
式继续使用桶排序进行排）。

###### 基数排序

> &emsp;基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类
推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按
高优先级排序。
