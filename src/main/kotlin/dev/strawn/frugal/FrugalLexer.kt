package dev.strawn.frugal

import com.intellij.lexer.LexerBase
import com.intellij.psi.tree.IElementType

class FrugalLexer : LexerBase() {
    
    private var buffer: CharSequence = ""
    private var startOffset: Int = 0
    private var endOffset: Int = 0
    private var currentOffset: Int = 0
    private var state: Int = 0
    
    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.buffer = buffer
        this.startOffset = startOffset
        this.endOffset = endOffset
        this.currentOffset = startOffset
        this.state = initialState
    }
    
    override fun getState(): Int = state
    
    override fun getTokenType(): IElementType? {
        if (currentOffset >= endOffset) return null
        
        val char = buffer[currentOffset]
        return when {
            char.isWhitespace() -> FrugalTokenTypes.WHITE_SPACE
            char.isLetter() -> FrugalTokenTypes.IDENTIFIER
            char.isDigit() -> FrugalTokenTypes.NUMBER
            char == '"' -> FrugalTokenTypes.STRING
            char == '/' && currentOffset + 1 < endOffset && buffer[currentOffset + 1] == '/' -> FrugalTokenTypes.COMMENT
            else -> FrugalTokenTypes.SYMBOL
        }
    }
    
    override fun getTokenStart(): Int = currentOffset
    
    override fun getTokenEnd(): Int {
        if (currentOffset >= endOffset) return endOffset
        
        val char = buffer[currentOffset]
        var end = currentOffset + 1
        
        when {
            char.isWhitespace() -> {
                while (end < endOffset && buffer[end].isWhitespace()) {
                    end++
                }
            }
            char.isLetter() -> {
                while (end < endOffset && (buffer[end].isLetterOrDigit() || buffer[end] == '_')) {
                    end++
                }
            }
            char.isDigit() -> {
                while (end < endOffset && buffer[end].isDigit()) {
                    end++
                }
            }
            char == '"' -> {
                while (end < endOffset && buffer[end] != '"') {
                    if (buffer[end] == '\\') end++
                    end++
                }
                if (end < endOffset) end++
            }
            char == '/' && currentOffset + 1 < endOffset && buffer[currentOffset + 1] == '/' -> {
                while (end < endOffset && buffer[end] != '\n') {
                    end++
                }
            }
        }
        
        return end
    }
    
    override fun advance() {
        currentOffset = tokenEnd
    }
    
    override fun getBufferSequence(): CharSequence = buffer
    
    override fun getBufferEnd(): Int = endOffset
}