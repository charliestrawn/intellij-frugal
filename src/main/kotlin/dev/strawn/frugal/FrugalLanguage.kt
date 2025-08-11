package dev.strawn.frugal

import com.intellij.lang.Language

object FrugalLanguage : Language("Frugal") {
    const val MIME_TYPE = "text/frugal"
    
    override fun getDisplayName(): String = "Frugal"
    
    override fun isCaseSensitive(): Boolean = true
}