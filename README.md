# IronReign – Hardcore Bodybuilding App (Pro Edition)

This repository contains the complete source code for **IronReign**, a high‑end Android application designed for professional bodybuilders and powerlifters.  The app focuses on maximal strength development, hypertrophic growth and mental discipline.  It is built natively with Kotlin using the MVVM architecture, Jetpack libraries and Material 3 design.  In dieser Version wurden die **Pro‑Features** (AR‑Coach, KI‑Planer, Wearables‑Integration und Challenge‑Modus) als eigenständige Module integriert.  Die Benutzeroberfläche und alle Texte sind vollständig auf Deutsch lokalisiert.

## Features

The app offers a rich set of features geared towards elite athletes:

- **Übungsbibliothek** — Durchstöbere eine Liste grundlegender Übungen (Bankdrücken, Kniebeugen, Kreuzheben, Schulterdrücken, Klimmzüge) und füge über die **+**‑Schaltfläche eigene Übungen hinzu.
- **Workout‑Logging** — Lege Workouts an, gib ihnen einen Namen und dokumentiere dein Training.  Erweiterungen für Sätze und Wiederholungen sind vorbereitet.
- **Fortschrittsvisualisierung** — Sieh dein Trainingsvolumen über die Zeit in einem interaktiven Line Chart (MPAndroidChart).  Die Daten basieren auf dem Produkt aus Wiederholungen × Gewicht pro Trainingstag.
- **Ernährungsrechner** — Berechne deine täglichen Makronährstoffe abhängig von Körpergewicht und Trainingsphase (Masse, Definition, Erhaltung).
- **AR‑Coach** — Ein Platzhalter zeigt an, dass ein Augmented‑Reality‑Coach in einer zukünftigen Version integriert wird.  Die Navigation ist bereits vorhanden.
- **AR‑Trainer** — Echtzeit‑Kamera‑Vorschau via CameraX mit eingeblendeten Anweisungen.  Die Kamera muss freigegeben werden; die Struktur ist vorbereitet für die Integration von ARCore/Sceneform und virtuellen Modellen.
- **KI‑Planer** — Ein interner Algorithmus teilt deine Übungen auf drei Trainingstage auf und stellt einen wöchentlichen Plan dar.  Die Ergebnisse werden in einer Liste dargestellt.
- **Wearables‑Integration** — Nutze den Schrittzähler deines Geräts: Starte und stoppe die Erfassung und sieh die Anzahl der während der Session absolvierten Schritte.  Bei fehlender Sensorunterstützung wird eine Meldung angezeigt.
- **PainZone‑Challenge** — Starte einen 30‑Sekunden‑Countdown, um dich selbst herauszufordern.  Die verbleibende Zeit wird sekündlich aktualisiert; nach Ablauf erscheint eine Erfolgsnachricht.
- **1RM‑Rechner** — Gib Gewicht und Wiederholungen ein, um deinen geschätzten One‑Rep‑Max nach der Epley‑Formel zu berechnen.  Diese Funktion ist als Beispiel für eine erweiterte Analytik in der App integriert.
- **AI‑Analyse** — Aggregiert deine Trainingsdaten, berechnet für jede Übung das Trainingsvolumen und einen geschätzten 1RM und zeigt die Ergebnisse übersichtlich an.  Diese Daten helfen dir, Fortschritte zu erkennen und Trainingspläne gezielt anzupassen.
- **Einstellungen** — Zeigt die App‑Version an und enthält Hinweise zu geplanten erweiterten Features wie IronDNA™.
- **Moderne Architektur** — MVVM + Repository‑Pattern; Hilt für Dependency Injection; Room für lokale Persistenz; Material Design 3 mit dunklem Thema.

## Project Structure

```
IronReignApp/
├── build.gradle.kts          # Root build script (AGP 8.13.0, Kotlin 2.1.20)
├── settings.gradle.kts       # Declares module and dependency repositories
├── gradle.properties         # Java/Kotlin build options
├── app/                      # Application module
│   ├── build.gradle.kts      # Module build script with dependencies
│   └── src/main/            
│       ├── AndroidManifest.xml
│       ├── java/com/ironreign/app/… # Kotlin sources (App, MainActivity, data, ui, di)
│       └── res/…              # XML layouts, navigation graph, resources
└── README.md                 # This file
```

## Build Instructions

1. **Install Android Studio** (Flamingo or later) with **JDK 17**.
2. Clone this repository and open the `IronReignApp` project in Android Studio.
3. Allow Gradle to download dependencies.  If you see a **“missing wrapper”** error, run `gradle wrapper --gradle-version=8.13` from the project root or add your own `gradle/wrapper` files.  The project uses **AGP 8.13.0** which requires **Gradle 8.13**.
4. Connect an emulator or device running **Android 8.0 (API 26)** or higher.
5. In Android Studio, select **Build → Build APK(s)**.  This will produce a debug‑signed APK at `app/build/outputs/apk/debug/app-debug.apk`.
6. Install the APK on your device (`adb install app-debug.apk`) or run the app directly from the IDE.

## Usage

Upon launching the app you will land on the dashboard.  Use the bottom navigation bar to switch between screens:

| Screen      | Functionality                                                                     |
|-------------|------------------------------------------------------------------------------------|
| **Dashboard** | Overview and welcome message.                                                     |
| **Exercises** | View the exercise library; tap the **+** button to add new exercises.             |
| **Workouts**  | Log workouts; tap the **+** button to create a new workout session.              |
| **Progress**  | Visualise training volume over time; data aggregates sets (reps × weight).        |
| **Nutrition** | Input body weight and select phase to calculate daily macros.                    |
| **Settings**  | View app version and learn about upcoming pro features.                          |

## Notes

* Die AR‑Coach‑Funktion zeigt derzeit eine Kameraplatzhalter-Ansicht; du kannst hier ARCore/Sceneform einbinden, um einen virtuellen Trainer über die Kamera einzublenden.  Die Codebasis ist bereits vorbereitet, um ein `ARFragment` zu integrieren.
* IronDNA™, umfangreiche KI‑Analyse und Wearable‑Anbindung (z. B. Garmin, Polar, Apple Watch) lassen sich als separate Module ergänzen.  Der aktuelle Code liefert die Grundgerüste für Erweiterungen.
* Die lokale Datenbank wird beim ersten Start mit einigen Standardübungen gefüllt; über die UI können weitere Übungen hinzugefügt werden.
* Für Netzwerkoperationen (Retrofit, OkHttp) ist noch kein API‑Endpunkt konfiguriert.  Du kannst den Repository‑Layer erweitern, um Daten von eigenen Servern abzurufen.

## License

This project is provided for demonstration and educational purposes.  Feel free to customise and extend it for your own use.