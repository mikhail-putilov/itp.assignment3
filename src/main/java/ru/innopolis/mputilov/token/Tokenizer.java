package ru.innopolis.mputilov.token;

import java.util.Iterator;

import static java.lang.String.valueOf;

/**
 * Tokenizer which responsibility to iterate given string token by token
 */
public class Tokenizer implements Iterator<String> {
    private int currentIndex = 0;
    private String toBeTokenized;
    private State state;
    private String debugString;
    private String peek;


    public Tokenizer(String toBeTokenized) {
        this.toBeTokenized = toBeTokenized;
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
            case '>':
            case '<':
                return true;
            default:
                return false;
        }
    }

    public String peek() {
        int currentIndex = this.currentIndex;
        StringBuilder parsedTokenBuilder = new StringBuilder();
        //best guess:
        char currentChar = toBeTokenized.charAt(currentIndex);
        if (isOperator(currentChar)) {
            return String.valueOf(currentChar);
        } else {
            if (Character.isAlphabetic(currentChar)) {
                state = State.WORD;
            } else if (Character.isDigit(currentChar)){
                state = State.INTEGER;
            }
        }

        for (; currentIndex < toBeTokenized.length(); currentIndex++) {
            char c = toBeTokenized.charAt(currentIndex);
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (isOperator(c) && isNotEmpty(parsedTokenBuilder)) {
                return parsedTokenBuilder.toString();
            } else if (isOperator(c) && isEmpty(parsedTokenBuilder)) {
                return valueOf(c);
            } else if (Character.isDigit(c) && state != State.INTEGER) {
                return parsedTokenBuilder.toString();
            } else if (Character.isAlphabetic(c) && state != State.WORD) {
                return parsedTokenBuilder.toString();
            }
            parsedTokenBuilder.append(c);
        }
        return parsedTokenBuilder.toString();
    }

    private String readNextToken() {
        debugString = toBeTokenized.substring(currentIndex);
        StringBuilder parsedTokenBuilder = new StringBuilder();
        //best guess:
        char currentChar = toBeTokenized.charAt(currentIndex);
        if (isOperator(currentChar)) {
            currentIndex++;
            return String.valueOf(currentChar);
        } else {
            if (Character.isAlphabetic(currentChar)) {
                state = State.WORD;
            } else if (Character.isDigit(currentChar)){
                state = State.INTEGER;
            }
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
            } else if (Character.isDigit(c) && state != State.INTEGER) {
                return parsedTokenBuilder.toString();
            } else if (Character.isAlphabetic(c) && state != State.WORD) {
                return parsedTokenBuilder.toString();
            }
            parsedTokenBuilder.append(c);
        }
        return parsedTokenBuilder.toString();
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

    enum State {INTEGER, WORD}
}
