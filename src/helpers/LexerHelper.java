package helpers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Supplier;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;

import static dist.SudoLexer.*;

public class LexerHelper {
    LinkedList<Integer> indentStack = new LinkedList<Integer>(Arrays.asList(0));
    LinkedList<Token> tokens = new LinkedList<Token>();

    /* 
     * Helper method to handle whitespace (NEWLINE, INDENT, DEDENT) while scanning tokens
     */
    public Token nextToken(Supplier<Token> _nextToken) {
        // The tokens list must be empty before looking at the next token
        if (tokens.size() > 0) return tokens.removeFirst();

        // Get the next token
        Token next = _nextToken.get();

        // 
        String nextText = next.getText();
        if (next.getType() != WHITESPACE) return next;

        String[] whitespace = nextText.split("(\\r|\\n)+");
        char lastChar = nextText.charAt(nextText.length() - 1);


        int indent = (whitespace.length == 0 || lastChar == '\n') 
            ? 0 
            : whitespace[whitespace.length - 1].length();

        // Check if whitespace results in INDENT
        if (indentStack.size() > 0 && indentStack.getLast() < indent) {
            indentStack.addLast(indent);
            tokens.addLast(new CommonToken(INDENT, "<INDENT>"));
        } 
        // Check if whitespace results in DEDENT
        else if (indentStack.size() > 0 && indentStack.getLast() > indent) {
            if (!indentStack.contains(indent)) {
                tokens.addLast(new CommonToken(ERR_DEDENT, "<ERR_DEDENT>"));
            }
            else {
                while(indentStack.size() > 0 && indentStack.getLast() != indent) {
                    tokens.addLast(new CommonToken(DEDENT, "<DEDENT>"));
                    indentStack.removeLast();
                }
            }
        }
        // Always return NEWLINE
        return new CommonToken(NEWLINE, "<NEWLINE>");
    }
}
