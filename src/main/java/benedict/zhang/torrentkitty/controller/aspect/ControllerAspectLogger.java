package benedict.zhang.torrentkitty.controller.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(value = Integer.MAX_VALUE)
public class ControllerAspectLogger {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAspectLogger.class);

    @Pointcut("execution(* onAction(..))")
    private void controllerRequested(){

    }

    public void before(JoinPoint joinPoint){

    }

    public void after(JoinPoint joinPoint){

    }

    public void exception(JoinPoint joinPoint, Exception exception){

    }
}
