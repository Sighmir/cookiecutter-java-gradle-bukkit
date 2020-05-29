# cookiecutter-java-gradle-bukkit

Cookiecutter for Bukkit Plugins using Java with Gradle, Groovy, Spigradle, JUnit 4, Mockito, PowerMock, GitHub Actions, Codecov and SonarQube.

## Features

`Gradle`, `Groovy`, `Spigradle`, `JUnit 4`, `Mockito`, `PowerMock`, `GitHub Actions`, `Codecov`, `SonarQube`.

**Note**:

1. for private projects, you must set the `CODECOV_TOKEN` environment
   variable under `Secrets`, otherwise remove the `coverage` job from `.github/workflows/config.yml`.
2. for `SonarQube` to work you must set the `SONAR_TOKEN` and `SONAR_HOST` environment
   variables under `Secrets`, otherwise comment or remove the `quality` job from `.github/workflows/config.yml`.
3. for use with SonarCloud you will have to edit the `sonar.projectKey` and `sonar.organization`
   according to your project on their website.
4. for automatic releases you must push tags following the pattern `v*`.

## Installation

Install cookiecutter:

```
$ pip install --user cookiecutter
```

Create a new repo:

```
$ cookiecutter git@github.com:Sighmir/cookiecutter-java-gradle-bukkit
```
