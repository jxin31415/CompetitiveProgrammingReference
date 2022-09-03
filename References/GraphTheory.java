package References;

import java.util.List;

public class GraphTheory {
    
    // Method to determine graphicalness (is it possible to construct a graph with the given node degrees?)
    public static boolean erdosgallai(List<Integer> arr){
        // Make arr 1-indexed
        arr.add(0, 0);
        
        long[] pSum = new long[arr.size()];
        for(int i = 1; i < pSum.length; i++) {
            pSum[i] = pSum[i-1] + arr.get(i);
        }
        
        if(pSum[arr.size()-1] % 2 == 1) {
//            return false;
            throw new IllegalArgumentException();
        }
        
        int w = arr.size() - 1;
        for(int i = 1; i < arr.size(); i++) {
            while(w > 0 && arr.get(w) < i) {
                w--;
            }
            int y = Math.max(w, i);
            if(pSum[i] > i * (y - 1) + pSum[arr.size() - 1] - pSum[y]) {
                arr.remove(0);
                return false;
            }
        }
        
        arr.remove(0);
        return true;
        
    }
    
}
