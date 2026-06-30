/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mlopsapi.exceptions;

import com.example.mlopsapi.models.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
@Provider
public class WorkspaceNotEmptyExceptionMapper implements ExceptionMapper<WorkspaceNotEmptyException> {

    @Override
    public Response toResponse(WorkspaceNotEmptyException exception) {

        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                409,
                "https://your-api-docs.com/errors"
        );

       return Response.status(Status.CONFLICT)
        .entity(errorMessage)
        .type(MediaType.APPLICATION_JSON)
        .build(); }}