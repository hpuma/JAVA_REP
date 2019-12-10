import java.util.ArrayList;
/*ArraytoList: Takes in a 2D Array and generates a random list at a specified length 
NOTE: Each subarray must be the same length! 
NOTE: Each subarray generates the list with respect to the same index with its adjacent array */
public class ShuffArraytoList{
    public static void main(String[] args) {
        ShuffArraytoList test = new ShuffArraytoList();
        
        String[][] testList = {{"22100","21100","22100","21700","22100","22100"}};
        test.generateLists(testList, 100);
    }
    // generateList: Produces the random list from the contents of each array in lists
    // lists: Contents that are meant to be randomly chosen
    // NewListLength: The length of the new list that is randomly using the contents of "lists"
    public void generateLists(String[][] lists, int newListLength){
        // Base case: The array is empty, then do nothing
        if(lists == null || lists.length == 0){
            return;
        }
        // printIndex: ArrayList used to keep track of the randomly chosen item in the original array
        ArrayList<Integer> printIndex = new ArrayList<Integer>();
        // Since all arrays must be the same size and we are guaranteed 1 array then we take the size of the last index of the first array to note the ending boundary of random()
        int lastIndex = lists[0].length -1;
        // Generate a random index and then value for the new list. Do this until the newListLength is met
        for(int i = 0; i < newListLength; i++){
             printIndex.add((int)(Math.random() * ((lastIndex + 1))));
        }
        // Prints out the new list in the console
        for(int i = 0; i < lists.length; i++){
            System.out.println("FOR LIST " + i);
            for (int j = 0; j < newListLength; j++){
                System.out.println(lists[i][printIndex.get(j)]);
            }
        }
    }
}
