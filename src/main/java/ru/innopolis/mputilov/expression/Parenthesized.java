package ru.innopolis.mputilov.expression;

/**
 * Created by mputilov on 04.09.16.
 */
public class Parenthesized extends Primary {
    private Expression expression;

    public Parenthesized(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
