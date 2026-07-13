# 06 — Java GitLab Pipeline (Maven + Nexus + SonarQube)

## Pipeline Overview

A production-grade Java pipeline that:

1. **Compile** — `mvn compile` to catch syntax and import errors
2. **Test** — `mvn test` + publish JUnit results to GitLab
3. **Code Quality** — SonarQube analysis (SAST for Java)
4. **Package** — `mvn package` to produce the JAR
5. **Publish to Nexus** — upload the JAR to a Nexus artifact repository
6. **Build Docker image** — containerize the application
7. **Push to GitLab registry** — push the Docker image
8. **Deploy** — run the container (on main only, manual gate for production)

## Required CI/CD Variables

| Variable              | Description                                   | Masked | Protected |
|-----------------------|-----------------------------------------------|--------|-----------|
| `NEXUS_URL`           | e.g. `https://nexus.mycompany.com`            | No     | No        |
| `NEXUS_USERNAME`      | Nexus deployment user                         | No     | No        |
| `NEXUS_PASSWORD`      | Nexus deployment user password                | Yes    | Yes       |
| `SONAR_HOST_URL`      | e.g. `https://sonarqube.mycompany.com`        | No     | No        |
| `SONAR_TOKEN`         | SonarQube project authentication token        | Yes    | Yes       |
| `DEPLOY_HOST`         | SSH hostname of deployment server             | No     | Yes       |
| `DEPLOY_SSH_KEY`      | Private SSH key for deployment (PEM format)   | Yes    | Yes       |

## Maven Settings (Nexus Authentication)

GitLab injects the `settings.xml` file at pipeline runtime via a CI/CD
File variable — see the `settings.xml` file in this folder.
Add it as a **File** type variable named `MAVEN_SETTINGS_XML`.

## SonarQube Setup

1. In SonarQube: create a new project, generate a token
2. Add the token as a masked GitLab variable: `SONAR_TOKEN`
3. Set `SONAR_HOST_URL` to your SonarQube server URL
4. The `sonar-maven-plugin` is already in the `pom.xml`
