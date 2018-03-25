package implementations;

import repository.IFetcher;
import repository.IJokeFetcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

/**
 * General class to fetch API calls
 * @author Alexander Falk
 */
public class Fetcher implements IFetcher {

    private final List<String> availableTypes
            = Arrays.asList("EduJoke", "ChuckNorris", "Mom", "Tambal");

    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    @Override
    public List<IJokeFetcher> getJokes(String jokes) {
        String[] tokens = jokes.split(",");
        List<IJokeFetcher> getJokes = new ArrayList<>();
        for(String token : tokens){
            switch(token){
                case "eduprog" :
                    getJokes.add(new EduJoke());
                    break;
                case "chucknorris" :
                    getJokes.add(new ChuckNorrisJoke());
                    break;
                case "mom" :
                    getJokes.add(new MomJoke());
                    break;
                case "tambal" :
                    getJokes.add(new TambalJoke());
                default:
                    System.out.println("You've entered something incorrect. Please try again... ;-)");
            }
        }

        return getJokes;
    }
}
