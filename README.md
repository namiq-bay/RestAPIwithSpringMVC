# Rest API with SpringMVC

## Using technologies

- Spring Boot & MVC
- H2 Embedded DB
- JPA/Hibernate
- Maven



### REST API User Endpoints 
| HTTP Method | Request URI | Request Body | Response Body | HTTP Status |
| ------ | ------ | ------ | ------ | ------ |
| GET | /users | - | List < User > | 200/500 | 
| GET | /user?ln=surname | - | List < User > | 200/500 | 
| GET | /user/{id} | - | User | 200/404/500 |
| POST | /user | User | URI | 201/500 | 
| PUT | /user/{id} | User | - | 200/404/500 |
| DELETE | /user/{id} | - | - | 200/404/500 |

## Examples
> This Get request returns all users
```sh
GET http://localhost:8080/rest/users
```
> Here’s the JSON response
```sh
[
    {
        "id": 1,
        "firstName": "Namiq",
        "lastName": "Bayramov",
        "age": 25
    },
    {
        "id": 2,
        "firstName": "Azər",
        "lastName": "Ağayev",
        "age": 21
    },
    {
        "id": 3,
        "firstName": "Ali",
        "lastName": "Aliyev",
        "age": 35
    },
    {
        "id": 4,
        "firstName": "Güney",
        "lastName": "Çakır",
        "age": 22
    }
]

```
___

> This Get request returns the user that matches the id path variable
```sh
GET http://localhost:8080/rest/user/1
```
> Here’s the JSON response
```sh
{
    "id": 1,
    "firstName": "Namiq",
    "lastName": "Bayramov",
    "age": 25
}

```
---
> This Get request returns the users that matches the ln request parameter
```sh
GET http://localhost:8080/rest/user?ln=Aliyev
```

> Here’s the JSON response
```sh
[
    {
        "id": 3,
        "firstName": "Ali",
        "lastName": "Aliyev",
        "age": 35
    }
]

```
---
> This Post request creates a new User object that corresponds to the User context sent in the request body

```sh
POST http://localhost:8080/rest/user

```
> Here’s the JSON request body
```sh
{
    "id": 7,
    "firstName": "NewUser",
    "lastName": "Test",
    "age": 22
}
```

> Here’s the response header
```sh
location     http://localhost:8080/rest/user/7
```
---
> User content sent by this Put request updates the User object that matches the id value in the Database.

```sh
PUT http://localhost:8080/rest/user/1

```
> Here’s the JSON request body
```sh
{
    "firstName": "UpdateFirstName",
    "lastName": "UpdateLastName",
    "age": 33
}
```
---
> This Delete request delete the User object that matches the id path variable
```sh
DELETE http://localhost:8080/rest/user/1
```
---

Visit my blog [kiberbloq.com](https://kiberbloq.com)
