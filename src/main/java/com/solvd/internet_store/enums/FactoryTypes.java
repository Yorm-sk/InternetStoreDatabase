package com.solvd.internet_store.enums;

public enum FactoryTypes {
    MYBATIS("mybatis"), SQL ("sql");

    private String string;

    FactoryTypes(String string){
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
