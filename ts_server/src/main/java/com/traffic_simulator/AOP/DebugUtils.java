package com.traffic_simulator.AOP;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Map;

public class DebugUtils {
    public static String getParameters(Parameter[] parameters){
        StringBuilder stringBuilder = new StringBuilder("Params: ");

        for(int i = 0; i<parameters.length;i++) {
            Arrays.stream(parameters[i].getAnnotations()).forEach(annotation ->
                    stringBuilder
                            .append("@")
                            .append(annotation.annotationType().getSimpleName())
                            .append(" "));

            stringBuilder
                    .append(parameters[i].getType().getSimpleName())
                    .append(", ");
        }

        return parameters.length>0 ?
                stringBuilder.substring(0,stringBuilder.length()-2):
                stringBuilder.append("none").toString();
    }

    public static String getRequestPath(Method method) throws Exception {
        Annotation annotation = method.getDeclaredAnnotations()[0];
        String[] value = (String[])annotation.annotationType().getMethod("value").invoke(annotation);

        String res = value.length == 1? value[0]: "";

        try {
            annotation = method.getDeclaringClass().getAnnotation(RequestMapping.class);
            value = (String[]) annotation.annotationType().getMethod("value").invoke(annotation);
            res = value[0] + res;
        } catch (Exception ignore){}

        return res;
    }

    public static String getNote(Map<String, String> map){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("|")
                .append(map.get("time"))
                .append("|    ")
                .append(map.get("httpMethod"))
                .append("    \"")
                .append(map.get("path"))
                .append("\"    ")
                .append(map.get("methodName"))
                .append(" [")
                .append(map.get("params"))
                .append("]");

        return stringBuilder.toString();
    }
}
