# AppDynamics Deployment Status Tool

##Use Case
A simple tool to generate reports (Excel, PDF) about :
* deployment with different granularities (Application, Tier, Node, Business Transaction)
* License consumption
* Audit

##Installation
1. To build from source, clone this repository and run 'mvn clean install'. This will produce a deployment-VERSION.jar in the target directory. Alternatively, download the latest release archive from [Github](https://github.com/rhallier/appdynamics-deployment/releases).
2. java -jar deployment-VERSION.jar
3. Open a web browser : http://localhost:8080

Java system properties :

| Property                     |      Comment          | Default |
| ---------------------------- | --------------------- | ------- |
| server.port                  | Web server port       | 8080    |
| appdynamics.ignoreCookies    | Ignore cookies        | false   |
| appdynamics.ignoreSSLCheck   | Bypass SSL checks     | false   |

For instance, if you want to change the port, eg 8082, run the following command : java -Dserver.port=8082 -jar deployment-VERSION.jar 

##Contributing

Always feel free to fork and contribute any changes directly here on GitHub.
