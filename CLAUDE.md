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

- IntelliJ Platform SDK (2025.1)
- Java 21 (required by IntelliJ 2025.1)
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

## CI/CD Pipeline

### GitHub Actions Workflows

**CI Workflow** (`.github/workflows/ci.yml`):
- Runs on push/PR to master/main
- Tests on Ubuntu, Windows, and macOS
- Executes: test, verifyPlugin, buildPlugin
- Uploads build artifacts and test results

**Release Workflow** (`.github/workflows/release.yml`):
- Triggers on version tags (v*)
- Updates version in build.gradle from tag
- Builds and verifies plugin
- Signs plugin (if certificates configured)
- Creates GitHub release with artifacts
- Publishes to JetBrains Marketplace (if token configured)

**Dependabot** (`.github/dependabot.yml`):
- Weekly dependency updates for Gradle and GitHub Actions
- Automatically creates PRs for dependency updates

### Required Secrets for Publishing

Set these in GitHub repository settings → Secrets:
- `PUBLISH_TOKEN`: JetBrains Marketplace token
- `CERTIFICATE_CHAIN`: Plugin signing certificate (optional)
- `PRIVATE_KEY`: Plugin signing private key (optional)  
- `PRIVATE_KEY_PASSWORD`: Private key password (optional)

### Release Process

1. Create and push version tag: `git tag v1.0.0 && git push origin v1.0.0`
2. GitHub Actions automatically builds, tests, and releases
3. Plugin is published to GitHub Releases and JetBrains Marketplace

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