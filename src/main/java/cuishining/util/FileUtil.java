package cuishining.util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 *
 * Created by shining.cui on 2016/7/12.
 */
public class FileUtil {
    public static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 读取指定路径下的所有文件，使用队列实现
     * 
     * @param filePath 指定的文件夹目录
     * @param nameSuffix 指定后缀，若为null或者" "则匹配所有
     * @return 文件夹及其子文件夹内所有文件
     */
    public static List<File> getAllFilesUnderPath(String filePath, String nameSuffix) {
        logger.info("接受的文件夹路径为:{},文件名匹配后缀为:{}", filePath, nameSuffix);
        File basicfile = new File(filePath);
        List<File> fileLis = Lists.newArrayList();
        LinkedList<File> fileQueue = Lists.newLinkedList(Lists.newArrayList(basicfile));
        while (!fileQueue.isEmpty()) {
            File file = fileQueue.poll();
            if (file.isDirectory() && file.listFiles() != null) {
                fileQueue.addAll(Lists.newArrayList(file.listFiles()));
            } else {
                fileQueue = matchTheSuffix(file, nameSuffix, fileQueue, fileLis);
            }
        }
        logger.info("得到的文件列表的长度为:{}", fileLis.size());
        return fileLis;
    }

    private static LinkedList<File> matchTheSuffix(File file, String nameSuffix, LinkedList<File> fileQueue,
            List<File> fileList) {
        String fileName = file.getName();
        logger.info("filename:{}", file);
        if (StringUtils.isNotEmpty(nameSuffix)
                && StringUtils.endsWith(fileName.toLowerCase(), nameSuffix.toLowerCase())) {
            // 当有后缀名时，匹配的放入队列
            fileList.add(file);
        } else if (StringUtils.isEmpty(nameSuffix)) {
            // 没有匹配名时，所有的都放入队列
            fileList.add(file);
        }
        return fileQueue;
    }

}
