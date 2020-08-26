binding
==============

Template for a simple Vaadin application that only requires a Servlet 3.0 container to run.

## Contents
The project contains:

* data binding
* UI components
* Uploading files

## Summary pag ... - 62
This was a big chapter. Look at what we have covered:

* We learned how to create Vaadin applicatons that communicate with
business classes.
* We took a look at the underlying technologies that make it possible to write web
applicatons entrely in Java.
* We got to know the Vaadin data model and its core concepts: propertes, items,
and containers.
* We saw a simplifed version of the UI components hierarchy.
* We learned the common functonality for UI components and we saw that
this functonality is defned in the Component interface paired with the
AbstractComponent class.
* We have learned how to use most of the input components available in Vaadin.
So far, our applicatons have had a boring layout (VerticalLayout). In the next chapter we
will start making more appealing applicatons by learning a lot about layouts in Vaadin. See
you there.

Workflow
========

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

Debugging client side code
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"

Developing a theme using the runtime compiler
-------------------------

When developing the theme, Vaadin can be configured to compile the SASS based
theme at runtime in the server. This way you can just modify the scss files in
your IDE and reload the browser to see changes.

To use the runtime compilation, open pom.xml and comment out the compile-theme 
goal from vaadin-maven-plugin configuration. To remove a possibly existing 
pre-compiled theme, run "mvn clean package" once.

When using the runtime compiler, running the application in the "run" mode 
(rather than in "debug" mode) can speed up consecutive theme compilations
significantly.

It is highly recommended to disable runtime compilation for production WAR files.

Using Vaadin pre-releases
-------------------------

If Vaadin pre-releases are not enabled by default, use the Maven parameter
"-P vaadin-prerelease" or change the activation default value of the profile in pom.xml .
