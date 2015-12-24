package com.sigma.api.common.filters;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.collections.EnumerationUtils;

/**
 * 可以修改Headers的HttpServletRequestWrapper
 */
public class MutableHttpServletRequestWrapper extends HttpServletRequestWrapper{

    @Override
	public Enumeration getHeaders(String name) {
    	List<String> names = EnumerationUtils.toList(super.getHeaders(name));
    	if(headers.containsKey(name)){
    		names.add(name);
    	}

		return new Vector<String>(names).elements();
	}
    
	@Override
	public Enumeration getHeaderNames() {
		List<String> names = EnumerationUtils.toList(super.getHeaderNames());
		names.addAll(headers.keySet());
		
		return new Vector<String>(names).elements();
	}

	private Map<String, String> headers = new HashMap<String, String>();
    
    public MutableHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    
    /**
     * add a header with given name and value
     * @param name
     * @param value
     */
    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    /*@Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        for (String name : headers.keySet()) {
            names.add(name);
        }
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values = Collections.list(super.getHeaders(name));
        if (headers.containsKey(name)) {
            values.clear();
            values.add(headers.get(name));
        }
        return Collections.enumeration(values);
    }*/

	@Override
	public String getHeader(String name) {
		String value = headers.get(name);
		
		if(value == null){
			return super.getHeader(name);
		}
		
		return value;
	}

}
