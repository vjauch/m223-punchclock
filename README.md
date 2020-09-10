# M223: Punchclock
Dies ist eine Beispielapplikation für das Modul M223.
Die Applikation kann verwendent werden, um Arbeitszeiten zu erfassen und zu verwalten.
Zeit-Einträge werden auf Activities gebucht, die wiederum einem Projekt untergeordnet sind.

## Backend (Spring Boot)
Folgende Schritte befolgen um mit dem Backend zu arbeiten:
1. Sicherstellen, dass JDK 11 installiert und in der Umgebungsvariable `path` definiert ist.
1. Ins Verzeichnis der Applikation wechseln und über die Kommandozeile mit `./gradlew bootRun` oder `./gradlew.bat bootRun` starten.
1. Unittest mit `./gradlew test` oder `./gradlew.bat test` ausführen.
1. Ein ausführbares JAR kann mit `./gradlew bootJar` oder `./gradlew.bat bootJar` erstellt werden.

Folgende Dienste stehen während der Ausführung im Profil `dev` zur Verfügung:
- REST-Schnittstelle der Applikation: http://localhost:8081
- Dashboard der H2 Datenbank: http://localhost:8081/h2-console

## Frontend (Angular)
1. Ins Verzeichnis `punchclock-web` wechseln.
1. Den Entwicklungsserver mit `ng serve` starten. Die App ist verfügbar unter https://localhost:4200
1. Unit-Tests mit `ng test` starten.
1. E2E-Tests mit `ng e2e` starten.
1. Das Projekt mit `ng build` bauen. Für die Produktion das Flag `--prod` beim Build verwenden. Die Build-Artefakte werden im Ordner `dist/` abgelegt.
