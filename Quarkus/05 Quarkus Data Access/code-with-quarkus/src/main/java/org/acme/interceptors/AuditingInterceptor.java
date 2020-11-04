package org.acme.interceptors;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * @@@ Aspect oriented programming
 */
@Audited
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@Slf4j
public class AuditingInterceptor {
    @AroundInvoke
    public Object logExecutions(InvocationContext invocationContext) throws Exception {
        log.info("Method: " + invocationContext.getMethod().getName());
        log.info("Arguments: " + invocationContext.getParameters());
        log.info("Executing the called method");
        Object possibleReturn = invocationContext.proceed();
        log.info("The object the method was invoked on: " + invocationContext.getTarget());
        return possibleReturn;
    }

}
