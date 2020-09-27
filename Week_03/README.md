#### 递归

##### 基本思想

&emsp;将一个规模大的问题转化为规模小的相似子问题来解决

##### 递归模板

```
public void recur(int level, int param) {
    // terminator
    if(level > MAX_LEVEL) {
        // process result
        return;
    }
    
    // process current logic
    process(level, param);
    
    // drill down
    recur(level: level + 1, newParam);
    
    // restore current status
}
```

##### 思维要点

1.不要进行人肉递归（最大误区）

2.找到最近最简方法，将其拆解成可重复解决的子问题

3.数学归纳法思维
