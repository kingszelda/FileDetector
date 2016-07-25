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

    public boolean renameFiles(List<File> fileList) {
        RenamePolicy renamePolicy = new RenameByTimePolicy();
        return renameFilesByJpgTime(renamePolicy, fileList);
    }

    private boolean renameFilesByJpgTime(RenamePolicy renamePolicy, List<File> fileList) {
        return renamePolicy.rename(fileList);
    }
}
