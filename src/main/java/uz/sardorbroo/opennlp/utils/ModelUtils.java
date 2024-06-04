package uz.sardorbroo.opennlp.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;

public class ModelUtils {

    public static InputStream getModelInputStream(String model) {

        if (StringUtils.isBlank(model)) {
            throw new IllegalArgumentException("Invalid argument is passed! Model name must not be blank!");
        }

        return ModelUtils.class.getResourceAsStream(model);
    }
}
