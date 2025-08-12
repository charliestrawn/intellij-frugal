package dev.strawn.frugal;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

public class FrugalTokenTypes {
    public static final IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    public static final IElementType IDENTIFIER = new IElementType("IDENTIFIER", FrugalLanguage.INSTANCE);
    public static final IElementType NUMBER = new IElementType("NUMBER", FrugalLanguage.INSTANCE);
    public static final IElementType STRING = new IElementType("STRING", FrugalLanguage.INSTANCE);
    public static final IElementType COMMENT = new IElementType("COMMENT", FrugalLanguage.INSTANCE);
    public static final IElementType SYMBOL = new IElementType("SYMBOL", FrugalLanguage.INSTANCE);
    
    // Keywords
    public static final IElementType NAMESPACE = new IElementType("NAMESPACE", FrugalLanguage.INSTANCE);
    public static final IElementType INCLUDE = new IElementType("INCLUDE", FrugalLanguage.INSTANCE);
    public static final IElementType SERVICE = new IElementType("SERVICE", FrugalLanguage.INSTANCE);
    public static final IElementType STRUCT = new IElementType("STRUCT", FrugalLanguage.INSTANCE);
    public static final IElementType ENUM = new IElementType("ENUM", FrugalLanguage.INSTANCE);
    public static final IElementType EXCEPTION = new IElementType("EXCEPTION", FrugalLanguage.INSTANCE);
    public static final IElementType UNION = new IElementType("UNION", FrugalLanguage.INSTANCE);
    public static final IElementType TYPEDEF = new IElementType("TYPEDEF", FrugalLanguage.INSTANCE);
}