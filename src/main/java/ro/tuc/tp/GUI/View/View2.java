package ro.tuc.tp.GUI.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class View2 extends JFrame {
    private final JPanel content;
    private final JLabel lbv21;
    private final JLabel lbv22;
    private final ArrayList<JLabel> queuesLabels= new ArrayList<>();
    public View2(int NoOfQueues) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 560, 350);
        content = new JPanel();
        content.setBorder(new EmptyBorder(7, 7, 7, 7));
        setContentPane(content);
        content.setLayout(null);

        JLabel lblNewLabel = new JLabel("Results");
        lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 25));
        lblNewLabel.setBounds(160, 15, 100, 50);
        content.add(lblNewLabel);

        JLabel lbv23 = new JLabel("Time:");
        lbv23.setFont(new Font("Roboto", Font.PLAIN, 15));
        lbv23.setBounds(10, 50, 45, 15);
        content.add(lbv23);

        JLabel lbv24 = new JLabel("Waiting tasks:");
        lbv24.setFont(new Font("Roboto", Font.PLAIN, 15));
        lbv24.setBounds(10, 80, 120, 15);
        content.add(lbv24);

        for(int i = 0; i < NoOfQueues; i ++){
            JLabel j= new JLabel("Queue" + (i + 1) + ": ");
            j.setFont(new Font("Roboto", Font.PLAIN, 15));
            j.setBounds(10, 121 + i * 31, 80, 20);
            content.add(j);
            queuesLabels.add(new JLabel(""));
            queuesLabels.get(i).setFont(new Font("Roboto", Font.PLAIN, 15));
            queuesLabels.get(i).setBounds(80, 121 + i * 31, 350, 20);
            content.add(queuesLabels.get(i));
        }

        lbv21= new JLabel("");
        lbv21.setBounds(50, 50, 45, 15);
        content.add(lbv21);

        lbv22 = new JLabel("");
        lbv22.setBounds(133, 85, 300, 15);
        content.add(lbv22);
    }

    public void setSimTime(String s) {
        this.lbv21.setText(s);
    }
    public void setClWait(String s){
        this.lbv22.setText(s);
    }
    public void setCl(String a, int i){
        queuesLabels.get(i).setText(a);
    }
}

