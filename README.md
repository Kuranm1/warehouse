# Introduction

This is required project for Interview by Mohammad Alquraan.

## Installation

You should have Docker installed and run this command

```
docker-compose up --build
```

The project will run on port 8081.

## FX deal Request
You are required to provide FX deal request with the following structure and fields format.
```json
{
    "dealId" : "1",
    "fromCurrency": "EUR",
    "toCurrency": "USD",
    "dealTimestamp": "2024-03-01T12:30:45",
    "dealAmount": 76
}
```

## FX deal Response

The response has additional field: ``Status``

It will have ``Success`` or ``Failure Reason`` values on Create and Update methods

and ``null`` for reading methods.

```json
{
    "dealId" : "1",
    "fromCurrency": "EUR",
    "toCurrency": "USD",
    "dealTimestamp": "2024-03-01T12:30:45",
    "dealAmount": 76,
    "status": "Success"
}
```

## API's Documentation

Get FX deal by using deal id.

HTTP method: [GET]
```
http://localhost:8081/fxdeals/1
```

Get all FX deals.

HTTP method: [GET]
```
http://localhost:8081/fxdeals
```

Create new FX deal by providing FX deal as JSON in the request body.

Note: Deal id should be unique.

HTTP method: [POST]
```
http://localhost:8081/fxdeals
```

UpdateFX deal by providing FX deal as JSON in the request body.

Note: Deal id should be exist.

HTTP method: [PUT]
```
http://localhost:8081/fxdeals
```
## Validations

**Deal id**: unique id

**Currency**: Based on defined ISO Codes in the code

**Timestamp**: follow the format (yyyy-MM-dd'T'HH:mm:ss)

**Deal ammount**: Double positive value