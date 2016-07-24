package cuishining.service;

import java.io.File;
import java.util.List;

/**
 * Created by shining.cui on 2016/7/23.
 */
public interface RenamePolicy {
    void rename(List<File> fileList);
}
