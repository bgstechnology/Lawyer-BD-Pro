# Lawyer BD Pro

A premium and professional Android application designed for lawyers, judges, law students, and legal practitioners in Bangladesh. Built with modern Android development practices, it features a clean and elegant Material Design 3 interface tailored for productivity.

## Features

*   **Digital Law Library**: Access major laws of Bangladesh including the Penal Code, CrPC, CPC, and more. Features offline access and quick search capabilities.
*   **AI Legal Assistant**: Powered by the Gemini API, this smart assistant can explain complex legal provisions, draft documents, and answer legal questions accurately.
*   **Case Management System**: Keep track of your legal cases, hearing dates, client details, and case statuses all in one place. Includes a local database for seamless offline support.
*   **Smart Dashboard**: A centralized hub displaying today's hearings, recent documents, and quick navigation to all essential tools.
*   **Lawyer Profile**: A dedicated space to manage professional details like chamber information and enrollment numbers.

## Tech Stack

*   **Language**: Kotlin
*   **UI Framework**: Jetpack Compose (Material Design 3)
*   **Architecture**: MVVM (Model-View-ViewModel)
*   **Local Storage**: Room Database
*   **Network & API**: Retrofit & Kotlinx Serialization
*   **Navigation**: Navigation Compose
*   **AI Integration**: Google Gemini API

## Setup Instructions

1.  **Clone the repository** and open it in Android Studio.
2.  **Configure the Gemini API Key**:
    *   Rename the `.env.example` file in the root directory to `.env`.
    *   Add your Gemini API key to the `.env` file:
        ```env
        GEMINI_API_KEY=your_actual_api_key_here
        ```
    *   Note: The `.env` file is ignored by Git to keep your credentials secure.
3.  **Build and Run** the application on an emulator or a physical device.

## Design

The application implements a "Clean Minimalism" design theme, prioritizing readability, high contrast, and efficient use of screen space. It utilizes responsive layouts suitable for both smartphones and tablets.
