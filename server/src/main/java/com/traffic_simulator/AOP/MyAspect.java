package com.traffic_simulator.AOP;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class MyAspect {
    Logger logger = LoggerFactory.getLogger(MyAspect.class);
    private final HttpServletRequest request;

    public MyAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Around("execution(* com.traffic_simulator.controllers.*.*(..))")
    public Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        Long time = System.currentTimeMillis();
        Object result = null;

        try {
            result = joinPoint.proceed();
        } catch (Exception e){
            var note = new LoggerNote(
                    time,
                    System.currentTimeMillis() - time,
                    ((MethodSignature) joinPoint.getSignature()).getMethod(),
                    joinPoint.getArgs(),
                    request);

            logger.error(e.getClass().getName().toUpperCase() + " " + note);
            e.printStackTrace();
            return null;
        }

        var note = new LoggerNote(
                time,
                System.currentTimeMillis() - time,
                ((MethodSignature) joinPoint.getSignature()).getMethod(),
                joinPoint.getArgs(),
                request);

        logger.info(note.toString());

        return result;
    }
}