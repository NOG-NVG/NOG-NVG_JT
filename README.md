# NOG-NVG_JT
Usage

When the game starts, you will see the main menu with the following options:
1: Start a new game
0: Exit the game

Choose option 1 to start a new game.
Enter the required information, such as the player's date of birth (if required) and the size of the game map.
Follow the prompts to set up the tents on the terrain.
The game will display the current state of the terrain and provide feedback on the validity of your moves.
Continue placing tents until you win or decide to exit the game.

The game reads terrain data and counters from text files with the following format:

Terrain Data File: <numLines>x<numColumns>.txt

Each line represents a tree's coordinates in the format <line>,<column>.
Example:

0,0
1,1
2,2

Counters File: <numLines>x<numColumns>_counters.txt

The first line contains comma-separated counter values for each column.
The second line contains comma-separated counter values for each line.
Use 0 for empty counters.
Example:

3,2,1
2,3,1,2
