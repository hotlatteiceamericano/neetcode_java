package org.example.app.Trie;

import java.util.HashMap;
import java.util.Map;

// a tree to store chars
// also a boolean to indicates if its a word
class PrefixTree {
    Node root;

    public PrefixTree() {
        this.root = new Node(null, false);
    }

    public void insert(String word) {
        Node.insert(this.root, word);
    }

    public boolean search(String word) {
        return this.root.search(word, false);
    }

    public boolean startsWith(String prefix) {
        return this.root.search(prefix, true);
    }
}

class Node {
    public boolean wordEnds = false;
    public Character c;
    public Map<Character, Node> children;

    public Node(Character c, boolean wordEnds) {
        this.wordEnds = wordEnds;
        this.c = c;
        this.children = new HashMap<>();
    }

    public void insert(String word) {
        if (word.length() == 0) return;

        boolean isChildWordEnd = word.length() == 1;

        Node child = this.children.get(word.charAt(0));
        if (child == null) {
            child = new Node(word.charAt(0), isChildWordEnd);
            this.children.put(word.charAt(0), child);
        } else {
            if (!child.wordEnds) {
                child.wordEnds = isChildWordEnd;
            }
        }

        child.insert(word.substring(1, word.length()));
    }

    public boolean search(String word, boolean isStartsWith) {
        if (word.length() == 0) {
            if (isStartsWith) {
                return true;
            } else {
                return this.wordEnds;
            }
        }

        if (this.children.containsKey(word.charAt(0))) {
            return this.children
                    .get(word.charAt(0))
                    .search(word.substring(1, word.length()), isStartsWith);
        } else {
            return false;
        }
    }
}
