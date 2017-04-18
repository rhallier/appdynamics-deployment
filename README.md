# AppDynamics Deployment Status Tool

##Use Case
A simple tool to generate reports (Excel, PDF) about :
* deployment and with different granularities (Application, Tier, Node, Business Transaction)
* License consumption
* Audit

##Installation
1. To build from source, clone this repository and run 'mvn clean install'. This will produce a deployment-VERSION.jar in the target directory. Alternatively, download the latest release archive from [Github](https://github.com/Appdynamics/appdynamics-deployment/releases).
2. java -jar deployment-VERSION.jar
3. Open a web browser : http://localhost:8080

If you want to change the port, eg 8082, run the following command : java -Dserver.port=8082 -jar deployment-VERSION.jar 

##Contributing

Always feel free to fork and contribute any changes directly here on GitHub.
