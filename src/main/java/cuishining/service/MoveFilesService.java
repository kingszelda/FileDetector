package cuishining.service;

import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shining.cui on 2016/7/30.
 */
public class MoveFilesService {
    private static final Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}.*");
    private static final Logger logger = LoggerFactory.getLogger(MoveFilesService.class);

    public void moveFilesToDirectory(File directory, List<File> fileList) {

        for (File file : fileList) {
            String name = file.getName();
            String targetFilePath = directory.getAbsolutePath() + "\\" + name;
            File targetFile = new File(targetFilePath);
            if (checkMatch(name)) {
                boolean b = file.renameTo(targetFile);
                if (b) {
                    logger.info("文件{}移动到文件夹{}中，成功！", file, directory);
                } else {
                    logger.error("文件{}移动失败。", file);
                }
            }
        }
    }

    private boolean checkMatch(String name) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
