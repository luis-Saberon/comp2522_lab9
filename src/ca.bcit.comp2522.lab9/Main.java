import java.util.Scanner;

public class Main
{
    private static final String EXIT_STRING = "quit";
    public static void main(String[] args)
    {
        final Game game;
        final Scanner scan;

        scan = new Scanner(System.in);
        game = new Game();

        String input;
        boolean gameWon = false;
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
