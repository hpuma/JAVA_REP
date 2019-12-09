import java.util.ArrayList;

public class ArraytoList{
    public static void main(String[] args) {
        ArraytoList test = new ArraytoList();

        String[][] testList = {{"22100","21100","22100","21700","22100","22100"}};

        test.generateLists(testList, 100);
    }
    public void generateLists(String[][] lists, int newListLength){
        if(lists == null || lists.length == 0){
            return;
        }
        ArrayList<Integer> printIndex = new ArrayList<Integer>();
        int lastIndex = lists[0].length -1;
        for(int i = 0; i < newListLength; i++){
             printIndex.add((int)(Math.random() * ((lastIndex + 1))));
        }
        
        for(int i = 0; i < lists.length; i++){
            System.out.println("FOR LIST " + i);
            for (int j = 0; j < newListLength; j++){
                System.out.println(lists[i][printIndex.get(j)]);
            }
        }
    }
}
