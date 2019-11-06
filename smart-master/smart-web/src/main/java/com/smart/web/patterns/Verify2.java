package com.smart.web.patterns;

/**
 * @author lwq
 */
public class Verify2 extends DutyChain {

    public Verify2(Object... params) {
        super(params);
    }

    public Verify2(String errorMsg, Object... params) {
        super(params);
    }

    @Override
    protected boolean handle(Object... param) {
        return param[0].toString().length() > 0;
    }
}
