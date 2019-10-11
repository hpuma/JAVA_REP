import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
// Source for Generator: https://dzone.com/articles/random-number-generation-in-java
public class ArrayBuilder{
    // TESTING
    public static void main(String[] args) {
        // TEST 1 SHUFFLE ARRAY
        /*
        int []TestArr = {2,5,1,6,1,6,1,8,9,3,5,6,7,8};
        ArrayBuilder build = new ArrayBuilder(TestArr);
        int[] testArray = build.ShuffleArray();
        */
        
        // TEST 2 RANDOM ARRAY 
        
        ArrayBuilder build2 = new ArrayBuilder();
        int[] testArray = build2.RandomArray(25,1,3);
        
        // OUTPUT 
        System.out.print(Arrays.toString(testArray));
    }
    // Data: A REFERENCE ARRAY USED TO BUILD OTHER ARRAYS.
    public static int[] Data;

    // CONSTRUCTORS.
    ArrayBuilder() {

    }
    ArrayBuilder(int[] InputArray) {
        Data = InputArray;
    }
    // DATA ARRAY MANUPULATIONS.
    public void setData(int[] InputData){
        Data = InputData;
    }
    public int[] getData(){
        return Data;
    }
    // Prints the Data Array.
    public void printDataArray(){
        System.out.print(Arrays.toString(Data));
    }
    // Makes sure that we are able to reference a valid Data Array. Where there is more than one element.
    public Boolean validData(){
        return (Data != null && Data.length > 1);
    }

    // ARRAY MAKER BASED ON DATA.
    // ShuffleArray: Returns the shuffled version of the Data Array.
    public int[] ShuffleArray() {
        if(!validData()){
            System.out.println("DATA ARRAY IS NOT SUFFICIENT LENGTH!");
            return null;
        }
        // Make a seperate array that is the same length as Data.
        int DataLength = Data.length;
        int[] ShuffledArray = new int[DataLength];
        // We make a HashMap to keep track of indexes that have already been chosen reandomly.
        HashMap<Integer, Boolean> IndexTracker = new HashMap<Integer, Boolean>();
        // min: Is used to set the lowest index possible that can be chosen randomly.
        // max: Is the largest indext possible that can be chosen randomly.
        int min = 0;
        int max = DataLength - 1;
        // Index: used to add on to the Shuffled Array.
        int Index = 0;
        // RandomIndex: Temporary variable used to store the random index chosen by the generator.
        int RandomIndex = 0;
        // SearchIndex: Checks to see if we have randomly chosen a distinct index from Data.
        Boolean SearchIndex = true;
        // While-Loop that randomly choses indexes Data.length amount of times.
        while (Index < DataLength) {
            // Reset the boolean to true because we are searching for another random index.
            SearchIndex = true;
            // Keep generating a random index until it is distinct from the previous ones chosen.
            while (SearchIndex) {
                // Generates random possible indexes.
                RandomIndex = (int) ((random() * ((max - min) + 1)) + min);
                // If we find an index that is not in the HashMap and it is valid index, then add it to the hashmap.
                if ((IndexTracker.get(RandomIndex) == null) && (RandomIndex >= min)  && (RandomIndex <= max)){
                    // Add the valid index from the Data array onto the ShuffledArray.
                    ShuffledArray[Index] = Data[RandomIndex];
                    // Adding the distinct into the HashMap so that we don't choose it again.
                    IndexTracker.put(RandomIndex, true);
                    // Set the boolean to false because we no longer need to search another distinct index... On to the next one.
                    SearchIndex = false;
                }
            }
            // Incremenet the index of the ShuffledArray so that we generate the next random index.
            Index+=1;
        }
        return ShuffledArray;
    }
    // ExtractEven: Iterates through the Data Array and returns an array containing any even numbers.
    public Object[] ExtractEven(){
        if(!validData()){
            System.out.println("THE DATA ARRAY IS NOT VALID!");
            return null;
        }
        ArrayList<Integer> EvenList = new ArrayList<Integer>();
        for(int Item: Data){
            if(Item%2 == 0 && Item%3 != 0){
                EvenList.add(Item);
            }
        }
        return EvenList.toArray();
    }
    // ExtractOdd: Iterates through the Data Array and returns an array containing any odd numbers.
    public Object[] ExtractOdd(){
        if(!validData()){
            System.out.println("THE DATA ARRAY IS NOT VALID!");
            return null;
        }
        ArrayList<Integer> OddList = new ArrayList<Integer>();
        for (int Item: Data){
            if(Item%3 == 0 && Item%2 != 0){
                OddList.add(Item);
            }
        }
        return OddList.toArray();
    }
    // RANDOM ARRAY GENERATORS. 
    // NOTE: DATA ARRAY IS NOT BEING REFERENCED HERE!
    // Random Array: Generates an array with a specific size and values at a certain interval.
    public int[] RandomArray(int Size, int LeftBound, int RightBound){
        // Make sure that the size value isn't a negative number because it is an invalid index. Make sure that the right bound is greater than the left bound.
        if(Size < 0 || RightBound < LeftBound){
            System.out.println("INCORRECT BOUNDS!");
            return null;
        }
        // NewRandom: Array used to store the random values generated.
        int []NewRandom = new int[Size];
        // Index: used to add the next random value chosen.
        int Index = 0;
        // RandomNumber: Temp variable used to store the random value generated.
        int RandomNumber = 0;
        // While-loop that generates random values that fills the entire RandomArray.
        while(Index < Size){
            // Since repetition is allowed, we generate the random value without checking for duplicates.
            RandomNumber = (int)((random() * ((RightBound - LeftBound) + 1)) + LeftBound);
            // Add the random value to the RandomArray and increment Index to add another random value.
            NewRandom[Index] = RandomNumber;
            Index+=1;
        }
        return NewRandom;
    }
    // NoRepeatArray: Generates an array with a specific size and values at a certain interval with NO REPETITIONS.
    public int[] NoRepeatArray(int Size, int LeftBound, int RightBound){
        // Checks if the size is valid, we cannot have negative sizes. Also checks if the interval is wide enough so that each element contains a distinct value!
        if(Size < 0 || RightBound < LeftBound || RightBound - LeftBound < Size){
            System.out.println("INCORRECT BOUNDS!");
            return null;
        }
        // DistinctArray: Stores the distinct values in the size specified array.
        int [] DistinctArray = new int[Size];
        // ValueTracker: Stores the distinct values that have already been generated.
        HashMap<Integer,Boolean> ValueTracker = new HashMap<Integer,Boolean>();
        // Index: Stores the index for the next random value generated.
        int Index = 0;
        // RandomNumber: Temp variable used to store a random value generated.
        int RandomNumber = 0;
        Boolean SearchValue = true;
        // NOTE: THE REST FOLLOWS THE SAME LOGIC AS THE "RandomArray" method.
        while(Index < Size){
            SearchValue = true;
            while(SearchValue){
                RandomNumber = (int)((random() * ((RightBound - LeftBound) + 1)) + LeftBound);
                if ((ValueTracker.get(RandomNumber) == null) && (RandomNumber >= LeftBound)  && (RandomNumber <= RightBound)){
                    DistinctArray[Index] = RandomNumber;
                    ValueTracker.put(RandomNumber, true);
                    SearchValue = false;
                }
            }
            Index+=1;
        }
        return DistinctArray;
    }
}