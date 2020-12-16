package messenger;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JoinFrame extends JFrame {

   private JPanel contentPane;
   private JLabel lblJoin;
   private JButton joinCompleteBtn;
   private JTextField tfUsername;
   private JTextField tfPassword;
   private JTextField tfName;
   private JTextField tfEmail;
   private JTextField tfPhone;
   private JTextField tfNickname;
   private JTextField tfBirth;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               JoinFrame frame = new JoinFrame();

            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public JoinFrame() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(430, 490);
      setLocationRelativeTo(null);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      lblJoin = new JLabel("Sign up");
      Font f1 = new Font("돋움", Font.BOLD, 20); // 궁서 바탕 돋움
      lblJoin.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      lblJoin.setBounds(159, 25, 101, 29);
      contentPane.add(lblJoin);

      JLabel lblUsername = new JLabel("Password");
      lblUsername.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblUsername.setBounds(69, 130, 76, 20);
      contentPane.add(lblUsername);

      JLabel lblId = new JLabel("ID");
      lblId.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblId.setBounds(69, 89, 69, 20);
      contentPane.add(lblId);

      JLabel lblName = new JLabel("Name");
      lblName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblName.setBounds(69, 213, 69, 20);
      contentPane.add(lblName);

      JLabel lblEmail = new JLabel("Email");
      lblEmail.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblEmail.setBounds(69, 255, 69, 20);
      contentPane.add(lblEmail);

      JLabel lblPhone = new JLabel("phone");
      lblPhone.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblPhone.setBounds(69, 342, 69, 20);
      contentPane.add(lblPhone);

      JLabel lblNickname = new JLabel("Nickname");
      lblNickname.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblNickname.setBounds(69, 168, 76, 18);
      contentPane.add(lblNickname);

      tfUsername = new JTextField();
      tfUsername.setColumns(10);
      tfUsername.setBounds(159, 82, 186, 27);
      contentPane.add(tfUsername);

      tfPassword = new JTextField();
      tfPassword.setColumns(10);
      tfPassword.setBounds(159, 129, 186, 27);
      contentPane.add(tfPassword);

      tfName = new JTextField();
      tfName.setColumns(10);
      tfName.setBounds(159, 210, 186, 27);
      contentPane.add(tfName);

      tfEmail = new JTextField();
      tfEmail.setColumns(10);
      tfEmail.setBounds(159, 252, 186, 27);
      contentPane.add(tfEmail);

      tfPhone = new JTextField();
      tfPhone.setColumns(10);
      tfPhone.setBounds(159, 341, 186, 27);
      contentPane.add(tfPhone);

      joinCompleteBtn = new JButton("Complete");
      joinCompleteBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      joinCompleteBtn.setBounds(259, 402, 139, 29);
      contentPane.add(joinCompleteBtn);

      tfNickname = new JTextField();
      tfNickname.setBounds(159, 168, 186, 27);
      contentPane.add(tfNickname);
      tfNickname.setColumns(10);

      JLabel lblBirth = new JLabel("Birth");
      lblBirth.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblBirth.setBounds(69, 304, 62, 18);
      contentPane.add(lblBirth);

      tfBirth = new JTextField();
      tfBirth.setBounds(159, 302, 186, 27);
      contentPane.add(tfBirth);
      tfBirth.setColumns(10);

      setVisible(true);
      // 회원가입완료 액션
      joinCompleteBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {

            try {
               BufferedWriter user = new BufferedWriter(new FileWriter("userData.txt", true));
               BufferedReader user1 = new BufferedReader(new FileReader("userData.txt"));
               String str = null;

               while ((str = user1.readLine()) != null) {
                  String[] arr = str.split(",");

                  if (tfUsername.getText().equalsIgnoreCase(arr[0])) {
                     JOptionPane.showMessageDialog(null, "해당 아이디가 존재합니다! 다시 가입해주세요.");
                     System.exit(0);
                  } 
               }
               
               StringBuffer strBuffer = new StringBuffer(); 
               String password = tfPassword.getText();
               strBuffer.append(password);
                      
               user.write(tfUsername.getText() + ",");
               user.write(strBuffer.reverse() + ",");
               user.write(tfNickname.getText() + ",");
               user.write(tfName.getText() + ",");
               user.write(tfEmail.getText() + ",");
               user.write(tfBirth.getText() + ",");
               user.write(tfPhone.getText() + "\n");

               user.close();
               
               JOptionPane.showMessageDialog(null, "Congratulations! Sign up is complete!");
               dispose();
               

            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, "Sorry!! you fail to sign up.");
            }
            dispose();

         }
      });

   }
}