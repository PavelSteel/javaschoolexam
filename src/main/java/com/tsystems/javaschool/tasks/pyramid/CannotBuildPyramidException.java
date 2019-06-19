package com.tsystems.javaschool.tasks.pyramid;

public class CannotBuildPyramidException extends RuntimeException {
    public CannotBuildPyramidException(){
        System.out.println("PYRAMID CANNOT BE BUILT!");
    }
}
