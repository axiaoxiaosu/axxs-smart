package com.smart.web.codefragment;

import com.common.constants.Constants;
import org.apache.commons.beanutils.ConvertUtils;

/**
 * @author lwq
 */
public class ControllerCode {


    public static Long[] convertId(String ids) {

        String[] idArray;
        if (ids.indexOf(Constants.DUNHAO) > -1) {
            idArray = ids.split(Constants.DUNHAO);
        } else {
            idArray = new String[]{ids};
        }
        return (Long[]) ConvertUtils.convert(idArray, Long.class);
    }


}
