package dev.strawn.frugal

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class FrugalParserDefinition : ParserDefinition {
    
    companion object {
        val FILE = IFileElementType(FrugalLanguage)
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.EMPTY
        val STRINGS = TokenSet.EMPTY
    }
    
    override fun createLexer(project: Project?): Lexer {
        return FrugalLexer()
    }
    
    override fun createParser(project: Project?): PsiParser {
        return FrugalParser()
    }
    
    override fun getFileNodeType(): IFileElementType {
        return FILE
    }
    
    override fun getCommentTokens(): TokenSet {
        return COMMENTS
    }
    
    override fun getStringLiteralElements(): TokenSet {
        return STRINGS
    }
    
    override fun getWhitespaceTokens(): TokenSet {
        return WHITE_SPACES
    }
    
    override fun createElement(node: ASTNode?): PsiElement {
        return FrugalPsiElement(node)
    }
    
    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return FrugalFile(viewProvider)
    }
}