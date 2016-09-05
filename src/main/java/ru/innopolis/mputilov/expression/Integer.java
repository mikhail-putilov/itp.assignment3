package ru.innopolis.mputilov.expression;

/**
 * Created by mputilov on 04.09.16.
 */
public class Integer extends Primary {
    private long value;

    public Integer(String number) {
        value = Long.valueOf(number);
    }

    public String getValue() {
        return String.valueOf(value);
    }
}
