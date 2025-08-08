# Recruitment Task -GitHub Repository Api

The application is solution of recruitment task.
Application is integrating with external GitHub Api to fetch all repositories of given user.
All fetched repositories include names of all branches and last commit sha of every branch.


## Features

- Fetching public repositories by GitHub login
- Filtering fork repositories
- List of branches and last comit sha for each repository
- Response with standardized JSON format
- Error handling when client try fetch not existing GitHub user
- Integration test with wireMock

---

## Endpoint

```
GET /{username}
```

**Headers**

- `Accept: application/json` (optional)

---

## Example Response

```json
{
  "repositories": [
    {
      "repositoryName": "MiniGames",
      "ownerLogin": "OskarPala",
      "branches": [
        {
          "name": "master",
          "lastCommitSha": "26a2de58ec80c40eb6d208f1d88815de7c3d25cd"
        }
      ]
    }
  ]
}
```

---

## How to start application in IntelliJ (with GitHub plugin)

1. Open IntelliJ IDEA or Community
2. Clone repository with Git plugin
    - Click File->New->Project from Version Control
    - Choose Git
    - Paste repository URL:
       ```https://github.com/OskarPala/RecruitmentTask.git```
3. Wait for the project load
4. Run application
    - Open RecruitmentTaskApplication.java
    - Click green triangle button next to the main method
5. After application start open browser or Postman and go to
   ``` http://localhost:8081/{github-username}```

---

## How to start integration test with IntelliJ

1. Click with right button on directory ```src/test/java```
2. Choose run "All Tests" or run test by clicking
   green triangle next to specific method
   in HappyPathIntegrationTest class

---

## Technologies

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-brightgreen)
![Spring Cloud OpenFeign](https://img.shields.io/badge/OpenFeign-Enabled-blueviolet)
![WireMock](https://img.shields.io/badge/WireMock-2.35.0-yellow)
![JUnit 5](https://img.shields.io/badge/JUnit_5-Test-red)
![Maven](https://img.shields.io/badge/Maven-Build-orange)

---

## Error handling

Example response when user doesn't exist:

```json
{
  "status": 404,
  "message": "User: wqetdsafdsfhwer does not exist"
}
```

---

## Configuration

```yaml
# application.yml
github:
  api:
    url: https://api.github.com/
```

---

## Author

Oskar Pala
---
