import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class PopulateTable {
    public static void main(String[] args) throws IOException {
        PopulateTable test = new PopulateTable("HenryDB", 25);
        String[] sourceData = {"src1.txt","src2.txt"};
        System.out.println(test.InsertData("PopulateOutput.sql", sourceData));
    }
    private String tableName;
    private int numEntries;
    private Scanner scnr;
    private FileWriter write;
    ArrayList<ArrayList<String>> fileSetLines;
    ArrayList<String> fileLines;

    PopulateTable(String tableName, int numEntries) {
        this.tableName = tableName;
        this.numEntries = numEntries;
        this.fileSetLines = null;
        this.fileLines = null;
    }
    public boolean InsertData(String targetFile, String[] filePaths){
        try{
            fileSetLines = new ArrayList<ArrayList<String>>();
            // Adding the lines of each file into a 2d Array list.
            for(String thisFile: filePaths){
                scnr  = new Scanner(new File(thisFile));
                fileLines = new ArrayList<String>();
                while(scnr.hasNextLine()){
                    fileLines.add(scnr.nextLine());
                }
                if(fileLines.size() < numEntries){
                    System.out.println(thisFile + " DOES NOT HAVE "+ numEntries + " ENTRIES");
                    return false;
                }
                fileSetLines.add(fileLines);
            }
            write = new FileWriter(new File(targetFile),true);
            String StmtBuild = "INSERT INTO " + tableName;
            write.write(StmtBuild + System.getProperty("line.separator"));

            for(int i = 0; i < numEntries; i++){
                StmtBuild = "VALUES (";
                for(int j = 0; j < fileSetLines.size(); j++){
                    StmtBuild+=fileSetLines.get(j).get(i);
                    if(j != fileSetLines.size()-1){
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