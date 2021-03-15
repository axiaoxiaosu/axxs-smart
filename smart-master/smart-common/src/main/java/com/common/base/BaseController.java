package com.common.base;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lwq
 * @data 2019/7/16
 */
public class BaseController {

    public static List<Long> convertId(String ids) {
        return Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
    }


}
