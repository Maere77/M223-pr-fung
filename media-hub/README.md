# Media-Hub Frontend

Ein modernes React-Frontend für den Media-Hub mit Registrierungsformular, Service Layer und Dark Mode.

## Features

✅ **Registrierungs-Formular**
- Name, Vorname, E-Mail Eingabe
- Validierung mit aussagekräftigen Fehlermeldungen
- Success Feedback

✅ **Service Layer**
- `authService.registerUser()` - Dummy Registrierung mit 1s Verzögerung
- `authService.validateEmail()` - Email Validierung
- Einfach erweiterbar für echte API-Calls

✅ **Dark Mode**
- Automatische Detektion der Systemeinstellung
- Toggle Button (☀️/🌙)
- Persistierung in localStorage
- Shadcn-ähnliches Design

✅ **Clean Code**
- Komponenten-basierte Struktur
- CSS-Variablen für einfache Theme-Verwaltung
- Responsive Design
- Vite für schnelle Entwicklung

## Installation

```bash
cd media-hub
npm install
```

## Development

```bash
npm run dev
```

Öffne http://localhost:5173

## Build

```bash
npm run build
```

## Projektstruktur

```
src/
├── components/
│   ├── RegisterForm.jsx      # Registrierungs-Komponente
│   └── ThemeToggle.jsx       # Dark Mode Toggle
├── services/
│   └── authService.js        # Service Layer mit Dummy Funktionen
├── styles/
│   ├── globals.css           # Globale Styles & CSS Variablen
│   ├── RegisterForm.css      # RegisterForm Styles
│   ├── ThemeToggle.css       # ThemeToggle Styles
│   └── App.css               # App Styles
├── App.jsx                   # Hauptkomponente
└── main.jsx                  # Entry Point
```

## Übungs-Ideen für Lernende

1. **Service Layer erweitern**
   - `loginUser()` implementieren
   - `getProfile()` hinzufügen
   - Mock Datenspeicherung mit localStorage

2. **Neue Komponenten erstellen**
   - LoginForm
   - UserProfile
   - Navigation

3. **Validierung verbessern**
   - Stärkere Passwort-Validierung
   - Server-seitige Validierung simulieren

4. **Styling**
   - Neue CSS Variablen hinzufügen
   - Custom Animations erstellen
   - Weitere Themes implementieren

5. **State Management**
   - Context API für User-State
   - LocalStorage Persistierung

## Technologien

- **React 18** - UI Library
- **Vite** - Build Tool
- **CSS3** - Styling (keine Libraries)
- **localStorage** - Persistierung

## Lizenz

Für Unterrichtsgebrauch
