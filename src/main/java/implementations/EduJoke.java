package implementations;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.response.ExtractableResponse;
import repository.IJokeFetcher;
import testex.Joke;

public class EduJoke implements IJokeFetcher {
    @Override
    public Joke getJoke() {
        try{
            ExtractableResponse res =  given().get("http://jokes-plaul.rhcloud.com/api/joke").then().extract();
            String joke = res.path("joke");
            String reference = res.path("http://jokes-plaul.rhcloud.com/api/joke");
            return new Joke(joke,reference);
        }catch(Exception e){
            return null;
        }
    }
}
