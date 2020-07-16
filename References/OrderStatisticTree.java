package References;
import java.util.*;

public class OrderStatisticTree { 	// UNTESTED. Same functionality as IndexedTreeSet, but with duplicates(I think) and cleaner
									// However, can only store ints, not longs
    static class OSTree {
        int[] value;
 
        public OSTree(int n) {
            value = new int[n+1];
        }
 
        int getSum(int index) {
            int sum = 0;
            index++;
            while(index > 0) {
                sum += value[index];
                index -= index & (-index);
            }
            return sum;
        }

        int getSum(int a, int b) {
            return getSum(b) - getSum(a-1);
        }

        void update(int index, int val) {
            index++;
            while(index < value.length) {
                value[index] += val;
                index += index & (-index);
            }
        }
        
        int findKthSmallest(int target) {
            int low = 0, high = value.length;
            int ans = -1;
            while(low <= high) {
                int mid = (low+high) >> 1;
                if(target <= getSum(mid)) {
                    ans = mid;
                    high = mid-1;
                }
                else low = mid+1;
            }
            return ans;
        }
        
        void insertElement(int target) {
            update(target, 1);
        }
        
        void deleteElement(int target) {
            update(target, -1);
        }
        
        int findRank(int target) {
            return getSum(target);
        }
    }
}
