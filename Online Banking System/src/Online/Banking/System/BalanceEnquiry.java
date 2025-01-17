package Online.Banking.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {

    String pin;
    JLabel label2;
    JButton b1;
    BalanceEnquiry(String pin){
        this.pin =pin;

//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
//        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel l3 = new JLabel(i3);
//        l3.setBounds(0,0,1550,830);
//        add(l3);

        JLabel label1 = new JLabel("Your Current Balance is Rs ");
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("System", Font.BOLD, 30));
        label1.setBounds(133,70,500,35);
        add(label1);

        label2 = new JLabel();
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("System", Font.BOLD, 30));
        label2.setBounds(134,125,500,35);
        add(label2);

        b1 = new JButton("Back");
        b1.setBounds(430,270,130,40);
        b1.setFont(new Font("System", Font.BOLD, 20));
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        int balance = 0;
        try{
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("Select * from bank where pin = '"+pin+"'");
            while (resultSet.next()){
                if (resultSet.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        label2.setText(""+balance);

        setLayout(null);
        setSize(700, 500);
        setLocation(450,220);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main_Class(pin);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
