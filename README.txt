How to build the project?
1. Run the Application
2. Send Request to the required api endpoint (I used Postman for that purpose)
    2.1. If it is POST request provide entity in JSON format and send the request.
    2.2. If it is DELETE or PUT request provide the id of the entity you want to delete.
    2.3. If it is GET request you will get the output as JSON body.
3. Go to http://localhost:8080/h2-console (no password, username: sa) and run queries against the required table

URLS to access the application
GET http://localhost:8080/api/v1/developers/ - get all developers
POST http://localhost:8080/api/v1/developers/register-developer - register new developer
DELETE http://localhost:8080/api/v1/developers/(type id here) - delete developer with the provided id
PUT http://localhost:8080/api/v1/developers/(type id here) - update developer with the provided id
GET http://localhost:8080/api/v1/issues/ - get all issues
POST http://localhost:8080/api/v1/issues/register-story - register new story
POST http://localhost:8080/api/v1/issues/register-bug - register new bug
DELETE http://localhost:8080/api/v1/issues/(type id here) - delete issue with the provided id
PUT http://localhost:8080/api/v1/issues/update-story/(type issueId here) - update story with the provided id
PUT http://localhost:8080/api/v1/issues/update-bug/(type issueId here) - update bug with the provided id

Short Description of Technical Decisions and why I made them

1. One table for all issues
I decided to store stories and bugs in one table because you don't have to worry about generating unique id
for 2 tables, and if for example you know only issueId you don't know what table to access.

2. Use enums for constant values
Issue Status, Bug Priority, Issue Type can be only particular values, so I decided to pre-define them by using enums.

3. Use JpaRepository instead of CrudRepository
JpaRepository extends CrudRepository and provides JPA related methods such as flushing the persistence context
and delete records in a batch. If we will need this functionality in the future we just add it and don't rewrite our
repositories.

Problems I faced
1. The main problem I faced is to segregate getPlan method to small methods, so getPlan method looks pretty complex
and monolithic.

Which part I chose to leave
1. I didn't leave any part of the project.

What I would do if I had more time
1. I would split getPlan method on small methods.
2. Also, I would make application more secure and provide some more checks, for example we can check IssueType before
updating bug or story.

