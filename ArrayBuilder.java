import static java.lang.Math.random;
import java.util.Arrays;
import java.util.HashMap;

public class ArrayBuilder {
    // TESTING
    public static void main(String[] args) {
        ArrayBuilder build = new ArrayBuilder();
        int[] testArray = build.NoRepeatArray(100,1,200);
        System.out.print(Arrays.toString(testArray));
    }

    public static int[] Data;

    ArrayBuilder() {

    }

    ArrayBuilder(int[] InputArray) {
        Data = InputArray;
    }

    public int[] ShuffleArray() {
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

    public int[] RandomArray(int Size, int LeftBound, int RightBound){
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


    public int[] NoRepeatArray(int Size, int LeftBound, int RightBound){

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

    public static void printArray(){
        System.out.print(Arrays.toString(Data));
    }

}