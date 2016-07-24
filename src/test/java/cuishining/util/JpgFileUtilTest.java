package cuishining.util;

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
       String filePath="D:\\workspace\\testJpg\\DSC_0389.JPG";
       String filePath1="D:\\workspace\\testJpg\\IMG_20120402_134657.JPG";

//        String photoTimeStr = JpgFileUtil.getPhotoTimeStr(new File(filePath));
        String photoTimeStr1 = JpgFileUtil.getPhotoTimeStr(new File(filePath1));

//        logger.info("{}",photoTimeStr);
        logger.info("{}",photoTimeStr1);
    }

}