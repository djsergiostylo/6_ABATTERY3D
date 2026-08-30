# ABATTERY 3D

Monitor de bateria Android con visualizacion 3D en WebGL (Three.js).

## Stack
- Kotlin + Jetpack Compose + MVVM
- Room (historial de sesiones)
- Three.js (bateria 3D en WebView)
- AdMob (banner + interstitial + rewarded)
- WorkManager (alertas termicas)

## Estructura
```
app/src/main/
  java/com/stylo/abattery3d/
    MainActivity.kt
    ads/          AdMobManager, AdBannerComposable
    data/         BatterySnapshot, Repository, Tracker, Predictor, Analyzer
    data/local/   Room entities, DAO, Database
    ui/           ViewModel, WebViewScreen
    ui/theme/     Theme
  assets/
    abatery_3d.html   (Three.js frontend)
  res/
    ...
```

## Build
```
git clone https://github.com/djsergiostylo/6_ABATTERY3D
cd 6_ABATTERY3D
./gradlew assembleDebug
```
