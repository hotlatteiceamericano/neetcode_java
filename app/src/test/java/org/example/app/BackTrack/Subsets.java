package org.example.app.BackTrack;

import java.util.ArrayList;
import java.util.List;

class Solution1 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        this.dfs(0, new ArrayList<>(), nums);
        return this.res;
    }

    public void dfs(int start, List<Integer> list, int[] nums) {
        this.res.add(new ArrayList<>(list));
        if (start >= nums.length) {
            // this.res.add(new ArrayList<>(list));
            return;
        }

        for (; start < nums.length; start++) {
            list.add(nums[start]);
            dfs(start + 1, list, nums);
            list.remove(list.size() - 1);
        }
    }
}

class Solution2 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        dfs(nums, 0, subset, res);
        return res;
    }

    private void dfs(int[] nums, int i, List<Integer> subset, List<List<Integer>> res) {
        if (i >= nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        subset.add(nums[i]);
        dfs(nums, i + 1, subset, res);
        subset.remove(subset.size() - 1);
        dfs(nums, i + 1, subset, res);
    }
}
