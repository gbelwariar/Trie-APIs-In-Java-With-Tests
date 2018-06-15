package problem;

import com.google.inject.Inject;
import java.util.List;
import service.Trie;


/**
 * This is my local comment.
 *
 * @author gbelwariar
 */
public class Problem {
    
    private final Trie trie;
    
    @Inject
    public Problem(Trie trie) {
        this.trie = trie;
    }
    
    /**
     * Returns the unconcatenated document with the minimum number of
     * unrecognized characters.
     * 
     * @param document The document to unconcatenate.
     * @param dictionary The dictionary of recognized words.
     * @return String.
     */
    public String
        getUnconcatenatedDocumentWithMinimumUnrecognizedCharacters(
            String document, List<String> dictionary) {
        if (document == null || document.isEmpty()) {
            throw new IllegalArgumentException(
                "The document is either null or is empty or the dictionary is"
                + " not defined");
        } 
        trie.addWords(dictionary.toArray(new String[dictionary.size()]));
        int dp[] = new int[document.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        int spaceIndex[] = new int[document.length()];
        assignMinUnrecognizedChar(
            document.length()-1, spaceIndex, dp, document);
        return unconcatenateString(
            document, spaceIndex, new String(), document.length()-1);
    }
        
    private int assignMinUnrecognizedChar(
        int index, int spaceIndex[], int dp[], String document) {
        if (index < 0) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        int min = Integer.MAX_VALUE;
        for (int j = -1; j < index; j++) {
            int localMin =
                assignMinUnrecognizedChar(j, spaceIndex, dp, document)
                    + getUnrecognizedCharacters(document, j+1, index);
            if (localMin < min) {
                min = localMin;
                spaceIndex[index] = j;
            }
        }
        dp[index] = min;
        return min;
    }
        
    private String unconcatenateString(
        String document,
        int spaceIndex[],
        String res,
        int currentIndex) {
        if (spaceIndex[currentIndex] != -1) {
            res += unconcatenateString(
                document, spaceIndex, res, spaceIndex[currentIndex]);
            res += " ";
        }
        res += document.substring(
            spaceIndex[currentIndex] + 1, currentIndex + 1);
        return res;
    }
    
    private int getUnrecognizedCharacters(String word, int start, int end) {
        if (trie.isWordPresent(word.substring(start, end+1))) {
            return 0;
        } else {
            return end - start + 1;
        }   
    }
}
