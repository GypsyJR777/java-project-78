# Data Validator

[![Actions Status](https://github.com/GypsyJR777/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/GypsyJR777/java-project-78/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=GypsyJR777_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=GypsyJR777_java-project-78)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=GypsyJR777_java-project-78&metric=bugs)](https://sonarcloud.io/summary/new_code?id=GypsyJR777_java-project-78)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=GypsyJR777_java-project-78&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=GypsyJR777_java-project-78)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=GypsyJR777_java-project-78&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=GypsyJR777_java-project-78)

`Data Validator` is a small Java library for declarative data validation. It provides fluent schemas for strings, numbers and `Map` objects, including nested validation for map fields.

The project is built as a reusable library rather than a standalone application, so it is intended to be connected to other Java projects and used through the `Validator` API.

## Features

- String validation: `required()`, `minLength()`, `contains()`
- Number validation: `required()`, `positive()`, `range()`
- Map validation: `required()`, `sizeof()`, `shape()`
- Nested validation for `Map` values with different schemas

## Run

From the `app` directory:

```bash
./gradlew test
```

Run style checks:

```bash
./gradlew checkstyleMain checkstyleTest
```

Build the library:

```bash
./gradlew build
```
