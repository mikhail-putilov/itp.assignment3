package ru.innopolis.mputilov.token;

import java.util.Iterator;

import static java.lang.String.valueOf;

/**
 * Tokenizer which responsibility to iterate given string token by token
 */
public class Tokenizer implements Iterator<String> {
    private int currentIndex = 0;
    private String toBeTokenized;

    public Tokenizer(String toBeTokenized) {
        this.toBeTokenized = toBeTokenized;
    }

    private String readNextToken() {
        StringBuilder parsedTokenBuilder = new StringBuilder();
        //best guess:
        String mayBeOperator = valueOf(toBeTokenized.charAt(currentIndex));
        if (isOperator(mayBeOperator)) {
            currentIndex++;
            return mayBeOperator;
        }

        for (; currentIndex < toBeTokenized.length(); currentIndex++) {
            char c = toBeTokenized.charAt(currentIndex);
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (isOperator(c) && isNotEmpty(parsedTokenBuilder)) {
                return parsedTokenBuilder.toString();
            } else if (isOperator(c) && isEmpty(parsedTokenBuilder)) {
                currentIndex++;
                return valueOf(c);
            }
            parsedTokenBuilder.append(c);
        }
        return parsedTokenBuilder.toString();
    }

    public static boolean isOperator(String mayBeOperator) {
        return isOperator(mayBeOperator.charAt(0));
    }

    private static boolean isOperator(char mayBeOperator) {
        switch (mayBeOperator) {
            case '*':
            case '+':
            case '-':
            case '/':
            case '(':
            case ')':
                return true;
            default:
                return false;
        }
    }

    private boolean isNotEmpty(StringBuilder parsedTokenBuilder) {
        return parsedTokenBuilder.length() > 0;
    }

    private boolean isEmpty(StringBuilder parsedTokenBuilder) {
        return parsedTokenBuilder.length() == 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < toBeTokenized.length();
    }

    @Override
    public String next() {
        return readNextToken();
    }
}
