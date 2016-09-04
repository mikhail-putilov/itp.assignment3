
package ru.innopolis.mputilov.token;

import java.util.Iterator;

/**
 * String expression which can be iterated with foreach
 *
 * Created by mputilov on 15.07.16.
 */
public class TokenIterableExpression implements Iterable<String>{

    private final Tokenizer tokenizer;

    public TokenIterableExpression(String expression) {
        tokenizer = new Tokenizer(expression);
    }

    @Override
    public Iterator<String> iterator() {
        return tokenizer;
    }
}
