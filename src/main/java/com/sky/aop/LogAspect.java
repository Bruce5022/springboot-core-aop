package com.sky.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LogAspect {
    //切入点表达式
    @Pointcut("execution(public * com.sky.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("before==========start=========before");
        System.out.println("before==========" + joinPoint.getSignature().getName() + "运行...");
        System.out.println("before==========" + joinPoint.getSignature().getName() + "参数：{" + JSONObject.toJSONString(joinPoint.getArgs()) + "}");
    }

    @After("pointCut()")
    public void logEnd() {
        System.out.println("after==========end=========after");
    }

    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("returning==========end=========returning");
        System.out.println("returning==========" + joinPoint.getSignature().getName() + "结果：{" + JSONObject.toJSONString(result) + "}");
    }

    // JoinPoint一定在第一个位置,不然抛异常
    @AfterThrowing(pointcut = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println("throwing==========" + joinPoint.getSignature().getName() + "异常：{" + exception.getMessage() + "}");
        System.out.println("throwing==========end=========throwing");
    }

    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        System.out.println("around==========around1==========around");
        Object data = point.proceed();
        System.out.println("around==========" + data + "==========around");
        System.out.println("around==========around2==========around");
        return data;
    }
}
