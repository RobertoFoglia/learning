package org.acme.services;

import javax.enterprise.context.ApplicationScoped;

/**
 * @@@ Bean scoping
 */
//@ApplicationScoped // - the wrong using
public interface HelloWorldService {
    //    default String greeting() { return "hello"; } // - the wrong using
    String greeting();

    String greetingWithAnException();
}
