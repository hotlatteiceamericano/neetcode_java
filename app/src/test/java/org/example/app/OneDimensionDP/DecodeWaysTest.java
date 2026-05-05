package org.example.app.OneDimensionDP;

import org.junit.jupiter.api.Test;

class DecodeWaysSolution {
	  public int numDecodings(String s) {
        // 11111x
        // dfs when possible combination
        // when at the end, means one valid deocde way
        return dfs(s, 0, new HashMap<>());
    }

    int dfs(String s, int i, Map<Integer, Integer> cache) {
        if (i >= s.length()) {
            return 1;
        }

        if (cache.containsKey(i)) {
            return cache.get(i);
        }

        // can either decide one number or decode two numbers
        // decoding one number only has one condition on 0
        if (s.charAt(i) == '0') {
            return 0;
        }

        int waysDecode = dfs(s, i+1, cache);
        if (i < s.length()-1 && (
            s.charAt(i) == '1' ||
            (s.charAt(i) == '2' && Character.getNumericValue(s.charAt(i+1)) < 7))) {
                waysDecode += dfs(s, i+2, cache);
        }

        cache.put(i, waysDecode);

        return cache.get(i);

}

public class DecodeWaysTest {
    @Test
    public void test() {
        new DecodeWaysSolution().numDecodings("1234");
    }
}
