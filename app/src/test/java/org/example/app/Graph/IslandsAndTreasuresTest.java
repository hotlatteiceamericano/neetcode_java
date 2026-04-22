package org.example.app.Graph;

import java.util.LinkedList;
import java.util.Queue;

class IslandsAndTreasuresSolution {

    static int LAND = Integer.MAX_VALUE;
    static int[][] directions =
            new int[][] {new int[] {0, 1}, new int[] {1, 0}, new int[] {0, -1}, new int[] {-1, 0}};

    public void islandsAndTreasure(int[][] grid) {
        // BFS to nearest 0
        // replace it with distance

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) {
                    bfs(grid, r, c);
                }
            }
        }
    }

    void bfs(int[][] grid, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r, c});
        int steps = 0;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                int[] cord = queue.poll();

                if (grid[cord[0]][cord[1]] > 0) {
                    grid[cord[0]][cord[1]] = Math.min(grid[cord[0]][cord[1]], steps);
                }

                for (int[] direction : this.directions) {
                    int nextR = cord[0] + direction[0];
                    int nextC = cord[1] + direction[1];
                    if (nextR >= 0
                            && nextR < grid.length
                            && nextC >= 0
                            && nextC < grid[0].length
                            && grid[nextR][nextC] > steps) {
                        queue.offer(new int[] {nextR, nextC});
                    }
                }
            }

            steps += 1;
        }
    }
}
