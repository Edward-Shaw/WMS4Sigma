package com.sigma.api.common.models;


public class RankingListElement {
    
    private String user;
    
    private String value;
    
    public RankingListElement(){}
    
    public RankingListElement(String user, String value){
        this.user = user;
        this.value = value;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }   
}
