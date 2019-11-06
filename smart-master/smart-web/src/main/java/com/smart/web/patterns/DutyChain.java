package com.smart.web.patterns;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lwq
 */
@Data
@Slf4j
public abstract class DutyChain {

    /**
     * 下一个处理器
     */
    protected DutyChain nextVerifyDispose;
    /**
     * 校验数据
     */
    private Object param;
    /**
     * 失败返回消息
     */
    private String errorMsg;

    public DutyChain(String errorMsg, Object... param) {
        this.param = param;
        this.errorMsg = errorMsg;
    }

    public DutyChain(Object... param) {
        this.param = param;
        this.errorMsg = "处理失败";

    }

    /**
     * 校验判断
     */
    public final boolean dispose() {
        //校验是否可以通过
        if (!this.handle(param)) {
            //为通过 直接返回
            return false;
        }
        //是否有下一个责任连
        if (nextVerifyDispose != null) {
            return nextVerifyDispose.dispose();
        }
        return true;
    }

    /**
     * 子类实现自己的校验逻辑
     *
     * @param param 校验对象
     */
    protected abstract boolean handle(Object... param);

}
