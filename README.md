# Project 2 - Midterm Assignment - Jokes
* Explain the necessary steps you did to make the code testable, and some of the patterns involved in this step  
  
To make this code testable I had to take the advantage of the Factory Pattern, which gives us the flexible and the reuseable object-oriendted software. This means that objects are easier to reuse, test, implement, or change.  
The **JokeFetcher.java** method has been splitted heaviliy up and is now called **Fetcher.java**. Each method that requests for a joke API has been made into its own class. These classes have a shared interface, which is called: IJokeFetcher. The interface is very small and only consists of 1 method: __getJoke()__, which returns a Joke-object. The 4 different classes, with the 4 different jokes, implement their own implementation of how they want to get their API. The only thing that is required is: return a Joke-object.  
The **Fetcher.java** class also implement an interface (IFetcher.java), which consist of two methods: __getvAvailableTypes()__ & __getJokes()__, which both returns a list of strings. The __getJokes()__ method has also been revamped, so you are in charge of putting the different tokens as a parameter splitted by comma, and then it will return each of the 4 new joke classes, dependent of what input you give it. Then it returns all the jokes it found. The TimezoneString has been removed.  
Then I rewrote the DateFormatter.java class as well. I made it more managable with dependency injection. An extra parameter (Date-object) was added and the method was shrunk for the better. Now each client that wants to use the DateFormatter class is in charge of the Date-object and timezone themselves.  


* Explain basically about JUnit, Hamcrest, Mockito and Jacoco, and what problems they solve for testers  
  
  **JUnit**  
  **Hamcrest**  
  **Mockito**  
  **Jacoco**  
  
* Demonstrate how you used Mockito to mock away external Dependencies
* Demonstrate how/where you did state-based testing and how/where you did behaviour based testing

* Explain about Coverage Criterias, using the results presented by running Jacoco (or a similar tool) against you final test code.
* Explain/demonstrate what was required to make this project use, JUnit (Hamcrest), Mockito and Jacoco
