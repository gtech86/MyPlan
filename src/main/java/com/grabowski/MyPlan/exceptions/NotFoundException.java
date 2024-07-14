package com.grabowski.MyPlan.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException
{
    public NotFoundException(String msg){
        super(msg);
    }
}