/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Notepad;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextArea;
/**
 *
 * @author DELL
 */
public class JFontDialog extends JDialog{

    private JList lstFontName, lstStyle,lstSize;
    private JLabel lbPreview;
    private JButton btOk, btCancel;
    
    private Font font;
    private int[] arrStyle = { Font.PLAIN, Font.ITALIC, Font.BOLD, Font.ITALIC + Font.BOLD};    
    
    private JNotepad parent;
    
    public JFontDialog(Frame owner, boolean modal) {
        super(owner, modal);
        parent = (JNotepad)owner; //nhận cửa sổ cha
        createGUI();
        setFontPreview();
        processEvent();
        setSize(600, 450);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(owner);
    }

    private void createGUI() {
        
        JPanel p = new JPanel();
        p.setLayout(null);
        //tạo lstFontName
        //b1. lấy danh sách các font từ hệ thống
        String[] fontnames= GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        lstFontName = new JList(fontnames);
        JScrollPane scrollFontName = new JScrollPane(lstFontName);
        lstFontName.setSelectedIndex(0);
        
        //thêm lstFontName vào thùng chứa
        p.add(scrollFontName);
        scrollFontName.setBounds(5, 50, 200, 200);
        
        
         //tạo lstStyle
        String[] styles= {"Plain","Italic","Bold","Italic Bold"};
        lstStyle = new JList(styles);
        JScrollPane scrollStyle = new JScrollPane(lstStyle);
        lstStyle.setSelectedIndex(1);
        
        p.add(scrollStyle);
        scrollStyle.setBounds(210, 50, 200, 200);
        
        //tạo lstSize
        String[] size= {"8","9","10","11","12","14","16","22","24","32","48","72"};
        lstSize = new JList(size);
        JScrollPane scrollSize = new JScrollPane(lstSize);
        lstSize.setSelectedIndex(9);
        
        p.add(scrollSize);
        scrollSize.setBounds(420, 50, 100, 200);
        
        
        //tạo lbPreview
        p.add(lbPreview= new JLabel("AaBbYyZz"));
        lbPreview.setBounds(200, 270, 300, 80);
        
        //tạo btOk, btCancel        
        p.add(btOk = new JButton("OK"));
        p.add(btCancel = new JButton("Cancel"));
        btOk.setBounds(250, 350, 100, 50);
        btCancel.setBounds(380, 350, 100, 50);
        
        //đặt thùng chứa vào cửa sổ
        add(p);                 
        
    }
    
    
    private void setFontPreview()
    {
        String name = (String)lstFontName.getSelectedValue();
        int style =arrStyle[lstStyle.getSelectedIndex()];
        int size = Integer.parseInt(lstSize.getSelectedValue().toString());
        font = new Font(name, style, size);
        lbPreview.setFont(font);
    }

    private void processEvent() {
        
        //xu ly su kien thay doi chon ten font
        lstFontName.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                   setFontPreview();
            }
        });
        
        //xu ly su kien thay doi style
        lstStyle.addListSelectionListener((e) -> {
               setFontPreview();
        });
        
        
        //xu ly sụ kiện nhấn nút btOk
        btOk.addActionListener((e) ->{
             
              //set font cho vùng soạn thảo của cửa sổ cha (JNotepad)
               parent.getTxtEditor().setFont(font);
              //đóng hộp thoại
              this.dispose();                  
        });
    }
    
    
    
}
