package com.learning.aop.aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;


@Aspect
@Component
public class AopExecution {

    //PointCut Declarations
    @Pointcut("execution(* com.learning.aop.dao.*.*(..))")
    public void executeAccountDAO(){}

    @Before("executeAccountDAO()")
    public void addAccount(){
        System.out.println("Point cut matching any method");
    }
}
