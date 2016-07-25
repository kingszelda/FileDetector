package cuishining.ui;

import com.google.common.collect.HashMultimap;
import cuishining.bizz.DuplicateFileDetector;
import cuishining.bizz.FileRenameRefactor;
import cuishining.util.FileUtil;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * Created by shining.cui on 2016/7/25.
 */
class RenameJpgByTimeFrame extends JFrame implements ActionListener {
    private JTextField textDirPath;
    private JButton submit;
    private JTextArea result;
    private GridBagLayout g = new GridBagLayout();
    private GridBagConstraints c = new GridBagConstraints();

    void createFrame() {
        setTitle("照片按时间重命名工具");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLayout(g);
        setSize(550, 550);

        addComponent();

        submit.addActionListener(this);
        setVisible(true);
        setLocationRelativeTo(null);// 设居中显示;
    }

    private void addComponent() {
        JLabel toolName = new JLabel("照片按时间重命名工具");
        add(g, c, toolName, 0, 0, 1, 1);

        JLabel dirPath = new JLabel("请输入文件夹地址：");
        add(g, c, dirPath, 0, 1, 1, 1);
        //
        textDirPath = new JTextField(20);
        add(g, c, textDirPath, 1, 1, 2, 1);
        //
        submit = new JButton("开始重命名");
        c.insets = new Insets(7, 0, 4, 0);
        add(g, c, submit, 1, 5, 1, 1);

        result = new JTextArea(25, 45);
        JScrollPane jScrollPane = new JScrollPane(result);
        add(g, c, jScrollPane, 0, 6, 3, 4);
    }

    private void add(GridBagLayout g, GridBagConstraints c, JComponent jc, int x, int y, int gw, int gh) {
        c.gridx = x;
        c.gridy = y;
        c.anchor = GridBagConstraints.WEST;
        c.gridwidth = gw;
        c.gridheight = gh;
        g.setConstraints(jc, c);
        add(jc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String basicPath = textDirPath.getText();
        if (StringUtils.isEmpty(basicPath)) {
            result.setText("请输入文件夹地址！");
            textDirPath.grabFocus();
            return;
        }
        List<File> jpgFileList = FileUtil.getAllFilesUnderPath(basicPath, "jpg");
        boolean renameSuccess = new FileRenameRefactor().renameFiles(jpgFileList);
        if (renameSuccess) {
            result.setText("重命名成功");
        } else {
            result.setText("重命名失败");
        }
    }
}
