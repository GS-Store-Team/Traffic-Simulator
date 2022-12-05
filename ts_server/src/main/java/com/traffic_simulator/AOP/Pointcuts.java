package com.traffic_simulator.AOP;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* com.traffic_simulator.controllers.*.*(..))")
    public void allControllersMethods(){}

}
