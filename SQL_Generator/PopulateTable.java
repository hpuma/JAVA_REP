import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
// java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

public class PopulateTable {
    // TESTING
    public static void main(String[] args) throws IOException {
        // OLD INSERT
        PopulateTable test = new PopulateTable("students", 100);
        String[] sourceData = {"studentID.txt","firstName.txt","lastName.txt","sex.txt"};
        // System.out.println(test.InsertData("PopulateStudents.sql", "StudentsData",sourceData));

        // NEW INSERT
        // PopulateTable test = new PopulateTable("students", 100);
        // System.out.println(test.InsertData("PopulateStudents.sql", "StudentsData"));

        ArrayList<ArrayList<String> > ListLines = test.getFileLines("StudentsData", sourceData);
        ArrayList<String> SQLStatements = test.getSQLStatements(ListLines);

        // PRINT LIST LINES

        // for (int i = 0; i < ListLines.size(); i++){
        //     System.out.println("For " + sourceData[i]);
        //     for (int j = 0; j < ListLines.get(i).size(); j++){
        //         System.out.println(ListLines.get(i).get(j));
        //     }   
        // }

        // PRINT SQLSTATMENTS

        for(String Stmt : SQLStatements){
            System.out.println(Stmt);
        }
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
    // Clears the contents of the specific file
    private void clearFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); 
        PrintWriter pw= new PrintWriter(fw, false);
        pw.flush();
        pw.close();
        fw.close();
    }
    // Checks to see if the column data is text, if so then surround it with quotation marks
    private String format(String str){
        if(str != null && str.matches("^[a-zA-Z]*$")){ return "\"" +str + "\"";}
        return str;
    }
    public ArrayList<ArrayList<String> > getFileLines(String dataFolderName,String[] dataFileNames){
        try{
            ArrayList<ArrayList<String> > ListFileLines = new ArrayList<ArrayList<String>>(); // 2D ArrayList containing the lines of each txt file
            String dataPath = workingDirectory + "\\" + dataFolderName +  "\\"; // Full path of the dataFolder
            // Iterates through each file in the dataFileNames array
            for(String dataFile: dataFileNames){
                // Scanner used to read each file
                scnr  = new Scanner(new File(dataPath + dataFile));
                // Array list used to store the lines of the current file
                thisFileLines = new ArrayList<String>();
                // Adding all the liens of the file into the array list as strings
                while(scnr.hasNextLine()){
                    // Filter out non alphabetic or numeric symbols, then insert in quotation marks if the input is alphabetic
                    thisFileLines.add(format(scnr.nextLine().replaceAll("[^A-Za-z0-9]","")));
                }
                // If the file doesn't have the requiered amount of lines, then abort the writing and let the user know
                if(thisFileLines.size() < numEntries){
                    System.out.println(dataFile + " DOES NOT HAVE "+ numEntries + " ENTRIES");
                    return null;
                }
                // Add the lines of the file into the 2D array
                ListFileLines.add(thisFileLines);
            }
            return ListFileLines;
        } catch(FileNotFoundException ex){
            System.out.println(ex);
        }
        return null;
    }
    public ArrayList<String> getSQLStatements(ArrayList<ArrayList<String> > ListFileLines){
        // StmtBuild: Used to build each SQL line
        ArrayList<String> SQLStatements = new ArrayList<>();
        StringBuilder StmtBuild = new StringBuilder();
        for(int i = 0; i < numEntries; i++){ // Add numEntries SQL Statements
            StmtBuild = new StringBuilder( "INSERT INTO " + tableName + " VALUES ("); // Begin the SQL statement
            for(int j = 0; j < ListFileLines.size(); j++){ // Iterate throught the data text files
                StmtBuild.append(ListFileLines.get(j).get(i));
                if(j != ListFileLines.size()-1){ // Add Commas where appropriate in the statment
                    StmtBuild.append(", ");
                }   
            }
            StmtBuild.append(");"); // Finalazing the SQL Statement 
            SQLStatements.add(StmtBuild.toString());     
        }
        return SQLStatements;
    }
    // Creates an SQL file that inserts into a table using the contents of the files in "dataFilesNames"
    // NOTE: The target file is always cleared before anything
    // NOTE: The data file names must follow the column order of the table
    // targetFile: The file name where the SQL statements will be written
    // dataFolderName: The folder where the .txt files are located in
    // dataFileNames: Array containing the names of each txt files with the data
    public boolean InsertData(String targetFile, String dataFolderName, String[] dataFileNames){
        try{
            clearFile(targetFile); // Clear the target file before writing anything
            allFileLines = getFileLines(dataFolderName, dataFileNames);
            // Setting up the writing process for the target file
            write = new FileWriter(new File(targetFile),true);
            // StmtBuild: Used to build each SQL line
            StringBuilder StmtBuild = new StringBuilder();
            for(int i = 0; i < numEntries; i++){ // Add numEntries SQL Statements
                StmtBuild = new StringBuilder( "INSERT INTO " + tableName + " VALUES ("); // Begin the SQL statement
                for(int j = 0; j < allFileLines.size(); j++){ // Iterate throught the data text files
                    StmtBuild.append(allFileLines.get(j).get(i));
                    if(j != allFileLines.size()-1){ // Add Commas where appropriate in the statment
                        StmtBuild.append(", ");
                    }   
                }
                StmtBuild.append(");"); // Finalazing the SQL Statement
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
    // This works but it doesn't add the file data in the correct column order, must find a way to insert in the correct order.
    // public boolean InsertData(String targetFile, String dataFolderName){
    //     try{
    //         clearFile(targetFile); // Clear the target file
    //         ArrayList<ArrayList<String> > allFileLines = new ArrayList<ArrayList<String>>();
    //         String dataFolderPath = workingDirectory + "\\" + dataFolderName +  "\\";
    //         File dataFolder = new File(dataFolderPath);
    //         File[] dataFileList = dataFolder.listFiles(new FilenameFilter(){
    //             @Override
    //             public boolean accept(File dir, String name) {
    //                 return name.toLowerCase().endsWith(".txt");
    //             }
    //         });
    //         for(File dataFile: dataFileList){
    //             scnr = new Scanner(dataFile);
    //             thisFileLines = new ArrayList<String>();
    //             while(scnr.hasNextLine()){
    //                 thisFileLines.add(format(scnr.nextLine().replaceAll("[^A-Za-z0-9]","")));
    //             }
    //             if(thisFileLines.size() < numEntries){
    //                  System.out.println(dataFile + " DOES NOT HAVE "+ numEntries + " ENTRIES");
    //                  return false;
    //             }
    //             allFileLines.add(thisFileLines);
    //         }
    //         write = new FileWriter(new File(targetFile),true);
    //         // String StmtBuild = "";
    //         StringBuilder StmtBuild = new StringBuilder("");
    //         for(int i = 0; i < numEntries; i++){
    //             StmtBuild = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
    //             for(int j = 0; j < allFileLines.size(); j++){
    //                 StmtBuild.append(allFileLines.get(j).get(i));
    //                 if(j != allFileLines.size()-1){
    //                     StmtBuild.append(", ");
    //                 }   
    //             }
    //             StmtBuild.append(");"); 
    //             write.write(StmtBuild + System.getProperty("line.separator"));         
    //         }
    //         write.close();
    //         System.out.println("ADDED CONTENTS FROM " + Arrays.toString(dataFileList)+ " TO \"" + targetFile+"\"");
    //     } catch(FileNotFoundException error){
    //         System.out.println(error);
    //     } catch(IOException error){
    //         System.out.println(error);
    //     }
    //     return true;
    // }
}