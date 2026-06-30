/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mlopsapi.resources;

import com.example.mlopsapi.exceptions.WorkspaceNotEmptyException;
import com.example.mlopsapi.models.MLWorkspace;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/workspaces")
public class WorkspaceResource {

    private static List<MLWorkspace> workspaces = new ArrayList<>();
    public static List<MLWorkspace> getWorkspaces() {
    return workspaces;
}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MLWorkspace> getAllWorkspaces() {
        return workspaces;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MLWorkspace addWorkspace(MLWorkspace workspace) {
        workspaces.add(workspace);
        return workspace;
    }

    @GET
@Path("/{workspaceId}")
@Produces(MediaType.APPLICATION_JSON)
public MLWorkspace getWorkspaceById(@PathParam("workspaceId") String workspaceId) {

    for (MLWorkspace workspace : workspaces) {
        if (workspace.getId().equals(workspaceId)) {
            return workspace;
        }
    }

    return null;
}

@DELETE
@Path("/{workspaceId}")
public void deleteWorkspace(@PathParam("workspaceId") String workspaceId) {

    for (MLWorkspace workspace : workspaces) {
        if (workspace.getId().equals(workspaceId)) {

            if (!workspace.getModelIds().isEmpty()) {
                throw new WorkspaceNotEmptyException(
                    "Workspace cannot be deleted because it still contains models."
                );
            }

            workspaces.remove(workspace);
            return;
        }
    }
} 

}