package com.sigma.api.common;

import java.util.List;

public class ListAPIResponse extends APIResponse{
    private int count;

    public ListAPIResponse(int code, Object result, String appPrivateKey, int count){
        super(code, result, appPrivateKey);
        this.count = count;       
    }
    public ListAPIResponse(int code, List<?> result, int count){
        super(code, result);
        this.count = count;
    }
    
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }    
}
