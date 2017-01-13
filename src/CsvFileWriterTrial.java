import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CsvFileWriterTrial 
{
  public static void writeCsvFile(String fileName) 
  {
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(fileName);
      fileWriter.append("Alaa was here xD");
      fileWriter.append("\n");
      fileWriter.append("Hello from the other side :D");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
  }
  
  public static void main(String[] args) {
    
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader("AlaaWasHere.csv"));
    } catch (FileNotFoundException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    String line = null;
        try {
          while (( line = reader.readLine()) != null)
          {
            System.out.println(line);
          }
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        
        writeCsvFile("AlaaWasHere.csv");
        writeCsvFile("all_saved_games.csv");

  }
  
  
}
