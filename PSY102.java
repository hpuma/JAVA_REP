public class PSY102{
    public static void main(String[] args) {
        // INTACT PARTICIPANTS
        double vocab[] = {68.0,8.0,37.0,16.0,77.0,53.0,58.0,91.0,6.0,34.0};
        double reason[] = {3.242424,2.090909,3.787879,1.30303,3.151515,2.424242,2.575758,3.575758,2.0,1.848485};
        double face[] = {25.6,18.48,97.2,2.88,90.83,29.15,34.8,84.63,10.48,12.24};
        PSY102 a1 = new PSY102(vocab,face); a1.printResults();
        PSY102 a2 = new PSY102(face,reason); a2.printResults();
        PSY102 a3 = new PSY102(vocab,reason); a3.printResults();

        // SPLIT BRAIN PARTICIPANTS
        double vocab2[] = {25.0,58.0,32.0,84.0,23.0,36.0,42.0,9.0,94.0,96.0};
        double reason2[] = {2.76,2.909091,1.212121,2.57,2.41,2.57578,2.424242,1.424242,5.01,3.151515};
        double face2[] = {99.52,70.85,81.52,97.12,39.17,65.2,15.37,1.0,34.4,87.76};
        PSY102 a4 = new PSY102(vocab2,face2); a4.printResults();
        PSY102 a5 = new PSY102(face2,reason2); a5.printResults();
        PSY102 a6 = new PSY102(vocab2,reason2); a6.printResults();
    }
    public static int N = 10;
    public static double[] data1 = new double[N]; 
    public static double[] data2 = new double[N]; 
    PSY102(double []d1, double[]d2){
        data1 = d1;
        data2 = d2;
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
        System.out.println("II\t" + formula(data2));
        System.out.println("III\t" + formulaThree());
    }
}

