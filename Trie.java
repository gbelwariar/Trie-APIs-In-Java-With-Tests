/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.common.annotations.VisibleForTesting;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author BELWARIAR
 */
public class Trie {
    
    @VisibleForTesting public TrieNode root;
    
    public Trie() {
        this.root = new TrieNode();
    }
    
    public TrieNode getTrieRoot() {
        return this.root;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Trie) {
            Trie that = (Trie) obj;
            return that.root.equals(this.root);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return root.hashCode(); 
   }
    
    @Override
    public String toString() {
        return getAllWords().toString();
    }
    
    /**
     * Returns the list of words present in the trie.
     * 
     * @return List<String>
     */
    public List<String> getAllWords() {
        List<String> res = new LinkedList<>();
        if (root.children == null) {
            return res;
        }
        String currentWord = new String();
        getAllWordsRec(res, currentWord, root);
        return res;
    }
    
    private static void getAllWordsRec(
        List<String> res, String currentWord, TrieNode currentNode) {
        if (currentNode.isLastCharacter == true) {
            res.add(currentWord + currentNode.letter);
        }
        if (currentNode.children != null) {
            for (TrieNode childNode : currentNode.children) {
                getAllWordsRec(
                    res,
                    currentWord
                        + ((currentNode.letter != null) 
                            ? currentNode.letter.toString() : ""),
                    childNode);
            }
        }
    }
    
    /**
     * Adds a word in the trie.
     * 
     * @param word The word to insert in the trie.
     */
    public void addWord(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Cannot insert a null string");
        }
        // Only initialize the root node if a word is going to be inserted 
        // inside the trie.
        TrieNode currentNode = root;
        for (int i=0; i<word.length(); i++) {
            currentNode = TrieNode.addChild(
                currentNode, word.charAt(i), i == word.length()-1);
        }
    }
    
    /**
     * Adds the given input words into the trie.
     * 
     * @param words The words to be inserted in the trie.
     */
    public void addWords(String... words) {
        if (words == null) {
            throw new IllegalArgumentException("Cannot insert null strings");
        }
        for (String word : words) {
            addWord(word);
        }
    }
    
    /**
     * Returns whether the given word is present in the trie.
     * 
     * @param word The word to check for presence in the trie.
     * @return boolean Whether the given word is present in the trie or not.
     */
    public boolean isWordPresent(String word) {
        if (word == null) {
            throw new IllegalArgumentException(
                "Cannot search for a null word in the trie");
        }
        TrieNode currentNode = root;
        for (int i=0; i<word.length(); i++) {
            currentNode = TrieNode.getChildNode(currentNode, word.charAt(i)); 
            if (currentNode == null) {
                return false;
            }
        }
        return currentNode.isLastCharacter;
    }
    
    /**
     * Not making this class as immutable since we can mark one of the tree 
     * node's isLastWord equal to true.
     */
     
    public static class TrieNode {
        
        private Character letter;
        @VisibleForTesting public List<TrieNode> children;
        private boolean isLastCharacter;
        
        @VisibleForTesting
        public TrieNode() {
            this.children = null;
            this.letter = null;
            this.isLastCharacter = false;
        }
        
        @VisibleForTesting
        public TrieNode(Character letter, boolean isLastCharacter) {
            this.letter = letter;
            this.children = null;
            this.isLastCharacter = isLastCharacter;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof TrieNode) {
                TrieNode that = (TrieNode) obj;
                if (((that.letter == null || this.letter == null)
                    && (that.letter != null || this.letter != null))
                    || ((that.letter != null && this.letter!= null
                            && !that.letter.equals(this.letter))
                        || (that.isLastCharacter != this.isLastCharacter))) {
                    return false; 
                }
                if (that.children == null && this.children == null) {
                    return true;
                } else if ((that.children == null && this.children != null) 
                    || (that.children != null && this.children == null)) {
                    return false;
                } else {
                    return that.children.equals(this.children);
                }
            }
            return false;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(
                this.children, this.isLastCharacter, this.letter);
        }
        
        @Override
        public String toString() {
            return "[Root = " + this.letter + "(isLastChar = "
                + this.isLastCharacter + ")] -> " + this.children;
        }
        
        /**
         * Adds and return the node of the child letter to a parent trie node.
         * If the child is already in the trie then the node is simply returned.
         * 
         * @param parent The parent node.
         * @param childLetter The letter to add to children list of the parent
         *                    node.
         * @param isLastWord Whether the child is the last character of a word.
         */
        @VisibleForTesting
        public static TrieNode addChild(
            TrieNode parent, Character childLetter, boolean isLastCharacter) {
            if (parent.children == null) {
                parent.children = new LinkedList<>();
            }
            for (TrieNode node : parent.children) {
                if (node.letter.equals(childLetter)) {
                    if (isLastCharacter) {
                        node.isLastCharacter = true;
                    }
                    return node;
                }
            }            
            TrieNode child = new TrieNode(childLetter, isLastCharacter);
            parent.children.add(child);
            return child;
        }

        /**
         * Returns the TrieNode encapsulating the given child letter of the
         * given parent node.
         * 
         * @param parent The parent node.
         * @param childLetter The child letter of the parent node.
         * @return TrieNode The node encapsulating childLetter. If the child is
         *                  not present then a null is returned.
         */
        private static TrieNode getChildNode(
            TrieNode parent, Character childLetter) {
            if (parent.children == null) {
                return null;
            } 
            for (TrieNode node : parent.children) {
                if (node.letter.equals(childLetter)) {
                    return node;
                }
            }
            return null;
        }        
    } 
}
