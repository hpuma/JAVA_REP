
// Simple Search that checks the Array from both sides.
class DoubleSeq{
    public static void main(String[] args) {
        int[] testArr = {1};
        System.out.println(doubleSide(testArr,2));
    }
    public static boolean doubleSide(int []arr, int target){
        if(arr.length == 0){
            return false;
        }
        int left = 0;
        int right = arr.length-1;
        int end = right;
        while (left != end || right != -1){
            System.out.println(arr[left] + "\t" + arr[right]);
            if(arr[left] == target || arr[right] == target){
                return true;
            }
            if(left == right){
                return false;
            }
            left++; right--; 
        }
        return true;
    }
}