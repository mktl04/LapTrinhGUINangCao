/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Notepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author DELL
 */
public class JNotepad extends JFrame{

    private JMenuBar menuBar;
    private JMenu mFile, mEdit, mFormat, mView, mHelp;
    private JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemPageSetup, itemPrint, itemExit;
    private JMenuItem itemFont;
    private JCheckBoxMenuItem itemWrap;
    private JTextArea txtEditor;
    
    public JNotepad(String title){
        super(title);
        createMenu();
        createGUI();
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    private void createMenu(){
        menuBar = new JMenuBar();
        menuBar.add(mFile = new JMenu("File"));
        menuBar.add(mEdit = new JMenu("Edit"));
        menuBar.add(mFormat = new JMenu("Format"));
        menuBar.add(mView = new JMenu("View"));
        menuBar.add(mHelp = new JMenu("Help"));
        
        mFile.add(itemNew = new JMenuItem("New"));
        mFile.add(itemOpen = new JMenuItem("Open..."));
        mFile.add(itemSave = new JMenuItem("Save"));
        mFile.add(itemSaveAs = new JMenuItem("Save as..."));
        mFile.add(new JSeparator());
        mFile.add(itemPageSetup = new JMenuItem("Page Setup..."));
        mFile.add(itemPrint = new JMenuItem("Print"));
        mFile.addSeparator();
        mFile.add(itemExit = new JMenuItem("Exit"));
        
        mFormat.add(itemWrap = new JCheckBoxMenuItem("Wrod Wrap", true));
        mFormat.add(itemFont = new JMenuItem("Font..."));
        
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        itemPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        
        
        setJMenuBar(menuBar);
    }
    private void createGUI() {
        txtEditor = new JTextArea();
        JScrollPane SP = new JScrollPane(txtEditor);
        add(SP);
        txtEditor.setLineWrap(true);
        txtEditor.setFont(new Font("Arial",Font.PLAIN, 20));
    }
    private void processEvent() {
        itemExit.addActionListener((ActionEvent e) -> {
            if (JOptionPane.showConfirmDialog(null, "Are you to Exit?") == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JNotepad app = new JNotepad("Demo");
        app.setVisible(true);
    }
    
}

