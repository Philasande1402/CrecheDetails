/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ac.za.tut.gui;

import ac.za.tut.Kiddei.KiddieDetails;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public class CrecheFrameGIU extends JFrame{
    
    //PANEL
    private JPanel nameGenderButtonPnl;
    private JPanel namePnl;
    private JPanel genderPnl;
    private JPanel btnPnl;
    
    private JPanel textAreaPnl;
    private JPanel mainPnl;
    
    //LABEL
    private JLabel nameLbl;
    private JLabel genderLbl;
    
    //TEXTFIELD
    private JTextField nameTxt;
    
    //TEXTAREA
    private JTextArea textAreaMessage;
    
    //Radio button
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private ButtonGroup BtnGrp;
    
    //BUTTONS
    private JButton registerBtn;
    private JButton displayBtn;
    
    //SRCOLL PANE
    private JScrollPane pane;
    
    
    public CrecheFrameGIU()
    {
        setTitle("CRECHE 4 YOUR KIDDIE");
        setSize(700, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        
        nameGenderButtonPnl = new JPanel(new GridLayout(3, 1, 1, 1));
        
        namePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nameLbl = new JLabel("Name : ");
        nameTxt = new JTextField(20);
        nameTxt.setFocusable(true);
        namePnl.add(nameLbl);
        namePnl.add(nameTxt);
        
        genderPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderLbl = new JLabel("Gender : ");
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        BtnGrp = new ButtonGroup();
        BtnGrp.add(maleRadioButton);
        BtnGrp.add(femaleRadioButton);
        genderPnl.add(genderLbl);
        genderPnl.add(maleRadioButton);
        genderPnl.add(femaleRadioButton);
        
        btnPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registerBtn = new JButton("Register Kiddie");
        registerBtn.addActionListener(new RegisterKiddies());
        displayBtn = new JButton("Display Kiddies");
        displayBtn.addActionListener(new DisplayKiddies());
        btnPnl.add(registerBtn);
        btnPnl.add(displayBtn);
        
        nameGenderButtonPnl.add(namePnl);
        nameGenderButtonPnl.add(genderPnl);
        nameGenderButtonPnl.add(btnPnl);
        
        textAreaPnl = new JPanel(new FlowLayout());
        textAreaMessage = new JTextArea(30, 40);
        pane = new JScrollPane(textAreaMessage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textAreaMessage.setEditable(false);
        textAreaPnl.add(pane);
        
        mainPnl = new JPanel(new BorderLayout());
        mainPnl.add(nameGenderButtonPnl,BorderLayout.NORTH);
        mainPnl.add(textAreaPnl,BorderLayout.CENTER);
        
        add(mainPnl);
        
        
        pack();
        setVisible(true);
    }
    private void clearFields()
    {
        nameTxt.setText("");
        BtnGrp.clearSelection();
    }
    
    private class RegisterKiddies implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            String name = nameTxt.getText();
            String genderName =null;
            
            File file;
            BufferedWriter bw;
            
        if(name.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Provide an empty space");
        }else{
            
            if(maleRadioButton.isSelected())
            {
                genderName="Male";
            }else
            {
                if(femaleRadioButton.isSelected())
                {
                    genderName="Female";
                }
            }
            
            KiddieDetails thKiddieDetails = new KiddieDetails(name, genderName);
            
            JFileChooser fc = new JFileChooser();
            
            int value = fc.showSaveDialog(CrecheFrameGIU.this);
            if(value == JFileChooser.APPROVE_OPTION)
            {
                try {
                    file = fc.getSelectedFile();
                    bw = new BufferedWriter(new FileWriter(file, true));
                    bw.write(thKiddieDetails.toString());
                    bw.newLine();
                    bw.close();
                    
                    clearFields();
                    
                    JOptionPane.showMessageDialog(null,"You are successfully login!!");
                } catch (IOException ex) {
                    Logger.getLogger(CrecheFrameGIU.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          }
            
        }
        
    }
    
    private class DisplayKiddies implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            File file;
            BufferedReader br;
            String line="";
            String result;
            
            JFileChooser fc = new JFileChooser();
           
            int value = fc.showOpenDialog(CrecheFrameGIU.this);
            
            if(value == JFileChooser.APPROVE_OPTION)
            {
                try {
                    file = fc.getSelectedFile();
                    br = new BufferedReader(new FileReader(file));
                    
                    while((result = br.readLine()) != null)
                    {
                        line +=result +"\n";
                    }
                    br.close();
                    
                    textAreaMessage.setText(line);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CrecheFrameGIU.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CrecheFrameGIU.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        
    }
    
}
