package cuishining.bizz;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cuishining.service.RenamePolicy;
import cuishining.service.impl.RenameByTimePolicy;

/**
 * Created by shining.cui on 2016/7/23.
 */
public class FileRenameRefactor {
    private static final Logger logger = LoggerFactory.getLogger(FileRenameRefactor.class);

    public void renameFiles(List<File> fileList) {
        logger.info("接受参数fileList为:{}",fileList);
        RenamePolicy renamePolicy =new RenameByTimePolicy();
        renameFilesByJpgTime(renamePolicy,fileList);
    }

    private void renameFilesByJpgTime(RenamePolicy renamePolicy, List<File> fileList) {
        logger.info("接受参数renamePolicy为:{},fileList为:{}",renamePolicy,fileList);
        renamePolicy.rename(fileList);
    }
}