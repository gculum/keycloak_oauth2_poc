# keycloak_oauth2_poc





#Keycloak
docker run --name keycloak_test -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=password quay.io/keycloak/keycloak:latest start-dev

#Postgres
docker run -e POSTGRES_DB=fitness -e POSTGRES_USER=workout_user -e POSTGRES_PASSWORD=pwd505 -p 5432:5432 -d postgres
 