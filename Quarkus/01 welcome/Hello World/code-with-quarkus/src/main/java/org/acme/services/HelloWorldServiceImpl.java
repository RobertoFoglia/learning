package org.acme.services;

import io.quarkus.arc.DefaultBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/* @@@ Bean scoping */
@ApplicationScoped
@Named("default")
@DefaultBean  // it doesn't work
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String greeting() {
        return "hello";
    }
}
