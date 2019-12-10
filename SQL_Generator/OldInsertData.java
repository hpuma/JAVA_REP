import java.io.File;
import java.util.ArrayList;

public class OldInsertData {
    public static void main(String[] args) {}
    // This works but it doesn't add the file data in the correct column order, must find a way to insert in the correct order.
    public boolean InsertData(String targetFile, String dataFolderName){
        try{
            clearFile(targetFile); // Clear the target file
            ArrayList<ArrayList<String> > allFileLines = new ArrayList<ArrayList<String>>();
            String dataFolderPath = workingDirectory + "\\" + dataFolderName +  "\\";
            File dataFolder = new File(dataFolderPath);
            File[] dataFileList = dataFolder.listFiles(new FilenameFilter(){
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".txt");
                }
            });
            for(File dataFile: dataFileList){
                scnr = new Scanner(dataFile);
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
            // String StmtBuild = "";
            StringBuilder StmtBuild = new StringBuilder("");
            for(int i = 0; i < numEntries; i++){
                StmtBuild = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
                for(int j = 0; j < allFileLines.size(); j++){
                    StmtBuild.append(allFileLines.get(j).get(i));
                    if(j != allFileLines.size()-1){
                        StmtBuild.append(", ");
                    }   
                }
                StmtBuild.append(");"); 
                write.write(StmtBuild + System.getProperty("line.separator"));         
            }
            write.close();
            System.out.println("ADDED CONTENTS FROM " + Arrays.toString(dataFileList)+ " TO \"" + targetFile+"\"");
        } catch(FileNotFoundException error){
            System.out.println(error);
        } catch(IOException error){
            System.out.println(error);
        }
        return true;
    }
}