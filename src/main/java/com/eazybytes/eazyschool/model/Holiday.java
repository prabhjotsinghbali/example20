package com.eazybytes.eazyschool.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Holiday {

    private String day;
    private String reason;
    private Type type;

    public enum Type{
        FESTIVAL,FEDERAL
    }
}
