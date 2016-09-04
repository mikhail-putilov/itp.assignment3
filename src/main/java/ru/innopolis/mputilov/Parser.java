package ru.innopolis.mputilov;

import ru.innopolis.mputilov.expression.*;
import ru.innopolis.mputilov.token.TokenIterableExpression;
import ru.innopolis.mputilov.token.Tokenizer;

/**
 * Created by mputilov on 04.09.16.
 */
public class Parser {
    public static void main(String[] args) {
        String rawExpression = "1 + 2";
        Expression expression = new Parser().parseExpression(rawExpression);
        new PrettyPrinter().print(expression);
    }

    public Expression parseExpression(String rawExpression) {
        Expression current = new NullExpression();
        Expression leftChild = null, rightChild = null;
        Operator operator = null;

        for (String token : new TokenIterableExpression(rawExpression)) {
            if (Tokenizer.isOperator(token)) {
                operator = new Operator(token);
            } else {
                if (leftChild == null) {
                    leftChild = new Operand(token);
                } else if (rightChild == null) {
                    rightChild = new Operand(token);
                    operator.appendChild(leftChild);
                    operator.appendChild(rightChild);
                    current = operator;
                } else {
                    throw new IllegalStateException("");
                }
            }
        }
        return current;
    }
}
