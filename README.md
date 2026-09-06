# Sulav Proxy — Android App

Native Android/Jetpack Compose starter project for Sulav Proxy.

Features included in the UI:
- URL input
- Host / Port controls
- Start / Stop state
- LocalConfig preview
- Traffic dashboard
- HEX view
- Clear traffic
- Owner information

Default local configuration:
`{"serverLoginUrl":"http://127.0.0.1:8080/"}`

## Build
Open this folder in Android Studio and build the debug APK.

This project is intended for authorized local traffic debugging. It does not include automatic authentication-token extraction.


## GitHub Actions APK Build

The repository includes `.github/workflows/build-apk.yml`.
After pushing to GitHub, open **Actions → Build Sulav Proxy APK**. The workflow builds the debug APK automatically and uploads `SulavProxy-debug-apk` as a workflow artifact.

You can also start it manually with **Run workflow**.
