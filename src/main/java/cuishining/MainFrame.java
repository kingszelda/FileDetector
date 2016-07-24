package cuishining;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.*;

import com.google.common.collect.HashMultimap;

import cuishining.bizz.DuplicateFileDetector;

public class MainFrame extends JFrame implements ActionListener {
    GridBagLayout g = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    MainFrame(String str) {
        super(str);
        setSize(300, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(g);
        // 调用方法
        addComponent();
        submit.addActionListener(this);
        setVisible(true);
        setLocationRelativeTo(null);// 设居中显示;
    }

    // 在这个方法中将会添加所有的组件;
    // 使用的网格包布局;希望楼主能看懂;
    public void addComponent() {
        // 个人信息登记
        noteInformation = new JLabel("个人信息登记：");
        add(g, c, noteInformation, 0, 0, 1, 1);
        // 用户名
        userName = new JLabel("请输入文件夹地址：");
        add(g, c, userName, 0, 1, 1, 1);
        // 用户名输入框
        textUserName = new JTextField(10);
        add(g, c, textUserName, 1, 1, 2, 1);
        // 密码：
        password = new JLabel("请输入文件匹配后缀名：");
        add(g, c, password, 0, 2, 1, 1);
        // 密码输入框
        textUserPassword = new JTextField(10);
        add(g, c, textUserPassword, 1, 2, 2, 1);
        // 性别
        // submit按钮
        submit = new JButton("开始分析");
        c.insets = new Insets(7, 0, 4, 0);
        add(g, c, submit, 1, 5, 1, 1);

        result = new JTextArea(15, 20);
        add(g, c, result, 0, 6, 3, 4);

    }

    /*
     * public void ActionPerformed(ActionEvent e) { String s=textUserName.getText(); String
     * t=textUserPassword.getText(); String k=sexMan.getText(); String v=sexGirl.getText(); String a=(String)
     * year.getSelectedItem(); String b=(String)month.getSelectedItem(); String num="用户名："+s+"\n"+"密码: "+t+"性别: "
     * +(k==null?v:k)+"\n"+"出生日期:"+a+" "+b; result.append(num); }
     */
    public void add(GridBagLayout g, GridBagConstraints c, JComponent jc, int x, int y, int gw, int gh) {
        c.gridx = x;
        c.gridy = y;
        c.anchor = GridBagConstraints.WEST;
        c.gridwidth = gw;
        c.gridheight = gh;
        g.setConstraints(jc, c);
        add(jc);
    }

    public static void main(String args[]) {
        new MainFrame("重复文件检查器");
    }

    JLabel noteInformation, userName, password;
    JTextField textUserName, textUserPassword;
    JButton submit;
    JTextArea result;


    @Override
    public void actionPerformed(ActionEvent arg0) {

        String basicPath = textUserName.getText();
        String nameSuffix = textUserPassword.getText();
        HashMultimap<Long, String> duplicateFilesMap = new DuplicateFileDetector().detect(basicPath, nameSuffix);
        Set<Long> md5s = duplicateFilesMap.keySet();
        StringBuilder resultText = new StringBuilder();
        for (Long md5 : md5s) {
            Set<String> fileNames = duplicateFilesMap.get(md5);
            resultText.append("以下重复文件：\n");
            for (String fileName : fileNames) {
                resultText.append(fileName + "\n");
            }
        }
        String num = resultText.toString();
        result.setText(num);
    }
}
