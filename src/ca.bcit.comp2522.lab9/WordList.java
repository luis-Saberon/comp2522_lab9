import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a collection of country names loaded from a file.
 *
 * @author Hailey Kim
 * @author Luis Saberon
 * @version 1.0
 */
public class WordList
{
    private static final Path FILE  = Paths.get("countries.txt");
    private static final Path DIR   = Paths.get("src", "data");
    private static final Path PATH  = DIR.resolve(FILE);

    private final List<String> countries;
    private final Random random;

    /**
     * Constructs a WordList
     */
    public WordList()
    {
        this.random    = new Random();
        this.countries = loadCountries();
    }

    /**
     * Reads lines from the file at the given path, trims whitespace,
     * and adds non-empty lines to a list of countries.
     *
     * @return a list of strings containing the loaded country names
     */
    private List<String> loadCountries()
    {
        try
        {
            List<String> lines;
            final List<String> loadedCountries;

            lines           = Files.readAllLines(PATH, StandardCharsets.UTF_8);
            loadedCountries = new ArrayList<>();

            for (String line : lines)
            {
                final String trimmedLine;
                trimmedLine = line.trim();

                if (!trimmedLine.isEmpty())
                {
                    loadedCountries.add(trimmedLine);
                }
            }
            return loadedCountries;

        }

        catch (final IOException e)
        {
            System.err.println("Error reading country list file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Returns a random country name from the loaded list.
     *
     * @return a random country name from the list, or null if the list is empty
     */
    public String getRandomCountry()
    {
        if (countries.isEmpty())
        {
            System.err.println("Country list is empty.");
            return null;
        }
        return countries.get(random.nextInt(countries.size()));
    }
}