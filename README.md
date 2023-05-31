# RegistrationService
## Overview
RegistrationService is a Spring Boot-based RESTful API that manages a simple registration system. It allows users to save and retrieve Person details. It supports features like getting the oldest child of a person and handling persons with optional spouse names.

The key features of RegistrationService include:
* Saving a Person object in the system.
* Retrieving a Person object by their Social Security Number (SSN).
* Getting the oldest child of a Person based on their SSN.

## Usage
The RegistrationService exposes several REST endpoints:

* POST /persons: This endpoint saves a new Person object in the system.
* * Body: { "ssn": "", "name": "", "spouseName": "", "children": {} }
* GET /persons/{ssn}: This endpoint retrieves a Person object by their SSN.
* GET /persons/{ssn}/oldest-child: This endpoint gets the name of the oldest child of a Person given their SSN.

### Reference Documentation (WIKI)
## Advice for team members:
* Always validate the input data in your endpoints. Never trust client-side validation.
* Be consistent with the endpoint naming.
* Use the right HTTP status codes to give meaningful feedback.
* Keep security in mind, never expose sensitive information like SSN in URLs, better to use POST methods where possible.
