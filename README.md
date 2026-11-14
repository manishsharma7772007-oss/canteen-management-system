# Canteen Management System

A desktop canteen management application built with Java Swing and MySQL.

## Project files
- `src/` contains the Java application code.
- `images/` contains the image assets.

## Requirements
- JDK
- MySQL Server
- MySQL Connector/J added to the IntelliJ project libraries

## Database setup
The application connects to a local MySQL database named `canteen_db` as user `root`. Set the `CANTEEN_DB_PASSWORD` environment variable before launching the app. In IntelliJ IDEA, add `CANTEEN_DB_PASSWORD=<your-password>` in **Run → Edit Configurations → Environment variables**. Keep the password out of source control.

Run `Login.java` from IntelliJ to start the application.
