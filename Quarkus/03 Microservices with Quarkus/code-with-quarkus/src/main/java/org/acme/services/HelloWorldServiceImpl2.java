package org.acme.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/* @@@ Bean scoping */
@ApplicationScoped
@Named("HelloWorldServiceImpl2")
public class HelloWorldServiceImpl2 implements HelloWorldService {

    private static int instantationCounter = 0;

    public HelloWorldServiceImpl2() {
        // here the system enters with the default configuration
    }

    @Produces
    @Named("helloWorldWithRequestScope")
    @RequestScoped
    public HelloWorldService helloWorldService() {
        instantationCounter++;
        return new HelloWorldServiceImpl2();
    }

    @Override
    public String greeting() {
        return "instantation counter " + instantationCounter;
    }

    /* @@@ JAX-RS Exception handling */
    @Override
    public String greetingWithAnException() {
        throw new IllegalArgumentException("test of the exception hanlder");
    }
}
