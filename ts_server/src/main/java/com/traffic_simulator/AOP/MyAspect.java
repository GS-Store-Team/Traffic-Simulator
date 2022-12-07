package com.traffic_simulator.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Component
@Aspect
public class MyAspect {
    Logger logger = LoggerFactory.getLogger(MyAspect.class);

    @Around("Pointcuts.allControllersMethods()")
    public void mapLoggingAdvice(ProceedingJoinPoint joinPoint) throws Exception {
        Map<String, String> map = new HashMap<>();

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd hh:mm:ss");

        map.put("time", simpleDateFormat.format(new Date()));
        map.put("methodName", method.getName());
        map.put("httpMethod",method.getDeclaredAnnotations()[0].annotationType().getSimpleName().toUpperCase().replace("MAPPING", ""));
        map.put("path", DebugUtils.getRequestPath(method));
        map.put("params", DebugUtils.getParameters(method.getParameters()));

        logger.info(DebugUtils.getNote(map));
    }
}
