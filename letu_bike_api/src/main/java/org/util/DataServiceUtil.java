package org.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.util.Condition;



/**
 * 数据操作通用类
 */
public class DataServiceUtil {

    /**
     * 添加条件
     * @param criteria
     * @param conditions
     * @throws Exception
     */
    private static void addCondition(Object criteria,List<Condition> conditions) throws Exception {
        //条件组循环
        for (Condition condition : conditions) {
            if(condition==null){
                continue;
            }
            Object[] args = condition.getArgs();
            Class[] argsClass = condition.getArgsClass();
            String funcName = "and" + condition.getAttributeFindName() + condition.getTailName();
            //查询条件加入
            Method method = criteria.getClass().getMethod(funcName, argsClass);
            method.invoke(criteria, args);
        }
    }
    /**
     * 通用查找函数
     * @param mapper
     * @param example
     * @param criteria
     * @param conditions
     * @return
     */
    public static Object find(Object mapper,Object example,
                                    Object criteria,List<Condition> conditions) throws Exception {
        addCondition(criteria,conditions);
        //mapper查询
        Method[] ms =mapper.getClass().getMethods();
        //联合查询函数存在标记
        boolean union_flag=false;
        //选择函数存在标记
        boolean select_flag=false;
        for(Method m:ms){
            if(m.getName().equals("selectByExample")){
                select_flag = true;
            }
            if(m.getName().equals("selectUnionByExample")){
                union_flag  = true;
                break;
            }
        }
        Method method = null;
        if(union_flag){
            method = mapper.getClass().getMethod("selectUnionByExample",example.getClass());
        }else if(select_flag){
            method = mapper.getClass().getMethod("selectByExample",example.getClass());
        }else{
            return null;
        }
        return method.invoke(mapper,example);
    }

    /**
     * 通用count函数
     * @param mapper
     * @param example
     * @param criteria
     * @param conditions
     * @return
     * @throws Exception
     */
    public static Object count(Object mapper,Object example,
                               Object criteria,List<Condition> conditions) throws Exception{
        addCondition(criteria,conditions);
        //mapper查询
        Method method = mapper.getClass().getMethod("countByExample",example.getClass());
        return method.invoke(mapper,example);
    }
}
