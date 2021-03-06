package implementations;

import static com.jayway.restassured.RestAssured.*;
import repository.IJokeFetcher;
import testex.Joke;

public class ChuckNorrisJoke implements IJokeFetcher {
    @Override
    public Joke getJoke() {
        try{
            String joke  = given().get("http://api.icndb.com/jokes/random").path("value.joke");
            return new Joke(joke,"http://api.icndb.com/");
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
