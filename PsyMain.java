public class PsyMain{
    public static void main(String[] args) {
        // INTACT PARTICIPANTS
        double vocab[] = {68.0,8.0,37.0,16.0,77.0,53.0,58.0,91.0,6.0,34.0};
        double reason[] = {3.242424,2.090909,3.787879,1.30303,3.151515,2.424242,2.575758,3.575758,2.0,1.848485};
        double face[] = {25.6,18.48,97.2,2.88,90.83,29.15,34.8,84.63,10.48,12.24};
        System.out.println("INTACT: BRAIN------------------------------------------");
        System.out.println("\nVocab and Face");
        PSY102 a1 = new PSY102(vocab,face); a1.printResults();
        System.out.println("\nFace and Reason");
        PSY102 a2 = new PSY102(face,reason); a2.printResults();
        System.out.println("\nVocab and Reason");
        PSY102 a3 = new PSY102(vocab,reason); a3.printResults();

        // SPLIT BRAIN PARTICIPANTS
        double vocab2[] = {25.0,58.0,32.0,84.0,23.0,36.0,42.0,9.0,94.0,96.0};
        double reason2[] = {2.76,2.909091,1.212121,2.57,2.41,2.57578,2.424242,1.424242,5.01,3.151515};
        double face2[] = {99.52,70.85,81.52,97.12,39.17,65.2,15.37,1.0,34.4,87.76};
        System.out.println("\nSPLIT BRAIN------------------------------------------");
        System.out.println("\nVocab and Face");
        PSY102 a4 = new PSY102(vocab2,face2); a4.printResults();
        System.out.println("\nFace and Reason");
        PSY102 a5 = new PSY102(face2,reason2); a5.printResults();
        System.out.println("\nVocab and Reason");
        PSY102 a6 = new PSY102(vocab2,reason2); a6.printResults();
    }
}