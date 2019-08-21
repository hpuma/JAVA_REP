import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.lang.Math;
// Given an array of integers A sorted in non-decreasing order, 
// return a new array of the squares of each number, 
// also in sorted non-decreasing order.
class NonDecreasing {
    public static void main(String[] args) {
        int[] testArr = {-6,-2,-1,0,3,5,7,12,15};
        System.out.println( "Before:\t" + Arrays.toString(testArr));
        non_decrease(testArr);
        System.out.println("After:\t" + Arrays.toString(testArr));
    }
    public static void non_decrease(int[] arr){
        Stack<Integer> negativeNum = new Stack<Integer>();
        Queue<Integer> positiveNum = new LinkedList<Integer>();
        for(int i: arr){
            if(i < 0){
                negativeNum.add((int) Math.pow(i, 2));
            }else if(i >= 0){
                positiveNum.add((int) Math.pow(i, 2));
            }
        }
        int index = 0;
        while(index != arr.length){
            if(!negativeNum.isEmpty() && !positiveNum.isEmpty()){
                if(negativeNum.peek() < positiveNum.peek()){
                    arr[index++] = negativeNum.pop();
                }else if(negativeNum.peek() > positiveNum.peek()){
                    arr[index++]  = positiveNum.remove();
                }
                else if( negativeNum.peek() == positiveNum.peek()){
                    arr[index++] = negativeNum.pop();
                    arr[index++] = positiveNum.remove();
                }
            }else{
                if(positiveNum.isEmpty()){
                    arr[index++] = negativeNum.pop();
                }else{
                    arr[index++] = positiveNum.remove();
                }
            }
        }
    } 
}