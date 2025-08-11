package dev.strawn.frugal

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object FrugalFileType : LanguageFileType(FrugalLanguage) {
    
    override fun getName(): String = "Frugal"
    
    override fun getDescription(): String = "Frugal language files"
    
    override fun getDefaultExtension(): String = "frugal"
    
    override fun getIcon(): Icon? = IconLoader.getIcon("/icons/frugal.svg", javaClass)
}