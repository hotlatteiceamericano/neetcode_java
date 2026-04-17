package org.example.app.BackTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CombinationTargetSumIISolution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    void dfs(int[] candidates, int target, int start, List<Integer> list, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        if (target < 0) {
            return;
        }

        // [2,2,2,4], target=6
        // how to pick 2,2,2 while not picking 2,4 twice
        // the diff is don't use the same number "to start"
        // will skipping dfs when seeing a dupe works? wouldn't it skip the solution containing
        // dupes?
        for (int i = start; i < candidates.length; i++) {
            // what I didn't think of is how to distingiush [2,2,2] from [2,_,4] and [_,2,4]
            // from solution, it introduces another pointer,
            // and use that pointer to tell if the current iteration is at the same "level"
            // we can always pick the same number as we dfs one level down
            // however we cannot pick the same number when at the same dfs level
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            list.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}
