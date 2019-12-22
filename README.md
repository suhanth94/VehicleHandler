# Vehicle Handler

### Running Instructions

Steps to run the application from IDE:

1. Import project into any IDE like Intellij using pom.xml
2. Run App from Intellij Configuration straight away
3. (Or) Go to HeyCarApplication File -> Right Click -> Run

From command line:

1. Build the project using pom.xml - > mvn clean install
2. Run the application -> java -jar target/assignment-SNAPSHOT.jar

For docker image build (from command line) & Kubernetes Deploy (if needed):

1. docker build -t name:tag \<path to directory which has both binary and image file\>
2. Provide image registry URL under image placholder in deployment YAML
3. kubectl apply -f deployment.yml
  
### Testing Instructions

Sample Input (JSON)

```json
[
    {
        "code": "a",
        "make": "renault",
        "model": "megane",
        "kW": 132,
        "year": 2014,
        "color": "red",
        "price": 13990
    },
    {
        "code": "b",
        "make": "swiss",
        "model": "bmw",
        "kW": 256,
        "year": 2018,
        "color": "green",
        "price": 15000
    },
    {
        "code": "c",
        "make": "audi",
        "model": "a4",
        "kW": 144,
        "year": 2018,
        "color": "black",
        "price": 20000
    }
]
```

Sample Input (CSV)

```
code,make/model,power-in-ps,year,color,price
1,mercedes/a 180,123,2014,black,15950
2,audi/a3,111,2016,white,17210
3,vw/golf,86,2018,green,14980
4,skoda/octavia,86,2014,black,16990
```

API Endpoints:

| Command | HTTP Method | Description | Examples
| --- | --- | --- | ------ |
| `/vehicle-listings/dealer_id` | POST | Adds all vehicle listings (JSON Array) of a given dealer  | `/vehicle-listings/1`
| `/upload-csv/dealer_id` | POST | Adds all vehicle listings (CSV) of a given dealer  | `/upload-csv/2`
| `/vehicle-listings` | GET | Fetches all the available vehicle listings (Takes in a query param dealer_id optionally | `/vehicle-listings`<br>`/vehicle-listings/1`
| `/search` | GET | Search and returns vehicle listings based on the criteria (Takes in query params for search criteria - make/model/year/color) | `search?make=audi`<br>`search?year=2014&color=black`

Automated tests are checked in as part of POSTMAN API collection (in JSON format). Import the test suite and can be run via Postman Client.

### Implementation Details

* The application is designed using Spring Boot using layers (Model/Controller/Service).
* REST API operations are defined in controller which communciates with the service layer and sends back the data/response back to the controller.
* For key uniqueness, a combination of dealer ID and vehicle listing code is being used.
* For listing updates, hashmap directly repalces the specific entry in the map.
* As of now, in-memory data structure(hashmap) is being used, but in real-time, a database would be ideal for persistent storage.
* For CSV inputs, data is accepted with text/csv as the content type and then coverted to POJO bean by CSV Utils. The input is currently being read as string, but can be also be read via file/input stream.
* Get API can return all the listings and also return specific listings if dealer ID is provided.
* Search API returns matching vehicle listings based on the criteria parameters being passed (If no parameters are passed, it returns empty set as of now).

### Improvements

* If I had more time, I would handle search operations in a more generic fashion by optimizing and having the search criteria more flexible.
* Error handling and validation constraints/checks.
* Added swagger documentation instead of the API table above.
* Added unit/integration tests

