package dev.strawn.frugal;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class FrugalFile extends PsiFileBase {
    
    public FrugalFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, FrugalLanguage.INSTANCE);
    }
    
    @Override
    public @NotNull FileType getFileType() {
        return FrugalFileType.INSTANCE;
    }
    
    @Override
    public String toString() {
        return "Frugal File";
    }
}