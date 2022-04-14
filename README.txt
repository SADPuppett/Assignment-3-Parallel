Samuel Hearn

Put all the files in one folder then run the following in the command prompt

javac Main.java
java Main

The problem the servants probably ran into in the problem was that they didnt lock nodes, so that when removing a node, a node was added to the one removed
and hence lost forever. This would lead to not all of them being removed and the thank you letters being less than the presents.
This linked list is based off of the lazy linked list that we have learned about in class. 
It only locks the nodes currently connected to the nodes being added or deleted in the list so that not the entire list is locked. 
Whenever a thread needs a node that is locked they simply wait for it, like a queue.
The remove function simply removes the first node in the list that isnt the head node, because the assignment did not say in what order to remove them
I simply decided the servants removed the front one everytime.
It features a contain method that isnt used by the code inherently, but by making chaning how int n is used by switching what lines are commented
out it can search if needed.
For testing purporses I checked the size of the linked list at the end of the program to make sure that there wasn't any nodes left in line.

I did not get to do problem 2 because of my own time restraints.