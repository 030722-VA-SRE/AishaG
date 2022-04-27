# PROJECT NAME

## Project Description

Migrated and containerized a RESTful Javalin web application to Spring Boot deployed using Docker-Compose. Application logs are exported using Promtail to Loki and monitored through a Grafana dashboard. The application allows users to create, update, and delete 3d models. Future updates will include a shop where models can be bought and downloaded. 

## Technologies Used

-Spring Boot 4.0.0
-Maven 2.6.4
-Java 1.8
-JUnit
-Mockito
-Docker
-Promtail
-Loki
-Grafana
-Prometheus

## Features

List of features ready and TODOs for future development
* Make an account as a creator or buyer.
* As a creator, create, update, and delete models.
* Search models by creator, price, or description.

To-do list:
* Create a "cart" for users to buy models from creators
* Be able to search previously purchased models under your user account
* Create a currency/purchase service to allow transactions.

## Getting Started
   
clone repository :: git clone https://github.com/030722-VA-SRE/AishaG/tree/main/Project1/3dModels_R_Us.git
install Docker
create deployment :: kubectl apply -f ./deployment

## Usage

send requests to API (localhost:8081) using webbrowser, Postman, or similar application

create user :POST: 8081/users
  Body: raw -> JSON
  "username": ""
  "password": ""
  
 *Must create a login first
login :POST: :8081/auth
  Body: form-data
  key = username / value = ?
  key = password / value = ?
  
get all users :GET: :8081/users

get all items :GET: :8081/tdmodels

add/update/delete/view item by id :<POST/PUT/DELETE/GET>:8081/items/

visualize metrics using grafana * :3000
