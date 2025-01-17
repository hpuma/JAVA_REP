import java.util.ArrayList;
import java.util.Arrays;
// Sorts an array by ELIMINATING the element that causes the array to be unsorted.
class AbsRuler{ 
    public static void main(String[] args) {
        int[] testArr = {1,6,2,3,7,4,9,12,5};
        int[] testAns = absSort(testArr);
        System.out.print(Arrays.toString(testAns));
    }
    public static int[] absSort(int[] line){
        // Check if the array is less than 2 which is the easiest case.
        // If it is empty than return null, if it size 1 then do nothing. 
        // If the Array is length 2 then compare those two and eliminate accordingly.
        if (line.length  <= 2){
            switch(line.length){
                case 0: return null;
                case 1: return line; 
                case 2: if(line[1] < line[0]) return Arrays.copyOf(line,1);
            }
        }
        // ArrayList: used to build the new array that contains all the elements that make the array sorted.
        ArrayList<Integer> survivors = new ArrayList<Integer>();
        // LastGuy: stores the second to last possible index of the array.
        int LastGuy = line.length-1;
        // Compares the current element with the next element and checks if those two elements are sorted.
        for(int i = 0; i < LastGuy; i++){
            if(line[i] < line[i+1] ) {
                survivors.add(line[i]);
            }
        }
        // Looks at the very last element of the array and checks to see if it is greater than the second to last element of the array.
        if(line[LastGuy] > survivors.get(survivors.size()-1)){
            survivors.add(line[LastGuy]);
            if(line[LastGuy-1] > survivors.get(survivors.size()-1)){
                survivors.add(line[LastGuy-1]);
            }
        }
        // Returns the survivor list as an array.
        return survivors.stream().mapToInt(i -> i).toArray(); // MAGIC 
        //REF: https://stackoverflow.com/questions/718554/how-to-convert-an-arraylist-containing-integers-to-primitive-int-array/718606
    }
}