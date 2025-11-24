import java.util.Scanner;

/**
 * Runs the game class
 */
public class Main
{
    private static final String EXIT_STRING = "quit";

    public static void main(String[] args)
    {
        final Game game;
        final Scanner scan;
        boolean gameWon = false;
        String input;

        scan = new Scanner(System.in);
        game = new Game();

        game.printStartText();

        do{
            input = game.getInput(scan);
            if(!input.equals(EXIT_STRING))
            {
                gameWon = game.guessChecker(input);
            }
        } while(!input.equals(EXIT_STRING) && !gameWon);
    }
}
