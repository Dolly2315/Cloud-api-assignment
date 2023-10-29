## Part I - Implementation

Java service implementation using micronaut framework to expose unique REST end-point with the signature long getUniqueTick()
Terraform is used to make code changes and push to aws

To run the code locally : 

 ``` git clone <url> ```

in root directory , build the project : 

 ```./gradlew build  ``` 

run the application and once ran, api can be accessed at :  http://localhost:5000/tick 

```./gradlew run  ``` 

to run unit tests :
```./gradlew test  ``` 

Alternatively, if project imported and opened in IDE . gradle can be opened and tasks can be executed  
Also, ConcurrencyTest is added to test on actual endpoint to print response of all the requests running in concurrent fashion 


Terraform is used to create ec2 instance in the AWS 

```     terraform init      ```

 ```    terraform apply     ``` 

For the deployment , AWS elastic bean was used . Configuration and environment is created manually by uploading the project jar and selecting ec2, subnet, vpc created by teraform 

API can be accessed : http://cloudapi-env-1.eba-dhigsunw.eu-west-2.elasticbeanstalk.com:5000/tick