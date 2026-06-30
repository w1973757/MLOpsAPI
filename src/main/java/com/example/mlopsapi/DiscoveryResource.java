/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.mlopsapi;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class DiscoveryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getApiInfo() {

        Map<String, Object> info = new HashMap<>();

        info.put("apiName", "MLOps Pipeline Management API");
        info.put("version", "1.0");
        info.put("Nafisa Huda", "w1973757@westminster.ac.uk");

        Map<String, String> resources = new HashMap<>();
        resources.put("workspaces", "/api/v1/workspaces");
        resources.put("models", "/api/v1/models");

        info.put("resources", resources);

        return info;
    }
}