package References;

public class Trie { // for strings, but could be modified to use with arrays
    
    static class TrieNode 
    { 
        TrieNode[] children = new TrieNode[26]; 
        boolean isEndOfWord; 
          
        public TrieNode(){  
        	
        }
    }
       
    static TrieNode root;  
    
    // Insert a string into Trie
    static void insert(String key) 
    { 
    	TrieNode pCrawl = root; 
       
        for (int level = 0; level < key.length(); level++) { 
            int index = key.charAt(level) - 'a'; 
            if (pCrawl.children[index] == null) 
                pCrawl.children[index] = new TrieNode(); 
       
            pCrawl = pCrawl.children[index]; 
        } 
        pCrawl.isEndOfWord = true; 
    } 
       
    // Check if a Trie contains a string
    static boolean search(String key) 
    { 
        TrieNode pCrawl = root; 
       
        for (int level = 0; level < key.length(); level++) { 
            int index = key.charAt(level) - 'a'; 
            if (pCrawl.children[index] == null) 
                return false; 
            pCrawl = pCrawl.children[index]; 
        } 
       
        return (pCrawl != null && pCrawl.isEndOfWord); 
    } 
}
