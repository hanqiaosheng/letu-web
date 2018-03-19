package org.util;

/**
 * 说明:
 * 1. 若条件中只给lower,则取满足比lower大的
 * 2. 若条件中只给higher,则取满足比high小的
 * 3. 若条件中给出了两者，则取两者之间
 */
public class RangeCondition {
    //属性名
    private String attributeName;
    //区间低位
    public Object lower;
    //区间高位
    public Object higher;
}
