package org.example.app.Graph;

class GraphValidTreeSolution {
    public boolean validTree(int n, int[][] edges) {
        // no loop
        // all connected => can use UnionFind
        // UnionFind may not be the best fit because it is edges being given
        // so every edge should reduce number of graph by 1
        // if not, then it is a duplicate edge which causes loop
        // then how do I know if a new edge is connecting new graph?
        // if two nodes are already in the same graph, then it is now an edge we are looking for
        // ended up UnionFind may still be a valid solution, cause I can know if both nodes are in
        // the same graph or not
        if (edges.length != n - 1) {
            return false;
        }

        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return false;
            }
        }

        return uf.getComponents() == 1;
    }
}

class UnionFind {
    int[] parents;
    int[] sizes;
    int components;

    UnionFind(int n) {
        this.parents = new int[n];
        this.sizes = new int[n];
        for (int i = 0; i < n; i++) {
            this.parents[i] = i;
            this.sizes[i] = 1;
        }
        this.components = n;
    }

    int find(int n) {
        if (n != this.parents[n]) {
            this.parents[n] = find(this.parents[n]);
        }

        return this.parents[n];
    }

    public boolean union(int a, int b) {
        int parentA = this.find(a);
        int parentB = this.find(b);
        if (parentA == parentB) {
            return false;
        }

        int parentASize = this.sizes[parentA];
        int parentBSize = this.sizes[parentB];

        if (parentASize > parentBSize) {
            this.parents[parentB] = parentA;
            this.sizes[parentA] += this.sizes[parentB];
        } else {
            this.parents[parentA] = parentB;
            this.sizes[parentB] += this.sizes[parentA];
        }

        this.components -= 1;

        return true;
    }

    public int getComponents() {
        return this.components;
    }
}
