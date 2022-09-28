# Schedulo - hospitals

## Dev

Run `./gradlew bootRun`

Then you can proceed to a browser `http://localhost:8080/graphiql`

```mermaid
erDiagram
Organization }o--|| Department : contains
Department }o--|| User : has

Organization }o--|| OrganizationUserRoles : contains
User }o--|| OrganizationUserRoles : has
User }o--|| Shift : has
User }o--|| Availability : has

```

## Local Docker tests

`docker build --build-arg mongodb_uri="..." -t test .`
`docker rm test`
`docker run --name test -p 8080:8080  test`