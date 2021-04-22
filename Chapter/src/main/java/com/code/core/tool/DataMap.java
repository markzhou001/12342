package com.code.core.tool;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class DataMap {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DataMap.class);
    String errMsg;
    Map<String,String> result = new HashMap<String, String>();

    public String get(String key){
        if(StringUtils.isBlank(key)){
            errMsg = "Input key is blank!";
            logger.error(errMsg);
            return null;
        }
        return result.get(key.toUpperCase());
    }

    public void put(String key, String value) throws QaException {
        if(StringUtils.isBlank(key)){
            errMsg = "Input key is blank!";
            logger.error(errMsg);
            throw new QaException(errMsg);
        }
        result.put(key.toUpperCase(),value);
    }

    public Map<String, String> getResult() {
        return result;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(Map.Entry<String, String> e: result.entrySet()){
            sb.append(String.format("%s:%s,",e.getKey(), e.getValue()));
        }
        return sb.substring(0, sb.length() - 1).toString();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
