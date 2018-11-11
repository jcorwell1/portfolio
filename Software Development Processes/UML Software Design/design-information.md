1.	This UML diagram contrains two classes. One for newPlayer, and one for login. One of thise must be completed to access the User class. For the login class there is an attribute username, and method logIn() that will prompt the user to enter their username for acess.
	For newPlayer there is a promptUser() method that prompts the user to enter their name, email, and username to generate a new user account. The saveAccount method allows them to save their account.

2.	This UML diagram has a CreateScramble class which prompts the users for relavent information with the method generateScramble(), Users are then if satisfied with the generated scramble able to save it. This will create a Scramble in the Scramble class. To choose and solve a word scramble, we have created the SolveScramble class. This class shows scrambles available in the database stored as datatype List. The statistics for both players and scrambles are represented by the two classes PlayerStatistics and ScrambleStatistics respectively. These classes gather data from the database and then calculate the statistics within the class

3.	This is represented in the Database class. This class’s contents is not detailed as its not directly responsible for the modeling of the word scramble app. This class links directly with PlayerStatistics, ScrambleStatistics, SolveScramble, Scramble, and User classes. It serves as the data heart of the application.

4. This utitilty class is represented in the ExternalWebService utility class. It contains methods addPlayer(), checkUniqueness(), retrieveScrambles(), and reportScrambles(), sendScrambles, retrievePlayersInfo(). These work rather intuitively based off of the description of them. I have my database modelled as the local database, while the ExternalWebService pulls from my local database to the global database.


5.	A players first name, last name, username, email, will be prompted for the user ineput in the method promptUser() in the newPlayer class. Saving the account will be prompted for the user to do in the saveAccount() method. All input information will be then stored in the User class.


6. In the CreateScramble class, the generateScramble() method will prompt users to enter a phrase and a clue befor proceeding to generate a scrambledPhrase. The viewScramble() method will let users view the generated scramble. If the user is dissatisfied with the results they may regenerate, calling the generateScramble method again. When accepted, the saveScramble() method is called and the system generates an ID for the specific scramble.


7. The specifications for this step will be met in the generateScrambled() method. This is a relatively simple word scrambling method, and should be accomplishable in a single method.

8. The specifications for this step will be met in the generateScrambled() method with the previous requirements.


9.	The requriements in this step are met in the SolveScramble class. unsolvedScr List lets users view unsolved word scrambles with any progres shown first, and this information is pulled from the Database class. Users will be able to select a scramble to work on via the chooseScramble() method. An attempt at a solution is met with the enterSolution() method. When the scramble is deemed solved by the user they can submit it via the subitScramble() method. The displayCorrectness() method displays correctness of the solution, and returns the user to the proper location afterwards.


10. The saveAndExit() method will prompt the user upon attempting to exit if they would like to save the scramble. It will then store the scramble in the database.


11. The ScrambleStatistics class is responsible for the statistics of the scramble. retrieveScrambleInfo() will retieve the pertinent information from the database. Relavent information detailed in this requirement (ID, solved or created) will be stored in the Tuple scrambles within this class and sorted by the decreasing number of solutions. The sorting mechanism will be a part of retriving the info from the Database.


12. The players statistics is dictated by the PlayerSatistics class. numberSolved and numberCreated displays the Scramble numbers for each user. playerFullName holds the name of the user. scramUsedByOthers() is a list that contains the amount of times each scramble has been solved by other players. This will also contain scramble ID so that one may find the scramble. It will be sorted as specified in the requirements. retrieveUserInfo() is a method that pulls the info from the database which will return the info if available locally or refer to the ExternalWebService class to retrieve the info.


13. As per this assignment’s requirements, the GUI is not to be developed in this UML diagram. Therefore, it has not been.
