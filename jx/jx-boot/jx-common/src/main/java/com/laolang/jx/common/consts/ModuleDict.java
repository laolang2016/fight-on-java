package com.laolang.jx.common.consts;

public enum ModuleDict {
    product("product", "商品中心"),
    admin("admin", "后台");


    private final String code;
    private final String name;

    ModuleDict(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
