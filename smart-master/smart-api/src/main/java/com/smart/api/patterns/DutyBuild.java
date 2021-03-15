package com.smart.api.patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lwq
 */
public class DutyBuild {

    private List<DutyChain> chainList = new ArrayList<>();

    /**
     * 测试
     */
    public static void main(String[] args) {
        String param = "13173685107";
        System.out.println(new DutyBuild()
                .addChain(new Verify("呜啦呜啦", param))
                .addChain(new Verify2(param))
                .addChain(new RresetPassword("呜啦呜啦2", param))
                .execute());
    }

    /**
     * 添加校验
     */
    public DutyBuild addChain(DutyChain dutyChain) {
        chainList.add(dutyChain);
        return this;
    }

    /**
     * 执行
     */
    public boolean execute() {
        //放行空链
        if (chainList.isEmpty()) {
            return true;
        }
        //组装
        for (int i = 0; i < chainList.size(); i++) {
            if (i + 1 < chainList.size()) {
                chainList.get(i).setNextVerifyDispose(chainList.get(i + 1));
            }
        }
        //从第一个开始验证
        return chainList.get(0).dispose();
    }

}
