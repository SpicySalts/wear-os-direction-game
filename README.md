# wear-os-direction-game
Android Wear OS game demonstrating real-time motion tracking and sensor-based interactions.

## Project Overview
This project creates a **Random Direction Game** on Wear OS. The game randomly displays one of three directions to the player:

- **Left and Right** → Move the device in the y-axis
- **Towards and Away** → Move the device in the z-axis
- **Up and Down** → Move the watch in the x-axis

When the device is moved in the correct direction, the score is increased by 1, and a random new direction is chosen. The game repeats indefinitely, and tracks the score in real time.

## Key Features:
- Recognizes movement in the x, y, and z axes
- Tracks and displays player score dynamically
- Infinite randomized gameplay
- Uses Wear OS accelerometer data
