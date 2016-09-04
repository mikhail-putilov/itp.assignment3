package ru.innopolis.mputilov.expression;

/**
 * Created by mputilov on 04.09.16.
 */
public class Integer extends Primary {
    private long value;

    public Integer(long value) {
        this.value = value;
    }

    public String getValue() {
        return String.valueOf(value);
    }
}
