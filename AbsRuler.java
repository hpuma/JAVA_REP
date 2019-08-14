import java.util.ArrayList;
import java.util.Arrays;
// Sorts an array by ELIMINATING the element that causes the array to be unsorted.
class AbsRuler{ 
    public static void main(String[] args) {
        int[] testArr = {1,6,2,3,7};
        int[] testAns = absSort(testArr);
        System.out.print(Arrays.toString(testAns));
    }
    public static int[] absSort(int[] line){
        if (line.length  <= 2){
            switch(line.length){
                case 1: return line; 
                case 2: if(line[1] < line[0]) return Arrays.copyOf(line,1);
            }
        }
        ArrayList<Integer> survivors = new ArrayList<Integer>();
        int lastGuy = line.length-1;
        for(int i = 0; i < lastGuy; i++){
            if(line[i] < line[i+1]){
                survivors.add(line[i]);
            }
        }
        if(line[lastGuy] > survivors.get(survivors.size()-1)){
            survivors.add(line[lastGuy]);
        }
        return survivors.stream().mapToInt(i -> i).toArray(); // MAGIC 
        //REF: https://stackoverflow.com/questions/718554/how-to-convert-an-arraylist-containing-integers-to-primitive-int-array/718606
    }
}