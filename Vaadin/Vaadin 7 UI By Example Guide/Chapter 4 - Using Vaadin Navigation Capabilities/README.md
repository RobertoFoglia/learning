# 4. Using Vaadin Navigation Capabilities

## Introduction

In this chapter we will cover the following topics:
* Getng request informaton
* URL Path info, fragments and parameters
* VaadinService class
* Navigators and views
* Keeping state after refresh
* User session
* Menus
    * Submenu
* Shortcut keys
* Page structure with more pages and more .java
* Getting WebBrowser info
* Restart application in the UI (?restartApplication)

## Summary
This was a short but juicy chapter. Take a look at what we have learned: 

* We learned how to get path info, parameters, and fragments in the URL by using the VaadinRequest.getPathInfo, VaadinRequest.getParamenter, and Page.getUriFragment methods. 

* We learned how to get a referent to the current VaadinRequest by calling 

* VaadinService.getCurrentRequest(). 

* We learned how navigators make it easy to implement navigation capabilities through fragments by adding instances of View to a Navigator. 

* We learned how easy it is to preserve state in Vaadin applications by using the @ 

* PreserveOnRefresh annotation in our UI implementations. 

* We learned how to identify users and store related data by using VaadinSession. 

* We learned how to use menus and key shortcuts.
