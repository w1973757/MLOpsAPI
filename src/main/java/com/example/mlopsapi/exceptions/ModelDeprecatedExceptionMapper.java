/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mlopsapi.exceptions;

import com.example.mlopsapi.models.ErrorMessage;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ModelDeprecatedExceptionMapper implements ExceptionMapper<ModelDeprecatedException> {

    @Override
    public Response toResponse(ModelDeprecatedException exception) {

        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                403,
                "https://your-api-docs.com/errors"
        );

        return Response.status(Response.Status.FORBIDDEN)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}