package org.example.app.OneDimensionDP;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class CoinChangeSolution {
    static int INVALID = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        int res = dfs(coins, amount, amount, new HashMap<>());
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // asks for number of coins, but not combination
    // need to keep track of how many coins have been used so far
    // also need to keep track of what is the remaining amount

    // what I didn't understand is, it is how many coins needed to complete the remains
    // however passing numCoins from top means: "how many coins have spent"
    // in DP DFS, I need to think when ends, what is the baseline case
    int dfs(int[] coins, int amount, int remains, Map<Integer, Integer> cache) {
        if (remains == 0) {
            return 0;
        }
        if (remains < 0) {
            return INVALID;
        }

        if (cache.containsKey(remains)) {
            return cache.get(remains);
        }

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = dfs(coins, amount, remains - coin, cache);
            if (res != INVALID) {
                minCoins = Math.min(minCoins, res + 1);
            }
        }

        cache.put(remains, minCoins);

        return cache.get(remains);
    }
}

public class CoinChangeTest {
    @Test
    void test() {
        // assertEquals(35, new CoinChangeSolution().coinChange(new int[] {357, 239, 73, 52},
        // 9832));
        assertEquals(3, new CoinChangeSolution().coinChange(new int[] {1, 2, 5}, 11));
    }
}
