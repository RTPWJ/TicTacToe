/*
Name: Alexander Georgiadis (RezTech)
Date Started: 5/19/2015
Date Last Edited: 9/3/2015
Description: This is a partially complex Tic Tac Toe game, to be used with RTPWJ (Ready To Program With Java).
*/

import java.awt.*;
import hsa.Console;

public class TicTacToe_Educational
{
    //Console
    static Console c;

    //Arrays
    static int questionArray[] = new int [31], playedSpots[] = new int [10]; //I Prefer 31 So I Can Use 1-30 Instead Of 0-29
    static char answerArray[] = new char [31], playedSymbol[] = new char [10];
    static String promptArray[] = new String [31];

    //Variables
    static int playerNum, counter = 0, counter2 = 0;
    static char playerSymbol, playerSymbol2;

    public static void main (String[] args)
    {
        c = new Console (48, 236); //Fullscreen That Console

        //Fill Array
        for (int i = 0 ; i < 10 ; i++)
        {
            playedSymbol [i] = (' '); //Fills board with blank space, required for later on
        }

        selectPlayer (); //Randomly Selects A Player
    }


    public static void selectPlayer ()
    {
        //Output
        c.println ("Welcome! Let us see what player will be playing first...");
        c.println (); //Space is always nice

        //Generate The Player Number
        playerNum = (int) (Math.random () * 2);

        if (playerNum == 0) //This can be used, or one can be added to the Math.random (), I prefer this method, but either can be used
        {
            playerNum = 2;
        }


        //Output
        c.println ("It appears player " + playerNum + " will be going first! Please select if you would like to use X's or O's for your markings.");
        if (playerNum == 1)
        {
            playerSymbol = c.getChar (); //Stores player ones symbol in a char
        }
        else
        {
            playerSymbol2 = c.getChar (); //And if it is not player one, it is playrer two, that get's stored in playerSymbol2 char
        }

        //Check That Last One
        while (!(playerSymbol == 'X' || playerSymbol == 'O' || playerSymbol == 'x' || playerSymbol == 'o' || playerSymbol2 == 'X' || playerSymbol2 == 'O' || playerSymbol2 == 'x' || playerSymbol2 == 'o')) //Checks to make sure that a correct value is entered, not depending on capitilization
        {
            c.clear ();
            c.println ("Player number " + playerNum + "! Please select either X's or O's.");
            if (playerNum == 1)
            {
                playerSymbol = c.getChar ();
            }
            else
            {
                playerSymbol2 = c.getChar ();
            }
        }

        //Changes Lower Cases Letters To Upper Case (Prevents Issues Latter On)
        if (playerSymbol == 'x')
        {
            playerSymbol = 'X';
        }
        else if (playerSymbol == 'o')
        {
            playerSymbol = 'O';
        }
        
        //Fixes if player 2 selects X, player 1 does not get a marker that works (Box)
        if (playerSymbol2 == 'X')
        {
            playerSymbol = 'O';
        }
        else
        {
            playerSymbol = 'X';
        }

        //Assigns X/O's To The Second Player
        if (playerSymbol == 'X')
        {
            playerSymbol2 = 'O';
        }
        else
        {
            playerSymbol2 = 'X';
        }
        c.clear (); //Clear All Previous Content
        playGame (); //Let's play ball!
    }


    public static void playGame ()
    {

        //Variables
        int locationInt, hasFinished = 0;
        String location;

        //Print Begining Board
        drawBoard ();

        //Loop
        do
        {
            askQuestions ();

            //Prompts
            c.println ("Player " + playerNum + ", please select where you would like to play your marker.");
            location = c.readLine ();

            while (!(location.equals ("1") || location.equals ("2") || location.equals ("3") || location.equals ("4") || location.equals ("5") || location.equals ("6") || location.equals ("7") || location.equals ("8") || location.equals ("9"))) //No Invalid Placements
            {
                c.clear ();
                drawBoard ();
                c.println ("Please enter a valid placement!");
                location = c.readLine ();
            }

            locationInt = Integer.parseInt (location); //If It's All Good, Make It int

            while (!(playedSymbol [locationInt] == ' '))  //No Re-using Locations
            {
                c.clear ();
                drawBoard ();
                c.println ("That location is already being used! Please chose another one.");
                location = c.readLine ();

                //And Double Check Again
                while (!(location.equals ("1") || location.equals ("2") || location.equals ("3") || location.equals ("4") || location.equals ("5") || location.equals ("6") || location.equals ("7") || location.equals ("8") || location.equals ("9"))) //No Invalid Placements
                {
                    c.clear ();
                    drawBoard ();
                    c.println ("Please enter a valid placement!");
                    location = c.readLine ();
                }

                locationInt = Integer.parseInt (location); //Moving this into the while loop fixed the re-looping error because of non-matching values, let's keep this there
            }

            //Assign Value To The Array
            if (playerNum == 1)
            {
                playedSymbol [locationInt] = playerSymbol; //Assigns the spot on the board the player's symbol (Copy the char into the array)
            }
            else
            {
                playedSymbol [locationInt] = playerSymbol2;
            }

            //Clear Previous Board
            c.clear ();

            //Print Board
            drawBoard ();

            //Increment Counter
            counter++;

            //Check For Win

            //Side To Side (3)
            if (playedSymbol [1] == 'X' && playedSymbol [2] == 'X' && playedSymbol [3] == 'X') //Row 1
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [1] == 'O' && playedSymbol [2] == 'O' && playedSymbol [3] == 'O')
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [4] == 'X' && playedSymbol [5] == 'X' && playedSymbol [6] == 'X') //Row 2
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [4] == 'O' && playedSymbol [5] == 'O' && playedSymbol [6] == 'O')
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [7] == 'X' && playedSymbol [8] == 'X' && playedSymbol [9] == 'X') //Row 3
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [7] == 'O' && playedSymbol [8] == 'O' && playedSymbol [9] == 'O')
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }

            //Up And Down (3)
            else if (playedSymbol [1] == 'X' && playedSymbol [4] == 'X' && playedSymbol [7] == 'X') //Row 1
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [1] == 'O' && playedSymbol [4] == 'O' && playedSymbol [7] == 'O')
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [2] == 'X' && playedSymbol [5] == 'X' && playedSymbol [8] == 'X') //Row 2
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [2] == 'O' && playedSymbol [5] == 'O' && playedSymbol [8] == 'O')
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [3] == 'X' && playedSymbol [6] == 'X' && playedSymbol [9] == 'X') //Row 3
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [3] == 'O' && playedSymbol [6] == 'O' && playedSymbol [9] == 'O')
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }

            //Diagonal (2)
            else if (playedSymbol [1] == 'X' && playedSymbol [5] == 'X' && playedSymbol [9] == 'X') //Row 1
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [1] == 'O' && playedSymbol [5] == 'O' && playedSymbol [9] == 'O')
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [3] == 'X' && playedSymbol [5] == 'X' && playedSymbol [7] == 'X') //Row 2
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }
            else if (playedSymbol [3] == 'O' && playedSymbol [5] == 'O' && playedSymbol [7] == 'O') //Row 2
            {
                c.println ("Player " + playerNum + " has won!");
                hasFinished = 1;
            }

            //Tie Game
            else if (counter == 9)
            {
                c.println ("It's a cat's game!");
                hasFinished = 1;
            }

            //Swap Players
            swapPlayers ();
        }
        while (hasFinished == 0);

        //New Game Prompt
        newGame ();
    }


    public static void drawBoard () //Calling this method will always re-draw the board, and is only needed after the players have been selected
    
    {
        c.println ("----------------------------");
        c.println ("|1.      |2.      |3.      |");
        c.println ("|        |        |        |");
        c.println ("|   " + playedSymbol [1] + "    |    " + playedSymbol [2] + "   |    " + playedSymbol [3] + "   |"); //Draws the board with the blank spaces on the first run
        c.println ("|        |        |        |");
        c.println ("|        |        |        |");
        c.println ("----------------------------");
        c.println ("|4.      |5.      |6.      |");
        c.println ("|        |        |        |");
        c.println ("|    " + playedSymbol [4] + "   |    " + playedSymbol [5] + "   |    " + playedSymbol [6] + "   |");
        c.println ("|        |        |        |");
        c.println ("|        |        |        |");
        c.println ("----------------------------");
        c.println ("|7.      |8.      |9.      |");
        c.println ("|        |        |        |");
        c.println ("|    " + playedSymbol [7] + "   |    " + playedSymbol [8] + "   |    " + playedSymbol [9] + "   |");
        c.println ("|        |        |        |");
        c.println ("|        |        |        |");
        c.println ("----------------------------");
    }


    public static void askQuestions ()
    {
        //Variables
        int questionNumber;
        char trueOrfalse;

        //Increment The Counter
        counter2++;

        if (counter2 <= 30)
        {
            //Generates A Random Question
            questionNumber = (int) (Math.random () * 31); //So multiply by 31 to get 30... Seems about right

            //Makes Sure That Question Is Not 0
            while (questionNumber == 0) //While works better then if, that way it can loop if it gets another 0
            {
                questionNumber = (int) (Math.random () * 31);
            }

            //Checks For Repetitive Question, Could Be Cleaner, But Works
            while (questionArray [questionNumber] == 1)
            {
                questionNumber = (int) (Math.random () * 31);

                //Checks For The Question Being 0 Again
                while (questionNumber == 0)
                {
                    questionNumber = (int) (Math.random () * 31);
                }
            }

            //If All Conditions Meet, Good To Go
            questionArray [questionNumber] = 1;

            //Question And Answer Definition
            promptArray [1] = ("In Minecraft, gold is the most durable material that can be used to make equipment."); //False
            answerArray [1] = ('2'); //This program style uses 1 for true, and 2 for false. This prevents the user from entering an incorrect spelling of "True" or "False"

            promptArray [2] = ("In Minecraft, you can smelt 100 blocks using a bucket of lava in a furnace, and still get the bucket back."); //False
            answerArray [2] = ('2');

            promptArray [3] = ("In Minecraft, when you make a cake, you get the buckets back that was used to hold the milk."); //True
            answerArray [3] = ('1');

            promptArray [4] = ("In Minecraft, it is possible to build a world above 255 blocks high, starting from bedrock."); //True
            answerArray [4] = ('1');

            promptArray [5] = ("In Minecraft, redstone blocks can power any piece of redstone dust within a one block radius."); //True
            answerArray [5] = ('1');

            promptArray [6] = ("In Minecraft, it is possible to work with quantum physics."); //True
            answerArray [6] = ('1');

            promptArray [7] = ("In Minecraft, sand blocks not effected by gravity, but dirt blocks are."); //False
            answerArray [7] = ('2');

            promptArray [8] = ("In Minecraft, all of the music is made by an indie artist called C4."); //False
            answerArray [8] = ('2');

            promptArray [9] = ("In Minecraft, the bottom of the world is lined with a flat layer of bedrock."); //False
            answerArray [9] = ('2');

            promptArray [10] = ("In Minecraft, cobblestone is more durable then wood."); //True
            answerArray [10] = ('1');

            promptArray [11] = ("In Minecraft, it is possible to make armour out of stone."); //False
            answerArray [11] = ('2');

            promptArray [12] = ("In Minecraft, it is possible to surf the web while still playing the game."); //True
            answerArray [12] = ('1');

            promptArray [13] = ("In Minecraft, you can find pre-generated floating islands."); //True
            answerArray [13] = ('1');

            promptArray [14] = ("In Minecraft, physics are not applied to chunks that are loaded after the initial world creation."); //True
            answerArray [14] = ('1');

            promptArray [15] = ("In Minecraft, you can mine for ores."); //True
            answerArray [15] = ('1');

            promptArray [16] = ("In Minecraft, you must start off by punching trees."); //False
            answerArray [16] = ('2');

            promptArray [17] = ("In Minecraft, villagers have the most health, only to be followed by the ender dragon."); //False
            answerArray [17] = ('2');

            promptArray [18] = ("In Minecraft, you can write Lua scripts and execute them without mods."); //False
            answerArray [18] = ('2');

            promptArray [19] = ("In Minecraft, it is possible to jump a fence without building up."); //True
            answerArray [19] = ('1');

            promptArray [20] = ("In Minecraft, it is possible to accelerate sprinting by hitting your head against any overhanging blocks."); //True
            answerArray [20] = ('1');

            promptArray [21] = ("In Minecraft, steaks fill up the most hunger bars, taking only two to completely fill your hunger bar."); //True
            answerArray [21] = ('1');

            promptArray [22] = ("In Minecraft, you can die from being struck by lightning."); //True
            answerArray [22] = ('1');

            promptArray [23] = ("In Minecraft, it is possible to turn a regular pig into a pigman by making the pig be struck by lightning."); //True
            answerArray [23] = ('1');

            promptArray [24] = ("In Minecraft, it is possible to turn a villager into a zombie villager, but not back into a villager."); //False
            answerArray [24] = ('2');

            promptArray [25] = ("In Minecraft, you can create a mod to blow up the world."); //True
            answerArray [25] = ('1');

            promptArray [26] = ("In Minecraft, you can build a nuclear power plant."); //True
            answerArray [26] = ('1');

            promptArray [27] = ("One of the most downloaded Minecraft mods is called Forge."); //True
            answerArray [27] = ('1');

            promptArray [28] = ("It is possible to increase the performance of Minecraft by installing a modification called SEUS."); //False
            answerArray [28] = ('2');

            promptArray [29] = ("It is possible to increase the graphical capabilities of Minecraft by using external shaders."); //True
            answerArray [29] = ('1');

            promptArray [30] = ("Anything is possible in the world of Minecraft."); //True
            answerArray [30] = ('1');

            //Print The Question
            c.println ("Okay, now it is time to answer a question to see is player " + playerNum + " will be allowed their turn.");
            c.println ("Type in 1 to respond as true, or 2 to respond as false.");
            c.println ();
            c.println (promptArray [questionNumber]);
            trueOrfalse = c.getChar ();

            //Checks Response
            if (!(trueOrfalse == '1' || trueOrfalse == '2')) //Invalid Response
            {
                c.clear ();
                c.println ("Player " + playerNum + " has not answered in the correct format, and has forfeited his/her turn!");
                c.println ();

                //Swap Player Numbers
                swapPlayers ();

                playGame (); //Let's play ball!

            }
            else if (trueOrfalse == answerArray [questionNumber]) //Correct Response
            //Checks to see if the user's responce (trueOrfalse) is equal to the value in the array. Because they are both char's, we can use a ==
            {
                c.println ("Correct! Player " + playerNum + " gets to play!");
            }
            else //Incorrect Response
            {
                c.clear ();
                c.println ("Incorrect! Let us give the other player a chance.");

                //Swap Player Numbers
                swapPlayers ();

                playGame (); //Let's play ball!
            }
        }
        else //If we have used all 30 questions, there is othing else to do but this
        {
            c.clear ();
            c.println ("It appears we are out of questions, therefore it's a cat's game!");
            newGame (); //Ask For A New Game
        }
    }

    //A simple class to swap players after either an incorrect answer is given, or the player has made their move
    public static void swapPlayers ()
    {
        if (playerNum == 1)
        {
            playerNum = 2;
        }
        else
        {
            playerNum = 1;
        }
    }


    public static void newGame ()
    {

        String responce; //There is a reason for this being a string even though we are asking for an int to be entered

        //Prompt
        c.println ("Would you like to play again? Please press the 1 key and then the <Enter> key to play another round, or any other key and then the <Enter> key to end the program."); //This way, they don't skip it by accident
        responce = c.readLine (); //c.readLine allows us to not crash the program if the user presses the space bar. This helps for "Press any other key then <Enter> to end the program"
        c.clear ();

        if (responce.equals ("1"))
        {

            //Re-fill The Arrays To Prevent Spots Played In The Previous Game From Being Re-Used
            for (int i = 0 ; i < 10 ; i++)
            {
                playedSymbol [i] = (' ');
                playedSpots [i] = (' ');
            }
            for (int i = 1 ; i < 31 ; i++)
            {
                questionArray [i] = 0;
            }
            counter = 0; //Because this is now a static int, we need to re-set this when the game is done as it is not set to 0 in any other methods
            counter2 = 0; //Re-set's the round counter. If this is not done, a cat's game may be called even if it is not supposed to
            selectPlayer (); //Calls the array to re-select the player
        }
        else //Multiple options could be created here, but because it is either play again or quit, if it's not one, it's the either
        {
            System.exit (1337); //Kills the program (Not part of HSA, as it is a system command) This is used to prevent other methods from running after the user has requested the game to end
        }
    }
}
