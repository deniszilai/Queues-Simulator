package ro.tuc.tp.GUI.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class View extends JFrame {

    private final JPanel contentPane;
    private final JTextField textField;
    private final JTextField textField_1;
    private final JTextField textField_2;
    private final JTextField textField_3;
    private final JTextField textField_4;
    private final JTextField textField_5;
    private final JTextField textField_6;
    private final JButton btnNewButton;

    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(150, 150, 550, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(7, 7, 7, 7));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lb1 = new JLabel("Queues Simulator");
        lb1.setFont(new Font("Roboto", Font.PLAIN, 25));
        lb1.setBounds(83, 10, 200, 25);
        contentPane.add(lb1);

        JLabel lb2 = new JLabel("No of Queues:");
        lb2.setFont(new Font("Roboto", Font.PLAIN, 17));
        lb2.setBounds(10, 55, 150, 25);
        contentPane.add(lb2);

        JLabel lb3 = new JLabel("No of Clients:");
        lb3.setFont(new Font("Roboto", Font.PLAIN, 17));
        lb3.setBounds(10, 95, 150, 25);
        contentPane.add(lb3);

        JLabel lb4 = new JLabel("Simulation time:");
        lb4.setFont(new Font("Roboto", Font.PLAIN, 17));
        lb4.setBounds(10, 135, 150, 25);
        contentPane.add(lb4);

        JLabel lb5 = new JLabel("Min arrival time:");
        lb5.setFont(new Font("Roboto", Font.PLAIN, 17));
        lb5.setBounds(10, 175, 150, 25);
        contentPane.add(lb5);

        JLabel lb6 = new JLabel("Max arrival time:");
        lb6.setFont(new Font("Roboto", Font.PLAIN, 17));
        lb6.setBounds(10, 215, 150, 25);
        contentPane.add(lb6);

        JLabel lb7 = new JLabel("Min service time:");
        lb7.setFont(new Font("Roboto", Font.PLAIN, 17));
        lb7.setBounds(10, 255, 150, 25);
        contentPane.add(lb7);

        JLabel lb8 = new JLabel("Max service time:");
        lb8.setFont(new Font("Roboto", Font.PLAIN, 17));
        lb8.setBounds(10, 295, 150, 25);
        contentPane.add(lb8);

        textField = new JTextField();
        textField.setBounds(200, 300, 70, 20); //serv min
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(200, 260, 70, 20); //serv max
        contentPane.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(200, 220, 70, 20); //sos min
        contentPane.add(textField_2);

        textField_3 = new JTextField(); //sos max
        textField_3.setColumns(10);
        textField_3.setBounds(200, 180, 70, 20);
        contentPane.add(textField_3);

        textField_4 = new JTextField();//sim
        textField_4.setColumns(10);
        textField_4.setBounds(200, 140, 70, 20);
        contentPane.add(textField_4);

        textField_5 = new JTextField();//cl
        textField_5.setColumns(10);
        textField_5.setBounds(200, 100, 70, 20);
        contentPane.add(textField_5);

        textField_6 = new JTextField();//q
        textField_6.setColumns(10);
        textField_6.setBounds(200, 60, 70, 20);
        contentPane.add(textField_6);

        btnNewButton = new JButton("Start");
        btnNewButton.setBounds(170, 350, 150, 30);
        contentPane.add(btnNewButton);
    }
    public JButton getStartButton()
    {
        return this.btnNewButton;
    }

    public String getMaxProc()
    {
        return this.textField.getText();
    }
    public String getMinProc()
    {
        return this.textField_1.getText();
    }
    public String getMaxArr()
    {
        return this.textField_2.getText();
    }
    public String getMinArr()
    {
        return this.textField_3.getText();
    }
    public String getSimTime()
    {
        return this.textField_4.getText();
    }
    public String getNoClients()
    {
        return this.textField_5.getText();
    }
    public String getNoQueues()
    {
        return this.textField_6.getText();
    }

}
