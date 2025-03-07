# Toy Robot Simulator

## Overview
The **Toy Robot Simulator** is a Java-based console application that allows users to control a virtual robot on a grid. The robot responds to various commands to move, change direction, and interact with the environment, such as navigating through a maze.

## Features
- Move the robot forward and backward.
- Change direction (North, South, East, West).
- Execute replay commands to repeat previous actions.
- Navigate through a maze.
- Display help information for available commands.

## Project Structure
The project follows an object-oriented programming (OOP) approach and is structured as follows:

```
submission-oop-java-toyrobot2/
│-- src/main/java/za/co/wethinkcode/toyrobot/
│   │-- Command.java               # Base class for all commands
│   │-- BackCommand.java           # Handles backward movement
│   │-- ForwardCommand.java        # Handles forward movement
│   │-- Direction.java             # Enum representing directions
│   │-- Position.java              # Represents the robot's position
│   │-- Play.java                  # Main execution class
│   │-- HelpCommand.java           # Displays available commands
│   │-- ReplayCommand.java         # Repeats previous commands
│   │-- MazeRunnerCommand.java     # Handles maze navigation
│-- pom.xml                        # Maven configuration
│-- README.md                      # Project documentation
```

## Installation & Setup
### Prerequisites
- Java 11 or later
- Apache Maven

### Steps to Run
1. Clone this repository:
   ```sh
   git clone https://github.com/GXS-FIN/robot_world.git
   cd submission-oop-java-toyrobot2
   ```
2. Compile and package the project using Maven:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   java -cp "target/classes:libs/*" za.co.wethinkcode.toyrobot.Play
   ```

## Usage
After running the application, you can enter commands such as:
- `FORWARD 10` - Moves the robot forward by 10 steps.
- `BACK 5` - Moves the robot backward by 5 steps.
- `LEFT` / `RIGHT` - Rotates the robot in the respective direction.
- `REPLAY` - Re-executes previous commands.
- `HELP` - Displays available commands.
- `QUIT` - Exits the program.

## Contributing
Feel free to contribute by:
- Submitting bug reports.
- Enhancing existing features.
- Improving documentation.

## License
This project is open-source and available under the MIT License.

---

This `README.md` provides a clear understanding of the project, setup instructions, and how to use it. Let me know if you'd like any modifications!

