package org.example.app.Graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

// mistake 1: did not think of a case where two "island cell" have shared neighbor
// mistake 2: after mark neighbor cell as visited first, forget about marking the curr node as
// visited
class MaxAreaOfIslandsSolution {
    static int[][] directions =
            new int[][] {new int[] {0, 1}, new int[] {1, 0}, new int[] {-1, 0}, new int[] {0, -1}};

    public static int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    grid[r][c] = 0;
                    res = Math.max(res, bfs(grid, r, c));
                }
            }
        }
        return res;
    }

    static int bfs(int[][] grid, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r, c});
        int size = 0;
        while (!queue.isEmpty()) {
            int[] cord = queue.poll();
            size += 1;
            for (int[] direction : directions) {
                int nextRow = cord[0] + direction[0];
                int nextCol = cord[1] + direction[1];
                if (nextRow >= 0
                        && nextRow < grid.length
                        && nextCol >= 0
                        && nextCol < grid[0].length
                        && grid[nextRow][nextCol] == 1) {
                    queue.offer(new int[] {nextRow, nextCol});
                    grid[nextRow][nextCol] = 0;
                }
            }
        }
        return size;
    }
}

class MaxAreaOfIslandsTest {
    @Test
    void test() {
        assertEquals(
                6,
                MaxAreaOfIslandsSolution.maxAreaOfIsland(
                        new int[][] {
                            new int[] {0, 1, 1, 0, 1},
                            new int[] {1, 0, 1, 0, 1},
                            new int[] {0, 1, 1, 0, 1},
                            new int[] {0, 1, 0, 0, 1}
                        }));
    }
}
