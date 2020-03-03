
package com.mycompany.resources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.HashSet;

@ApplicationPath("api")
public class RestAPP extends Application {
    
    
    @Override
    
    public Set<Class<?>> getClasses(){
    Set<Class<?>> resources = new HashSet<>();
    resources.add(ClientResource.class);
    return resources;
    }
    
}