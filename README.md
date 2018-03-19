# WebAPI
To test GenRocket web API

Step 1: Download the Grails Framework

1. Download the grails-2.5.4 from http://www.grails.org/Download
2. Extract it on the appropriate location…say C:\grails
3. Create an environmental variable GRAILS_HOME which points to the path of installation of grails…i.e C:\grails
4. In the PATH environment variable.. point it to the /bin directory of grails i.e %GRAILS_HOME%\bin

In a nutshell, the environmental variables should be like..
 JAVA_HOME = C:\Program Files\Java\jdk-1.8
 GRAILS_HOME  = C:\grails
 PATH  =  %GRAILS_HOME%\bin;%JAVA_HOME%\bin;
 
Step 2: Update the Organization ID with your organization external id in AppConstant.groovy file. 
You can find your organization external ID after login to https://app.genrocket.com and going to My Organization Page.


 


