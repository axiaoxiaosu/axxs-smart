package com.smart.api.patterns;

/**
 * @author lwq
 */

public class Verify extends DutyChain {
/*
    public Verify(Object... params) {
        super(params);
    }*/

    public Verify(String errorMsg, Object... params) {
        super(params);
    }

    @Override
    protected boolean handle(Object... param) {
        return param[0].toString().equals("13173685107");
    }
}
