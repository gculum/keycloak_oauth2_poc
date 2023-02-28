# Keycloak_oauth2_poc

## Intro
this is a POC app that implements authentification and authorization of a REST API
using keycloak server.
Data is being persisted into Postgres DB

## Keycloak
Keycloak is exported into realm-export.json. Keep in mind to import it into Keycloak configuration if you run it from the scratch.

## Docker cookbook
#Keycloak
docker run --name keycloak_test -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=password quay.io/keycloak/keycloak:latest start-dev

#Postgres
docker run -e POSTGRES_DB=fitness -e POSTGRES_USER=workout_user -e POSTGRES_PASSWORD=pwd505 -p 5432:5432 -d postgres
 