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

1. docker build -t name:tag <path to directory which has both binary and image file>
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



