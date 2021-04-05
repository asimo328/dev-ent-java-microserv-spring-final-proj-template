# Mobile Payment Transaction 
Mobile payment transaction is a microservice that gives payment company to track user money spending habit. Provide suggestion to meet personal goal.

## list all transaction
Returns json data about all mobile payment transaction.

URL
/listAllTrans

Method:
GET

URL Params
None

Data Params
None

Success Response:<br/>
curl http://ec2-52-53-150-120.us-west-1.compute.amazonaws.com:8080/spring-proj-template/listAllTrans<br/>
1, Bob, Doe, 209 Steven Creek Blvd, Cupertino, CA 95014, Bank Of America, 34.82, 2021-04-03 19:35:20.0<br/>
2, Don, Valin, 298 W Mckinley Ave, Sunnyvale, CA 94086, Goldman Sach, 500, 2021-04-03 19:35:20.0<br/>
3, Ken, Chang, 367 Steven Creek Blvd, Santa Clara, CA 95051, Chase, 5.87, 2021-04-03 19:35:23.0<br/>

Error Response:<br/>
Code: 404 Not Found
Content: {}

## Insert a Product
Adds a new transaction.

URL
/api/products/

Method:
POST

URL Params
None

Data Params
{\"firstName\": \"Paul\", \"lastName\": \"Wong\", \"posLocation\": \"187 De Anza Blvd., Cupertino, CA 95014\", \"issuerBank\": \"Wells Fargo\", \"price\": \"180\"}

Success Response:<br/>
curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data "{\"firstName\": \"Paul\", \"lastName\": \"Wong\", \"posLocation\": \"187 De Anza Blvd., Cupertino, CA 95014\", \"issuerBank\": \"Wells Fargo\", \"price\": \"180\"}" "http://ec2-52-53-150-120.us-west-1.compute.amazonaws.com:8080/spring-proj-template/addTran"<br/>
HTTP/1.1 200 OK<br/>
Content-Type: application/json;charset=utf-8<br/>
Content-Length: 15<br/>
Server: Jetty(9.4.27.v20200227)<br/>

Error Response:<br/>
Code: 500 Internal Server Error<br/>
Content: {}<br/>

List Transaction After Insert Example:<br/>
curl http://ec2-52-53-150-120.us-west-1.compute.amazonaws.com:8080/spring-proj-template/listAllTrans<br/>
1, Bob, Doe, 209 Steven Creek Blvd, Cupertino, CA 95014, Bank Of America, 34.82, 2021-04-03 19:35:20.0<br/>
2, Don, Valin, 298 W Mckinley Ave, Sunnyvale, CA 94086, Goldman Sach, 500, 2021-04-03 19:35:20.0<br/>
3, Ken, Chang, 367 Steven Creek Blvd, Santa Clara, CA 95051, Chase, 5.87, 2021-04-03 19:35:23.0<br/>
4, null, null, null, null, 180, 2021-04-04 06:27:23.0<br/>
5, Paul, Wong, 187 De Anza Blvd., Cupertino, CA 95014, Wells Fargo, 180, 2021-04-04 06:38:27.0. <==<br/>

## Struggle encountered and learned during project:
This happened after accidentally terminate an instance instead of stopping after AWS charges. So I have to start from scratch to build the new VM. Then I run into connectivity failed.
- keyFile.key: To generate a new keyFile using AESUtils. Make sure you remove keyFile.key to generate it again. It won't overwrite and I keep running into connectivity problem.
- application.properties: from spring.datasource.url, due to new VM created with different name, I forget to change, hence war bundle with old VM name and failed connectivity.

## Extension of the project:
I suppose to add AI capability to provide suggestion to meet personal finance goal. I also think of this platform can extend to help user to investment the extra monthly money saved to reward user.
