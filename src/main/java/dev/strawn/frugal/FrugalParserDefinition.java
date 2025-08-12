package dev.strawn.frugal;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class FrugalParserDefinition implements ParserDefinition {
    
    public static final IFileElementType FILE = new IFileElementType(FrugalLanguage.INSTANCE);
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.EMPTY;
    public static final TokenSet STRINGS = TokenSet.EMPTY;
    
    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new FrugalLexer();
    }
    
    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new FrugalParser();
    }
    
    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }
    
    @Override
    public @NotNull TokenSet getCommentTokens() {
        return COMMENTS;
    }
    
    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return STRINGS;
    }
    
    @Override
    public @NotNull TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }
    
    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return new FrugalPsiElement(node);
    }
    
    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new FrugalFile(viewProvider);
    }
}