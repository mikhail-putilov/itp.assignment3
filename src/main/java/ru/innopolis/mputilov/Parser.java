package ru.innopolis.mputilov;

import ru.innopolis.mputilov.expression.*;

import java.util.Scanner;

/**
 * Created by mputilov on 04.09.16.
 */
public class Parser {

    Scanner scanner;
    private String input;
    private boolean isEnd;

    public Parser(String s) {
        input = s;
        scanner = new Scanner(s);
    }

    public static void main(String[] args) {
        String rawExpression = "1+1";
        Expression expression = new Parser(rawExpression).parse();
        new PrettyPrinter().print(expression);
    }

    public Expression parse() {
        return parseLogical();
    }

    private Expression parseLogical() {
        Expression a = parseRelation();
        if (isEnd) {
            return a;
        }
        Logical.OpCode op = parseLogOperator();
        if (op != Logical.OpCode.NONE) {
            Expression b = parseRelation();
            return new Logical(op, a, b);
        }
        return a;
    }

    private Logical.OpCode parseLogOperator() {
        if (scanner.hasNext("and")) {
            return Logical.OpCode.AND;
        }
        if (scanner.hasNext("or")) {
            return Logical.OpCode.OR;
        }
        return Logical.OpCode.NONE;
    }

    private Expression parseRelation() {
        Expression a = parseTerm();
        if (isEnd) {
            return a;
        }
        Relation.OpCode op = parseRelationOperator();
        if (op != Relation.OpCode.NONE) {
            Expression b = parseTerm();
            return new Relation(op, a, b);
        }
        return a;
    }

    private Relation.OpCode parseRelationOperator() {
        if (scanner.hasNext("<")) {
            return Relation.OpCode.LESS;
        }
        if (scanner.hasNext(">")) {
            return Relation.OpCode.GREATER;
        }
        return Relation.OpCode.NONE;
    }

    private Term.OpCode parseTermOperator() {
        if (scanner.hasNext("\\+")) {
            return Term.OpCode.PLUS;
        }
        if (scanner.hasNext("\\-")) {
            return Term.OpCode.MINUS;
        }
        return Term.OpCode.NONE;
    }

    private Expression parseTerm() {
        return new Term(Term.OpCode.NONE, new Factor(), new Factor());
    }
}
