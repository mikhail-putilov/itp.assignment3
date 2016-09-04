package ru.innopolis.mputilov.expression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mputilov on 04.09.16.
 */
public abstract class Expression {
    private String representative;
    private List<Expression> children = new ArrayList<>();

    Expression(String representative) {
        this.representative = representative;
    }

    public String getRepresentative() {
        return representative;
    }

    public void appendChild(Expression child) {
        children.add(child);
    }

    public List<Expression> getChildren() {
        return children;
    }
}
