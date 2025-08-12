package dev.strawn.frugal;

import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.lexer.Lexer;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FrugalSyntaxHighlighterFactory extends SyntaxHighlighterFactory {
    
    @Override
    public @NotNull SyntaxHighlighterBase getSyntaxHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile) {
        return new FrugalSyntaxHighlighter();
    }
    
    public static class FrugalSyntaxHighlighter extends SyntaxHighlighterBase {
        
        public static final TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey(
            "FRUGAL_KEYWORD",
            DefaultLanguageHighlighterColors.KEYWORD
        );
        
        public static final TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(
            "FRUGAL_STRING", 
            DefaultLanguageHighlighterColors.STRING
        );
        
        public static final TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey(
            "FRUGAL_NUMBER",
            DefaultLanguageHighlighterColors.NUMBER
        );
        
        public static final TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey(
            "FRUGAL_COMMENT",
            DefaultLanguageHighlighterColors.LINE_COMMENT
        );
        
        public static final TextAttributesKey IDENTIFIER = TextAttributesKey.createTextAttributesKey(
            "FRUGAL_IDENTIFIER",
            DefaultLanguageHighlighterColors.IDENTIFIER
        );
        
        private static final TextAttributesKey[] KEYWORDS = new TextAttributesKey[]{KEYWORD};
        private static final TextAttributesKey[] STRINGS = new TextAttributesKey[]{STRING};
        private static final TextAttributesKey[] NUMBERS = new TextAttributesKey[]{NUMBER};
        private static final TextAttributesKey[] COMMENTS = new TextAttributesKey[]{COMMENT};
        private static final TextAttributesKey[] IDENTIFIERS = new TextAttributesKey[]{IDENTIFIER};
        private static final TextAttributesKey[] EMPTY = new TextAttributesKey[0];
        
        @Override
        public @NotNull Lexer getHighlightingLexer() {
            return new FrugalLexer();
        }
        
        @Override
        public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
            if (tokenType == FrugalTokenTypes.NAMESPACE ||
                tokenType == FrugalTokenTypes.INCLUDE ||
                tokenType == FrugalTokenTypes.SERVICE ||
                tokenType == FrugalTokenTypes.STRUCT ||
                tokenType == FrugalTokenTypes.ENUM ||
                tokenType == FrugalTokenTypes.EXCEPTION ||
                tokenType == FrugalTokenTypes.UNION ||
                tokenType == FrugalTokenTypes.TYPEDEF) {
                return KEYWORDS;
            } else if (tokenType == FrugalTokenTypes.STRING) {
                return STRINGS;
            } else if (tokenType == FrugalTokenTypes.NUMBER) {
                return NUMBERS;
            } else if (tokenType == FrugalTokenTypes.COMMENT) {
                return COMMENTS;
            } else if (tokenType == FrugalTokenTypes.IDENTIFIER) {
                return IDENTIFIERS;
            } else {
                return EMPTY;
            }
        }
    }
}