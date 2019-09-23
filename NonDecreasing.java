import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.lang.Math;
// Given an array of integers A sorted in non-decreasing order, 
// return a new array of the squares of each number, 
// also in sorted non-decreasing order.
class NonDecreasing {
    // Testing
    public static void main(String[] args) {
        int[] testArr = {-6,-2,-1,0,3,5,7,12,15};
        System.out.println( "Before:\t" + Arrays.toString(testArr));
        non_decrease(testArr);
        System.out.println("After:\t" + Arrays.toString(testArr));
    }
    public static void non_decrease(int[] arr){
        if(arr.length == 0)
            return;
       /* Creates a stack that contains the negative numbers
        The top of the stack as the lowest absolute value*/
        Stack<Integer> negativeNum = new Stack<Integer>(); 
        /* Creates a Queue that contains the positive numbers 
        The next item in the queue is the lowest of the positive numbers */
        Queue<Integer> positiveNum = new LinkedList<Integer>();
        for(int i: arr){
            if(i < 0){ // When the item is a negative number, square it and add it to the stack.
                negativeNum.add((int) Math.pow(i, 2));
            }else if(i >= 0){ // When the number is greater than or equal to 0, square it and add it to the queue.
                positiveNum.add((int) Math.pow(i, 2));
            }
        }
        int index = 0;
        while(index != arr.length){
            if(!negativeNum.isEmpty() && !positiveNum.isEmpty()){ // Check that at both Stack and Queue are not empty.
                if(negativeNum.peek() < positiveNum.peek()){ // Check if the negative number squared is less than the positive number squared.
                    arr[index++] = negativeNum.pop();
                }else if(negativeNum.peek() > positiveNum.peek()){ // Otherwise, check if the positive number squared is less than negative number squared.
                    arr[index++]  = positiveNum.remove();
                }
                else if( negativeNum.peek() == positiveNum.peek()){
                    arr[index++] = negativeNum.pop();
                    arr[index++] = positiveNum.remove();
                }
            }else{ // If either the stack or the queue is empty, then check which one is and empty it out and insert in the array.
                if(positiveNum.isEmpty()){
                    arr[index++] = negativeNum.pop(); //If the Queue is empty, then the stack must not be empty ... so add it to the remaing of the stack
                }else{
                    arr[index++] = positiveNum.remove(); // The Queue is not empty so add it to the remainder of the array
                }
            }
        }
    } 
}