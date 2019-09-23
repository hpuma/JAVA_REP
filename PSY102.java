import java.math.*;
public class PSY102{
    public static void main(String[] args) {

    }
    public static int N = 10;
    public double[] data1 = new double[N]; 
    public double[] data2 = new double[N]; 
    PSY102(double []d1, double[]d2){
        This.data1 = d1;
        This.data2 = d2;
    }
    public static double takeSum(double []data,Boolean SQUARED){
        if(data == null){
            return 0;
        }
        else{
            double totalSum = 0;
                for(double i: data){
                    if(SQUARED){
                        totalSum+=Math.pow(i, 2);
                    }else{
                        totalSum+=i;
                    }   
                }
        }
        return totalSum;
    }
    public static double formula(double []data){
        return((N*takeSum(data,true)) - math.pow(takeSum(data,false),2));
    }
    public static double takeXY(){
        if(This.d1 == null || This.d2 == null){
            return -1;
        }
        else{
            double totalSum = 0;
            for(int i = 0; i < N; i++){
                totalSum+=(d1[i] * d2[i]);
            }
        }
        return totalSum;
    }
    public static double formulaThree(){
        if(d1 == null || d2 == null){
            return -1;
        }else{
            return ((N*(takeXY()) - (takeSum(d1,false) * takeSum(d2, false))));
        }
    }
    public static double rValue(){
        if(d1 == null || d2 == null){
            return -1;
        }
        return (formulaThree()/(Math.sqrt(formula(d1) * formula(d2))));
    }
    public static void printResults(){
        System.out.println("R VALUE:\t" + rValue());
        System.out.println("I:\t" + formula(d1));
        System.out.println("II\t" + formula(d2));
        System.out.println("III\t" + formulaThree());
    }
}

