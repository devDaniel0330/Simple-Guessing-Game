#Guessing Game#
---
##Introduction##
> A simple guessing game using the DecisionTreeInterface.java given.
---
##Game Flow Example##
1. Player thinks of an animal
2. The game asks no/yes questions based on the current decision tree
3. If the game guesses correctly, it prints "I guessed it!"
4. If it guesses incorrectly, it asks the player for the correct animal and a new distinguishing question, updating the tree for future games.
5. Game saves the updated decision tree to a file (gameTree.dat) so it can continue learning across multiple games.