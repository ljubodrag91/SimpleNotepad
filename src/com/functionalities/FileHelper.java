
package com.functionalities;

import GUI.mainFrame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileHelper {
    static StringBuffer sb;
    static BufferedWriter writer;
    static BufferedReader reader;
    
    public static void save(JTextArea textArea,File file){
        String content=textArea.getText();
        content = content.replaceAll("(?!\\r)\\n", "\r\n");
        try {
            try{
                writer =new BufferedWriter(new FileWriter(file));
                writer.write(content);
            }finally{
                writer.flush();
                writer.close();
                JOptionPane.showMessageDialog(null, "File Saved!");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "File Not Saved!");
        }
    }
    
    public static void open(JTextArea textArea,File file){
        sb=new StringBuffer();
        try {
            try{
                reader=new BufferedReader(new FileReader(file));
                char[] buffer = new char[1];
                int i;
                while((i=reader.read(buffer))!=-1){
                    sb.append(buffer);
                }
            }finally{
                reader.close();
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File Not Found!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "I/O Exception Occured!");
        }
        textArea.setText(sb.toString());
    }
    
    public static void openFile(JTextArea textArea){
        String userDir = System.getProperty("user.home");
        JFileChooser fileChooser = new JFileChooser(userDir +"/Desktop");
                     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                     FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Documents (*.txt)", "txt", "text");
                     fileChooser.setFileFilter(filter);
            if( fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                // open file
                open(textArea, file);
                mainFrame.mainFrame.setTitle(file.getName());
               }
    }
    
    public static void saveFile(JTextArea textArea){
        String userDir = System.getProperty("user.home");
        JFileChooser fileChooser = new JFileChooser(userDir +"/Desktop");
                     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                     FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Documents (*.txt)", "txt", "text");
                     fileChooser.setFileFilter(filter);
           if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                File renamed = new File(file.getAbsolutePath()+".txt");
                // save to file
                if(!fileChooser.getSelectedFile().getName().endsWith(".txt")){
                    save(textArea, renamed);
                }else{
                    save(textArea, file);
                }
                mainFrame.mainFrame.setTitle(file.getName());
              }
    }
    public static void saveAndEmpty(JTextArea textArea,File file){
        String content=textArea.getText();
        content = content.replaceAll("(?!\\r)\\n", "\r\n");
        try {
            try{
                writer =new BufferedWriter(new FileWriter(file));
                writer.write(content);
            }finally{
                writer.flush();
                writer.close();
                JOptionPane.showMessageDialog(null, "File Saved!");
                textArea.setText("");
                mainFrame.mainFrame.setTitle(mainFrame.title);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "File Not Saved!");
        }
    }
    public static void saveFileAndEmpty(JTextArea textArea){
        String userDir = System.getProperty("user.home");
        JFileChooser fileChooser = new JFileChooser(userDir +"/Desktop");
                     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                     FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Documents (*.txt)", "txt", "text");
                     fileChooser.setFileFilter(filter);
           if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                // save to file
                saveAndEmpty(textArea, file);
              }
    }
}
