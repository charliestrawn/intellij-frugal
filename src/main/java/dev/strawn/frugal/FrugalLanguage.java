package dev.strawn.frugal;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;

public class FrugalLanguage extends Language {
    public static final FrugalLanguage INSTANCE = new FrugalLanguage();
    public static final String MIME_TYPE = "text/frugal";
    
    private FrugalLanguage() {
        super("Frugal");
    }
    
    @Override
    public @NotNull String getDisplayName() {
        return "Frugal";
    }
    
    @Override
    public boolean isCaseSensitive() {
        return true;
    }
}