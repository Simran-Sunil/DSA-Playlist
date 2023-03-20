public class ZAlgorithm {
    
    private String text;
    private String pattern;
    private int[] zTable;

    public ZAlgorithm(String text, String pattern){
        this.text = text;
        this.pattern = pattern;
        // O(M+N) linear time complexity
        this.zTable = new int[text.length()+pattern.length()];
    }

    // it has linear O(M+N) linear time complexity
    public void search(){
        // Z table construction
        constructTable();

        for(int i=0;i<zTable.length;++i){
            if(zTable[i] >= pattern.length()){
                System.out.println("Match found at index: "+(i - pattern.length()));
            }
        }
    }

    // it has O(N+M) linear running time
    private void constructTable(){

        // concatenate the pattern and the text
        String patternText = pattern+text;
        // the length of S'
        int n = patternText.length();
        // the left pointer
        int left = 0;
        // the right pointer
        int right = 0;

        // consider the letters of S' (pattern + text) one by one
        for(int k=1;k<n;++k){
            // we have to use the naive Z calculation (CASE I.)
            if(k > right){
                int matchCounter = 0;
                while(k+matchCounter < n && patternText.charAt(matchCounter) == patternText.charAt(k+matchCounter)){
                    matchCounter++;
                }
                zTable[k] = matchCounter;
                // update the left and right indexes
                // we just update the z box indexes if the matchcounter is greater than 0
                if(matchCounter > 0){
                    left = k;
                    right = k+matchCounter-1;
                }
            }else{
                // K is within the Z box (pair index in the prefix)
                int p = k - left;

                //CASE II - we can copy the values no need to recalculate it again
                if(zTable[p] < right - k + 1){
                    zTable[k] = zTable[p];
                }else{
                    // but to use naive approach
                    // this is the first item we have to consider (outside the Z box)
                    int i = right + 1;

                    while(i < n && patternText.charAt(i) == patternText.charAt(i - k)){
                        i++;
                    }
                    zTable[k] = i-k;
                    // we have found another Z box
                    left = k;
                    right = i-1;
                }
            }
        }
    }
}
