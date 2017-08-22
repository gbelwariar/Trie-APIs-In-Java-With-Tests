# Trie APIs in Java with Tests
A collection of APIs related to Trie Data Structure, implemented in Java and backed by strong tests.

## Frameworks Used - 

1) Hamcrest Framework - To use the different Matchers available inside [TrieTest](https://github.com/gbelwariar/Trie-APIs-In-Java-With-Tests/blob/master/TrieTest.java).
2) JUnit Testing Framework - To test the Trie APIs.

## How to use the APIs- 

The ideal way to use the APIs would be to inject the [Trie](https://github.com/gbelwariar/Trie-APIs-In-Java-With-Tests/blob/master/Trie.java) service inside the class where it is intended to be used, using Dependency Injection, like- using [Google Guice Framework](https://github.com/google/guice). However, the Trie object can also be constructed in a tighly-coupled fashion by the constructor call as well (thus losing all the benefits mentioned [here](https://github.com/google/guice/wiki/Motivation)).  
