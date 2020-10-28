package org.acme.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/* @@@ Bean scoping */
@ApplicationScoped
@Named("default")
public class HelloWorldServiceImpl implements HelloWorldService {

    private static int instantationCounter = 0;

    public HelloWorldServiceImpl() {
        // here the system enters with the default configuration
    }

    /* @@@ @Producer - how to control the bean instantation pag 26 */
    @Produces
    @Named("helloWorldWithRequestScope")
    @RequestScoped
    public HelloWorldService helloWorldService() {
        instantationCounter++;
        return new HelloWorldServiceImpl();
    }

    @Override

    public String greeting() {
        return "hello";
    }

    @Override
    public String greeting2() {
        return "instantation counter " + instantationCounter;
    }
}
