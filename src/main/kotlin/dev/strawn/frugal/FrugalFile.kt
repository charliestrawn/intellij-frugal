package dev.strawn.frugal

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class FrugalFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, FrugalLanguage) {
    
    override fun getFileType(): FileType = FrugalFileType
    
    override fun toString(): String = "Frugal File"
}