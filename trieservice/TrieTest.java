/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trieservice;

import java.util.Arrays;
import java.util.LinkedList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author BELWARIAR
 */
public class TrieTest {
     
    private Trie trie = new Trie();
    
    @Before
    public void setUp() {
        trie = new Trie();
    }
    
    @Test
    public void shouldReturnNonNullTrieRoot() {
        assertThat(trie.getTrieRoot(), is(notNullValue()));
    }

    @Test
    public void shouldAddWord_inEmptyTrie() {
        Trie.TrieNode expectedRoot = new Trie.TrieNode();
        Trie.TrieNode child1 = new Trie.TrieNode('R', false);
        Trie.TrieNode child2 = new Trie.TrieNode('O', false);
        Trie.TrieNode child3 = new Trie.TrieNode('C', false);
        Trie.TrieNode child4 = new Trie.TrieNode('K', true);
        expectedRoot.children = new LinkedList<>(Arrays.asList(child1));
        child1.children = new LinkedList<>(Arrays.asList(child2));
        child2.children = new LinkedList<>(Arrays.asList(child3));
        child3.children = new LinkedList<>(Arrays.asList(child4));
        trie.addWord("ROCK");
        assertThat(trie.getTrieRoot(), is(equalTo(expectedRoot)));
    }
    
    @Test
    public void shouldAddANewSubWordOfAWordExistingAlreadyInTrie() {
        Trie.TrieNode expectedRoot = new Trie.TrieNode();
        Trie.TrieNode child1 = new Trie.TrieNode('R', false);
        Trie.TrieNode child2 = new Trie.TrieNode('O', true);
        Trie.TrieNode child3 = new Trie.TrieNode('C', false);
        Trie.TrieNode child4 = new Trie.TrieNode('K', true);
        expectedRoot.children = new LinkedList<>(Arrays.asList(child1));
        child1.children = new LinkedList<>(Arrays.asList(child2));
        child2.children = new LinkedList<>(Arrays.asList(child3));
        child3.children = new LinkedList<>(Arrays.asList(child4));
        trie.addWord("ROCK");
        trie.addWord("RO");
        assertThat(trie.getTrieRoot(), is(equalTo(expectedRoot)));
    }
    
    @Test
    public void shouldAddANewSuperWordOfAWordExistingAlreadyInTrie() {
        Trie.TrieNode expectedRoot = new Trie.TrieNode();
        Trie.TrieNode child1 = new Trie.TrieNode('R', false);
        Trie.TrieNode child2 = new Trie.TrieNode('O', false);
        Trie.TrieNode child3 = new Trie.TrieNode('C', false);
        Trie.TrieNode child4 = new Trie.TrieNode('K', true);
        Trie.TrieNode child5 = new Trie.TrieNode('E', false);
        Trie.TrieNode child6 = new Trie.TrieNode('T', true);
        expectedRoot.children = new LinkedList<>(Arrays.asList(child1));
        child1.children = new LinkedList<>(Arrays.asList(child2));
        child2.children = new LinkedList<>(Arrays.asList(child3));
        child3.children = new LinkedList<>(Arrays.asList(child4));
        child4.children = new LinkedList<>(Arrays.asList(child5));
        child5.children = new LinkedList<>(Arrays.asList(child6));
        trie.addWord("ROCK");
        trie.addWord("ROCKET");
        assertThat(trie.getTrieRoot(), is(equalTo(expectedRoot)));
    }
    
    @Test
    public void shouldAddANewWordBranchingFromAWordExistingAlreadyInTrie() {
        Trie.TrieNode expectedRoot = new Trie.TrieNode();
        Trie.TrieNode child1 = new Trie.TrieNode('R', false);
        Trie.TrieNode child2 = new Trie.TrieNode('O', false);
        Trie.TrieNode child3 = new Trie.TrieNode('C', false);
        Trie.TrieNode child4 = new Trie.TrieNode('K', true);
        Trie.TrieNode child5 = new Trie.TrieNode('A', false);
        Trie.TrieNode child6 = new Trie.TrieNode('D', true);
        expectedRoot.children = Arrays.asList(child1);
        child1.children = Arrays.asList(child2);
        child2.children = Arrays.asList(child3, child5);
        child3.children = Arrays.asList(child4);
        child5.children = Arrays.asList(child6);
        trie.addWord("ROCK");
        trie.addWord("ROAD");
        assertThat(trie.getTrieRoot(), is(equalTo(expectedRoot)));
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldGetWord_insertingNullWord() {
        trie.addWord(null);
    }
    
    @Test
    public void shouldAddWords_nominal() {
        Trie.TrieNode expectedRoot = new Trie.TrieNode();
        Trie.TrieNode child1 = new Trie.TrieNode('R', false);
        Trie.TrieNode child2 = new Trie.TrieNode('O', false);
        Trie.TrieNode child3 = new Trie.TrieNode('A', false);
        Trie.TrieNode child4 = new Trie.TrieNode('D', true);
        Trie.TrieNode child5 = new Trie.TrieNode('C', false);
        Trie.TrieNode child6 = new Trie.TrieNode('K', true);
        Trie.TrieNode child7 = new Trie.TrieNode('A', false);
        Trie.TrieNode child8 = new Trie.TrieNode('P', false);
        Trie.TrieNode child9 = new Trie.TrieNode('I', true);
        Trie.TrieNode child10 = new Trie.TrieNode('E', true);
        Trie.TrieNode child11 = new Trie.TrieNode('X', true);
        Trie.TrieNode child12 = new Trie.TrieNode('D', true);
        Trie.TrieNode child13 = new Trie.TrieNode('D', true);
        expectedRoot.children = Arrays.asList(child1, child7);
        child1.children = Arrays.asList(child2);
        child2.children = Arrays.asList(child3, child5);
        child3.children = Arrays.asList(child4);
        child5.children = Arrays.asList(child6);
        child7.children = Arrays.asList(child8, child12);
        child8.children = Arrays.asList(child9, child10);
        child10.children = Arrays.asList(child11);
        child12.children = Arrays.asList(child13);
        trie.addWords("ROAD", "API", "ROCK", "APEX", "AD", "APE", "ADD");
        assertThat(trie.getTrieRoot(), is(equalTo(expectedRoot)));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldAddWords_nullList() {
        trie.addWords(null);
    }
    
    @Test
    public void shouldGetAllWords_nominal() {
        Trie.TrieNode child1 = new Trie.TrieNode('R', false);
        Trie.TrieNode child2 = new Trie.TrieNode('O', false);
        Trie.TrieNode child3 = new Trie.TrieNode('A', false);
        Trie.TrieNode child4 = new Trie.TrieNode('D', true);
        Trie.TrieNode child5 = new Trie.TrieNode('C', false);
        Trie.TrieNode child6 = new Trie.TrieNode('K', true);
        Trie.TrieNode child7 = new Trie.TrieNode('A', false);
        Trie.TrieNode child8 = new Trie.TrieNode('P', false);
        Trie.TrieNode child9 = new Trie.TrieNode('I', true);
        Trie.TrieNode child10 = new Trie.TrieNode('E', true);
        Trie.TrieNode child11 = new Trie.TrieNode('X', true);
        Trie.TrieNode child12 = new Trie.TrieNode('D', true);
        Trie.TrieNode child13 = new Trie.TrieNode('D', true);
        trie.root.children = Arrays.asList(child1, child7);
        child1.children = Arrays.asList(child2);
        child2.children = Arrays.asList(child3, child5);
        child3.children = Arrays.asList(child4);
        child5.children = Arrays.asList(child6);
        child7.children = Arrays.asList(child8, child12);
        child8.children = Arrays.asList(child9, child10);
        child10.children = Arrays.asList(child11);
        child12.children = Arrays.asList(child13);
        assertThat(
            trie.getAllWords(),
            contains(
                "ROAD", "ROCK", "API", "APE", "APEX", "AD", "ADD"));
    }
    
    @Test
    public void shouldGetAllWords_fromEmptyTrie() {
        assertThat(trie.getAllWords().size(), is(equalTo(0)));
    }
    
    @Test
    public void shouldCheckIfWordExistsInTrie_nominal() {
        Trie.TrieNode child1 = new Trie.TrieNode('R', false);
        Trie.TrieNode child2 = new Trie.TrieNode('O', false);
        Trie.TrieNode child3 = new Trie.TrieNode('C', false);
        Trie.TrieNode child4 = new Trie.TrieNode('K', true);
        Trie.TrieNode child5 = new Trie.TrieNode('A', false);
        Trie.TrieNode child6 = new Trie.TrieNode('D', true);
        trie.root.children = Arrays.asList(child1);
        child1.children = Arrays.asList(child2);
        child2.children = Arrays.asList(child3, child5);
        child3.children = Arrays.asList(child4);
        child5.children = Arrays.asList(child6);
        assertThat(trie.isWordPresent("ROAD"), is(equalTo(true)));
        assertThat(trie.isWordPresent("ROA"), is(equalTo(false)));
        assertThat(trie.isWordPresent("XYZ"), is(equalTo(false)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCheckIfWordExistsInTrie_nullWord() {
        trie.isWordPresent(null);
    }
    
    @Test
    public void shouldCheckIfWordExistsInTrie_emptyTrie() {
        assertThat(trie.isWordPresent("ABC"), is(equalTo(false)));
    }
}
