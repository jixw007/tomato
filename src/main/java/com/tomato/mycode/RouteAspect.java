package com.tomato.mycode;

import com.tomato.mycode.util.dataRoute.RouteHolder;
import com.tomato.mycode.util.dataRoute.RouteSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class RouteAspect {
    public RouteAspect() {
        System.out.println("SimpleAspect initing ,thread_id=" + Thread.currentThread().getId());
    }

    /**
     * 切点表达式:
     * ..两个点表明多个，*代表一个
     * 表达式代表切入com..service包下的所有类的所有方法，方法参数不限，返回类型不限。
     * 其中访问修饰符可以不写，不能用*，，第一个*代表返回类型不限，第二个*表示所有类，第三个*表示所有方法，..两个点表示方法里的参数不限。
     */
    private final String POINT_CUT = "execution(public * com.tomato.mycode.service.AppleServiceImpl.getApple2ById(Long))";

    /**
     * 命名切点
     * public 切点可访问性修饰符
     * 与类可访问性修饰符的功能是相同的，它可以决定定义的切点可以在哪些类中可使用。
     * pointCut 切点名称
     * void   返回类型
     * <p>
     * 因为命名切点仅利用方法名及访问修饰符的信息，
     * 一般定义方法的返回类型为 void ，并且方法体为空
     */
    @Pointcut(POINT_CUT)
    public void pointCut() {
    }


    /**
     * 在切点方法之前执行
     *
     * @param joinPoint
     */
    @Before(value = "pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("@Before：切点方法之前执行.....,thread_id=" + Thread.currentThread().getId());
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature())
                .getMethod().getParameterTypes();
        try {
            if (classz != null && classz.length > 0) {
                Method m = classz[0].getMethod(method, parameterTypes);
                System.out.println("RouteSourceAspect:Before:getMethod= " + m);
                System.out.println("RouteSourceAspect:Before:isAnnotationPresent= " + m.isAnnotationPresent(RouteSource.class));
                if (m != null && m.isAnnotationPresent(RouteSource.class)) {
                    RouteSource data = m.getAnnotation(RouteSource.class);
                    System.out.println("RouteSourceAspect:Before:current data source is " + data.value());
                    RouteHolder.setRouteKey(data.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 在切点方法之后执行
     *
     * @param joinPoint
     */
    @After(value = "pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("@After：切点方法之后执行.....");
    }


    /**
     * 切点方法返回后执行
     * 如果第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法参数类型匹配时才能执行后置返回通知，否则不执行，
     * 参数为Object类型将匹配任何目标返回值
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void doAfter(JoinPoint joinPoint, Object result) {
        System.out.println("@AfterReturning：切点方法返回后执行.....");
        System.out.println("返回值：" + result);
    }


    /**
     * 切点方法抛异常执行
     * 定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     * throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     * 对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        System.out.println("@afterThrowing：切点方法抛异常执行.....");
    }


    /**
     * 属于环绕增强，能控制切点执行前，执行后，，用这个注解后，程序抛异常，会影响@AfterThrowing这个注解
     * <p>
     * org.aspectj.lang.JoinPoint 接口表示目标类连接点对象，它定义这些主要方法。
     * Object[] getArgs()：获取连接点方法运行时的入参列表。
     * Signature getSignature()：获取连接点的方法签名对象。
     * Object getTarget()：获取连接点所在的目标对象。
     * Object getThis()：获取代理对象。
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around：切点方法环绕start.....");
        Object[] args = pjp.getArgs();
        Object o = pjp.proceed(args);
        System.out.println("@Around：切点方法环绕end.....");
        return o;
    }
}
