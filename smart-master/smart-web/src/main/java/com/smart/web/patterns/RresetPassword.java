package com.smart.web.patterns;

/**
 * @author lwq
 */
public class RresetPassword extends DutyChain {

    public RresetPassword(String errorMsg, Object... params) {
        super(errorMsg, params);
    }

    @Override
    protected boolean handle(Object... param) {

        System.out.println(getErrorMsg());

        return false;
    }
}
