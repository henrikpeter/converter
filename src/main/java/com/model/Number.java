package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Number {
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
