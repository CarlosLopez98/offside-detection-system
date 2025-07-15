# 📦 Offside Detection System

A JavaFX-based application designed to assist in determining offside positions in football (soccer) by analyzing a single frame of a play. The system allows manual input of field lines and uses perspective geometry to estimate vanishing points, project player positions, and help visualize possible offside situations.

This tool is intended for use in sports analysis environments, research, or educational purposes where frame-by-frame visual assistance is required to evaluate offside scenarios.

## Table of contents

- [Demo](#-demo)
- [Features](#-features)
- [Installation](#-installation)
- [Project structure](#-project-structure)
- [Technologies](#-technologies)
- [Contributions](#-contributions)
- [Author](#-author)

---

## 🎬 Demo

_Si tienes una demo o preview del proyecto, agrega un link aquí o una imagen/gif:_

![demo gif](./assets/demo/offside_demo.gif)

---

## ✨ Features

- 📷 Load a static image (frame) of a football match play
- 🧭 Manually draw at least two horizontal and two vertical field lines
- 📐 Automatically compute two vanishing points using perspective geometry
- ⚽ Select attacker and defender reference points on the field
- 📏 Project a baseline using the horizontal vanishing point
- 🟥 Visual projection of the attacker's and defender's most advanced body parts
- 🧠 Helps visually determine potential offside situations
- 🎨 Intuitive JavaFX GUI for drawing and analysis



---

## 💻 Installation

### Prerequisites

- Java JDK 17 or later
- JavaFX SDK (if not using a build tool like Maven or Gradle)
- An IDE such as IntelliJ IDEA or Eclipse

### Option 1: Running from IDE

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/offside-detector.git
   cd offside-detector
   ```
2. Open the project in your preferred Java IDE
3. Make sure JavaFX libraries are properly configured in your project:
   - If using intellij IDEA: Add the JavaFX SDK in Project Structure.
   - If using Gradle/Maven: JavaFX dependencies will be handled automatically.
4. Run the main class.

### Option 2: Compile and Run from Terminal (manual setup)

1. Download and extract [JavaFX SDK](https://openjfx.io).
2. Compile:
   ```bash
   javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -d out src/*.java
   ```
3. Run:
   ```bash
   javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp out Main
   ```

> Replace `/path/to/javafx-sdk/lib` with the actual path to your JavaFX SDK installation.

---

## 🧱 Project structure

```bash
.
├── src/
│   ├── main/
│       ├── java/
│           ├── org.example/
│               ├── controller/
│               ├── mapper/
│               ├── model/
│               ├── tools/
│               ├── utils/
│               ├── view/
│               ├── Main.java
│       ├── resources/
│           ├── images/
│           ├── styles/
│               ├── button.css
│   ├── test/
│       ├── java/
│           ├── org.example/
│               ├── model/
│               ├── utils/
├── .gitignore
├── pom.xml
└── README.md
```

---

## 🛠 Technologies

This project uses the following technologies:

- **Java 17+** – Core language used to build the application
- **JavaFX** – UI toolkit for building rich client applications
- **Maven or Gradle** (optional) – Build tools to manage dependencies and project lifecycle
- **Scene Builder** (optional) – Visual tool for designing JavaFX layouts (FXML)
- **JUnit** – Unit testing framework for Java
- **Git & GitHub** – Version control and collaboration

---

## 🤝 Contributions

Contributions are welcome! If you want to improve this project, please follow these steps:

1. Fork the repository
2. Create a new branch:
   ```bash
   git checkout -b my-feature
   ```
3. Make your changes and commit them:
   ```bash
   git commit -m "feat: Add some feature"
   ```
4. Push to the branch:
   ```bash
   git push origin my-feature
   ```
5. Open pull request.

Make sure your code follows the existing style and includes necessary tests.

---

## 👤 Author

**Carlos Lopez**
- GitHub: [@carloslopez98](https://github.com/carloslopez98)
- Email: carloslopez18.c@gmail.com
- LinkedIn: [linkedin.com/in/carloslopez98](https://linkedin.com/in/carloslopez98)
