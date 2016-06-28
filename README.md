# AppDynamics MongoDB Monitoring Extension

##Use Case
The MongoDB custom monitor captures statistics from the MongoDB server and displays them in the AppDynamics Metric Browser.
This extension works only with the standalone machine agent.


Metrics include:

* Server up time
* Global lock time
* Operations currently queued, number waiting for the read-lock or write-lock
* Total active connections, number of read and write operations
* Memory metrics including bits, resident RAM, virtual memory, mapped memory, mapped memory with journaling
* Current and available connections
* Index counters including index access, hits and misses, resets
* Background flushing metrics such as number of times, total time, average time, last time
* Network traffic sent and received (in bytes), number of distinct requests received
* Number of database operations including: insert, query, update, delete, get more, and total number of commands
* Number of asserts since the server process started: regular, warnings, message, user, and number of times the rollover counter has rolled
* Database related stats
* Cluster related stats (status, health and uptime)

##Installation
1. To build from source, clone this repository and run 'mvn clean install'. This will produce a MongoMonitor-VERSION.zip in the target directory. Alternatively, download the latest release archive from [Github](https://github.com/Appdynamics/mongo-monitoring-extension/releases).
2. Unzip the file MongoMonitor-[version].zip into `<MACHINE_AGENT_HOME>/monitors/`.
3. In the newly created directory "MongoMonitor", edit the config.yml configuring the parameters specified in the below section.
4. Restart the machineagent
5. In the AppDynamics Metric Browser, look for: Application Infrastructure Performance  | \<Tier\> | Custom Metrics | Mongo Server.


## Configuration ##

Note : Please make sure to not use tab (\t) while editing yaml files. You may want to validate the yaml file using a [yaml validator](http://yamllint.com/)

1. Configure the Mongo instances by editing the config.yml file in `<MACHINE_AGENT_HOME>/monitors/MongoMonitor/`.

   For eg.
   ```
        # MongoDB host and port. If ReplicaSet is enabled, configure all OR subset of members of the cluster.
        servers:
          - host: "localhost"
            port: 27017
          - host: "localhost"
            port: 27018

        # Specify this key if Password Encryption Support is required else keep it empty
        # If specified, adminDBPassword and databases passwords are now the encrypted passwords.
        passwordEncryptionKey: ""

        # Admin DB username and password. Required if mongod is started with --auth (Authentication) else keep empty
        # The user should have clusterMonitor role as a minimum
        adminDBUsername: "admin"
        adminDBPassword: "admin"

        # Change ssl to true if mongod is started with ssl. Then specify the pemFilePath, if not keep empty.
        ssl: false
        pemFilePath: ""

        #prefix used to show up metrics in AppDynamics
        metricPathPrefix:  "Custom Metrics|Mongo Server|"

   ```
   
2. Configure the path to the config.yml file by editing the <task-arguments> in the monitor.xml file in the `<MACHINE_AGENT_HOME>/monitors/MongoMonitor/` directory. Below is the sample

     ```
     <task-arguments>
         <!-- config file-->
         <argument name="config-file" is-required="true" default-value="monitors/MongoMonitor/config.yml" />
          ....
     </task-arguments>
    ```

Note : By default, a Machine agent or a AppServer agent can send a fixed number of metrics to the controller. To change this limit, please follow the instructions mentioned [here](http://docs.appdynamics.com/display/PRO14S/Metrics+Limits).
For eg.  
```    
    java -Dappdynamics.agent.maxMetrics=2500 -jar machineagent.jar
```

##Password Encryption Support
To avoid setting the clear text password in the config.yml, please follow the process below to encrypt the password

1. Download the util jar to encrypt the password from [https://github.com/Appdynamics/maven-repo/blob/master/releases/com/appdynamics/appd-exts-commons/1.1.2/appd-exts-commons-1.1.2.jar](https://github.com/Appdynamics/maven-repo/blob/master/releases/com/appdynamics/appd-exts-commons/1.1.2/appd-exts-commons-1.1.2.jar) and navigate to the downloaded directory
2. Encrypt password from the commandline
`java -cp appd-exts-commons-1.1.2.jar com.appdynamics.extensions.crypto.Encryptor myKey myPassword`
3. Specify the passwordEncryptionKey and encrypted adminDBPassword in config.yml


##Metrics

###Server Stats

<table><tbody>
<tr>
<th align="left"> Metric Name </th>
<th align="left"> Description </th>
</tr>
<tr>
<td class='confluenceTd'> Up Time (ms) </td>
<td class='confluenceTd'> The duration of time that the server is up </td>
</tr>
</tbody>
</table>

#####Metric Category: Asserts

<table><tbody>
<tr>
<th align="left"> Metric Name </th>
<th align="left"> Description </th>
</tr>
<tr>
<td class='confluenceTd'> Message </td>
<td class='confluenceTd'>  </td>
</tr>
<tr>
<td class='confluenceTd'> Regular </td>
<td class='confluenceTd'>  </td>
</tr>
<tr>
<td class='confluenceTd'> Rollover </td>
<td class='confluenceTd'>  </td>
</tr>
<tr>
<td class='confluenceTd'> User </td>
<td class='confluenceTd'>  </td>
</tr>
<tr>
<td class='confluenceTd'> Warning </td>
<td class='confluenceTd'>  </td>
</tr>
</tbody>
</table>

#####Metric Category: Background Flushing 

<table><tbody>
<tr>
<th align="left"> Metric Name </th>
<th align="left"> Description </th>
</tr>
<tr>
<td class='confluenceTd'> Flushes </td>
<td class='confluenceTd'> Number of times the database has been flushed </td>
</tr>
<tr>
<td class='confluenceTd'> Total (ms) </td>
<td class='confluenceTd'> Total time (ms) that the mongod process spent writing data to disk </td>
</tr>
<tr>
<td class='confluenceTd'> Average (ms) </td>
<td class='confluenceTd'> Average time (ms) that the mongod process spent writing data to disk </td>
</tr>
<tr>
<td class='confluenceTd'> Last (ms) </td>
<td class='confluenceTd'> Time (ms) that the mongod process last spent writing data to disk </td>
</tr>
</tbody>
</table>

#####Metric Category: Connections

<table><tbody>
<tr>
<th align="left"> Metric Name </th>
<th align="left"> Description </th>
</tr>
<tr>
<td class='confluenceTd'> Current </td>
<td class='confluenceTd'> Number of current connections to the database server from clients.
This number includes the current shell connection as well as any inter-node connections to support
a replica set or sharded cluster.</td>
</tr>
<tr>
<td class='confluenceTd'> Available </td>
<td class='confluenceTd'> Number of unused available connections that the database can provide.
Consider this value in combination with the value of Current to understand the connection load on the database.</td>
</tr>
</tbody>
</table>


#####Metric Category: Global Lock
 
 <table>
 <tbody>
 <tr>
 <th align="left"> Metric Name </th>
 <th align="left"> Description </th>
 </tr>
 <tr>
  <td align="left"> Total Time </td>
  <td align="left"> The total time since the globalLock was started and created </td>
  </tr>
 </tbody>
 </table>
 
######Active Clients
 <table><tbody>
 <tr>
 <th align="left"> Metric Name </th>
 <th align="left"> Description </th>
 </tr>
 <tr>
 <td class='confluenceTd'> Total </td>
 <td class='confluenceTd'> Number of active client connections to the database </td>
 </tr>
 <tr>
 <td class='confluenceTd'> Readers </td>
 <td class='confluenceTd'> Number of readers performing read operations </td>
 </tr>
 <tr>
 <td class='confluenceTd'> Writers </td>
 <td class='confluenceTd'> Number of writers performing write operations </td>
 </tr>
 </tbody>
 </table>

######Current Queue

<table><tbody>
<tr>
<th align="left"> Metric Name </th>
<th align="left"> Description </th>
</tr>
<tr>
<td class='confluenceTd'> Total </td>
<td class='confluenceTd'> Number of operations queued before a lock </td>
</tr>
<tr>
<td class='confluenceTd'> Readers </td>
<td class='confluenceTd'> Number of operations waiting for the read-lock </td>
</tr>
<tr>
<td class='confluenceTd'> Writers </td>
<td class='confluenceTd'> Number of operations waiting for the write-lock </td>
</tr>
</tbody>
</table>

#####Metric Category: Index Counter
 
 <table>
 <tbody>
 <tr>
 <th align="left"> Metric Name </th>
 <th align="left"> Description </th>
 </tr>
 <tr>
  <td align="left"> Accesses </td>
  <td align="left"> </td>
  </tr>
 <tr>
  <td align="left"> Hits </td>
  <td align="left"> </td>
  </tr>
 <tr>
  <td align="left"> Misses </td>
  <td align="left"> </td>
  </tr>
<tr>
  <td align="left"> Resets </td>
  <td align="left"> </td>
  </tr>
 </tbody>
 </table>

#####Metric Category: Memory

<table><tbody>
<tr>
<th align="left"> Metric Name </th>
<th align="left"> Description </th>
</tr>
<tr>
<td class='confluenceTd'> Bits </td>
<td class='confluenceTd'> Target Architecture </td>
</tr>
<tr>
<td class='confluenceTd'> Resident </td>
<td class='confluenceTd'> Amount of RAM (MB) currently used by the database process </td>
</tr>
<tr>
<td class='confluenceTd'> Virtual </td>
<td class='confluenceTd'> MB currently used by the mongod process </td>
</tr>
<tr>
<td class='confluenceTd'> Mapped </td>
<td class='confluenceTd'> Amount of mapped memory (MB) used by the database </td>
</tr>
<tr>
<td class='confluenceTd'> Mapped With Journal </td>
<td class='confluenceTd'> Amount of mapped memory (MB), including memory used for journaling. </td>
</tr>
</tbody>
</table>


#####Metric Category: Network
<table><tbody>
<tr>
<th align="left"> Metric Name </th>
<th align="left"> Description </th>
</tr>
<tr>
<td class='confluenceTd'> Bytes In </td>
<td class='confluenceTd'> The amount of network traffic (bytes) received by the database </td>
</tr>
<tr>
<td class='confluenceTd'> Bytes Out </td>
<td class='confluenceTd'> The amount of network traffic (bytes) sent from the database </td>
</tr>
<tr>
<td class='confluenceTd'> Number Requests </td>
<td class='confluenceTd'> Number of distinct requests that the server has received </td>
</tr>
</tbody>
</table>

#####Metric Category: Operations
 <table><tbody>
 <tr>
 <th align="left"> Metric Name </th>
 <th align="left"> Description </th>
 </tr>
 <tr>
 <td class='confluenceTd'> Insert </td>
 <td class='confluenceTd'> Number of insert operations </td>
 </tr>
 <tr>
 <td class='confluenceTd'> Query </td>
 <td class='confluenceTd'> Number of query operations </td>
 </tr>
 <tr>
 <td class='confluenceTd'> Update </td>
 <td class='confluenceTd'> Number of update operations </td>
 </tr>
 <tr>
 <td class='confluenceTd'> Delete </td>
 <td class='confluenceTd'> Number of delete operations </td>
 </tr>
 <tr>
 <td class='confluenceTd'> GetMore </td>
 <td class='confluenceTd'> Number of getmore operations </td>
 </tr>
 <tr>
 <td class='confluenceTd'> Command </td>
 <td class='confluenceTd'> Total number of commands issued to database </td>
 </tr>
 </tbody>
 </table>

###Replica Stats
For each replica the following metrics are reported.
<table><tbody>
<tr>
<th align="left"> Metric Name </th>
<th align="left"> Description </th>
</tr>
<tr>
<td class='confluenceTd'> Up Time (ms) </td>
<td class='confluenceTd'> The duration of time that the server is up </td>
</tr>
<tr>
<td class='confluenceTd'> Health </td>
<td class='confluenceTd'> Conveys if the member is up (i.e. 1) or down (i.e. 0.) </td>
</tr>
<tr>
<td class='confluenceTd'> [State](http://docs.mongodb.org/manual/reference/replica-states/) </td>
<td class='confluenceTd'> Value between 0 and 10 that represents the replica state of the member. </td>
</tr>
</tbody>
</table>

###DB Stats
#####<DB Name>

<table><tbody>
 <tr>
 <th align="left"> Metric Name </th>
 <th align="left"> Description </th>
 </tr>
 <tr>
 <td class='confluenceTd'> avgObjSize </td>
 <td class='confluenceTd'> The average size of each document in bytes. This is the dataSize divided by the number of documents </td>
 </tr>
 <tr>
 <td class='confluenceTd'> collections </td>
 <td class='confluenceTd'> Contains a count of the number of collections in that database </td>
 </tr>
 <tr>
 <td class='confluenceTd'> dataSize </td>
 <td class='confluenceTd'> The total size in bytes of the data held in this database including the padding factor </td>
 </tr>
 <tr>
 <tr>
 <td class='confluenceTd'> fileSize </td>
 <td class='confluenceTd'> The total size in bytes of the data files that hold the database </td>
 </tr>
 <tr>
 <td class='confluenceTd'> indexes </td>
 <td class='confluenceTd'> Contains a count of the total number of indexes across all collections in the database </td>
 </tr>
 <tr>
 <td class='confluenceTd'> indexSize </td>
 <td class='confluenceTd'> The total size in bytes of all indexes created on this database </td>
 </tr>
 <tr>
 <td class='confluenceTd'> nsSizeMB </td>
 <td class='confluenceTd'> The total size of the namespace files (i.e. that end with .ns) for this database </td>
 </tr>
 <tr>
 <td class='confluenceTd'> numExtents </td>
 <td class='confluenceTd'> Contains a count of the number of extents in the database across all collections </td>
 </tr>
 <tr>
 <td class='confluenceTd'> objects </td>
 <td class='confluenceTd'> Contains a count of the number of objects (i.e. documents) in the database across all collections </td>
 </tr>
 <tr>
 <td class='confluenceTd'> storageSize </td>
 <td class='confluenceTd'> The total amount of space in bytes allocated to collections in this database for document storage </td>
 </tr>
 </tbody>
 </table>

#####Metric Category: Collection Stats
#####<collection name>

<table><tbody>
 <tr>
 <th align="left"> Metric Name </th>
 <th align="left"> Description </th>
 </tr>
 <tr>
 <td class='confluenceTd'> count </td>
 <td class='confluenceTd'> The number of objects or documents in this collection </td>
 </tr>
 <tr>
 <td class='confluenceTd'> lastExtentSize </td>
 <td class='confluenceTd'> The size of the last extent allocated </td>
 </tr>
 <tr>
 <td class='confluenceTd'> nindexes </td>
 <td class='confluenceTd'> The number of indexes on the collection </td>
 </tr>
 <tr>
 <tr>
 <td class='confluenceTd'> numExtents </td>
 <td class='confluenceTd'> The total number of contiguously allocated data file regions </td>
 </tr>
 <tr>
 <td class='confluenceTd'> paddingFactor </td>
 <td class='confluenceTd'> The amount of space added to the end of each document at insert time </td>
 </tr>
 <tr>
 <td class='confluenceTd'> size </td>
 <td class='confluenceTd'> The size of the data stored in this collection </td>
 </tr>
 <tr>
 <td class='confluenceTd'> storageSize </td>
 <td class='confluenceTd'> The total amount of storage allocated to this collection for document storage </td>
 </tr>
 <tr>
 <td class='confluenceTd'> systemFlags </td>
 <td class='confluenceTd'> Reports the flags on this collection that reflect internal server options </td>
 </tr>
 <tr>
 <td class='confluenceTd'> totalIndexSize </td>
 <td class='confluenceTd'> The total size of all indexes. The scale argument affects this value </td>
 </tr>
 <tr>
 <td class='confluenceTd'> userFlags </td>
 <td class='confluenceTd'> Reports the flags on this collection set by the user </td>
 </tr>
 </tbody>
 </table>



##Contributing

Always feel free to fork and contribute any changes directly here on GitHub.

##Community

Find out more in the [AppSphere](http://appsphere.appdynamics.com/t5/Extensions/MongoDB-Monitoring-Extension/idi-p/831) community.

##Support

For any questions or feature request, please contact [AppDynamics Support](mailto:help@appdynamics.com).
