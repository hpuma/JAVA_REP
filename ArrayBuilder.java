import static java.lang.Math.random;
import java.util.Arrays;
import java.util.HashMap;
// Source for Generator: https://dzone.com/articles/random-number-generation-in-java
public class ArrayBuilder {
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
    // Makes sure that we are able to reference a valid Data Array.
    public Boolean validData(){
        return (Data != null && Data.length > 1);
    }

    // ARRAY MAKER BASED ON DATA.
    // ShuffleArray: Returns the shuffled version of the Array.
    public int[] ShuffleArray() {
        if(!validData()){
            System.out.println("DATA ARRAY IS NOT SUFFICIENT LENGTH!");
            return null;
        }
        int DataLength = Data.length;
        int[] ShuffledArray = new int[DataLength];

        HashMap<Integer, Boolean> IndexTracker = new HashMap<Integer, Boolean>();

        int min = 0;
        int max = DataLength - 1;

        int Index = 0;
        int RandomIndex = 0;
        Boolean SearchIndex = true;

        while (Index < DataLength) {
            SearchIndex = true;
            while (SearchIndex) {
                RandomIndex = (int) ((random() * ((max - min) + 1)) + min);
                if ((IndexTracker.get(RandomIndex) == null) && (RandomIndex >= min)  && (RandomIndex <= max)){
                    ShuffledArray[Index] = Data[RandomIndex];
                    IndexTracker.put(RandomIndex, true);
                    SearchIndex = false;
                }
            }
            Index+=1;
        }
        return ShuffledArray;
    }

    // RANDOM ARRAY GENERATORS. 
    // NOTE: DATA ARRAY IS NOT BEING REFERENCED HERE!
    // Random Array: Generates an array with a specific size and values at a certain interval.
    public int[] RandomArray(int Size, int LeftBound, int RightBound){
        if(Size < 0 || RightBound < LeftBound){
            System.out.println("INCORRECT BOUNDS!");
            return null;
        }
        int []NewArray = new int[Size];
        int Index = 0;
        int RandomNumber = 0;
        
        while(Index < Size){
            RandomNumber = (int)((random() * ((RightBound - LeftBound) + 1)) + LeftBound);
            NewArray[Index] = RandomNumber;
            Index+=1;
        }
        return NewArray;
    }
    // NoRepeatArray: Generates an array with a specific size and values at a certain interval with NOT REPETITIONS.
    public int[] NoRepeatArray(int Size, int LeftBound, int RightBound){
        if(Size < 0 || RightBound < LeftBound || RightBound - LeftBound < Size){
            System.out.println("INCORRECT BOUNDS!");
            return null;
        }

        int [] NewArray = new int[Size];

        HashMap<Integer,Boolean> IndexTracker = new HashMap<Integer,Boolean>();

        int Index = 0;
        int RandomNumber = 0;
        Boolean SearchIndex = true;

        while(Index < Size){
            SearchIndex = true;
            while(SearchIndex){
                RandomNumber = (int)((random() * ((RightBound - LeftBound) + 1)) + LeftBound);
                if ((IndexTracker.get(RandomNumber) == null) && (RandomNumber >= LeftBound)  && (RandomNumber <= RightBound)){
                    NewArray[Index] = RandomNumber;
                    IndexTracker.put(RandomNumber, true);
                    SearchIndex = false;
                }
            }
            Index+=1;
        }
        return NewArray;
    }
}