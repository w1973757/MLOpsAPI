/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mlopsapi.exceptions;

public class ModelDeprecatedException extends RuntimeException {

    public ModelDeprecatedException(String message) {
        super(message);
    }
}
