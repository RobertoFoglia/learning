 Vaadin 7 JDK 8 (https://vaadin.com/docs/v7/framework/getting-started/getting-started-maven.html) 

mvn archetype:generate -DarchetypeGroupId=com.vaadin -DarchetypeArtifactId=vaadin-archetype-application -DarchetypeVersion=7.6.4 -DgroupId=com.howtodoinjava.vaadin.demo -DartifactId=VaadinExample -Dversion=1.0 -Dpackaging=war 

 

Then  

 

mvn install 

 

To do the hot deploy, then you must run after mvn install o clean install the below command 

 

mvn vaadin:update-widgetset install 

 

Da <https://vaadin.com/forum/thread/2541089> 