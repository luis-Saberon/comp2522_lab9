import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A logger service that makes a logger file for the current datetime
 * @author Luis Saberon
 * @author Hailey Kim
 * @version 1.0
 */
public class LoggerService
{
    private static final String END_OF_FILE             = "_COUNTRY.txt";
    private static final Path DIR                       = Paths.get("src", "data", "logs");
    private static final DateTimeFormatter FORMATTER    = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    private final Path path;

    /**
     * Makes a logger service that names a file the current datetime_COUNTRY.txt
     */
    public LoggerService()
    {
        final LocalDateTime now;
        final String fileName;
        final Path file;

        now = LocalDateTime.now();
        fileName = now.format(FORMATTER) + END_OF_FILE;

        file = Paths.get(fileName);
        path = DIR.resolve(file);

        makeFile();
    }

    /**
     * Updates the logger file with the current time, guess, and outcome
     * @param guess String what the user guessed
     * @param outcome String what the outcome of the guess was
     */
    public void updateFile(final String guess,
                           final String outcome)
    {
        final StringBuilder builder;
        final LocalDateTime now;

        builder = new StringBuilder();
        now = LocalDateTime.now();

        builder.append(now.format(FORMATTER));
        builder.append(", ");
        builder.append(guess);
        builder.append(", ");
        builder.append(outcome);
        builder.append("\n");

        try{
            if(Files.notExists(DIR))
            {
                Files.createDirectories(DIR);
            }


            Files.write(path, builder.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        } catch (final IOException e)
        {
            e.printStackTrace();
        }

    }


    /**
     * Makes a file at the path, checking if the DIR exists and if the file already exists
     */
    private void makeFile()
    {
        try{
            if(Files.notExists(DIR))
            {
                Files.createDirectories(DIR);
            }
            if(Files.notExists(path))
            {
                Files.createFile(path);
            }
        } catch (final IOException e)
        {
            e.printStackTrace();
        }
    }
}
