ROBOT WORLDS


DESCRIPTION:

"Robot World" is a terminal game that allows players to launch a robot of their choice inside a virtual world and start battling with each other. The game involves various commands and actions players can take to control their robots, interact with the environment, and engage in combat with other robots. The gameplay mechanics include movement, attacks, defense strategies, and other features like upgrading robots or completing objectives within the game world.



INSTALLATION

Prerequisites

JVM
java


INSTRUCTIONS

Navigate to the project directory on terminal:

cd jhb07_robot_worlds

INSTALLING THE SERVER:
sudo cp -r ServerController /usr/local/bin
chmod +x server
sudo cp server /usr/local/bin
use command 'server' to start running the server on the terminal

INSTALLING THE CLIENT CONTROLLER;
sudo cp -r ClientController /usr/local/bin
chmod +x play`
sudo cp play /usr/local/bin 
use command 'play' along with argument of ip address and port number to start running the client on the terminal ("play ip address port")


AVAILABLE ROBOTS:
Sniper (maxShield = 4) (maxShots = 5)
Tank (maxShield = 8) (maxShots =  6)
Assassin (maxShield = 3) (maxShots =  15)
Spy (maxShield = 2) (maxShots =  10)
Striker (maxShield = 5) (maxShots =  12)
Commander (maxShield = 6) (maxShots =  10)
Mabena (maxShield = 2) (maxShots =  3)

USAGE

Run [play ip_address port] to start the client
Give robot name
Use launch to activate a new robot
Select a robot from the available lists of robots. default robot is "mabena"
Input maxShield
Input maxShots

CLIENT COMMANDS

forward argument
back argument
look
turn [right or left]
fire
state
repair
quit
reload

SERVER COMMANDS

robots
dump
quit
