class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        // Step 1: Pick each number and use built-in method (Stream filter) to count occurrences
        for (int num : nums) {
            // Avoid recounting if the key is already calculated
            if (!map.containsKey(num)) {
                long count = Arrays.stream(nums).filter(x -> x == num).count();
                map.put(num, (int) count);
            }
        }

        // Step 2 & 3: Use built-in Collections.max to pull the highest frequencies
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            // Find the entry with the maximum value
            Map.Entry<Integer, Integer> maxEntry = Collections.max(
                map.entrySet(), 
                Map.Entry.comparingByValue()
            );

            result[i] = maxEntry.getKey();

            // Remove it so the next iteration finds the next highest frequency
            map.remove(maxEntry.getKey());
        }

        return result;
    }
}
