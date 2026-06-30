/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mlopsapi;

import com.example.mlopsapi.exceptions.GlobalExceptionMapper;
import com.example.mlopsapi.filters.LoggingFilter;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/v1")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        packages("com.example.mlopsapi");
        register(LoggingFilter.class);
        register(GlobalExceptionMapper.class);
    }
}