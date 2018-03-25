package repository;

import testex.Joke;
import testex.Jokes;

import java.util.List;

/**
 * Factory
 */
public interface IFetcher {
    List<String> getAvailableTypes();
    List<IJokeFetcher> getJokes(String jokes);
}
