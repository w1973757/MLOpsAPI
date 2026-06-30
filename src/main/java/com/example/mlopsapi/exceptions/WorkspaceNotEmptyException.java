/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mlopsapi.exceptions;

public class WorkspaceNotEmptyException extends RuntimeException {

    public WorkspaceNotEmptyException(String message) {
        super(message);
    }
}
