package dev.strawn.frugal.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import com.intellij.openapi.diagnostic.Logger
import dev.strawn.frugal.FrugalFileType
import java.io.File
import java.nio.file.Paths

class FrugalLspServerSupportProvider : LspServerSupportProvider {
    
    override fun fileOpened(project: Project, file: VirtualFile, serverStarter: LspServerSupportProvider.LspServerStarter) {
        if (file.fileType == FrugalFileType) {
            serverStarter.ensureServerStarted(FrugalLspServerDescriptor(project))
        }
    }
}

class FrugalLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Frugal") {
    
    companion object {
        private val LOG = Logger.getInstance(FrugalLspServerDescriptor::class.java)
    }
    
    override fun isSupportedFile(file: VirtualFile): Boolean {
        return file.fileType == FrugalFileType
    }
    
    override fun createCommandLine(): ProcessBuilder {
        val binaryPath = findFrugalLsBinary()
        LOG.info("Using frugal-ls binary at: $binaryPath")
        return ProcessBuilder(binaryPath)
    }
    
    private fun findFrugalLsBinary(): String {
        // Try bundled binary first
        val bundledBinary = getBundledBinary()
        if (bundledBinary != null && bundledBinary.exists() && bundledBinary.canExecute()) {
            return bundledBinary.absolutePath
        }
        
        // Fallback to system PATH
        return findInSystemPath() ?: "frugal-ls"
    }
    
    private fun getBundledBinary(): File? {
        val classLoader = javaClass.classLoader
        val os = System.getProperty("os.name").lowercase()
        val arch = System.getProperty("os.arch").lowercase()
        
        val binaryName = when {
            os.contains("windows") -> "frugal-ls-windows.exe"
            os.contains("mac") || os.contains("darwin") -> {
                if (arch.contains("aarch64") || arch.contains("arm")) {
                    "frugal-ls-macos-arm64"
                } else {
                    "frugal-ls-macos"
                }
            }
            os.contains("linux") -> "frugal-ls-linux"
            else -> return null
        }
        
        try {
            val resource = classLoader.getResource("binaries/$binaryName") ?: return null
            val tempFile = File.createTempFile("frugal-ls", if (os.contains("windows")) ".exe" else "")
            tempFile.deleteOnExit()
            
            resource.openStream().use { input ->
                tempFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            
            tempFile.setExecutable(true)
            return tempFile
        } catch (e: Exception) {
            LOG.warn("Failed to extract bundled frugal-ls binary: ${e.message}")
            return null
        }
    }
    
    private fun findInSystemPath(): String? {
        val pathEnv = System.getenv("PATH") ?: return null
        val pathSeparator = if (System.getProperty("os.name").lowercase().contains("windows")) ";" else ":"
        
        for (path in pathEnv.split(pathSeparator)) {
            val binaryName = if (System.getProperty("os.name").lowercase().contains("windows")) {
                "frugal-ls.exe"
            } else {
                "frugal-ls"
            }
            
            val binary = Paths.get(path, binaryName).toFile()
            if (binary.exists() && binary.canExecute()) {
                return binary.absolutePath
            }
        }
        
        return null
    }
}