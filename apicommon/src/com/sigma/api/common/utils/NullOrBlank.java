package com.sigma.api.common.utils;

public class NullOrBlank {
    private Object value;
    
    public NullOrBlank (){}
    
    public NullOrBlank (Object value){
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    /**
     * 如果所需判断的value不为String，则value不为null时返回false, 
     * 如果value为String时，value既不为null也不为空时返回false
     * @param value
     * @return
     */
    public static boolean check(Object value){
        boolean result = true;
        if(value != null){
            if(value instanceof String){
                String str = (String) value;
                if(!str.isEmpty()){
                    result = false;
                }                
            } else {
                result = false;
            }
        }
        
        return result;
    }
    
}
