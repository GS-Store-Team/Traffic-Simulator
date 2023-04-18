package com.traffic_simulator.AOP;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class LoggerNote {
    static private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
    private Long time;
    private Long elapsedTime;
    private Method method;
    private Object[] args;
    private HttpServletRequest request;

    LoggerNote(Long time, Long elapsedTime, Method method, Object[] args, HttpServletRequest request){
        this.time = time;
        this.elapsedTime = elapsedTime;
        this.method = method;
        this.args = args;
        this.request = request;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("| Elapsed in ").append(elapsedTime / 1000).append("s ").append(elapsedTime % 1000).append("ms | ")
                .append(request.getMethod().toUpperCase()).append(" ").append(request.getRequestURI()).append(" ")
                .append(method.getName()).append(" ")
                .append("[At: ").append(simpleDateFormat.format(time)).append("] ");

        Arrays.asList(args).forEach(arg -> stringBuilder.append(arg.toString()).append(" "));

        return stringBuilder.toString();
    }
}
