import java.util.Arrays;
import java.util.HashMap;
import java.lang.Math;
/*S: 9/12/19
E:
Given an array of positive integers, determine whether there are two numbers that
are inverses of each other*/
public class FindInverse{
    public static void main(String[] args) {
        Double val = new Double(1/5.0);
        Double[]testArr = {1.0,2.0,5.0,3.0,6.0,7.0,8.0,val};
        System.out.println("Current Test Array " + Arrays.toString(testArr));
        System.out.println("Output: " + fInverse(testArr));
    }
public static Boolean fInverse(Double[]A){
    HashMap<Double,Boolean> InverseDir = new HashMap<Double,Boolean>();
    for(Double i: A){
        if (Math.floor(i) == i){
            Double newKey = new Double(1/i);
            InverseDir.put(newKey,true);
        }else{
            if(InverseDir.containsKey(i)){
               InverseDir.entrySet().forEach(entry->{
                    System.out.println(entry.getKey() + " " + entry.getValue());  
                 });
                return true;
            }
        }
    }
    return false;
    }
}