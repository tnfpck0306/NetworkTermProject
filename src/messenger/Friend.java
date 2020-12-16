package messenger;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.GridBagLayout;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JEditorPane;
import java.awt.Font;
import java.awt.Color;

public class Friend{
   String serverAddress;
   static String userName;
   static Scanner in;
   static PrintWriter out;
   private static JFrame friend;
   private JTextField IDtext;
   private JTextField Passwordtext;
   private JButton LoginButton;
   private static JLabel information;
   private JButton info_change;
   private static JPanel Loginpanel;
   private static JPanel FriendPannel;
   private static JList list;
   private static JList Userlist;
   private JTextField SearchtextField;
   private JButton Find;
   private static String userinfo=null;
   private JTextArea publicData;
   static PopupMenu pm;
   static newWindow chat;
   private static String fine="";
   public Friend(String serverAddress) {
      
      chat.title = "NONE";

        this.serverAddress = serverAddress;
      friend = new JFrame();
      friend.setBounds(100, 100, 824, 414);
      friend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      friend.getContentPane().setLayout(null);
      
      FriendPannel = new JPanel();
      FriendPannel.setBounds(0, 0, 806, 367);
      friend.getContentPane().add(FriendPannel);
      FriendPannel.setLayout(null);
      SearchtextField = new JTextField();
      SearchtextField.setBounds(416, 37, 255, 34);
      FriendPannel.add(SearchtextField);
      SearchtextField.setColumns(10);
      
      Find = new JButton("Find");
      Find.setFont(new Font("Times New Roman", Font.PLAIN, 18));
      Find.setBounds(685, 41, 86, 27);
      Find.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String search =SearchtextField.getText();
            out.println("SEARCH"+search);
         }
      });
      FriendPannel.add(Find);
      
      Userlist = new JList();
      Userlist.setBounds(416, 84, 355, 196);
      FriendPannel.add(Userlist);
      information = new JLabel("");
      information.setBounds(29, -1, 346, 72);
      FriendPannel.add(information);
      
       info_change = new JButton("Change");
       info_change.setFont(new Font("Times New Roman", Font.PLAIN, 18));
       info_change.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            String nick = JOptionPane.showInputDialog(friend, "별명 변경", "", JOptionPane.PLAIN_MESSAGE);
             out.println("CHANGE /" + userName + "/" + nick);
          }
       });
       info_change.setBounds(268, 41, 105, 27);
       FriendPannel.add(info_change);
       
       
       System.out.println(fine);
       publicData = new JTextArea(fine);
       publicData.setBounds(29, 301, 742, 54);
       FriendPannel.add(publicData);
       publicData.setColumns(10);
       
       JLabel Friendlabel = new JLabel("Friends");
       Friendlabel.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
       Friendlabel.setBounds(29, 61, 346, 27);
       FriendPannel.add(Friendlabel);
       
       JLabel PublicLabel = new JLabel("Fine Dust");
       PublicLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
       PublicLabel.setBounds(29, 278, 237, 27);
       FriendPannel.add(PublicLabel);
       
       
       FriendPannel.setVisible(false);
      Loginpanel = new JPanel();
      Loginpanel.setBounds(0, 0, 806, 367);
      friend.getContentPane().add(Loginpanel);
      Loginpanel.setLayout(null);
      
      LoginButton = new JButton("Log in");
      LoginButton.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
      LoginButton.setBounds(566, 111, 105, 104);
      //로그인 버튼을 누르면 서버로 아이디와 비빌번호를 넘겨줌
        LoginButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
             out.println(IDtext.getText());
             out.println(Passwordtext.getText());
             IDtext.setText("");
             Passwordtext.setText("");    
         }
      });
        Loginpanel.add(LoginButton);
        
        IDtext = new JTextField();
        IDtext.setBounds(300, 127, 143, 30);
        Loginpanel.add(IDtext);
        IDtext.setColumns(10);
        
        Passwordtext = new JPasswordField();
        Passwordtext.setBounds(300, 183, 143, 30);
        Passwordtext.setColumns(10);
        Loginpanel.add(Passwordtext);
        
        JLabel ID = new JLabel("ID");
        ID.setFont(new Font("Source Code Pro Black", Font.PLAIN, 17));
        ID.setBounds(213, 132, 62, 18);
        Loginpanel.add(ID);
        
        JLabel Password = new JLabel("Password");
        Password.setFont(new Font("Source Code Pro Black", Font.PLAIN, 17));
        Password.setBounds(213, 188, 99, 18);
        Loginpanel.add(Password);    
        JButton RegisterButton = new JButton("register");
        RegisterButton.setBackground(Color.YELLOW);
        RegisterButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
        RegisterButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              JoinFrame frame = new JoinFrame();
           }
        });
        RegisterButton.setBounds(566, 287, 105, 27);
        Loginpanel.add(RegisterButton);
        
        pm = new PopupMenu();
        MenuItem pm_item1 = new MenuItem("send to message");
        MenuItem pm_item2 = new MenuItem("play to game");
        MenuItem pm_item3 = new MenuItem("information");
        MenuItem pm_item4 = new MenuItem("");
        
        pm.add(pm_item1);
        pm.addSeparator(); // 구분선
        pm.add(pm_item2);
        pm.add(pm_item3);
        pm.add(pm_item4);
        
        pm_item1.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if(e.getSource() == pm_item1) {
                  newWindow.userName = userName;
                  chat = new newWindow();
                  out.println("CHAT " + list.getSelectedValuesList() + userName);
                  chat.title = list.getSelectedValuesList() + userName;
                  chat.in = in;
                  chat.out = out;
                  chat.textField2.setEditable(false);
               }
           }           
        });
        
        pm_item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if(e.getSource() == pm_item2) {
                   System.out.println("DONE");
                }
            }           
         });
        
        pm_item3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if(e.getSource() == pm_item3) {      
                   out.println("INFO " + list.getSelectedValuesList());          
                }
            }           
         });    
   }
   
   private static int option() {
         return JOptionPane.showConfirmDialog(
                    friend, 
                    "방에 입장하시겠습니까?", 
                    "Confirm", 
                    JOptionPane.YES_NO_OPTION 
               );
   }
   
   public static void Log() {
      while (in.hasNextLine()) {
           //서버로부터 읽어옴
           String line = in.nextLine();
           System.out.println("1." + line);
           //접속허용
           
           if(line.startsWith("IDERROR"))  JOptionPane.showMessageDialog(friend, "Error : Non-existent ID");
           if(line.startsWith("PASSERROR"))  JOptionPane.showMessageDialog(friend, "Error : Invalid password");
           
            if (line.startsWith("SUBMIT")) {
                String[] friends=null;
                 String[] friends2 = null;
                line=line.substring(7);
                System.out.println(line);
                friends=line.split(" ");
                String friendLine = "";
                for(int i = 7; i < friends.length; i++) {
                   friendLine = friendLine + " " + friends[i];
                }
               
                friends2 = friendLine.split(" ");

                list = new JList(friends2);
                list.setBounds(30, 84, 274, 200);
                list.add(pm);
                
                //팝업 메뉴창 표시
                list.addMouseListener(new MouseAdapter() {                     
                    @Override
                    public void mousePressed(MouseEvent me) {                       
                       
                        // 오른쪽 마우스 버튼을 누르면 PopupMenu를 화면에 보여준다.
                       if (me.getButton() == MouseEvent.BUTTON3) {
                          list.setSelectedIndex(getRow(me.getPoint()));
                          pm.show(list, me.getX(), me.getY());   
                        }
                    }

               private int getRow(Point point) {
                  return list.locationToIndex(point);
               }
                });
                
                FriendPannel.add(list);

             //유저의 정보 표시
                information.setText("<html>"+"NAME : "+friends[3]+"<br>"+"E-mail : "+friends[4]+"<br>"+"Phone : "+friends[6]+"</html>");
                userinfo=line;
                userName=friends[2];
             
                FriendPannel.setVisible(true);
                Loginpanel.setVisible(false);
               }
            
            
            //새로운 사용자를 찾으면
           if(line.startsWith("FOUND")) {
              DefaultListModel listmodel = null;
                String[] users=null;
                line=line.substring(5);
                users=line.split(" ");
                listmodel = new DefaultListModel();
                for(int i=0;i<users.length;i++) {
            
                   listmodel.addElement(users[i]);
                   //리스트에 사용자를 표시
                   Userlist.setModel(listmodel);
                      }
                //리스트에서 사용자를 선택하면 친구로 추가
                Userlist.addListSelectionListener(new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                           out.println("ADD"+userinfo+Userlist.getSelectedValuesList());
                            System.out.println("ADD"+userinfo+Userlist.getSelectedValuesList());
                        }

                    }
                });
           }

           //새로 등록된 친구를 친구 리스트에 등록
           if(line.startsWith("NEWFOUND")) {
              line=line.substring(9);
              String[] info=null;
              info=line.split(" ");
                //리스트 업데이트 과정
              DefaultListModel listmodel = new DefaultListModel();
              for(int i=0;i<info.length;i++) {
                 listmodel.addElement(info[i]);
                }
                list.setModel(listmodel);
           }
           
           //채팅방 참가하기
           if (line.startsWith("[" + userName + "]")) {
              int result = option();
              
              // 프로그램을 닫으면
               if(result == JOptionPane.CLOSED_OPTION) {
                  out.println("CLOSE / " + line);
               }
               
               // 옵션에서 yes를 클릭하면
               else if(result == JOptionPane.YES_OPTION) {
                  newWindow.userName = userName;
                  chat = new newWindow();
                  chat.title = line;
                  chat.in = in;
                  chat.out = out;
                  out.println("START / " + newWindow.title);
               }
               
               // 옵션에서 no를 클릭하면
               else {
                  out.println("CLOSE / " + line);
               }         
           }
           
           // 채팅을 거부하거나, 한명이 나가면
           if (line.startsWith("CLOSE")) {
              System.out.println(chat.title);
              System.out.println(line);
              if(line.endsWith(chat.title)) {
                 chat.messageField.setText("");
                 chat.dispose();
              }
           }
           
           // 채팅이 시작되면, 채팅창 활성화
           if (line.startsWith("START")) {
              if(line.endsWith(chat.title)) {
                 chat.textField2.setEditable(true);
              }
           }
           
           // 메세지 내용이 오면 채팅방에 출력
           if (line.startsWith("MESSAGE")) {
              if(line.endsWith(chat.title)) {
                 String array[] = line.split("/");
                 chat.messageField.append(array[1] + "\n");
              }
           }
           
           if (line.startsWith("INFO")) {
              String array[] = line.split("/");
              JOptionPane.showMessageDialog(friend, array[1] + "\n" + array[2] + "\n" + array[3], "Information", JOptionPane.PLAIN_MESSAGE);
           }
      }
            

   }

   private void run() throws Exception {
      try{
           Socket socket = new Socket(serverAddress, 59001);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);  
     
           Log();
      }//try 끝
      finally {}

    }//run 함수 끝
   
   public static void main(String[] args) throws Exception {     
 
                 // 미세먼지 경보 정보 조회 서비스 - 한국환경공단 api 서비스
                    try {
                        // 인증키
                        String serviceKey = "CzYhQo4IxF3EOauiocZ0%2B21CO9pHEmDOzvaY3CdyFuswL%2BjedKxQiTMDM9AcaYHxPKDfUuTTthVEtwebQAimPQ%3D%3D";
                        
                        String urlStr = "http://openapi.airkorea.or.kr/openapi/services/rest/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo";
                        urlStr += "?"+ URLEncoder.encode("ServiceKey","UTF-8") +"=" + serviceKey;
                        urlStr += "&"+ URLEncoder.encode("numOfRows","UTF-8") +"=200";
                        urlStr += "&"+ URLEncoder.encode("pageNo","UTF-8") +"=1";
                        urlStr += "&"+ URLEncoder.encode("year","UTF-8") +"=2020";
                        urlStr += "&"+ URLEncoder.encode("_returnType","UTF-8") +"=json";
                        
                        URL url = new URL(urlStr);
                        
                        String line = "";
                        String result = "";
                        
                        BufferedReader br;
                        br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
                        while ((line = br.readLine()) != null) {
                            result = result.concat(line);
                            //System.out.println(line);                
                        }      
                        // JSON parser 만들어 문자열 데이터를 객체화한다.
                        JSONParser parser = new JSONParser();
                        JSONObject obj = (JSONObject)parser.parse(result);
                        
                        // list 아래가 배열형태로
                        // {"list" : [ {"returnType":"json","clearDate":"--",.......} ] 
                        JSONArray parse_listArr = (JSONArray)obj.get("list");
                        
                        String miseType = "";
                        
                        // 객체형태로
                        // {"returnType":"json","clearDate":"--",.......},... 
                        int p=0;
                        for (int i=0;i< parse_listArr.size();i++) {
                            JSONObject weather = (JSONObject) parse_listArr.get(i);
                            String dataDate = (String) weather.get("dataDate");            // 발령날짜
                            String districtName = (String) weather.get("districtName");    // 발령지역
                            String moveName = (String) weather.get("moveName");            // 발령권역
                            String issueDate = (String) weather.get("issueDate");        // 발령일자
                            String issueTime = (String) weather.get("issueTime");        // 발령시간
                            String issueVal  = (String) weather.get("issueVal");        // 발령농도
                            String itemCode  = (String) weather.get("itemCode");        // 미세먼지 구분 PM10, PM25
                            String issueGbn  = (String) weather.get("issueGbn");        // 경보단계 : 주의보/경보
                            String clearDate = (String) weather.get("clearDate");        // 해제일자
                            String clearTime = (String) weather.get("clearTime");        // 해제시간
                            String clearVal = (String) weather.get("clearVal");            // 해제시 미세먼지농도
                            
                            if (itemCode.equals("PM10")) {            
                                miseType = "";
                            } else if (itemCode.equals("PM25")) {    
                                miseType = "초미세먼지";
                            }
                            StringBuffer sb = new StringBuffer();
                            sb.append("발령날짜 : " + dataDate + ", 지역 : " + districtName + " ("+ moveName +"), " + "발령시간 : "+ issueDate + " " + issueTime + ", 농도 : " + issueVal + " ("+ issueGbn +") " + miseType);
                           
                            System.out.println(sb.toString());
                            
                           if(p<2) {fine=fine.concat(sb.toString()+"\n");p++;}
                        }

                        
                        br.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    Friend client = new Friend ("127.0.0.1");
                    client.friend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //프레임이 보여지게 만듦
                    client.friend.setVisible(true);
                    client.run();
   }
}