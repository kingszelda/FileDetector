package cuishining.bizz;

import cuishining.util.FileUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by shining.cui on 2016/7/24.
 */
public class FileRenameRefactorTest {
    private static final Logger logger = LoggerFactory.getLogger(FileRenameRefactorTest.class);
    @Test
    public void renameFiles() throws Exception {
        String filePath = "D:\\workspace\\testJpg";
        String nameSuffix ="jpg";
        List<File> fileList = FileUtil.getAllFilesUnderPath(filePath, nameSuffix);
        new FileRenameRefactor().renameFiles(fileList);
        logger.info("运行结束");
    }

}