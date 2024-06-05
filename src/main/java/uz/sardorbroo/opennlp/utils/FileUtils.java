package uz.sardorbroo.opennlp.utils;

import org.apache.commons.lang3.StringUtils;

import java.net.URL;

public class FileUtils {

    public static URL getFileUrlWithResources(String path) {

        if (StringUtils.isBlank(path)) {
            throw new IllegalArgumentException("Invalid argument is passed! Path name must not be blank!");
        }

        return FileUtils.class.getResource(path);
    }
}
