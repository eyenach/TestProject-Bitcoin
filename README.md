### TestProject-Bitcoin

> clone project
* git clone https://github.com/eyenach/TestProject-Bitcoin.git

> properties
* java version 11
* spring framework version 2.5.9

> API
* Save Record `POST/ localhost:{port}/bitcoin`
  * input example
  ```
  {
    "datetime": "2019-10-05T13:58:00+01:00",
    "amount": 3
  }
  ```
  * output example
  ```
  {
    "status": 201,
    "result": "Created.",
    "data": []
  }
  ```
* Get History `GET/ localhost:{port}/history`
  * input example
  ```
  {
    "startDatetime": "2019-10-05T12:48:00+01:00",
    "endDatetime": "2019-10-05T13:48:00+01:00"
  }
  ```
  * output example
  ```
  {
    "status": 200,
    "result": "Success",
    "data": [
        {
            "datetime": "2019-10-05T12:00:00+01:00",
            "amount": 1000.0
        },
        {
            "datetime": "2019-10-05T13:00:00+01:00",
            "amount": 1003.0
        }
     ]
  }
  ```
* Get All Record `GET/ localhost:{port}/`
  * output example
  ```
  {
    "status": 200,
    "result": "Success",
    "data": [
        {
            "datetime": "2019-10-05T19:58:00+07:00",
            "amount": 3.0
        }
     ]
  }
  ```
  collect `datetime` in local timezone.
