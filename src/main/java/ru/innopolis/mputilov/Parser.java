package ru.innopolis.mputilov;

import ru.innopolis.mputilov.expression.*;
import ru.innopolis.mputilov.expression.Integer;
import ru.innopolis.mputilov.token.Tokenizer;

import java.util.StringJoiner;

/**
 * Created by mputilov on 04.09.16.
 */
public class Parser {

    private final Tokenizer iterator;

    public Parser(String input) {
        iterator = new Tokenizer(input);
    }

    public static void main(String[] args) {
        String rawExpression = "1 + 2     + 4 -5 > 3 and (3 > (7 + 8))";
        Expression expression = new Parser(rawExpression).parse();
        new PrettyPrinter().print(expression);

        System.out.println(new Parser("1 + 2 + 3 - 3 < 2").parse().evaluate());
    }


    public Expression parse() {
        return parseLogical();
    }

    private Expression parseLogical() {
        Expression a = parseRelation();
        Logical.OpCode op = parseLogOperator();
        if (op != Logical.OpCode.NONE) {
            Expression b = parseLogical();
            return new Logical(op, a, b);
        }
        return a;
    }

    private Logical.OpCode parseLogOperator() {
        if (iterator.hasNext()) {
            switch (iterator.peek()) {
                case "and":
                    iterator.next();
                    return Logical.OpCode.AND;
                case "or":
                    iterator.next();
                    return Logical.OpCode.OR;
                default:
                    break;
            }
        }
        return Logical.OpCode.NONE;
    }

    private Expression parseRelation() {
        Expression a = parseTerm();
        Relation.OpCode op = parseRelationOperator();
        if (op != Relation.OpCode.NONE) {
            Expression b = parseTerm();
            return new Relation(op, a, b);
        }
        return a;
    }

    private Relation.OpCode parseRelationOperator() {
        if (iterator.hasNext()) {
            switch (iterator.peek()) {
                case "<":
                    iterator.next();
                    return Relation.OpCode.LESS;
                case ">":
                    iterator.next();
                    return Relation.OpCode.GREATER;
                default:
                    break;
            }
        }
        return Relation.OpCode.NONE;
    }

    private Term.OpCode parseTermOperator() {
        if (iterator.hasNext()) {
            switch (iterator.peek()) {
                case "+":
                    iterator.next();
                    return Term.OpCode.PLUS;
                case "-":
                    iterator.next();
                    return Term.OpCode.MINUS;
                default:
                    break;
            }
        }
        return Term.OpCode.NONE;
    }

    private Expression parseTerm() { //"1 + 2 + 3"
        Expression a = parseFactor();
        Term.OpCode op = parseTermOperator();
        if (op != Term.OpCode.NONE) {
            Expression b = parseTerm();
            return new Term(op, a, b);
        }
        return a;
    }

    private Expression parseFactor() {
        Expression a = parsePrimary();
        Factor.OpCode op = parseFactorOperator();
        if (op != Factor.OpCode.NONE) {
            Expression b = parseFactor();
            return new Factor(op, a, b);
        }
        return a;
    }

    private Expression parsePrimary() {
        if (iterator.hasNext()) {
            String token = iterator.next();
            switch (token) {
                case "(":
                    return new Parenthesized(new Parser(readAllInParenthesis()).parse());
                default:
                    return new Integer(token);
            }
        }
        throw new RuntimeException("sad");
    }

    private String readAllInParenthesis() {
        int openParenthesisCount = 1;
        StringJoiner insideParenthesis = new StringJoiner(" ");
        while (iterator.hasNext()) {
            String token = iterator.next();
            if (token.equals("(")) {
                openParenthesisCount++;
            } else if (token.equals(")")) {
                openParenthesisCount--;
            }
            if (openParenthesisCount == 0) {
                return insideParenthesis.toString();
            }
            insideParenthesis.add(token);
        }
        throw new IllegalStateException("");
    }

    private Factor.OpCode parseFactorOperator() {
        if (iterator.hasNext()) {
            switch (iterator.peek()) {
                case "*":
                    iterator.next();
                    return Factor.OpCode.MUL;
                case "/":
                    iterator.next();
                    return Factor.OpCode.DIV;
                default:
                    break;
            }
        }
        return Factor.OpCode.NONE;
    }
}
