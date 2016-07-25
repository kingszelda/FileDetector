package cuishining.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cuishining.service.RenamePolicy;
import cuishining.util.JpgFileUtil;

/**
 * Created by shining.cui on 2016/7/23.
 */
public class RenameByTimePolicy implements RenamePolicy {
    private static final Logger logger = LoggerFactory.getLogger(RenameByTimePolicy.class);

    @Override
    public boolean rename(List<File> fileList) {
        logger.info("接受参数fileList为:{}", fileList);
        for (File file : fileList) {
            String photoTimeStr = JpgFileUtil.getPhotoTimeStr(file);
            String path = file.getParentFile().getAbsolutePath();
            if (StringUtils.isNotEmpty(photoTimeStr)) {
                renameFile(file, photoTimeStr, path);
            }
        }
        return true;
    }

    private void renameFile(File file, String photoTimeStr, String path) {
        File renamedFile = new File(path + File.separator + photoTimeStr + ".jpg");
        if (renamedFile.exists()) {
            logger.error("{}文件已经存在，无法重命名。", renamedFile);
        } else {
            boolean renameSuccess = file.renameTo(renamedFile);
            if (renameSuccess) {
                logger.info("{}文件命名为{}", file.getName(), renamedFile.getName());
            }
        }
    }
}
