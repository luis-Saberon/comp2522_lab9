import java.util.Scanner;

/**
 * A game class that makes users guess a country name
 * @author Luis Saberon
 * @author Hailey Kim
 */
public class Game
{
    private static final String TITLE           = "LUCKY VAULT - COUNTRY MODE. Type QUIT to exit";
    private static final WordList COUNTRIES     = new WordList();
    private static final int INITIAL_ATTEMPTS   = 0;
    private static final int INITIAL_CORRECT     = 0;

    private int attempts;
    private final String country;
    private final LoggerService logger;


    /**
     * Instantiates a random country, the logger service, and makes attempts 0
     */
    public Game()
    {
        country     = COUNTRIES.getRandomCountry().toLowerCase();
        attempts    = INITIAL_ATTEMPTS;
        logger      = new LoggerService();
    }

    /**
     * Prints the start text to the game
     */
    public void printStartText()
    {
        System.out.println(TITLE);
        System.out.println(country);
        System.out.println("Secret word length: " + country.length());
        System.out.println("Current best: " +  HighScoreHandler.getHighScore());
    }


    /**
     * Gets the input of the game, if input is empty makes them try again
     * @param scan is the Scanner used
     * @return String of the input
     */
    public String getInput(final Scanner scan)
    {
        String input;
       while(true)
       {
           System.out.println("Your guess: ");
           input = scan.nextLine();

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

    /**
     * Checks if the guess is correct by comparing it to the country
     * @param guess String guess that the user made
     * @return true if guess is correct, false otherwise
     */
    public boolean guessChecker(final String guess)
    {
        attempts++;
        System.out.println("The guess is " + guess);
        if(guess.equals(country)){
            System.out.println("Correct in " + attempts + " attempts! Word was: " + country);
            logger.updateFile(guess, "correct");
            if(HighScoreHandler.newHighScore(attempts))
            {
                System.out.println("NEW BEST for COUNTRY mode");
            }
            return true;
        }

        if(guess.length() != country.length())
        {
            System.out.println("Wrong length " + guess.length() + ". Need " + country.length());
            logger.updateFile(guess, "wrong length");
            return false;
        }

        int correctMatches;
        correctMatches = guessComparer(guess);
        System.out.println("Not it. " + correctMatches +
                           " letter(s) correct (right position)");
        logger.updateFile(guess, "matches=" + correctMatches);
        return false;
    }

    /**
     * Checks how many of the characters in the guess string are in the same spot as the
     * country.
     * @param guess String guess made by user
     * @return int how many characters are in the correct place.
     */
    private int guessComparer(final String guess)
    {
        int correctPosition = INITIAL_CORRECT;

        for(int i = 0; i < country.length(); i++)
        {
            if(country.charAt(i) == guess.charAt(i))
            {
                correctPosition++;
            }
        }
        return correctPosition;
    }
}
