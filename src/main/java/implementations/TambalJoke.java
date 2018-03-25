package implementations;

import static com.jayway.restassured.RestAssured.*;
import repository.IJokeFetcher;
import testex.Joke;

public class TambalJoke implements IJokeFetcher {
    @Override
    public Joke getJoke() {
        try{
            //API does not set response type to JSON, so we have to force the response to read as so
            String joke = given().get("http://tambal.azurewebsites.net/joke/random").andReturn().jsonPath().getString("joke");
            return new Joke(joke,"http://tambal.azurewebsites.net/joke/random");
        }catch(Exception e){
            return null;
        }
    }
}
