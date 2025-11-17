import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HighScoreHandler
{
    private static final String HIGHSCORE_STRING    = "COUNTRY=";
    private static final Path FILE                  = Paths.get( "highscores.txt");
    private static final Path DIR                   = Paths.get("src", "data");
    private static final Path PATH                  = DIR.resolve(FILE);
    public static void main(String[] args) throws IOException
    {
        System.out.println(getHighScore());
    }

    /**
     * Checks if the newscore is higher than the current highscore, if so, updates the
     * high score
     * @param newScore int the new score to check against the highscore
     * @return true if the new score beats the highscore, false if not.
     */
    public static boolean newHighScore(final int newScore)
    {
        final int currentHighScore;

        currentHighScore = getHighScore();
        if(newScore > currentHighScore)
        {
            updateHighScore(newScore);
            return true;
        }
        return false;
    }

    public static void updateHighScore(final int newScore)
    {

    }
    public static int getHighScore()
    {
        checkHighscoreFile();

        try(BufferedReader lines = Files.newBufferedReader(PATH))
        {
            final String line;
            final int highScore;
            line = lines.readLine();
            if(!line.startsWith(HIGHSCORE_STRING))
            {
                return 0;
            }

            try
            {
                highScore = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                return highScore;
            }
            catch(final NumberFormatException e)
            {
                return 0;
            }
        } catch(final IOException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Checks if highscore file exists, if it does not, creates it
     * @throws IOException if something goes wrong in the making of the directories and file
     */
    private static void checkHighscoreFile()
    {
        //this is probably pretty ugly, but idk how to make it better
        if (Files.notExists(PATH))
        {
            if (Files.notExists(DIR))
            {
                try
                {
                    Files.createDirectories(DIR);
                } catch(final IOException e)
                {
                    e.printStackTrace();
                }
            }
            try
            {
                Files.createFile(PATH);
            } catch (final IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
