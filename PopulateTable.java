import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class PopulateTable {
    public static void main(String[] args) throws IOException{
    PopulateTable test = new PopulateTable("DATA", 15,1);
    String[] sourceData = {"C:\\Users\\pumah\\Desktop\\JAVA_REP\\TextFiles\\src1.txt","C:\\Users\\pumah\\Desktop\\JAVA_REP\\TextFiles\\src2.txt"};
    System.out.println(test.InsertData("C:\\Users\\pumah\\Desktop\\JAVA_REP\\TextFiles\\outputTEST.sql", sourceData));

    }
    private String tableName;
    private int numEntries;
    private int numColumns;
    private Scanner read;
    private FileWriter write;
    PopulateTable(String tableName, int numEntries, int numColumns) {
        this.tableName = tableName;
        this.numEntries = numEntries;
        this.numColumns = numColumns;
    }
    public boolean InsertData(String targetFile, String[] fileSources){
        try{

            ArrayList<ArrayList<String>> fileLines = new ArrayList<ArrayList<String>>();
            // Adding the lines of each file into a 2d Array list.
            for(String currentFile: fileSources){
                read  = new Scanner(new File(currentFile));
                ArrayList<String> currentFileLines = new ArrayList<String>();
                while(read.hasNextLine()){
                    currentFileLines.add(read.nextLine());
                }
                fileLines.add(currentFileLines);
            }

            for(ArrayList<String> fileData: fileLines){
                if(fileData.size() < numColumns){
                    System.out.print("THERE IS A MISMATCH IN COLUMN ENTRY SIZE!");
                    return false;
                }
            }

            write = new FileWriter(new File(targetFile),true);
            String StmtBuild = "INSERT INTO " + tableName;
            write.write(StmtBuild + System.getProperty("line.separator"));

            for(int i = 0; i < numEntries; i++){
                StmtBuild = "VALUES (";
                for(int j = 0; j < fileLines.size(); j++){
                    StmtBuild+=fileLines.get(j).get(i);
                    if(j != fileLines.size()-1){
                        StmtBuild+=", ";
                    }   
                }
                StmtBuild+=");";   
                write.write(StmtBuild + System.getProperty("line.separator"));         
            }
            write.close();
        } catch(FileNotFoundException error){
            System.out.println(error);
        } catch(IOException error){
            System.out.println(error);
        }
        System.out.println("SUCCESSFUL");
        return true;
    }
}