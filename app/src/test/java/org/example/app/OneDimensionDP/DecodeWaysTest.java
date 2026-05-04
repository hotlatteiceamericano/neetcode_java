package org.example.app.OneDimensionDP;

import org.junit.jupiter.api.Test;

class DecodeWaysSolution {
    private int dfs(int i, String s) {
        if (i == s.length())
            return 1;
        if (s.charAt(i) == '0')
            return 0;

        int res = dfs(i + 1, s);
        if (i < s.length() - 1) {
            if (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
                res += dfs(i + 2, s);
            }
        }
        return res;
    }

    public int numDecodings(String s) {
        return dfs(0, s);
    }
}

public class DecodeWaysTest {
    @Test
    public void test() {
        new DecodeWaysSolution().numDecodings("1234");
    }
}