package problem;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.not;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reportMatcher;
import static org.easymock.EasyMock.verify;
import org.easymock.IArgumentMatcher;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import service.Trie;

/**
 *
 * @author BELWARIAR
 */
public class ProblemTest {
    
    private Trie trie;
    private Problem problem;
    
    @Before
    public void setUp() {
        trie = createMock(Trie.class);
        problem = new Problem(trie);
    }

    private static String isWordPresentMatcher(List<String> dictionary) {
        reportMatcher(
            new IArgumentMatcher() {
                @Override
                public boolean matches(Object argument) {
                    if (argument instanceof String) {
                        String strArgument = (String) argument;
                        return dictionary.contains(strArgument);
                    }
                    return false;
                }
                
                @Override
                public void appendTo(StringBuffer buffer) {
                    buffer.append("Using trieWordSearchMatcher");
                }
            });
        return null;
    } 
    
    @Test
    public void
    shouldGetUnconcatenatedDocumentWithMinimumUnrecognizedCharacters_nominal() {
        String document = "jesslookedjustliketimherbrother";
        List<String> dictionary =
            Arrays.asList(
                "looked",
                "look",
                "her",
                "broth",
                "her",
                "herb",
                "brother",
                "just",
                "like");
        // Recording phase.
        trie.addWords(dictionary.toArray(new String[dictionary.size()]));
        expect(trie.isWordPresent(isWordPresentMatcher(dictionary)))
            .andReturn(true).anyTimes();
        expect(trie.isWordPresent(not(isWordPresentMatcher(dictionary))))
            .andReturn(false).anyTimes();
        // Replay mock's methods.
        replay(trie);
        assertThat(
            problem.getUnconcatenatedDocumentWithMinimumUnrecognizedCharacters(
                document, dictionary),
            is(equalTo("jess looked just like tim her brother")));
        // Verify the above methods of "trie" mock.
        verify(trie);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldGetUnconcatenatedDocumentWithMinimumUnrecognizedCharacters_invalidDocument() {
        problem.getUnconcatenatedDocumentWithMinimumUnrecognizedCharacters(
            new String(), new LinkedList<>());
    }
        
    @Test(expected = IllegalArgumentException.class)
    public void shouldGetUnconcatenatedDocumentWithMinimumUnrecognizedCharacters_undefinedDictionary() {
        problem.getUnconcatenatedDocumentWithMinimumUnrecognizedCharacters(
            "ABC", null);
    }
     
    @Test
    public void shouldGetUnconcatenatedDocumentWithMinimumUnrecognizedCharacters_wholeDocumentUnrecognized() {
        String document = "jesslookedjustliketimeherbrother";
        trie.addWords(new String[0]);
        expect(trie.isWordPresent(anyObject(String.class)))
            .andReturn(false).anyTimes();
        replay(trie);
        assertThat(
            problem.getUnconcatenatedDocumentWithMinimumUnrecognizedCharacters(
                document, new LinkedList<>()),
            is(equalTo(document)));
        verify(trie);
    }
    
    @Test
    public void shouldGetUnconcatenatedDocumentWithMinimumUnrecognizedCharacters_dictionaryWithAllLetters() {
        String document = "esslooedustlietimeherbrother";
        List<String> dictionary = 
            Arrays.asList( 
               "b", "d", "e", "h", "i", "l", "m", "o", "r", "s", "t", "u");
        // Recording phase
        trie.addWords(dictionary.toArray(new String[dictionary.size()]));
        expect(trie.isWordPresent(isWordPresentMatcher(dictionary)))
            .andReturn(true).anyTimes();
        expect(trie.isWordPresent(not(isWordPresentMatcher(dictionary))))
            .andReturn(false).anyTimes();
        replay(trie);
        String expected =
            "e s s l o o e d u s t l i e t i m e h e r b r o t h e r";
        assertThat(
            problem.getUnconcatenatedDocumentWithMinimumUnrecognizedCharacters(
                document, dictionary),
            is(equalTo(expected)));
        verify(trie);
    }
}
