# Project 2 - Midterm Assignment - Jokes
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
As stated above, I splitted the different joke methods up into their own classes, which implements an interface, and it provides a functionality called: __getJoke()__. This method has been mocked away and I'm mocking each object of the different classes (ex.: TambalJoke.java) and then I'm trying to see whether it returns as expected. These tests can be found in the __FetcherTest.java__ class.     
* Demonstrate how/where you did state-based testing and how/where you did behaviour based testing

* Explain about Coverage Criterias, using the results presented by running Jacoco (or a similar tool) against you final test code.
* Explain/demonstrate what was required to make this project use, JUnit (Hamcrest), Mockito and Jacoco
