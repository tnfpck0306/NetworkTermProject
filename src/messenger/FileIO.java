package messenger;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class FileIO {

   String fileName="UserData.txt";
   String[][] UserData=new String[100][100];
   int[] length=new int[100];
    int height=0;
    
   public FileIO() throws FileNotFoundException{
   BufferedReader input = new BufferedReader(new FileReader(fileName));

   String l;
    try {
      while((l=input.readLine())!=null) {
          
         String[] array=l.split(",");
         for(int i=0;i<array.length;i++) {
            UserData[i][height]=array[i];
            System.out.println(UserData[i][height]);
            }
         length[height]=array.length; 
         height++;
      }
      input.close();
   } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }
   }
   
   public void setNew(String user, String newUser) throws IOException {

      height=0;
      File inputFile=new File(fileName);
      FileInputStream fis = new FileInputStream(inputFile);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
        
      File outFile = new File("$$$$$$$$.tmp");
        FileOutputStream fos = new FileOutputStream(outFile);
          PrintWriter out = new PrintWriter(fos);
          
      String l;
       try {
         while((l=in.readLine())!=null) {
             
            String[] array=l.split(",");
           if(array[0].equals(user)){
              l=l.concat(","+newUser);
              String[] arr1=l.split(",");
              for(int i=0;i<arr1.length;i++) {
                  UserData[i][height]=arr1[i];
                  System.out.println(UserData[i][height]);
                  }
              length[height]=arr1.length; 
               height++;
               out.println(l);
           }
           else {
              for(int i=0;i<array.length;i++) {
                  UserData[i][height]=array[i];
                  System.out.println(UserData[i][height]);
                  }
              length[height]=array.length; 
               height++;
               out.println(l);
           }
         }
         
         out.flush();
         out.close();
         in.close();
         
         inputFile.delete();
         
         outFile.renameTo(inputFile);
      
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }
   
   public String[][] getUserData(){
      return UserData;
   }
   
   public int getheight() {
      return height;
   }
   
   public int[] getlength() {
      return length;
   }
}