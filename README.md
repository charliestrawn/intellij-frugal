# IntelliJ Frugal Plugin

[![CI](https://github.com/charliestrawn/intellij-frugal/actions/workflows/ci.yml/badge.svg)](https://github.com/charliestrawn/intellij-frugal/actions/workflows/ci.yml)
[![Release](https://github.com/charliestrawn/intellij-frugal/actions/workflows/release.yml/badge.svg)](https://github.com/charliestrawn/intellij-frugal/actions/workflows/release.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

An IntelliJ IDEA plugin that adds comprehensive support for [Frugal](https://github.com/Workiva/frugal) (.frugal) files.

## Features

- **File Type Recognition**: Automatic detection and handling of .frugal files
- **Syntax Highlighting**: Rich syntax highlighting for Frugal language constructs
- **Language Server Integration**: Full LSP support via [frugal-ls](https://github.com/charliestrawn/frugal-ls)
- **Code Completion**: Intelligent code completion and suggestions
- **Error Detection**: Real-time error highlighting and diagnostics
- **Cross-Platform**: Works on Windows, macOS, and Linux

## Installation

### From JetBrains Marketplace (Recommended)
1. Open IntelliJ IDEA
2. Go to `File` → `Settings` → `Plugins`
3. Search for "Frugal Language Support"
4. Click `Install`

### Manual Installation
1. Download the latest release from [GitHub Releases](https://github.com/charliestrawn/intellij-frugal/releases)
2. Open IntelliJ IDEA
3. Go to `File` → `Settings` → `Plugins`
4. Click the gear icon → `Install Plugin from Disk...`
5. Select the downloaded `.zip` file

## Usage

Once installed, the plugin automatically:
- Recognizes `.frugal` files
- Provides syntax highlighting
- Starts the language server for advanced features

**No additional setup required!** The plugin includes bundled frugal-ls binaries for all platforms.

## Language Server

This plugin integrates with [frugal-ls](https://github.com/charliestrawn/frugal-ls) to provide advanced language features. The language server binaries are automatically bundled with the plugin, so no separate installation is needed.

### Binary Resolution Priority
1. **Bundled binary** (included with plugin)
2. **System PATH** (if frugal-ls is installed separately)
3. **Fallback** (graceful degradation)

## Development

### Prerequisites
- Java 21+
- IntelliJ IDEA 2025.1+ (for testing)
- Gradle 8.6+

### Building
```bash
./gradlew build
```

### Running in Development
```bash
./gradlew runIde
```

### Testing
```bash
./gradlew test
./gradlew verifyPlugin
```

### Creating a Release
1. Create and push a tag:
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```
2. GitHub Actions will automatically build and publish the release

## CI/CD

This project uses GitHub Actions for:
- **Continuous Integration**: Automated testing on multiple platforms
- **Automated Releases**: Tag-triggered releases to GitHub and JetBrains Marketplace
- **Dependency Updates**: Dependabot keeps dependencies current

### Required Secrets (for publishing)
- `PUBLISH_TOKEN`: JetBrains Marketplace token
- `CERTIFICATE_CHAIN`: Plugin signing certificate (optional)
- `PRIVATE_KEY`: Plugin signing private key (optional)
- `PRIVATE_KEY_PASSWORD`: Private key password (optional)

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Related Projects

- [frugal-ls](https://github.com/charliestrawn/frugal-ls) - Language Server Protocol implementation for Frugal
- [Frugal](https://github.com/Workiva/frugal) - The Frugal RPC framework