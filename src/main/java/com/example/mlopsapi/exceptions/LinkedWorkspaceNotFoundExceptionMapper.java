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
public class LinkedWorkspaceNotFoundExceptionMapper implements ExceptionMapper<LinkedWorkspaceNotFoundException> {

    @Override
    public Response toResponse(LinkedWorkspaceNotFoundException exception) {

        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                422,
                "https://your-api-docs.com/errors"
        );

        return Response.status(422)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}