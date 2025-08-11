package dev.strawn.frugal

import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.lexer.Lexer
import com.intellij.psi.tree.IElementType

class FrugalSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): FrugalSyntaxHighlighter {
        return FrugalSyntaxHighlighter()
    }
}

class FrugalSyntaxHighlighter : SyntaxHighlighterBase() {
    
    companion object {
        val KEYWORD = TextAttributesKey.createTextAttributesKey(
            "FRUGAL_KEYWORD",
            DefaultLanguageHighlighterColors.KEYWORD
        )
        
        val STRING = TextAttributesKey.createTextAttributesKey(
            "FRUGAL_STRING", 
            DefaultLanguageHighlighterColors.STRING
        )
        
        val NUMBER = TextAttributesKey.createTextAttributesKey(
            "FRUGAL_NUMBER",
            DefaultLanguageHighlighterColors.NUMBER
        )
        
        val COMMENT = TextAttributesKey.createTextAttributesKey(
            "FRUGAL_COMMENT",
            DefaultLanguageHighlighterColors.LINE_COMMENT
        )
        
        val IDENTIFIER = TextAttributesKey.createTextAttributesKey(
            "FRUGAL_IDENTIFIER",
            DefaultLanguageHighlighterColors.IDENTIFIER
        )
        
        private val KEYWORDS = arrayOf(KEYWORD)
        private val STRINGS = arrayOf(STRING)
        private val NUMBERS = arrayOf(NUMBER)
        private val COMMENTS = arrayOf(COMMENT)
        private val IDENTIFIERS = arrayOf(IDENTIFIER)
        private val EMPTY = emptyArray<TextAttributesKey>()
    }
    
    override fun getHighlightingLexer(): Lexer {
        return FrugalLexer()
    }
    
    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        return when (tokenType) {
            FrugalTokenTypes.NAMESPACE,
            FrugalTokenTypes.INCLUDE,
            FrugalTokenTypes.SERVICE,
            FrugalTokenTypes.STRUCT,
            FrugalTokenTypes.ENUM,
            FrugalTokenTypes.EXCEPTION,
            FrugalTokenTypes.UNION,
            FrugalTokenTypes.TYPEDEF -> KEYWORDS
            
            FrugalTokenTypes.STRING -> STRINGS
            FrugalTokenTypes.NUMBER -> NUMBERS
            FrugalTokenTypes.COMMENT -> COMMENTS
            FrugalTokenTypes.IDENTIFIER -> IDENTIFIERS
            else -> EMPTY
        }
    }
}