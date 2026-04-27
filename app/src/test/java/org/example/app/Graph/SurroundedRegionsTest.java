package org.example.app.Graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurroundedRegionSolution {
    int[][] DIRECTIONS = new int[][]{new int[]{0,1},new int[]{1,0}};
    public void solve(char[][] board) {
        // so I need to detect 0s in the same graph
        // and see if these 0s is surrounded by Xs
        // union find can help me find different groups of 0s
        // I can then manually check if each group is surrounded

        // I cannot immediately flip the 0 when seeing one, because I wouldn't know if it is being surrounded

        // iterate the board, when seeing a 0, see if there are adjacent 0s
        // if yes, call union to merge
        // if any node is on the border, mark the parent's isSurrounded to false

        // iterate the board again, if its parent's isSurrounded is true, then flip it to X

        // choose to not use Node class to implement, since I will have to initiate all the cord to Node first
        // when the given input is a 2D array

        // store entire group's is surrounded or not in a group's parent
        UnionFind uf = new UnionFind(board.length * board[0].length);
        int numColumns = board[0].length;

        for (int r=0; r<board.length; r++) {
            for (int c=0; c<board[0].length; c++) {
                if (board[r][c] == 'O') {
                    int index = r * numColumns + c;
                    for (int[] direction: DIRECTIONS) {
                        int nextR = r + direction[0];
                        int nextC = c + direction[1];
                        if (nextR >= 0 && nextR < board.length && nextC >= 0 && nextC < board[0].length && board[nextR][nextC] == 'O') {
                            int nextIndex = nextR * numColumns + nextC;
                            uf.union(index, nextIndex);
                        }
                    }

                    boolean isSurrounded = r != 0 && r != board.length-1 && c != 0 && c != board[0].length-1;
                    int parent = uf.find(index);
                    uf.isSurrounded[parent] = isSurrounded;
                }
            }
        }

        for (int r=0; r<board.length; r++) {
            for (int c=0; c<board[0].length; c++) {
                int parent = uf.find(r * numColumns + c);
                boolean isSurrounded = uf.isSurrounded[parent];
                if (isSurrounded) {
                    board[r][c] = 'X';
                }
            }
        }
    }
}

// initiate each point's parent to be itself
// when condition is met, call union() to merge the two
// find() return a node's parent, if parent is not itself, then keep looking for that node's parent
// compress the parent path by updating its parent all the way to the upmost parent
// one option is to convert the board to a single dimention array by unfolding each row
// or use a LinkedList as a node
class UnionFind {
    int[] parents;
    int[] sizes;
    boolean[] isSurrounded;

    public UnionFind(int n) {
        this.parents = new int[n];
        this.sizes = new int[n];
        isSurrounded = new boolean[n];
    }

    public void union(int a, int b) {
        int parentA = this.find(a);
        int parentB = this.find(b);

        if (parentA != parentB) {
            int parentASize = sizes[parentA];
            int parentBSize = sizes[parentB];

            if (parentASize > parentBSize) {
                parents[b] = parentA;
                sizes[parentA] = parentASize + parentBSize;
            } else {
                parents[a] = parentB;
                sizes[parentB] = parentASize + parentBSize;
            }
        }
    }

    public int find(int n) {
        int parent = parents[n];
        if (parent != n) {
            parents[n] = find(parent);
        }
        return parents[n];
    }
}

public class SurroundedRegionsTest {
    @Test
    void test() {
        char[][] board = new char[][]{
                new char[] {'O','X','X','O','X'},
                new char[] {'X','O','O','X','O'},
                new char[] {'X','O','X','O','X'},
                new char[] {'O','X','O','O','O'},
                new char[] {'X','X','O','X','O'}
        };
        char[][] expected = new char[][]{
                new char[] {'O','X','X','O','X'},
                new char[] {'X','X','X','X','O'},
                new char[] {'X','X','X','O','X'},
                new char[] {'O','X','O','O','O'},
                new char[] {'X','X','O','X','O'}
        };
        SurroundedRegionSolution instance= new SurroundedRegionSolution();
        instance.solve(board);
        assertEquals(board, expected);
    }
}