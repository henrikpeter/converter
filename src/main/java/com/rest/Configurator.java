package com.rest;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Configurator extends Application {

    public Configurator() {
        init();
    }

    private void init() {
      
    	  BeanConfig beanConfig = new BeanConfig();
          beanConfig.setVersion("1.0.0");
          beanConfig.setSchemes(new String[]{"http"});
          beanConfig.setHost("localhost:8080");
          beanConfig.setBasePath("/api");
          beanConfig.setResourcePackage(RestService.class.getPackage().getName());
          beanConfig.setTitle("Swagger for the converter service");
          beanConfig.setScan(true);
    }

}

