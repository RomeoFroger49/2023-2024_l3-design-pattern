package com.fges.todoapp.Logic.Const;

public enum TypoEnum {
    LIST("list"),
    MIGRATE("migrate"),

    INSERT("insert"),

    JSON(".json"),

    CSV(".csv");

    private final String typo;

    TypoEnum(String typo) {
        this.typo = typo;
    }

    public String getTypo() {
        return typo;
    }
}
