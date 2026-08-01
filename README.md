# Pac-Man Game (Java)

## Project Information

- **Project:** Pac-Man
- **Course:** Advanced Programming
- **Language:** Java
- **Architecture:** Object-Oriented Programming (OOP)

---

# Development Environment

| Item | Version |
|------|---------|
| Java | OpenJDK 26 |
| IntelliJ IDEA | 2026.1.1 |
| Build Tool | Apache Maven |
| Database | SQLite |
| Database Driver | sqlite-jdbc 3.46.0.0 |
| Operating System | Windows 11 |

---

# Technologies Used

- Java Swing (GUI)
- Java AWT
- Object-Oriented Programming
- Maven
- SQLite
- JDBC
- Git
- GitHub

---

# Maven

This project is managed using **Apache Maven**.

Maven is responsible for:

- Managing external libraries
- Downloading dependencies automatically
- Project build management
- Dependency version control

The project includes a **pom.xml** file.

The main dependency is:

```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.46.0.0</version>
</dependency>
```

When the project is opened in IntelliJ IDEA, Maven automatically downloads the required libraries.

---

# Project Structure

```
src
 └── main
      ├── java
      │     ├── Main
      │     ├── database
      │     ├── engine
      │     ├── model
      │     ├── ui
      │     └── util
      │
      └── resources
            └── image
```

### Packages

### Main
Application entry point.

### database

Contains all database classes.

- DatabaseConnection
- PlayerDAO

Responsible for:

- Creating database connection
- Creating player table
- Saving players
- Reading High Score
- Updating High Score

---

### engine

Contains the game logic.

GameEngine controls:

- Game update loop
- Collision detection
- Win condition
- Lose condition
- Retry
- High Score management

---

### model

Contains all game entities.

Examples:

- Pacman
- Ghost
- Maze
- Wall
- Pellet
- Position
- Entity
- Direction

---

### ui

Contains graphical user interface.

Classes:

- WelcomeFrame
- MainFrame
- GamePanel
- GameFrame

---

### util

Utility classes.

Examples:

- Constants
- ImageLoader
- ScoreManager

---

# Database

The project uses **SQLite**.

Database file:

```
pacman.db
```

The database is created automatically when the game starts.

The table is also created automatically if it does not already exist.

Table:

```
players
```

Columns:

| Column | Description |
|---------|-------------|
| id | Player ID |
| name | Player Name |
| high_score | Highest Score |

Only one record exists for each player because the player name is unique.

---

# High Score System

When the game starts:

- Player is automatically created if not already موجود باشد.
- Previous High Score is loaded from the database.

During the game:

- If current score becomes greater than High Score,
  the displayed High Score updates immediately.

When the game ends:

- High Score is saved into SQLite only if the player beats the previous record.

---

# Gameplay Features

Implemented features:

- Pac-Man movement
- Ghost movement
- Pellet collection
- Score system
- High Score system
- Lives system
- Win condition
- Game Over screen
- Retry (R)
- Exit (ESC)
- SQLite database support

---

# Images

All game images are stored inside:

```
src/main/resources/image
```

Images include:

- Pac-Man
- Ghosts
- Walls

They are loaded using the ImageLoader utility class.

---

# Git & GitHub

Git was used throughout the development process.

The repository was connected to GitHub.

Meaningful commits were used during implementation.

Examples:

- Create Pacman movement
- Add Ghost AI
- Connect SQLite database
- Add High Score system
- Finish Pacman game project

---

# How to Run

### 1.

Clone the repository

```bash
git clone <repository-url>
```

or download the ZIP file.

---

### 2.

Open the project in IntelliJ IDEA.

---

### 3.

Wait for Maven to finish downloading dependencies.

---

### 4.

Run

```
Main.java
```

---

### 5.

Enter your player name.

---

### 6.

Press **Start**.

The database will be created automatically if it does not already exist.

---

# Controls

| Key | Action |
|------|--------|
| ↑ | Move Up |
| ↓ | Move Down |
| ← | Move Left |
| → | Move Right |
| R | Retry after Game Over |
| ESC | Exit after Game Over |

---

# Notes

- The game stores only the player's highest score.
- High Score is persistent between executions.
- Database is updated only when a new record is achieved.
- All required libraries are managed by Maven automatically.