# Media-Hub Frontend - Quick Start

## 🚀 Projekt starten

### 1. ZIP-Datei entpacken
```bash
unzip media-hub.zip
cd media-hub
```

### 2. Abhängigkeiten installieren
```bash
npm install
```

### 3. Development Server starten
```bash
npm run dev
```

Die App läuft dann auf: **http://localhost:5173**

---

## 📁 Das Projekt erkunden

### **Komponenten** (`src/components/`)
- `RegisterForm.jsx` - Registrierungs-Formular mit Validierung
- `ThemeToggle.jsx` - Dark Mode Toggle Button

### **Services** (`src/services/`)
- `authService.js` - Business Logic Layer
  - `registerUser()` - Registriert einen Benutzer (dummy)
  - `validateEmail()` - Validiert Email-Adressen
  
### **Styles** (`src/styles/`)
- `globals.css` - CSS Variablen & Dark Mode
- `RegisterForm.css` - Formular Styling
- `ThemeToggle.css` - Toggle Button Styling

---

## 🎨 Darkmode testen

1. Klick auf den Toggle Button (oben rechts: 🌙/☀️)
2. Die App wechselt sofort zum Dark Mode
3. Die Einstellung wird in localStorage gespeichert

---

## ✏️ Übungen für Lernende

### Level 1: Styling
- [ ] Ändere die Akzentfarbe in `src/styles/globals.css`
- [ ] Passe den Abstand (padding/margin) des Formulars an
- [ ] Erstelle eine neue CSS Animation

### Level 2: Service Layer
- [ ] Implementiere `loginUser()` in `authService.js`
- [ ] Speichere registrierte User in localStorage
- [ ] Füge eine `getStoredUsers()` Funktion hinzu

### Level 3: Komponenten
- [ ] Erstelle eine `LoginForm.jsx` Komponente
- [ ] Baue eine `UserCard.jsx` zum Anzeigen von Profilen
- [ ] Verbinde die Komponenten mit der RegisterForm

### Level 4: State Management
- [ ] Nutze Context API für globalen User-State
- [ ] Speichere den eingeloggten User
- [ ] Erstelle eine geschützte "Dashboard" Seite

---

## 🔧 Wichtige npm Commands

```bash
npm run dev      # Development Server mit Hot Reload
npm run build    # Production Build erstellen
npm run preview  # Production Build Preview
```

---

## 💡 Tipps

- **Hot Reload**: Speichern = automatisches Neuladen der App
- **Browser DevTools**: Öffne F12 für Console und Netzwerk-Debugging
- **CSS Variablen**: Alle Farben sind in `globals.css` definiert - einfach ändern!
- **Darkmode**: Öffne DevTools → F12 → Console und gib ein:
  ```javascript
  localStorage.setItem('theme', 'dark');
  location.reload();
  ```

Viel Spaß beim Lernen! 🎉
