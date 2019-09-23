public class PSY102{
    public static void main(String[] args) {
    }
    public static int N = 10;
    public static double[] data1 = new double[N]; 
    public static double[] data2 = new double[N]; 
    PSY102(double []d1, double[]d2){
        data1 = d1;
        data2 = d2;
    }
    public static double takeSum(double []data,Boolean SQUARED){
        if(data.length == 0){
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
            return totalSum;
        }
    }
    public static double formula(double []data){
        return((N*takeSum(data,true)) - Math.pow(takeSum(data,false),2));
    }
    public static double takeXY(){
        if(data1.length == 0 || data2.length == 0){
            return -1;
        }
        else{
            double totalSum = 0;
            for(int i = 0; i < N; i++){
                totalSum+=(data1[i] * data2[i]);
            }
            return totalSum;
        }
    }
    public static double formulaThree(){
        if(data1.length == 0 || data2.length == 0){
            return -1;
        }else{
            return ((N*(takeXY()) - (takeSum(data1,false) * takeSum(data2, false))));
        }
    }
    public static double rValue(){
        if(data1.length == 0 || data2.length == 0){
            return -1;
        }
        return (formulaThree()/(Math.sqrt(formula(data1) * formula(data2))));
    }
    public static void printResults(){
        System.out.println("R VALUE:\t" + rValue());
        System.out.println("I:\t" + formula(data1));
        System.out.println("II:\t" + formula(data2));
        System.out.println("III:\t" + formulaThree());
    }
}

