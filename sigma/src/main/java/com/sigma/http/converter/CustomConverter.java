package com.sigma.http.converter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter;
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

public class CustomConverter extends AbstractCollectionConverter{

    public CustomConverter(Mapper mapper) {
        super(mapper);
    }
    
    public boolean canConvert(Class type) {  
        String classname = type.getName();
        if (classname.indexOf("Map") >= 0 
                || classname.indexOf("List") >= 0
                || classname.indexOf("Bean") >= 0)
            return true;
        else
            return false;
    }  
  
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {  
        if (source instanceof Map){
            Map map = (Map) source;  
            for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {  
                Entry entry = (Entry) iterator.next();  
                ExtendedHierarchicalStreamWriterHelper.startNode(writer, entry.getKey().toString(), Entry.class);        
                writer.setValue(entry.getValue().toString());  
                writer.endNode();  
            }  
        }
        else if(source instanceof  List){
            List<Map<String, Object>> list = (List) source;  
            Iterator<Map<String, Object>> mapIterator = list.iterator();
            for(Map<String, Object> l : list){
                writer.startNode("item");
                Map map = (Map) l;  
                for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {  
                    Entry entry = (Entry) iterator.next();  
                    ExtendedHierarchicalStreamWriterHelper.startNode(writer, entry.getKey().toString(), Entry.class);        
                    writer.setValue(entry.getValue().toString());  
                    writer.endNode();  
                }    
                writer.endNode();  
            }
        }
    }  


    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {  
        return null;  
    }

}
