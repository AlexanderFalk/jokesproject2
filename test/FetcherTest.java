import implementations.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import repository.IFetcher;
import repository.IJokeFetcher;
import testex.Joke;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FetcherTest {


    @Mock
    private EduJoke eduJoke;

    @Mock
    private MomJoke momJoke;

    @Mock
    private ChuckNorrisJoke chuckNorrisJoke;

    @Mock
    private TambalJoke tambalJoke;

    @Mock
    List<IJokeFetcher> mockData;

    private IFetcher fetcher;

    @Before
    public void init() {
        fetcher = new Fetcher();

    }

    @Test
    public void testGetJokes() {
        System.out.println("##### START GETJOKES TESTING #####\n");
        List<IJokeFetcher> data = fetcher.getJokes("eduprog,chucknorris,mom,tambal");

        assertThat(data.get(0), instanceOf(EduJoke.class));
        //System.out.println(data.get(0).getJoke().getJoke());
        assertThat(data.get(1), instanceOf(ChuckNorrisJoke.class));
        System.out.println("Chuck Norris Joke: " + data.get(1).getJoke().getJoke());
        assertThat(data.get(2), instanceOf(MomJoke.class));
        System.out.println("Yo Momma Joke: " + data.get(2).getJoke().getJoke());
        assertThat(data.get(3), instanceOf(TambalJoke.class));
        //System.out.println(data.get(3).getJoke().getJoke());

        assertThat(data, hasSize(4));
    }

    @Test
    public void testGetJokesFail() {
        System.out.println("##### START TESTGETJOKES TESTING #####");
        List<IJokeFetcher> data = fetcher.getJokes("chucknorris,eduprog,mom,tambal");

        assertThat(data.get(0), not(EduJoke.class));
        assertThat(data.get(1), not(ChuckNorrisJoke.class));
        assertThat(data.get(2), instanceOf(MomJoke.class));
        assertThat(data.get(3), instanceOf(TambalJoke.class));
    }

    @Test
    public void testAvailableTypes() {
        System.out.println("##### START TESTAVAILABLETYPES TESTING #####");
        List<String> data = new ArrayList<>();
        data.add("EduJoke");
        data.add("ChuckNorris");
        data.add("Mom");
        data.add("Tambal");

        assertThat(data, containsInAnyOrder(fetcher.getAvailableTypes().toArray(new String[data.size()])));
    }

    // **MOCKING WILL BE BELOW HERE - PLEASE CHECK IT OUT, BECAUSE IT IS DAMN COOL ;-)**
    /**********************************************************************************/

    @Test
    public void testGetEduJokesMocking() {
        System.out.println("##### START TESTGETEDUJOKESMOCKING TESTING #####");
        String eJoke = "Mathematicians are like Frenchmen: whatever you say to them, they translate it " +
                "into their own language, and forthwith it means something entirely different.";
        Mockito.when(eduJoke.getJoke()).thenReturn(new Joke(eJoke, "http://jokes-plaul.rhcloud.com/api/joke"));
        assertThat(eduJoke.getJoke().getReference(), equalTo("http://jokes-plaul.rhcloud.com/api/joke"));
        mockData.add(eduJoke);
        Mockito.verify(mockData).add(eduJoke);

        Mockito.when(mockData.size()).thenReturn(1);
        assertThat(mockData.size(), equalTo(1));
    }

    @Test
    public void testGetChuckNorrisJokesMocking() {
        System.out.println("##### START TESTGETCHUCKNORRISJOKESMOCKING TESTING #####");
        String cnJoke = "In the first Jurassic Park movie, the Tyrannosaurus Rex wasn't chasing the jeep. " +
                "Chuck Norris was chasing the Tyrannosaurus AND the jeep.";

        Mockito.when(chuckNorrisJoke.getJoke()).thenReturn(new Joke(cnJoke, "http://api.icndb.com/"));
        assertThat(chuckNorrisJoke.getJoke().getReference(), equalTo("http://api.icndb.com/"));
        mockData.add(chuckNorrisJoke);
        Mockito.verify(mockData).add(chuckNorrisJoke);
        Mockito.when(mockData.size()).thenReturn(1);
        assertThat(mockData.size(), equalTo(1));
    }

    @Test
    public void testGetMomJokesMocking() {
        System.out.println("##### START TESTGETMOMJOKESMOCKING TESTING #####");
        String mJoke = "Yo momma so fat when she plays hop scotch she goes Chicago, New York, L.A.";

        Mockito.when(momJoke.getJoke()).thenReturn(new Joke(mJoke, "http://api.yomomma.info/"));
        mockData.add(momJoke);
        assertThat(momJoke.getJoke().getReference(), equalTo("http://api.yomomma.info/"));
        Mockito.verify(mockData).add(momJoke);
        Mockito.when(mockData.size()).thenReturn(1);
        assertThat(mockData.size(), equalTo(1));
    }

    @Test
    public void testGetTambalJokesMocking() {
        System.out.println("##### START TESTGETTAMBALJOKESMOCKING TESTING #####");
        String tJoke = "Dati ang gamot sa sakit na LOVEnat ay KISSpirin at YAKAPsul ngayon hindi na... " +
                "biogeSEX na pwedeng gawin kahit walang laman ang tyan dahil magkakalaman din yan..oh pano? INGAT!";

        Mockito.when(tambalJoke.getJoke()).thenReturn(new Joke(tJoke, "http://tambal.azurewebsites.net/joke/random"));
        mockData.add(tambalJoke);
        assertThat(tambalJoke.getJoke().getReference(), equalTo("http://tambal.azurewebsites.net/joke/random"));
        Mockito.verify(mockData).add(tambalJoke);
        Mockito.when(mockData.size()).thenReturn(1);
        assertThat(mockData.size(), equalTo(1));
    }

    @Test
    public void testListSize() {
        System.out.println("##### START TESTLISTSIZE TESTING #####");
        fetcher = Mockito.mock(Fetcher.class);
        Mockito.when(fetcher.getAvailableTypes()).thenReturn(Arrays.asList("EduJoke", "ChuckNorris", "Mom", "Tambal"));
        assertThat(fetcher.getAvailableTypes().size(), equalTo(4));
    }
}
