package dev.strawn.frugal

import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

object FrugalTokenTypes {
    @JvmField val WHITE_SPACE = TokenType.WHITE_SPACE
    @JvmField val IDENTIFIER = IElementType("IDENTIFIER", FrugalLanguage)
    @JvmField val NUMBER = IElementType("NUMBER", FrugalLanguage)
    @JvmField val STRING = IElementType("STRING", FrugalLanguage)
    @JvmField val COMMENT = IElementType("COMMENT", FrugalLanguage)
    @JvmField val SYMBOL = IElementType("SYMBOL", FrugalLanguage)
    
    // Keywords
    @JvmField val NAMESPACE = IElementType("NAMESPACE", FrugalLanguage)
    @JvmField val INCLUDE = IElementType("INCLUDE", FrugalLanguage)
    @JvmField val SERVICE = IElementType("SERVICE", FrugalLanguage)
    @JvmField val STRUCT = IElementType("STRUCT", FrugalLanguage)
    @JvmField val ENUM = IElementType("ENUM", FrugalLanguage)
    @JvmField val EXCEPTION = IElementType("EXCEPTION", FrugalLanguage)
    @JvmField val UNION = IElementType("UNION", FrugalLanguage)
    @JvmField val TYPEDEF = IElementType("TYPEDEF", FrugalLanguage)
}