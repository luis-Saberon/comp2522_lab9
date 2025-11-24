import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Abstract class to handle high scores.
 */
abstract class HighScoreHandler
{
    private static final String HIGHSCORE_STRING    = "COUNTRY=";
    private static final Path FILE                  = Paths.get( "highscores.txt");
    private static final Path DIR                   = Paths.get("src", "data");
    private static final Path PATH                  = DIR.resolve(FILE);
    private static final int DEFAULT_SCORE          = 0;

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
        if(currentHighScore == DEFAULT_SCORE)
        {
            updateHighScore(newScore);
            return true;
        }
        if(newScore < currentHighScore)
        {
            updateHighScore(newScore);
            return true;
        }
        return false;
    }


    /**
     * Writes to the highscore file with the new highscore
     * @param newScore is the newest high score.
     */
    private static void updateHighScore(final int newScore)
    {
        final String content;

        content = HIGHSCORE_STRING + newScore;
        try{
            if(Files.notExists(DIR))
            {
                Files.createDirectories(DIR);
            }

            try(final BufferedWriter writer =
                    Files.newBufferedWriter(PATH, StandardCharsets.UTF_8))
            {
                writer.write(content);
            }
        } catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Gets the highscore from the designated file. If it's empty or not in the format of
     * COUNTRY=x, return highscore of 0. Else, return the highscore
     * @return int highscore
     */
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
                return DEFAULT_SCORE;
            }

            try
            {
                highScore = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                return highScore;
            }
            catch(final NumberFormatException e)
            {
                return DEFAULT_SCORE;
            }
        } catch(final IOException e)
        {
            e.printStackTrace();
        }
        return DEFAULT_SCORE;
    }

    /**
     * Checks if highscore file exists, if it does not, creates it
     */
    private static void checkHighscoreFile()
    {
        try{
            if(Files.notExists(DIR))
            {
                Files.createDirectories(DIR);
            }

            if(Files.notExists(PATH))
            {
                Files.createFile(PATH);
            }
        } catch (final IOException e)
        {
            e.printStackTrace();
        }

    }
}
