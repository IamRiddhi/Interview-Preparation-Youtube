class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
	
	    Map<Integer , Integer> indexTracker = new HashMap<>();
        indexTracker.put(0 , -1);
	
	    int prefixSum = arr[0];
        indexTracker.put(prefixSum , 0);
	
	    int forward[] = new int[n];
        int backward[] = new int[n];
        
        if(prefixSum == target)
            forward[0] = 1;
        else
            forward[0] = Integer.MAX_VALUE;
        
        for(int curIndex  = 1 ; curIndex < n ; curIndex ++)
        {
            prefixSum += arr[curIndex];
            forward[curIndex] = forward[curIndex - 1];
            indexTracker.put(prefixSum , curIndex);
            
            int index = indexTracker.getOrDefault(prefixSum - target , -2);
            if(index > -2)
            {
                int length = curIndex - index;
                forward[curIndex] = Math.min(forward[curIndex] , length);
            }
        }
        
        prefixSum = arr[n - 1];
        
        indexTracker = new HashMap<>();
        indexTracker.put(0 , n);
        indexTracker.put(prefixSum , n - 1);
	
        if(prefixSum == target)
            backward[n - 1] = 1;
        else
            backward[n - 1] = Integer.MAX_VALUE;
        
        for(int curIndex  = n - 2 ; curIndex >= 0 ; curIndex --)
        {
            prefixSum += arr[curIndex];
            backward[curIndex] = backward[curIndex + 1];
            indexTracker.put(prefixSum , curIndex);
            
            int index = indexTracker.getOrDefault(prefixSum - target , n + 1);
            if(index <= n)
            {
                int length = index - curIndex;
                backward[curIndex] = Math.min(backward[curIndex] , length);
            }
        }
        
        int minSum = Integer.MAX_VALUE;
        for(int i = 0 ; i < n - 1 ; i ++)
        {
            if(forward[i] == Integer.MAX_VALUE || backward[i + 1] == Integer.MAX_VALUE)
                continue;
            minSum = Math.min(forward[i] + backward[i + 1] , minSum);
        }
        
        if(minSum == Integer.MAX_VALUE) 
            minSum = -1;
        
        return minSum;
    }
}
