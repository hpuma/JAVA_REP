public class PSY102{
    /* S:9/23/19
    E:9/24/19
    NOTE: ARRAYS MUST BE OF TYPE  DOUBLE!! and Array size MUST be 10 and non empty!*/
    public static void main(String[] args) {
    }
    // N is the number of participants, we will always expect N = 10.
    public static int N = 10;
    // Data Arrays meant from vocab, reason or face. We are using DOUBLE for more precision!
    // data1 = X and data2 = Y
    public static double[] data1 = new double[N]; 
    public static double[] data2 = new double[N]; 
    // Constructor that takes two arrays.
    PSY102(double []d1, double[]d2){
        data1 = d1;
        data2 = d2;
    }
    // Takes the sum of an array, if SQUARED, then we square each element instead and add.
    public static double takeSum(double []data,Boolean SQUARED){
        // In case we are given an empty array.
        if(data.length == 0){
            return 0;
        }
        else{
            //Keeps track of the total sum.
            double totalSum = 0;
                for(double i: data){
                    // Square the value and add.
                    if(SQUARED){
                        totalSum+=Math.pow(i, 2);
                    }else{
                        // Just add.
                        totalSum+=i;
                    }   
                }
            return totalSum;
        }
    }
    // Function meant for formulas I and II since they are practically the same.
    public static double formula(double []data){
        // N times the sum of each element squared  minus the total sum and then squared.
        return((N*takeSum(data,true)) - Math.pow(takeSum(data,false),2));
    }
    // Takes the total sum of each X * Y element, In this case for each element of data1 and data2.
    public static double takeXY(){
        // If one of them is empty, then return a negative number.
        if(data1.length == 0 || data2.length == 0){
            return -1;
        }
        else{
            double totalSum = 0;
            // Iterated through both arrays and sums up each data1 * data2 
            for(int i = 0; i < N; i++){
                totalSum+=(data1[i] * data2[i]);
            }
            return totalSum;
        }
    }
    //Taken straight from the formula
    public static double formulaThree(){
        if(data1.length == 0 || data2.length == 0){
            return -1;
        }else{ 
            return ((N*takeXY()) - (takeSum(data1,false) * takeSum(data2, false)));
        }
    }
    // Taken straight from the formula.
    public static double rValue(){
        if(data1.length == 0 || data2.length == 0){
            return -1;
        }
        return (formulaThree()/(Math.sqrt(formula(data1) * formula(data2))));
    }
    // Computes and prints the value for each.
    public void printResults(){
        System.out.println("R VALUE:\t" + rValue());
        System.out.println("I:\t" + formula(data1));
        System.out.println("II:\t" + formula(data2));
        System.out.println("III:\t" + formulaThree());
    }
}
