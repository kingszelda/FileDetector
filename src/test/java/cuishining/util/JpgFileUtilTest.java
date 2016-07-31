package cuishining.util;

import com.google.common.io.Files;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by shining.cui on 2016/7/24.
 */
public class JpgFileUtilTest {
    private static final Logger logger = LoggerFactory.getLogger(JpgFileUtilTest.class);
    @Test
    public void getPhotoTimeStr() throws Exception {
       String source="G:\\照片\\媳妇iphone\\IMG_6771.JPG";
       String target="G:\\2.JPG";
        File sourceFile = new File(source);
        File targetFile = new File(target);
//        String photoTimeStr = JpgFileUtil.getPhotoTimeStr(new File(filePath));
//        String photoTimeStr1 = JpgFileUtil.getPhotoTimeStr(new File(filePath1));

        boolean b = sourceFile.renameTo(targetFile);
//        logger.info("{}",photoTimeStr);
        logger.info("{}",b);
    }

}