import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PopulateTable {
    public static void main(String[] args) throws IOException {
        PopulateTable test = new PopulateTable("students", 100);
        String[] sourceData = {"studentID.txt","firstName.txt","lastName.txt","sex.txt"};
        System.out.println(test.InsertData("PopulateStudents.sql", "StudentsData",sourceData));
    }
    private String tableName;
    private String workingDirectory;
    private int numEntries;
    Scanner scnr;
    FileWriter write;
    ArrayList<ArrayList<String>> allFileLines;
    ArrayList<String> thisFileLines;

    PopulateTable(String tableName, int numEntries) {
        this.tableName = tableName;
        this.numEntries = numEntries;
        this.workingDirectory = System.getProperty("user.dir");
        this.allFileLines = null;
        this.thisFileLines = null;
    }
    private void clearFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); 
        PrintWriter pw= new PrintWriter(fw, false);
        pw.flush();
        pw.close();
        fw.close();
    }
    private String format(String str){
        if(str != null && str.matches("^[a-zA-Z]*$")){
            return "\"" +str + "\"";
        }
        return str;
    }
    public boolean InsertData(String targetFile, String dataFolderName, String[] dataFileNames){
        try{
            clearFile(targetFile);
            allFileLines = new ArrayList<ArrayList<String>>();
            // Adding the lines of each file into a 2d Array list.
            String dataPath = workingDirectory + "\\" + dataFolderName +  "\\";
            for(String dataFile: dataFileNames){
                scnr  = new Scanner(new File(dataPath + dataFile));
                thisFileLines = new ArrayList<String>();
                while(scnr.hasNextLine()){
                    thisFileLines.add(format(scnr.nextLine().replaceAll("[^A-Za-z0-9]","")));
                }
                if(thisFileLines.size() < numEntries){
                    System.out.println(dataFile + " DOES NOT HAVE "+ numEntries + " ENTRIES");
                    return false;
                }
                allFileLines.add(thisFileLines);
            }

            write = new FileWriter(new File(targetFile),true);

            String StmtBuild = "";
            for(int i = 0; i < numEntries; i++){
                StmtBuild = "INSERT INTO " + tableName + " VALUES (";
                for(int j = 0; j < allFileLines.size(); j++){
                    StmtBuild += allFileLines.get(j).get(i);
                    if(j != allFileLines.size()-1){
                        StmtBuild+=", ";
                    }   
                }
                StmtBuild += ");";   
                write.write(StmtBuild + System.getProperty("line.separator"));         
            }
            write.close();
        } catch(FileNotFoundException error){
            System.out.println(error);
        } catch(IOException error){
            System.out.println(error);
        }
        System.out.println("ADDED CONTENTS FROM " + Arrays.toString(dataFileNames)+ " TO \"" + targetFile+"\"");
        return true;
    }
}
