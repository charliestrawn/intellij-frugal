package dev.strawn.frugal;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FrugalLexer extends LexerBase {
    
    private CharSequence buffer = "";
    private int startOffset = 0;
    private int endOffset = 0;
    private int currentOffset = 0;
    private int state = 0;
    
    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.buffer = buffer;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.currentOffset = startOffset;
        this.state = initialState;
    }
    
    @Override
    public int getState() {
        return state;
    }
    
    @Override
    public @Nullable IElementType getTokenType() {
        if (currentOffset >= endOffset) return null;
        
        char ch = buffer.charAt(currentOffset);
        if (Character.isWhitespace(ch)) {
            return FrugalTokenTypes.WHITE_SPACE;
        } else if (Character.isLetter(ch)) {
            return FrugalTokenTypes.IDENTIFIER;
        } else if (Character.isDigit(ch)) {
            return FrugalTokenTypes.NUMBER;
        } else if (ch == '"') {
            return FrugalTokenTypes.STRING;
        } else if (ch == '/' && currentOffset + 1 < endOffset && buffer.charAt(currentOffset + 1) == '/') {
            return FrugalTokenTypes.COMMENT;
        } else {
            return FrugalTokenTypes.SYMBOL;
        }
    }
    
    @Override
    public int getTokenStart() {
        return currentOffset;
    }
    
    @Override
    public int getTokenEnd() {
        if (currentOffset >= endOffset) return endOffset;
        
        char ch = buffer.charAt(currentOffset);
        int end = currentOffset + 1;
        
        if (Character.isWhitespace(ch)) {
            while (end < endOffset && Character.isWhitespace(buffer.charAt(end))) {
                end++;
            }
        } else if (Character.isLetter(ch)) {
            while (end < endOffset && (Character.isLetterOrDigit(buffer.charAt(end)) || buffer.charAt(end) == '_')) {
                end++;
            }
        } else if (Character.isDigit(ch)) {
            while (end < endOffset && Character.isDigit(buffer.charAt(end))) {
                end++;
            }
        } else if (ch == '"') {
            while (end < endOffset && buffer.charAt(end) != '"') {
                if (buffer.charAt(end) == '\\') end++;
                end++;
            }
            if (end < endOffset) end++;
        } else if (ch == '/' && currentOffset + 1 < endOffset && buffer.charAt(currentOffset + 1) == '/') {
            while (end < endOffset && buffer.charAt(end) != '\n') {
                end++;
            }
        }
        
        return end;
    }
    
    @Override
    public void advance() {
        currentOffset = getTokenEnd();
    }
    
    @Override
    public @NotNull CharSequence getBufferSequence() {
        return buffer;
    }
    
    @Override
    public int getBufferEnd() {
        return endOffset;
    }
}