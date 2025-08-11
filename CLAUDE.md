# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an IntelliJ IDEA plugin that adds support for Frugal (.frugal) files. The plugin integrates with the frugal-ls language server to provide comprehensive language features including syntax highlighting, code completion, and error detection.

## Development Commands

### Build the plugin
```bash
./gradlew build
```

### Run the plugin in a development IDE instance
```bash
./gradlew runIde
```

### Test the plugin
```bash
./gradlew test
```

### Verify the plugin
```bash
./gradlew verifyPlugin
```

### Build plugin distribution
```bash
./gradlew buildPlugin
```

## Architecture

### Core Components

- **FrugalLanguage**: Defines the language instance for IntelliJ
- **FrugalFileType**: Handles .frugal file type recognition and icons
- **FrugalLexer**: Basic lexical analysis for syntax highlighting
- **FrugalParser**: Minimal parser for PSI tree construction
- **FrugalSyntaxHighlighter**: Provides syntax highlighting rules

### Language Server Integration

- **FrugalLspServerSupportProvider**: Integrates with frugal-ls language server
- **FrugalLspServerDescriptor**: Configures LSP server connection

The plugin expects `frugal-ls` to be available in the system PATH. The language server provides advanced features like:
- Code completion
- Error detection
- Go-to-definition
- Hover information

### Project Structure

```
src/main/
├── kotlin/dev/strawn/frugal/
│   ├── lsp/                    # Language server integration
│   ├── FrugalLanguage.kt       # Language definition
│   ├── FrugalFileType.kt       # File type handling
│   ├── FrugalLexer.kt          # Lexical analyzer
│   ├── FrugalParser.kt         # Parser
│   └── FrugalSyntaxHighlighter.kt # Syntax highlighting
└── resources/
    ├── META-INF/plugin.xml     # Plugin configuration
    ├── icons/frugal.svg        # File type icon
    └── messages/               # Localization bundles
```

## Dependencies

- IntelliJ Platform SDK (2023.2.5)
- Kotlin stdlib
- frugal-ls language server (bundled with plugin)

## Binary Management

The plugin automatically downloads and bundles frugal-ls binaries for all supported platforms during build:
- Windows x64: frugal-ls-windows.exe
- macOS x64: frugal-ls-macos  
- macOS ARM64: frugal-ls-macos-arm64
- Linux x64: frugal-ls-linux

Binary resolution priority:
1. Bundled binary (extracted to temp file)
2. System PATH installation
3. Fallback to "frugal-ls" command

The `downloadFrugalLsBinaries` Gradle task fetches binaries from GitHub releases during build.

## Plugin Configuration

The plugin registers:
- File type association for .frugal files
- Language parser and lexer
- Syntax highlighter
- LSP server integration

Key extension points used:
- `fileType` - File type registration
- `lang.parserDefinition` - Parser registration
- `lang.syntaxHighlighterFactory` - Syntax highlighting
- `platform.lsp.serverSupportProvider` - LSP integration