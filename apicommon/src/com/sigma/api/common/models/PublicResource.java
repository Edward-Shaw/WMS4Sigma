package com.sigma.api.common.models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 需要在公网传输的资源都带有PublicKey, 从该类进行派生
 * @author XuGang
 *
 */
public class PublicResource {
	
	@JsonProperty("key")
    private String publicKey = "";
    
    public PublicResource(){}
    
    public PublicResource(String publicKey){
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return publicKey;
    }
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
    
}
