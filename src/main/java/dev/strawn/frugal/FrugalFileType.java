package dev.strawn.frugal;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

public class FrugalFileType extends LanguageFileType {
    public static final FrugalFileType INSTANCE = new FrugalFileType();
    
    private FrugalFileType() {
        super(FrugalLanguage.INSTANCE);
    }
    
    @Override
    public @NotNull String getName() {
        return "Frugal";
    }
    
    @Override
    public @NotNull String getDescription() {
        return "Frugal language files";
    }
    
    @Override
    public @NotNull String getDefaultExtension() {
        return "frugal";
    }
    
    @Override
    public @Nullable Icon getIcon() {
        return IconLoader.getIcon("/icons/frugal.svg", getClass());
    }
}