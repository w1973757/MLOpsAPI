/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mlopsapi.resources;

import com.example.mlopsapi.exceptions.LinkedWorkspaceNotFoundException;
import com.example.mlopsapi.models.MachineLearningModel;
import com.example.mlopsapi.models.MLWorkspace;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/models")
public class ModelResource {

    private static List<MachineLearningModel> models = new ArrayList<>();

    public static List<MachineLearningModel> getModelList() {
        return models;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MachineLearningModel> getModels(@QueryParam("status") String status) {

        if (status == null || status.isEmpty()) {
            return models;
        }

        List<MachineLearningModel> filteredModels = new ArrayList<>();

        for (MachineLearningModel model : models) {
            if (model.getStatus().equalsIgnoreCase(status)) {
                filteredModels.add(model);
            }
        }

        return filteredModels;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MachineLearningModel addModel(MachineLearningModel model) {

        MLWorkspace foundWorkspace = null;

        for (MLWorkspace workspace : WorkspaceResource.getWorkspaces()) {
            if (workspace.getId().equals(model.getWorkspaceId())) {
                foundWorkspace = workspace;
                break;
            }
        }

        if (foundWorkspace == null) {
    throw new LinkedWorkspaceNotFoundException(
        "Model cannot be created because the linked workspace does not exist."
    );
}

        model.setId("MOD-" + UUID.randomUUID().toString());

        models.add(model);

        foundWorkspace.getModelIds().add(model.getId());

        return model;
    }

    @Path("/{modelId}/metrics")
    public EvaluationMetricResource getMetricResource(@PathParam("modelId") String modelId) {
        return new EvaluationMetricResource(modelId);
    }
}