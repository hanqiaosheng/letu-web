package org.util;

import java.io.Serializable;
import java.util.List;
import org.util.TransUtil;
/**
 * 说明:
 * 1. 若条件中只给lower,则取满足比lower大的
 * 2. 若条件中只给higher,则取满足比high小的
 * 3. 若条件中给出了两者，则取两者之间
 * 4. 否定：
 *      1. true:对该属性值进行否定（类似反选）
 *      2. false：默认值
 * 5. 正则查询：暂不提供
 * 6. 值域
 */
public class Condition implements Serializable {
    //属性名
    private String attributeName;
    //属性查询名
    private String attributeFindName;
    //参数表
    private Object[] args;
    //参数类型表
    private Class[] argsClass;
    //函数尾名
    private String tailName;

    /**
     * 单值设置
     * @param name
     * @param value
     */
    private void setOne(String name,Object value){
        attributeName = name;
        attributeFindName = TransUtil.transCriteriaFunctionName(name);
        args = new Object[1];
        argsClass = new Class[1];
        args[0]=value;
        argsClass[0]=value.getClass();
    }

    /**
     * 双值设置
     * @param name
     * @param valueOne
     * @param valueTwo
     */
    private void setTwo(String name,Object valueOne,Object valueTwo){
        attributeName = name;
        attributeFindName = TransUtil.transCriteriaFunctionName(name);
        args = new Object[2];
        argsClass = new Class[2];
        args[0]=valueOne;
        argsClass[0]=valueOne.getClass();
        args[1]=valueTwo;
        argsClass[1]=valueTwo.getClass();

    }
    /**
     * 条件类型：不等
     * @param
     */
    public void setNotEqualTo(String name,Object value){
        setOne(name,value);
        tailName = "NotEqualTo";
    }

    /**
     * 条件类型：等于
     * @param name
     * @param value
     */
    public void setEqualTo(String name,Object value){
        setOne(name,value);
        tailName = "EqualTo";
    }

    /**
     * 条件类型：大于
     * @param name
     * @param value
     */
    public void setGreaterThan(String name,Object value){
        setOne(name,value);
        tailName = "GreaterThan";
    }

    /**
     * 条件类型：大于等于
     * @param name
     * @param value
     */
    public void setGreaterThanOrEqualTo(String name,Object value){
        setOne(name,value);
        tailName = "GreaterThanOrEqualTo";
    }

    /**
     * 条件类型：小于
     * @param name
     * @param value
     */
    public void setLessThan(String name,Object value){
        setOne(name,value);
        tailName = "LessThan";
    }

    /**
     * 条件类型：小于等于
     * @param name
     * @param value
     */
    public void setLessThanOrEqualTo(String name,Object value){
        setOne(name,value);
        tailName = "LessThanOrEqualTo";
    }

    /**
     * 条件类型：区间
     * @param name
     * @param value1
     * @param value2
     */
    public void setBetween(String name,Object value1,Object value2){
        setTwo(name,value1,value2);
        tailName = "Between";
    }

    /**
     * 条件类型：值域内
     * @param name
     * @param values
     */
    public void setIn(String name,List<Object> values){
        setOne(name,values);
        tailName = "In";
    }

    /**
     * 条件类型：值域外
     * @param name
     * @param values
     */
    public void SetNotIn(String name,List<Object> values){
        setOne(name,values);
        tailName = "NotIn";
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeFindName() {
        return attributeFindName;
    }

    public String getTailName() {
        return tailName;
    }
    public Object[] getArgs(){
        return args;
    }
    public Class[] getArgsClass(){
        return argsClass;
    }
}
