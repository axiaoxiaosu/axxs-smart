package com.smart.db.beanenum;

/**
 * @author lwq
 */
public enum MplMenuEnum {
    MENUTYPE_1(1, "目录"),
    MENUTYPE_2(2, "菜单"),
    MENUTYPE_3(3, "按钮")
    ;
    private int value;
    private String text;

    MplMenuEnum(int value, String text) {
        this.text = text;
        this.value = value;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }}
