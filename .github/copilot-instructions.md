# Copilot instructions for ScaffoldingAndroid3

## Build, test, and lint

Run commands from the repository root.

- Build debug APK: `./gradlew.bat :app:assembleDebug`
- Install on a connected device/emulator: `./gradlew.bat :app:installDebug`
- Run the unit test suite: `./gradlew.bat :app:testDebugUnitTest`
- Run one test method/class: `./gradlew.bat :app:testDebugUnitTest --tests "ar.edu.unlam.scaffoldingandroid3.ExampleUnitTest.addition_isCorrect"`
- Run instrumented tests on a device: `./gradlew.bat :app:connectedDebugAndroidTest`
- Run static checks used by CI: `./gradlew.bat :app:ktlintCheck :app:lint`
- Auto-format Kotlin sources: `./gradlew.bat :app:ktlintFormat`
- Generate the Kover report: `./gradlew.bat :app:koverXmlReportRelease`

The Android CI setup expects JDK 17 for the app target; the Gradle daemon configuration may request JetBrains JDK 25, but the project bytecode is Java 17. Keep `local.properties` local and uncommitted.

## High-level architecture

This repository is a single-module Android app built around Jetpack Compose and a hexagonal / clean-layer approach.

- `app/src/main/java/ar/edu/unlam/scaffoldingandroid3/`
  - `domain/`: pure business models and repository contracts (`Book`, `BookRepository`, `BookService`)
  - `data/`: implementations/adapters such as `BookStubRepo`; these return domain models, not Android or persistence-layer types
  - `ui/`: Compose UI (`MainActivity`, `components/`, `theme/`); the app is a single-activity Compose app
  - `di/`: Hilt modules; many are still empty stubs in this teaching scaffold

The intended dependency flow is:

- `ui` depends on `domain`
- `data` implements the domain contracts
- `domain` stays independent from Android, Room, Retrofit, ViewModels, and other UI concerns

This is a teaching scaffold: many classes are intentionally stubbed, but the structure should remain consistent with the layering above.

## Key project conventions

- Use the app module layout from the existing source: keep domain models and interfaces in `domain/`, repository implementations in `data/`, and composables in `ui/components/`.
- Follow the package conventions already in this repo: `ar.edu.unlam.scaffoldingandroid3.*` with lowercase package names and `PascalCase` for classes and Compose functions when it matches the project style.
- Keep the business layer free of Android/Hilt/Retrofit/Room specifics. If a class is in `domain/`, it should not depend on UI, database, or HTTP code.
- Prefer small, single-purpose functions and early returns; keep comments only for non-obvious logic.
- `ktlint` is enforced in CI, and `.editorconfig` allows `PascalCase` names for `@Composable` functions.
- Dependency versions live in `gradle/libs.versions.toml`; do not duplicate version numbers across files.
- The app is intended to use Hilt and `hiltViewModel()` for screen-level state management, but the scaffold still contains many empty stubs and minimal wiring.

## Relevant repository guidance

This repo's existing docs are consistent on the same core points:

- `README.md` describes the hexagonal architecture and the intended separation between `domain`, `data`, and `ui`.
- `AGENTS.md` is the contributor-facing checklist for build commands, dependency rules, and testing guidance.
- `CLAUDE.md` clarifies the project is a teaching scaffold and notes that many classes remain empty stubs.

Use these docs as the source of truth for architecture and workflow, and keep future changes aligned with the current project structure instead of introducing alternative layering patterns.
