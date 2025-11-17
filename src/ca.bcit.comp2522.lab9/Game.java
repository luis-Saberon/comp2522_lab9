import java.util.Scanner;

public class Game
{
    // TO DO
    // CHANGE THE COUNTRY NAME TO USE COUNTRY LIST
    // ADD FUNCTIONALITY FOR ATTEMPT FILE READING AND WRITING
    private static final String TITLE       = "LUCKY VAULT - COUNTRY MODE. Type QUIT to exit";
    private static final String EXIT_STRING = "quit";
    private static int attempts             = 0;
//    private static final int bestAttempt; get the best attempt from different object

    public static void main(final String[] args)
    {
        //make a country list object
        //for now, country name is Canada
        final String countryName;
        final Scanner scan;
        String input;
        boolean gameWon;
        //get random country from country list
        scan = new Scanner(System.in);
        countryName = "canada";
        gameWon = false;

        System.out.println(TITLE);
        System.out.println("Secret word length: " + countryName.length());
        System.out.println("Current best: 0 attempts");

        do{
            input = getInput(scan);
            if(!input.equals(EXIT_STRING))
            {
                gameWon = guessChecker(input, countryName);
            }
        } while(!input.equals(EXIT_STRING) && !gameWon);
    }

    private static String getInput(final Scanner scan)
    {
        String input;
       while(true)
       {
           System.out.println("Your guess: ");
           input = scan.next();

           if(input.isBlank())
           {
               System.out.println("Empty guess, try again");
           }
           else
           {
               return input.trim().toLowerCase();
           }
       }
    }

    private static boolean guessChecker(final String guess,
                                     final String countryName)
    {
        attempts++;
        if(guess.equals(countryName)){
            System.out.println("Correct in " + attempts + " attempts! Word was: " + countryName);
            return true;
        }

        if(guess.length() != countryName.length())
        {
            System.out.println("Wrong length " + guess.length() + ". Need " + countryName.length());
            return false;
        }

        System.out.println("Not it. " + guessComparer(guess, countryName) +
                           " letter(s) correct (right position)");
        return false;
    }

    private static int guessComparer(final String guess,
                                     final String countryName)
    {
        int correctPosition = 0;

        for(int i = 0; i < countryName.length(); i++)
        {
            if(countryName.charAt(i) == guess.charAt(i))
            {
                correctPosition++;
            }
        }
        return correctPosition;
    }
}
