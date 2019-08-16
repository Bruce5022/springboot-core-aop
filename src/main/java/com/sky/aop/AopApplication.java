package com.sky.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * AOP[动态代理]
 * 指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式;
 * 1.导入aop模块依赖包:spring-aspects
 * 2.定义一个业务逻辑类;在业务逻辑运行的时候,将日志进行打印(方法之前,方法运行结束,方法出现异常...)
 * 3.定义一个日志切面类,切面类里面的方法,需要动态感知业务类运行到哪里,然后执行;
 *      前置通知@Before:目标方法运行前
 *      后置通知@After:目标方法运行后(无论方法正常返回,还是异常都调用)
 *      返回通知@AfterReturning:目标方法正常返回后
 *      异常通知@AfterThrowing:目标方法异常返回后
 *      环绕通知@Around:动态代理,手动推进目标方法运行(joinPoint.Procced)
 * 4.给切面类的目标方法标注何时何地运行(通知注解)
 * 5.将切面类和业务逻辑类(目标方法所在的类)都加入到容器中
 * 6.必须告诉spring哪个类是切面类(加注解@Aspect)
 * 7.必须开启aop模式@EnableAspectJAutoProxy
 */
public class AopApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        MathCalculator bean = context.getBean(MathCalculator.class);
        bean.divide(1, 1);
    }
}
