import java.util.Scanner;

public class Game
{
    private static final String TITLE       = "LUCKY VAULT - COUNTRY MODE. Type QUIT to exit";
    private static final String EXIT_STRING = "QUIT";

    public static void main(final String[] args)
    {
        //make a country list object
        final Scanner scan;
        String input;
        //get random country from country list


        System.out.println(TITLE);
        System.out.println("Secret word length: " + 0);
        System.out.println("Current best: 0 attempts");
        scan = new Scanner(System.in);

        do{
            input =  scan.nextLine();

        } while(!input.equals(EXIT_STRING));
    }
}
