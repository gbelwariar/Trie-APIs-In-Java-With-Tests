# Trie APIs in Java with Tests
A collection of APIs related to Trie Data Structure, implemented in Java and backed by strong tests and a sample problem that uses the trie by injecting the Trie Service in it through [Google Guice Framework](https://github.com/google/guice) (Dependency Injection Framework).

## Frameworks/Libraries Used - 

1) [Google Guice Framework](https://github.com/google/guice) - Google's Dependency Injection Framework.
2) [Google Guava Library](https://github.com/google/guava) - The project uses annotations like - [@VisibleForTesting](https://github.com/google/guava/blob/master/guava/src/com/google/common/annotations/VisibleForTesting.java) to give the tests access to the instance variables and methods. 
3) [Hamcrest Framework](http://hamcrest.org/) - To use the different Matchers available inside [TrieTest](https://github.com/gbelwariar/Trie-APIs-In-Java-With-Tests/blob/master/TrieTest.java).
4) [EasyMock Framework](http://easymock.org/) - To mock the Trie Service in order to test the solution to our sample problem in isolation.
5) [JUnit Testing Framework](http://junit.org/junit4/) - To test the Trie APIs.

## How to use the APIs - 

The ideal way to use the APIs would be to inject the [Trie](https://github.com/gbelwariar/Trie-APIs-In-Java-With-Tests/blob/master/Trie.java) service inside the class where it is intended to be used, using Dependency Injection, like- using [Google Guice Framework](https://github.com/google/guice). However, the Trie object can also be constructed in a tighly-coupled fashion by the constructor call as well (thus losing all the benefits mentioned [here](https://github.com/google/guice/wiki/Motivation)). To make the  test of the class using the Trie Service isolated from it, one should use a mocking framework like - [Mockito](http://site.mockito.org/) or [EasyMock](http://easymock.org/) (This project uses EasyMock).  
