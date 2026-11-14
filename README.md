# Canteen Management System

A Java Swing desktop application for managing canteen food items and viewing available menu items. It uses MySQL for login and menu data.

## Features

- Admin and Student role-based login
- Admin food-item add, update, and delete screens
- Student view of available food items
- Password reset saved to the MySQL `users` table

## Requirements

- JDK 17 or newer
- MySQL Server
- MySQL Connector/J added as a library in IntelliJ IDEA
- IntelliJ IDEA (the project currently uses IntelliJ project settings)

## Database setup

1. Open `database/schema.sql` in MySQL Workbench and execute it. This creates the `canteen_db` database and the `users` and `food_items` tables.
2. Create local test accounts in MySQL Workbench. Use the roles exactly as shown and choose coursework-only passwords:

   ```sql
   USE canteen_db;
   INSERT INTO users (username, password, role)
   VALUES ('admin', 'choose-a-local-test-password', 'Admin');

   INSERT INTO users (username, password, role)
   VALUES ('student', 'choose-another-local-test-password', 'Student');
   ```

   Replace the example password text before running these statements. This version stores passwords as entered, so use test credentials that you do not use elsewhere.
3. In IntelliJ, open **Run → Edit Configurations** and add `CANTEEN_DB_PASSWORD` to the environment variables for both `Login` and `TestConnection`. Set its value to your local MySQL `root` password. Keep that value out of source files and GitHub.
4. Run `TestConnection`. It should print `Connection Successful!` when the server, database, Connector/J library, and password are configured.
5. Run `Login` to open the application.

## Project layout

- `src/` — Java Swing screens and database access classes
- `database/schema.sql` — MySQL database and table definitions
- `images/` — application image assets

## Notes

The app connects to `localhost:3306/canteen_db` as MySQL user `root`. Change the connection settings in `src/DBConnection.java` if your local MySQL setup uses a different host, port, database, or username. Do not commit IntelliJ run configurations containing local passwords.
