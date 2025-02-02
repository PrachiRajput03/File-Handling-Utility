import java.io.*;
public class FileOperations {
    //It writes content to a file
    public static void WriteToFile(String fileName, String data) {
        FileWriter fileWriter = null;
        try {
            //Code for writing content
            fileWriter = new FileWriter(fileName);
            fileWriter.write(data);
            System.out.println("File is written successfully!");
        } catch (IOException e) {
            System.out.println("Sorry!, unable to write to the file. Please check the file path.");
        } finally {
            try {
                if (fileWriter != null) fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while closing the file writer.");
            }
        }
    }

    //Reads content from a file
    public static void ReadFromFile(String fileName) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            //code for reading content
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            System.out.println("Reading content from the file:");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {                //In case the file doesn't exist
            System.out.println("File doesn't exist.");  
        } catch (IOException e) {
            System.out.println("Error while reading the file.");
        } finally {
            try {
                if(bufferedReader != null) bufferedReader.close();
                if(fileReader != null) fileReader.close();
            } catch (IOException e) {                      //In case if the file can't be closed due to some error
                System.out.println("Some error occurred while closing the file reader.");
            }
        }
    }

    //Modifies content of the file by replacing a word
    public static void ModifyFileContent(String fileName, String oldWord, String newWord) {
        File tempFile = new File("temp.txt");  //Temporary File
        File ogFile = new File(fileName);     //Original File
        BufferedReader reader  = null;
        BufferedWriter writer = null;
        
        //Code for modification
        try {
            reader = new BufferedReader(new FileReader (ogFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                String modifiedLine = currentLine.replace(oldWord, newWord);    //Replacing old word with the new word
                writer.write(modifiedLine);   //Displays the modified line
                writer.newLine();      //Just to add a new line (Not necessary though)

            }
            writer.close();
            reader.close();

            //Now replacing the old file with the new file
            if(ogFile.delete()){
                tempFile.renameTo(ogFile);   //Renames the temporary file to the original file
                System.out.println("Hurray!, File is successfully modified.");
            } else{
                System.out.println("Sorry!, couldn't replace the original file.");   //In case the replacing failed

            }
        } catch(IOException e){
            System.out.println("An issue occurred while modifying the file..");
        } finally {
            try{
                if(reader != null) reader.close();
                if(writer != null) writer.close();
            } catch(IOException e){
                System.out.println("Error while closing file streams.");
            }
        }
    }

    //Main Function
    public static void main(String[] args){
        //File name for demonstration purpose
        String fileName = "demo.txt";

        //Step 1: Write content to the file
        WriteToFile(fileName, "Hello! My name is Prachi and this is a demo file.\nFile Hanndling in Java is fun.");

        //Step 2: REad content of the file
        ReadFromFile(fileName);

        //Step 3: modify file content
        ModifyFileContent(fileName, "demo", "sample");

        //Step 4: Read the file again to verify the changes
        ReadFromFile(fileName);
    }
}
