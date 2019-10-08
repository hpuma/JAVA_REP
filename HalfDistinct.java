import java.util.Arrays;
import java.lang.Math;
import java.util.HashMap;
/*
S: 9/12/19
E: 9/13/19
Given a set A of n integers, determine whether or not an array has an integer k that occurs 
at least n/2 times. */
public class HalfDistinct{
    // Testing
    public static void main(String[] args) {
        int testArr[] = {1,1,7,1,1};
        System.out.println("Test Array:\t" + Arrays.toString(testArr));
        System.out.println("Function Output:\t" + findInverse(testArr));
    }
    public static boolean findInverse(int[] A ){
        // HashMap that keeps track of integers in A.
        HashMap<Integer,Integer> hashInt = new HashMap<Integer,Integer>(); 
        // Stores the length of the array in n and 
        int n = A.length;         
        int medium =(int) Math.floor(n/2);
        // Iterate through every element in the array where i <=> A[i].
        for(int i : A){
            // When an element in the array does NOT exist in the hashtable, then add it to the hashtable and set its count to 1.
            if(hashInt.get(i) == null){
                hashInt.put(i, 1);
            }else{ // If it already exists in the hashtable, then retrieve its count and update by incrementing its value.
                int count = hashInt.get(i);
                hashInt.put(i,count+1);
            }
        }
        // Checks to see if there is a count in the hash table that is greater than medium... Meaning it appears at least n/2 times.
        for(int i = medium+1; i < n; i++){
            if(hashInt.containsValue(i)){
                return true;
            }
        }
        // The hash table did not contain a value that appears at least n/2 times.
        return false;
    }
}
