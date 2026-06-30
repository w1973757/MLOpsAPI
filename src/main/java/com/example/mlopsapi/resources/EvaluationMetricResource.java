/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mlopsapi.resources;

import com.example.mlopsapi.exceptions.ModelDeprecatedException;
import com.example.mlopsapi.models.EvaluationMetric;
import com.example.mlopsapi.models.MachineLearningModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class EvaluationMetricResource {

    private String modelId;

    private static Map<String, List<EvaluationMetric>> metricsByModel = new HashMap<>();

    public EvaluationMetricResource(String modelId) {
        this.modelId = modelId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EvaluationMetric> getMetrics() {

        if (!metricsByModel.containsKey(modelId)) {
            metricsByModel.put(modelId, new ArrayList<>());
        }

        return metricsByModel.get(modelId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EvaluationMetric addMetric(EvaluationMetric metric) {

        for (MachineLearningModel model : ModelResource.getModelList()) {
            if (model.getId().equals(modelId)) {
                if (model.getStatus().equalsIgnoreCase("DEPRECATED")) {
                    throw new ModelDeprecatedException(
                            "Metrics cannot be added because this model is deprecated."
                    );
                }
            }
        }

        if (!metricsByModel.containsKey(modelId)) {
            metricsByModel.put(modelId, new ArrayList<>());
        }

        metric.setId("MET-" + UUID.randomUUID().toString());
        metric.setTimestamp(System.currentTimeMillis());

        metricsByModel.get(modelId).add(metric);

        for (MachineLearningModel model : ModelResource.getModelList()) {
            if (model.getId().equals(modelId)) {
                model.setLatestAccuracy(metric.getAccuracyScore());
                break;
            }
        }

        return metric;
    }
}
