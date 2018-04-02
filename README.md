# Project 2 - Midterm Assignment - Jokes

**PLEASE BE AWARE THAT TAMBAL & EDU JOKES ARE NO LONGER WORKING. THE APIS ARE NO LONGER AVAILABLE**

* Explain the necessary steps you did to make the code testable, and some of the patterns involved in this step  
  
To make this code testable I had to take the advantage of the Factory Pattern, which gives us the flexible and the reuseable object-oriendted software. This means that objects are easier to reuse, test, implement, or change.  
The **JokeFetcher.java** method has been splitted heaviliy up and is now called **Fetcher.java**. Each method that requests for a joke API has been made into its own class. These classes have a shared interface, which is called: IJokeFetcher. The interface is very small and only consists of 1 method: __getJoke()__, which returns a Joke-object. The 4 different classes, with the 4 different jokes, implement their own implementation of how they want to get their API. The only thing that is required is: return a Joke-object.  
The **Fetcher.java** class also implement an interface (IFetcher.java), which consist of two methods: __getvAvailableTypes()__ & __getJokes()__, which both returns a list of strings. The __getJokes()__ method has also been revamped, so you are in charge of putting the different tokens as a parameter splitted by comma, and then it will return each of the 4 new joke classes, dependent of what input you give it. Then it returns all the jokes it found. The TimezoneString has been removed.  
Then I rewrote the DateFormatter.java class as well. I made it more managable with dependency injection. An extra parameter (Date-object) was added and the method was shrunk for the better. Now each client that wants to use the DateFormatter class is in charge of the Date-object and timezone themselves.  


* Explain basically about JUnit, Hamcrest, Mockito and Jacoco, and what problems they solve for testers  
  
  **JUnit**  
  JUnit is a testing framework for Java, which helps the developer executing the theory behind Unit testing. JUnit emphasizes the idea of "test first, then code", which can help to reveal bugs before they become critical.  
  
  **Hamcrest**  
  Hamcrest is a framework for writing matcher objects allowing 'match' rules to be defined declaratively. There are a number of situations where matchers are invaluable, such as UI validation, or data filtering, but it is in the area of writing flexible tests that matchers are most commonly used. Hamcrest gives more freedom to the developer and it also helps testing more specific units, such as the content of a list and if certain elements exists.  
  
  **Mockito**  
  Mockito is a popular mock framework which can be used in conjunction with JUnit. Mockito allows you to create and configure mock objects. Using Mockito simplifies the development of tests for classes with external dependencies significantly. The usage of this is helpful, when you want to test a certain functionality, but that functionality might be bound to other functionalities, which dependent on the outcome, so we want to "mock" the object to believe it executed a functionality, but it really didn't. A side note to mocking: don't mock everything.  
  
  **Jacoco**  
  JaCoCo is an open-source toolkit for measuring and reporting Java code coverage.  
    
  These tools help the developer by providing a lot of useful functionality to test whether certain units works as intended; if things are inserted correctly and objects are constructed as expected; a functionality returns the correct value(s); and to help reveal bugs early in the phase of development. This is very important to keep the cost down throughout the whole project. By during testing first and checking different ways of interacting with the program, we can keep a good development speed going, because we know we satisfy the requirements.   
    
* Demonstrate how you used Mockito to mock away external Dependencies  
As stated above, I splitted the different joke methods up into their own classes, which implements an interface, and it provides a functionality called: __getJoke()__. This method has been mocked away and I'm mocking each object of the different classes (ex.: TambalJoke.java) and then simulating the real object to see whether it returns as expected. These tests can be found in the __FetcherTest.java__ class.  
The TambalJoke.java class has been mocked as shown below:  
```
    @Mock
    private TambalJoke tambalJoke;
```
...  
```
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
```
* Demonstrate how/where you did state-based testing and how/where you did behaviour based testing  

I used State-based testing in those tests, where there have been no mocks used. As an example is the __testGetJokes()__, which just tests whether the jokes is getting added to the list of jokes. This changes the state of the new list, that we create under test.  
```
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
```

The list will get its state changed, if everything is working as intended.  
  
The behaviour-based testing is testing with mocks, where we check if the mocked object made the correct calls. We do this check by telling the mock what to expect during setup and asking the mock to verify itself during verification. We have demonstrated this above with the __TambalJoke__ testing class, which shows the object denoted with @Mock, and then the test itself, which takes the functionality being tested and telling it that: "When you are getting called; I want you to return this". Then we verify it and if it goes through, then we give it a thumbs up and smile :).  

* Explain about Coverage Criterias, using the results presented by running Jacoco (or a similar tool) against you final test code.
I must admit here, that I couldn't get the JaCoCo to work with my project. I may have done something wrong, but it wouldn't create the report as I expected it to do. Though the Coverage Criterias should be < 80%, this would've been fullfilled, if it wasn't for two API's being down. This has been stated with capital, bold letters at the top of this document.  

* Explain/demonstrate what was required to make this project use, JUnit (Hamcrest), Mockito and Jacoco
To make this project use all the above tools, I had to nearly re-develop the whole project, since it was lousy built for testing. It was tightly coupled and I couldn't check the different jokes APIs directly. A lot of things were moved to its own classes and interfaces. Some methods were made more transparent and dependency injection was permitted. As an example were the __DateFormatter__ class, which had the method: __getFormattedDate__. This method had only one parameter, which was a timezone that you could enter. Here was a Date-object added as a parameter, so the user of the class was in control of the Date object themselves.  
JUnit, Mockito, and Hamcrest were all already added as dependencies in Maven, so these were easy to get going with. JaCoCo had to be added as a dependency / plugin and the some settigns had to be added to the pom.xml maven file. This didn't work and I hadn't been able to figure out why. The documentation for JaCoCo is not useful in any manner.
