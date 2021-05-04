/* https://leetcode.com/problems/permutations/ */
/* Video Explanation: https://youtu.be/ajjAbeWDFrE */

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        permutations.add(new ArrayList<>());
        
        for(int x : nums)
        {
            List<List<Integer>> currentPermutations = new ArrayList<>();
            for(List<Integer> perm : permutations)
            {
                int size = perm.size();
                for(int i = 0 ; i <= size ; i ++)
                {
                    List<Integer> currentPermutation = new ArrayList<>();
                    for(int j = 0 ; j < i ; j ++)
                        currentPermutation.add(perm.get(j));
                    currentPermutation.add(x);
                    for(int j = i ; j < size ; j ++)
                        currentPermutation.add(perm.get(j));
                    currentPermutations.add(currentPermutation);
                }
            }
            permutations = currentPermutations;
        }
        
        return permutations;
    }
}
