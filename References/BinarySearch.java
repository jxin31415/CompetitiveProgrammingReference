package References;

public class BinarySearch {
    int binarySearch(int arr[], int x) { 
        int lo = 0, hi = arr.length - 1; 
        while (lo <= hi) { 
            int mid = (lo + hi) / 2; 
              if (arr[mid] == x) 
                return mid; 
              if (arr[mid] < x) 
                lo = mid + 1; 
              else
                hi = mid - 1; 
        } 
        return -1; 
    } 
    int binarySearchInequality(boolean[] arr) { // given an array of booleans with x consecutive falses followed by
                                                // (n - x) consecutive trues, returns the index of the first true
//      int lo = 0, hi = arr.length - 1; 
//        while (lo < hi-1) { 
//            int mid = (lo + hi) / 2; 
//              if (arr[mid]) 
//                hi = mid; 
//              else
//                lo = mid; 
//        } 
//        
//        if(arr[lo])
//          return lo;
//        if(arr[hi])
//          return hi;
//        return -1;
        
        // Alternate implementation
        int ans = -1;
        int lo = 0, hi = arr.length-1;
        
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid]) {
                hi = mid-1;
                ans = mid;
            } else {
                lo = mid+1;
            }
        }
     
        return ans;
    } 
}