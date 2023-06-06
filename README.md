# My App

## Description
This is a sample application that provides information about a student. It exposes an API endpoint that returns student details when requested.

## Requirements
To run this application, you need to have Docker and Docker Compose installed on your machine.

## Installation and Usage
1. Clone the repository to your local machine.
2. Navigate to the project directory.

### Running the Application and Making a Request
To run the application, execute the following command in your terminal:

```shell
docker-compose up -d
```

This command will start the application in detached mode, allowing it to run in the background.

To test the application, send a GET request to http://localhost:9090/student/960054419. You can use tools like cURL or Postman to send the request. The expected response should be:

```json
{
    "studentNumber": "960054419",
    "firstName": "Joe",
    "lastName": "Smith",
    "phoneNumber": "8976543324",
    "grades": [
        {
            "subject": "math",
            "grade": "90"
        },
        {
            "subject": "english",
            "grade": "80"
        }
    ],
    "address": {
        "streetNumber": "8200",
        "streetName": "Dixie Road",
        "city": "Brampton",
        "province": "ON"
    }
}

```

### Configuration
The application uses default configurations, but you can modify them by editing the docker-compose.yml file.

### Troubleshooting
If you encounter any issues or errors while running the application, please make sure that you have followed the installation steps correctly and that all dependencies are properly installed. If you need further assistance, feel free to contact the developer at <a href="mailto:krasnenkov.evgen@gmail.com">Send Email</a>.

### MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
