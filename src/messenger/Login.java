package messenger;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.event.ActionEvent;

public class Login{
   
   private static Set<String> names = new HashSet<>();
   private static Set<PrintWriter> writers = new HashSet<>();
   
   static String fileName="UserData.txt";
   static BufferedReader input = null;
   static PrintWriter output=null;
   static String[][] UserData=new String[100][100];
   static int[] length=new int[100];
   static int height=0;
   static FileIO read;

   public static void main(String[] args) throws Exception {
      //      о    
      try {
           input = new BufferedReader(new FileReader(fileName));
        } catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + fileName);
            System.exit(0);
        }
        
        String l;
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
      System.out.println("The chat server is running...");
      
      ExecutorService pool = Executors.newFixedThreadPool(500);
      
      try (ServerSocket listener = new ServerSocket(59001)) {
         while (true) {
            
            pool.execute(new Handler(listener.accept()));
         }
      }
   }

   public static void updateFile() throws FileNotFoundException {
         read=new FileIO();
           UserData=read.getUserData();
           length=read.getlength();
           height=read.getheight();
   }
   
   public static void addFile(String user, String newUser) throws IOException {
         
         read=new FileIO();
         read.setNew(user, newUser);
          UserData=read.getUserData();
              length=read.getlength();
              height=read.getheight();
      }

   
   private static class Handler implements Runnable {
      private String name;
      private Socket socket;
      private Scanner in;
      private PrintWriter out;

      
      public Handler(Socket socket) {
         this.socket = socket;
      }

      
      
      @SuppressWarnings("null")
      public void run() {
         try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
         
       
            int user=0;
            while(true) {
               int found1=0;
               int found2=0;
               while(true) {
                  updateFile();
                  String str=in.nextLine();
                  System.out.println(str);
                  for(int i=0;i<height;i++) {
                     if(str.equals(UserData[0][i])) 
                        found1=1;
                  }
                  if(found1==1)break;
                  out.println("IDERROR");
                  break;
               }
         
               while(true) {
                  String str=in.nextLine();
                  System.out.println(str);
                  str = (new StringBuffer(str)).reverse().toString();
                  for(int i=0;i<height;i++) {
                     if(str.equals(UserData[1][i])) {
                        found2=1;
                        user=i;
                     }
                  }
                  if(found2==1)break;
                  out.println("PASSERROR");
                  break;
               }
        
               if(found2==1&&found1==1) {            
                  String data =" ";
                  names.add(UserData[2][user]);
                  for(int i=0;i<length[user];i++) {
                     data=data.concat(UserData[i][user]+" ");
                  }           
                  out.println("SUBMIT"+data); break;
               }
               else out.println("ERROR");   
            }
            
            writers.add(out);
            
            
            while(true) {
               int menu = 0;
               String search = in.nextLine();
               System.out.println(input);         
               
             //사용자를 찾는 과정
               if(search.startsWith("SEARCH")) {
                  search=search.substring(6);
                  String search1=" ";
                  for(int i=1;i<height;i++) {
                     if(UserData[2][i].contains(search)) {
                        search1=search1.concat(UserData[2][i]);
                     }
                  }
                  //클라이언트로로 정보를 다시 넘겨준다
                  out.println("FOUND"+search1);
               } 

               
               //친구추가하는 과정
               if(search.startsWith("ADD")) {
                  search=search.substring(3);
                  String[] users=null;
                  String newUser=null;
                  users=search.split(" ");
                  newUser=users[users.length-1];
                  newUser=newUser.substring(1,newUser.length()-1);
                  //파일 업데이트
                  addFile(users[0], newUser);
                  String sentence = " ";
                  for(int i=7;i<users.length-1;i++) {
                     sentence=sentence.concat(users[i]+" ");
                     System.out.println(users[i]);
                  }
                  //클라이언트로로 정보를 다시 넘겨준다
                  out.println("NEWFOUND"+sentence+newUser);
               } 
               
                if(search.startsWith("CHANGE")){
                        for(int i=1;i<height;i++) {
                           String array[] = search.split("/");
                               if(UserData[2][i].equals(array[1])) {
                                  UserData[2][i] = array[1];
                                  /*
                                   * 업데이트 해주기
                                   */
                            }
                         }
                     }
                     
                     // 팝업 메뉴를 통해 유저 정보 보내기
                     if(search.startsWith("INFO")) {
                        for(int i=1;i<height;i++) {
                           String search1=search.substring(6, search.length()-1);
                               if(UserData[2][i].equals(search1)) {
                                  out.println("INFO/" + UserData[3][i] + "/" + UserData[4][i] + "/" + UserData[6][i]);
                                  break;
                            }
                         }
                     }
                     
                 
                     
                     // 채팅방에 대한 프로토콜
                       if (search.startsWith("CHAT")) {
                          for (PrintWriter writer : writers) {
                             writer.println(search.substring(5));
                          }
                       }
                       
                       // 채팅방 거부에 대한 프로토콜
                       if(search.startsWith("CLOSE")) {
                          for (PrintWriter writer : writers) {
                             writer.println(search);
                          }
                       }
                       
                       // 채팅방 활성화에 대한 프로토콜
                       if(search.startsWith("START")) {
                          for (PrintWriter writer : writers) {
                             writer.println(search);
                          }
                       }
                       
                       // 메세지 전달에 대한 프로토콜
                       if(search.startsWith("MESSAGE")){
                          for (PrintWriter writer : writers) {
                             writer.println(search);
                          }
                       }
            }
         }
         
         catch (Exception e) {
            System.out.println(e);
         }
      }
   }
}