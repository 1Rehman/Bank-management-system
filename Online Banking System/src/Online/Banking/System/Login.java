package Online.Banking.System;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel label1, label2, label3;
    JTextField textField2;
    JPasswordField passwordField3;
    JButton button1, button2;
    Login()
    {
        //Title
        super("Online Banking System");

        //Frame
        setLayout(null); //because we are giving the size below.
        setSize(700, 500);
        setLocation(450, 220);


        //Bank icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icon/bank.png")); //image/icon is now stored in i1.
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT); //Scaling(giving size) to the image.
        ImageIcon i3 = new ImageIcon(i2); //Passing Image(i2) in ImageIcon(i3) so that it can be used in JLabel.
        JLabel i4 = new JLabel(i3); //JLabel is a display area and inside which we can display either text or image. We cannot pass Image directly in JLabel, so we get Image(i2) into ImageIcon(i3).
        i4.setBounds(290, 10, 100, 100); //here we are giving size and location to the display area. x&y are for location and width and height are the size which should be same as scaled image.
        add(i4); //Adds the final image to the frame.

        //Welcome to AUR Bank
        label1 = new JLabel("Welcome to AR Bank");
        label1.setForeground(Color.yellow);
        label1.setFont(new Font("Arial Black", Font.BOLD, 30));
        label1.setBounds(150, 110, 450, 40);
        add(label1);

        //Card Number
        label2 = new JLabel("Account no: ");
        label2.setForeground(Color.white);
        label2.setFont(new Font("Calibri", Font.PLAIN, 24));
        label2.setBounds(150, 190, 375, 25);
        add(label2);

        //Card number text box
        textField2 = new JTextField();
        textField2.setFont(new Font("Arial", Font.PLAIN, 15));
        textField2.setBounds(280, 185, 220, 28);
        add(textField2);

        //MPin
        label3 = new JLabel("Pin: ");
        label3.setForeground(Color.white);
        label3.setFont(new Font("Calibri", Font.PLAIN, 24));
        label3.setBounds(150, 240, 375, 25);
        add(label3);

        //Pin text box
        passwordField3 = new JPasswordField();
        passwordField3.setBounds(280, 233, 220, 28);
        add(passwordField3);

        //Sign-in Button
        button1 = new JButton("Sign-in");
        button1.setBounds(280, 300, 100, 28);
        button1.addActionListener(this);
        add(button1);

        //Sign-up Button
        button2 = new JButton("Sign-up");
        button2.setBounds(400, 300, 100, 28);
        button2.addActionListener(this);
        add(button2);

        //Background image(same procedure as above)
        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("Icon/background.jpg"));
        Image ii2 = ii1.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel ii4 = new JLabel(ii3);
        ii4.setBounds(0, 0, 700, 500); //x&y both are zero because to set for whole background.
        add(ii4);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try{
            if (e.getSource()==button1){
                Conn c = new Conn();
                String cardno = textField2.getText();
                String pin = passwordField3.getText();
                String q = "select * from login where card_number = '"+cardno+"' and  pin = '"+pin+"'";
                ResultSet resultSet = c.statement.executeQuery(q);
                if (resultSet.next()){
                    setVisible(false);
                    new main_Class(pin);
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN");
                }


            }else if (e.getSource() == button2){
                new Signup();
                setVisible(false);
            }
        }catch (Exception E){
            E.printStackTrace();
        }

    }

    public static void main(String[] args)
    {

        new Login();
    }
}
