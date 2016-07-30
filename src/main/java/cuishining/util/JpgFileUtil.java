package cuishining.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifDirectoryBase;

/**
 * Created by shining.cui on 2016/7/23.
 */
public class JpgFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(JpgFileUtil.class);

    public static String getPhotoTimeStr(File file) {
        Date date = null;
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            for (Directory dr : metadata.getDirectories()) {
                if (dr.containsTag(ExifDirectoryBase.TAG_DATETIME_ORIGINAL)) {
                    date = dr.getDate(ExifDirectoryBase.TAG_DATETIME_ORIGINAL);
                }
                if (date != null) {
                    return TimeUtil.parseDateFromJpgFileDate(date);
                }
            }
        } catch (ImageProcessingException e) {
            logger.error("jpg文件读取错误", e);
        } catch (IOException e) {
            logger.error("发生io错误", e);
        }
        return null;
    }
}
