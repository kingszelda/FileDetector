package cuishining.bizz;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashMultimap;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import cuishining.util.FileUtil;

/**
 * Created by shining.cui on 2016/7/20.
 */
public class DuplicateFileDetector {
    private static final Logger logger = LoggerFactory.getLogger(DuplicateFileDetector.class);

    public HashMultimap<Long, String> detect(String path, String nameSuffix) {
        List<File> fileList = FileUtil.getAllFilesUnderPath(path, nameSuffix);
        HashMultimap<Long, String> md5AndFilePathMultiMap = analyzeMd5OfAllFiles(fileList);
        return analyzeDuplicateFiles(md5AndFilePathMultiMap);
    }

    private HashMultimap<Long, String> analyzeMd5OfAllFiles(List<File> fileList) {
        HashMultimap<Long, String> md5FileNameMultiMap = HashMultimap.create();
        for (File file : fileList) {
            logger.error("文件{}，正在分析中……",file);
            try {
                long md5 = Files.hash(file, Hashing.md5()).asLong();
                String path = file.getCanonicalPath();
                md5FileNameMultiMap.put(md5, path);
            } catch (IOException e) {
                logger.error("文件hash出错，请检查文件是否可读。",e);
            }

        }
        return md5FileNameMultiMap;
    }

    private HashMultimap<Long, String> analyzeDuplicateFiles(HashMultimap<Long, String> multimap) {
        Set<Long> md5s = multimap.keySet();
        HashMultimap<Long, String> duplicateFilesMap = HashMultimap.create();
        for (Long md5 : md5s) {
            Set<String> fileNames = multimap.get(md5);
            // 如果对应md5的value多于1个，证明是重复的文件，放入新的map中返回
            if (fileNames.size() > 1) {
                for (String name : fileNames) {
                    duplicateFilesMap.put(md5, name);
                }
            }
        }
        return duplicateFilesMap;
    }
}
