package cuishining.ui;

import com.google.common.base.Stopwatch;
import com.google.common.collect.HashMultimap;
import cuishining.bizz.DuplicateFileDetector;
import cuishining.service.MoveFilesService;
import cuishining.util.FileUtil;
import cuishining.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by shining.cui on 2016/7/25.
 */
class MovePhotosToDirectoryFrame extends JFrame implements ActionListener {
    private static final Logger logger = LoggerFactory.getLogger(MovePhotosToDirectoryFrame.class);
    private JTextField sourceDirPath, targetDirPath;
    private JButton submit;
    private JTextArea result;
    private GridBagLayout g = new GridBagLayout();
    private GridBagConstraints c = new GridBagConstraints();

    void createFrame() {
        setTitle("移动照片到文件夹工具");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLayout(g);
        setSize(800, 800);

        addComponent();

        submit.addActionListener(this);
        setVisible(true);
        setLocationRelativeTo(null);// 设居中显示;
    }

    private void addComponent() {
        JLabel toolName = new JLabel("重复文件删除工具");
        add(g, c, toolName, 0, 0, 1, 1);

        JLabel source = new JLabel("要从哪个文件夹移走：");
        add(g, c, source, 0, 1, 1, 1);
        //
        sourceDirPath = new JTextField(20);
        add(g, c, sourceDirPath, 1, 1, 2, 1);
        //
        JLabel target = new JLabel("要移动到哪个文件夹：");
        add(g, c, target, 0, 2, 1, 1);
        //
        targetDirPath = new JTextField(10);
        add(g, c, targetDirPath, 1, 2, 2, 1);
        //
        submit = new JButton("开始匹配后移动");
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
        Stopwatch stopwatch = Stopwatch.createStarted();
        String sourceDirPathText = sourceDirPath.getText();

        if (StringUtils.isEmpty(sourceDirPathText)) {
            result.setText("请输入文件夹地址！");
            sourceDirPath.grabFocus();
            return;
        }

        String targetDirPathText = targetDirPath.getText();
        File targetDir = new File(targetDirPathText);
        if (!targetDir.isDirectory()) {
            result.setText("请确认目标文件夹地址正确！");
            targetDirPath.grabFocus();
            return;
        }
        logger.info("================================================================================");
        logger.info("开始移动文件操作，当前时间为:{}", TimeUtil.parseDateFromSystemDate(new Date()));
        java.util.List<File> filesUnderPath = FileUtil.getAllFilesUnderPath(sourceDirPathText, "jpg");
        new MoveFilesService().moveFilesToDirectory(targetDir,filesUnderPath);
        result.setText("移动完毕");
        logger.info("移动文件操作运行完毕，当前时间为:{}", TimeUtil.parseDateFromSystemDate(new Date()));
        stopwatch.stop();
        logger.info("================================================================================");
        logger.info("程序共耗时{}",stopwatch.elapsed(TimeUnit.SECONDS));

    }
}
