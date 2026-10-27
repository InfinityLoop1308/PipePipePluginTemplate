# PipePipe Plugin Template

Starting point for a third-party [PipePipe](https://github.com/InfinityLoop1308/PipePipe) service plugin. A plugin adds one `StreamingService` to the app. Plugins run inside PipePipe with the app's permissions, so users should only install code they trust.

## Requirements

- JDK 25 (Gradle downloads it automatically if missing)
- Android SDK with `build-tools;37.0.0` and `platforms;android-36`. Set `sdk.dir` in `local.properties` or `ANDROID_HOME`.

## Make it yours

1. Rename the package `com.example.myservice`, the classes, and `rootProject.name` in `settings.gradle`.
2. Edit `src/main/resources/plugin.json` (see below).
3. Implement `MyService`. Capabilities you don't support can return `null`; start with `getStreamLHFactory()` and `getStreamExtractor()`.

By default the plugin compiles against the extractor's `main` branch through JitPack (`extractorVersion` in `gradle.properties`). To build against a local checkout, put `extractorDir=../PipePipeExtractor` into `local.properties`.

## plugin.json

| Field | Rules |
| ----- | ----- |
| `pluginId` | Reverse domain, `[a-z0-9_.]`, at most 128 chars. Never change it after release. |
| `name` | Must equal the `StreamingService` name. |
| `versionCode` | Integer, increase with every release. |
| `versionName` | Shown to users. |
| `apiVersion` | `major.minor` of the plugin API you build against. Currently `1.0`. A host loads plugins with the same major and a minor not above its own. |
| `author` | Required. Shown in the plugin list. |
| `entryClass` | Class implementing `PipePipePlugin`. |
| `description` | Optional. |
| `icon` | Optional, path of a PNG/WebP in the package. Square, single color, transparent background, at most 512 px. The app tints it. |
| `settings` | Optional list of `string` or `boolean` settings. The app renders them; read the values with `context.getPreferences()` (see `MyService.getBaseUrl()`). |

The `serviceId` passed to `createService` must be handed to the `StreamingService` constructor unchanged.

## Build, sign, publish

```
./gradlew :tools:genKey      # once, creates signing.key (keep it secret, never commit it)
./gradlew :tools:index       # builds build/repo/<name>-<versionCode>.ppp and build/repo/index.json
```

Use the same key for every release. PipePipe pins the public key on first install and rejects updates signed with another one.

Users discover plugins through [awesome-pipepipe-plugins](https://github.com/InfinityLoop1308/awesome-pipepipe-plugins):

1. Upload the `.ppp` to a stable HTTPS URL, for example a GitHub release asset.
2. Take the entry for your plugin from `build/repo/index.json` and set `package` to that absolute URL. Set `nsfw` to `true` if the content is NSFW; the generated value is always `false`.
3. Open a pull request to awesome-pipepipe-plugins that adds the entry and a row in its table, and links your source code.

`build/repo` is only build output. It may still contain packages of older versions, so check the index before copying an entry.

## CI

`.github/workflows/ci.yml` builds the plugin and generates an index with a throwaway key on every push and pull request.

The throwaway key only validates the build; nothing it signs is published. If you add a release workflow, sign with your permanent key (for example from a repository secret). PipePipe rejects updates signed with a different key than the one used at first install.

## License

GPL-3.0, same as PipePipe. See `LICENSE`.
