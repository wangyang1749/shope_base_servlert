package com.shop.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

public class ParamUtil {

    public static  <T> void get(T t, HttpServletRequest req){
        Map<String,String[]> map= req.getParameterMap();
        Set<String> keys = map.keySet();
        for(String key : keys){
            try {
                BeanUtils.copyProperty(t, key, map.get(key));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
