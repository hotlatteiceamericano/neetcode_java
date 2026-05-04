package org.example.app.OneDimensionDP;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class CoinChangeSolution {
    public int coinChange(int[] coins, int amount) {
        Map<Integer, Integer> cache = new HashMap<>();

        int res = dfs(coins, amount, amount, cache);

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    int dfs(int[] coins, int amount, int curr, Map<Integer, Integer> cache) {
        if (curr == 0) return 0;
        if (curr < 0) return Integer.MAX_VALUE;

        if (cache.containsKey(curr)) return cache.get(curr);

        int minCount = Integer.MAX_VALUE;
        for (int i=0; i<coins.length; i++) {
            int count = dfs(coins, amount, curr-coins[i], cache);
            if (count != Integer.MAX_VALUE)
                minCount = Math.min(minCount, count);
        }

        if (minCount != Integer.MAX_VALUE){
            cache.put(curr, minCount+1);
        } else {
            cache.put(curr, Integer.MAX_VALUE);
        }

        return cache.get(curr);

    }
}

public class CoinChangeTest {
    @Test
    void test() {
        new CoinChangeSolution().coinChange(new int[]{357,239,73,52}, 9832);
    }
}
