package cuishining.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Created by shining.cui on 2016/7/24.
 */
public class FileDetectorFrame extends JFrame implements ActionListener {
    private JButton bt1 = new JButton("重复文件删除(功能设计中，勿用)");
    private JButton bt2 = new JButton("照片按拍摄时间重命名");
    private JButton bt3 = new JButton("移动照片到指定文件夹");

    private void createFileDetectorFrame() {
        Container contentPane = getContentPane();
        GridLayout gridLayout = new GridLayout(3, 1, 20, 20);
        setLayout(gridLayout);
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        setSize(300, 300);
        setTitle("文件探测器");
        setVisible(true);
        setLocationRelativeTo(null);// 设居中显示;
        contentPane.add(bt1);
        contentPane.add(bt2);
        contentPane.add(bt3);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new FileDetectorFrame().createFileDetectorFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            new DuplicateFileDetectorFrame().createFrame();
        }
        if (e.getSource() == bt2) {
            new RenameJpgByTimeFrame().createFrame();
        }
        if (e.getSource() == bt3) {
            new MovePhotosToDirectoryFrame().createFrame();
        }

    }
}
