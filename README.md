# MLOps Pipeline Management API

**Student:** Nafisa Huda  
**Student ID:** w1973757




Project Overview - 

This project is a RESTful  API intended to mimic a Machine Learning Operation (MLOps) backend using Java and JAX-RSv (Jersey). Users of the API can control models, assessment, matrix, and machine learning workspace. Moreover, it incorporates JSON-based communication, request/response, login, and custom exception handling. As needed by the coursework, Java collection (‘ArrayList’ and ‘HashMap’) are used to store data.

Steps : 

1) Launch that beans and open the project
2) Right click the project
3) Choose “Clean and Build” 
4) Once the build has successfully finished, right click the project once more.
5) Choose Run
6) the program will immediately launch in Apache Tomcat 


Curls Commands :

Discovery Endpoint

curl http://localhost:8080/MLOpsAPI/api/v1

Create Workspace 

curl -X POST http://localhost:8080/MLOpsAPI/api/v1/workspaces \
-H "Content-Type: application/json" \
-d "{\"id\":\"WS-VISION-01\",\"teamName\":\"Computer Vision Lab\",\"storageQuotaGb\":500}"


Get all workspaces 
curl http://localhost:8080/MLOpsAPI/api/v1/workspaces

Machine Learning being Register 
curl -X POST http://localhost:8080/MLOpsAPI/api/v1/models \
-H "Content-Type: application/json" \
-d "{\"framework\":\"TensorFlow\",\"status\":\"DEPLOYED\",\"latestAccuracy\":0.92,\"workspaceId\":\"WS-VISION-01\"}"

Filter by Status
curl http://localhost:8080/MLOpsAPI/api/v1/models?status=DEPLOYED

Get Evaluation metrics 
curl http://localhost:8080/MLOpsAPI/api/v1/models/MODEL_ID/metrics

Cursework Questions 

Part 1 

1. When a JAX-RS resource creates a Java object, a MessageBodyWriter transforms it into a format like JSON for transmission to the client. This project uses Jackson to automatically serialise Java objects into JSON, enabling the API to deliver structured responses without the need for human JSON creation.

2. When an HTTP request is stateless all the data needed to process is included. Client session data is not stored on the server. As every server can handle any request without depending on prior interactions, this helps calling of REST APIs

Part 2

1. Instead of constantly requesting the same information, clients can temporarily cache response via Cache-Control headers. This saves bandwidth, lesson server workload, and enhances response time.

2. Since the HEAD function returns the response headers without the body, it should be utilised. It uses less bandwidth than GET and allows clients to determine whether a resource is present

Part 3

1. To avoid duplicate identities and guarantee data integrity, the server should produce unique IDs. Therefore every model is guaranteed to receive a secure and distinct via using UUID.randomUUID()

2. reserve letters as well as spaces cannot be properly contained in URLs. By substituting included values for these characters, URL encoding allows the server to properly understand the request.

Part 4

1.  By using @Produce at the class level, the annotation is not replicated on every method. By default, all matters return the same media type; however, if necessary, individual method can alter this.

2. When  updating latestAccuracy, clients won’t  need to retrieve the complete suspend history as the model will always display as most updated performance result.


Part 5

1) The result of an invalid workspaceId is incorrect client input hence a 4xx status code should be returned. 5xx status codes are set aside for unforeseen server malfunctions

2) The most specialised ExceptionMapper is always used by JAX-RS. When there isn’t a specified mapper for the thrown exception, the global map is used.

3) Request header, the URI, and the HTTP method are all provided by ContainerRequetContext. Debugging and logging can benefit from the response status code, hackers, and response entity provided by ContainerResponseContext 

