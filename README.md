We use Hibernate to create database tables and implement crud for each of the tables: character, movie and franchise and the database is then seeded with some basic data.

Using Spring Web we make an API so that we can access and update the database. For updating relations between tables we have specific patch methods that work by sending a patch request to api/v1/franchises/id or api/v1/movies/id with a body of an integer array to update the movies in franchises or characters in movies respectively.

For the exact endpoints that can be used we have swagger openapi documentation to see how each works.

Docker Deployment didn't work so that's not there.
