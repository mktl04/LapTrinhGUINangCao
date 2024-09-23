/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Notepad;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.*;
/**
 *
 * @author DELL
 */
public class JNotepad extends JFrame{

    private JMenuBar menuBar;
    private JMenu mFile, mEdit, mFormat, mView, mHelp, mZoom;
    private JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemPageSetup, itemPrint, itemExit;
    private JMenuItem itemUndo, itemCut, itemCopy, itemPaste, itemDelete, itemFind, itemSelectAll;
    private JMenuItem itemZoomIn, itemZoomOut, itemRDZoom;
    private JMenuItem itemFont;
    private JCheckBoxMenuItem itemWrap, itemStatusBar;
    private JToolBar toolBar;
    private JTextArea txtEditor;
    private JButton btnNew, btnOpen, btnSave;
    private JFontDialog fontDlg;
    public JTextArea getTxtEditor() {
        return txtEditor;
    }
    
    public JNotepad(String title){
        super(title);
        createMenu();
        createGUI();
        createToolBar();
        processEvent();
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    private void createMenu(){
        menuBar = new JMenuBar();
        //Menu
        menuBar.add(mFile = new JMenu("File"));
        menuBar.add(mEdit = new JMenu("Edit"));
        menuBar.add(mFormat = new JMenu("Format"));
        menuBar.add(mView = new JMenu("View"));
        menuBar.add(mHelp = new JMenu("Help"));

        //File menu
        mFile.add(itemNew = new JMenuItem("New"));
        mFile.add(itemOpen = new JMenuItem("Open..."));
        mFile.add(itemSave = new JMenuItem("Save"));
        mFile.add(itemSaveAs = new JMenuItem("Save As..."));
        mFile.add(new JSeparator());
        mFile.add(itemPageSetup = new JMenuItem("Page Setup..."));
        mFile.add(itemPrint = new JMenuItem("Print..."));
        mFile.addSeparator();
        mFile.add(itemExit = new JMenuItem("Exit"));

        //Edit menu
        mEdit.add(itemUndo = new JMenuItem("Undo"));
        mEdit.addSeparator();
        mEdit.add(itemCut = new JMenuItem("Cut"));
        mEdit.add(itemCopy = new JMenuItem("Copy"));
        mEdit.add(itemPaste = new JMenuItem("Paste"));
        mEdit.add(itemDelete = new JMenuItem("Delete"));
        mEdit.addSeparator();
        mEdit.add(itemFind = new JMenuItem("Find"));
        mEdit.add(itemSelectAll = new JMenuItem("Select All"));

        //View menu
        mView.add(mZoom = new JMenu("Zoom"));
        mZoom.add(itemZoomIn = new JMenuItem("Zoom In"));
        mZoom.add(itemZoomOut = new JMenuItem("Zoom Out"));
        mZoom.add(itemRDZoom = new JMenuItem("Restore default zoom"));

        //Format menu
        mFormat.add(itemWrap = new JCheckBoxMenuItem("Word Wrap", true));
        mFormat.add(itemFont = new JMenuItem("Font..."));

        //Hotkey
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        itemPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        //tao phim nong cho cac item edit
        itemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        //itemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE));
        itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        itemSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));

        //tao phim nong cho cac item view
        itemZoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));
        itemZoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
        itemRDZoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK));
        
        //gan thanh thuc don vao cua so
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
            if (JOptionPane.showConfirmDialog(null, "Are you want to Exit?") == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        itemWrap.addActionListener((ActionEvent e) -> {
            if (itemWrap.isSelected()) {
                txtEditor.setLineWrap(true);
            } else {
                txtEditor.setLineWrap(false);
            }
        });
        itemOpen.addActionListener((ActionEvent e) -> {
            {
                openFile();
            }
        });
        itemSave.addActionListener((ActionEvent e) -> {
            {
                saveFile();
            }
        });
        itemFont.addActionListener((ActionEvent e) -> {
            {
                fontDlg = new JFontDialog(this, true);
                fontDlg.setVisible(true);
            }
        });
    }
    private void openFile() {
        JFileChooser dlgFile = new JFileChooser();
        if (dlgFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                //tao luong va lien ket voi tep tin
                FileInputStream fis = new FileInputStream(dlgFile.getSelectedFile());
                byte[] b = new byte[fis.available()];
                //doc noi dung tep tin
                txtEditor.setText(new String(b));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Loi doc file");
            }
        }
    }
    private void createToolBar() {
        toolBar = new JToolBar();
        toolBar.add(btnNew = new JButton("New"));
        toolBar.add(btnOpen = new JButton("Open"));
        toolBar.add(btnSave = new JButton("Save"));

        add(toolBar, BorderLayout.NORTH);
    }
    private void saveFile() {
        JFileChooser dlgFile = new JFileChooser();
        if (dlgFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                //tao luong va lien ket voi tep tin
                FileOutputStream fos = new FileOutputStream(dlgFile.getSelectedFile());
                //ghi noi dung vung van ban ra tep tin
                fos.write(txtEditor.getText().getBytes());
                //dong luong
                fos.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Loi doc file");
            }
        }
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

