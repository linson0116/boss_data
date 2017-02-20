package com.linson;

import com.linson.utils.ToolsUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import static com.linson.utils.ConfigUtils.JSON_CUSTOMERS_FILENAME;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MyGUI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JTextField textField1;
    private JButton button1;
    private JLabel label1;
    private JTextArea textArea1;
    private JButton button2;
    private JLabel label2;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button3;
    private JButton button4;
    private JLabel label3;
    private JLabel label4;
    private JButton button5;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    static MyGUI gui = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("数据查询导出工具1.0");
        gui = new MyGUI();
//        JPanel rootPane=new MyGUI().panel1;
        frame.setContentPane(gui.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
//        frame.setLocationRelativeTo(rootPane);
        frame.setLocation(500, 100);
        frame.setSize(700, 800);
        //----------------------------------------------

        gui.button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchId();
            }
        });
        gui.button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.textArea1.setText("");
                gui.textField1.setText("");
                gui.textField2.setText("");
                gui.textField3.setText("");
                gui.textField4.setText("");
                gui.textField5.setText("");
                gui.label4.setText("时间：");
                gui.label5.setText("输出结果：");
            }
        });
        gui.button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //读取用户ID文件------------------------------------------
                File file = new File(ToolsUtils.getJarDir(), JSON_CUSTOMERS_FILENAME);
                String[] customerIdArr = ToolsUtils.getCustomersNum(ToolsUtils.readFile2String(file));
                int begin = Integer.parseInt(gui.textField2.getText());
                int end = Integer.parseInt(gui.textField3.getText());
                String time = Main.createDb2Json(begin, end, customerIdArr);
                gui.label4.setText("时间：" + time );
            }
        });
        gui.button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.createCustomerIdFile();
            }
        });
        gui.textField1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchId();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        gui.button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cityCode = gui.textField4.getText();
                if (cityCode == null || cityCode.equals("")) {
                    gui.label5.setText("请输入城市ID");
                    return;
                }
                String nums = gui.textField5.getText();
                String result = Main.outputExcelByCityCode(cityCode,nums);
                gui.label5.setText(result);
            }
        });

    }

    private static void searchId() {
        String id = gui.textField1.getText();
        String str = Main.inputCustomerIdORServiceId(id);
        gui.textArea1.setText(str);
    }



}
