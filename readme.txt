======================
Project: MazeSolver
======================
This project contains a program to solve mazes.
The program reads in a file, which recursively solves the maze and outputs the solution.


====================
Startup instructions
====================
To start this application, call the static “main” method of the class “MazeLogic”.
A popup dialog box should appear, navigate to the file and click OK.

If the maze can be solved, the solution is printed to console, along with a maze solved message.
If the maze cannot be solved, a maze cannot be solved message is printed

The program quits if the popup dialog box is cancelled, the maze is solved or unsolved.


=========
More info
=========
This project was created in IntelliJ Idea 2018.3.5
Source code is located in the src folder.
Test mazes used to test this program is in the test folder.


=========================
Maze input/output formats
=========================
The input is a maze description file in plain text.
 1 - denotes walls
 0 - traversable passage way

INPUT:
<WIDTH> <HEIGHT><CR>
<START_X> <START_Y><CR>		(x,y) location of the start. (0,0) is upper left and (width-1,height-1) is lower right
<END_X> <END_Y><CR>		    (x,y) location of the end
<HEIGHT> rows where each row has <WIDTH> {0,1} integers space delimited

OUTPUT:
 the maze with a path from start to end
 walls marked by '#', passages marked by ' ', path marked by 'X', start/end marked by 'S'/'E'

Example file:
10 10
1 1
8 8
1 1 1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 0 0 1
1 0 1 0 1 1 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 0 1 0 1 1 1
1 0 1 0 0 1 0 1 0 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 1 0 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1

Example output:
##########
#SXX     #
# #X######
# #XX    #
# ##X# ###
# # X# # #
# # XX   #
# ###X####
# #  XXXE#
##########


