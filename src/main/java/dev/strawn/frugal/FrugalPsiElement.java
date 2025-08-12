package dev.strawn.frugal;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class FrugalPsiElement extends ASTWrapperPsiElement {
    
    public FrugalPsiElement(@NotNull ASTNode node) {
        super(node);
    }
}