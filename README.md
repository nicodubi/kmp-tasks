# KMP Tasks  

A cross-platform task management app built using **Kotlin Multiplatform (KMP)** and **Jetpack Compose**, allowing seamless UI sharing across **Android** and **iOS**.  

## 📝 Overview  

KMP Tasks is a simple yet powerful task management app that enables users to create, complete, and manage their tasks efficiently. The app follows **Material Design 3** guidelines to provide a modern and intuitive user experience.  

## 📌 Features  

- Add, complete, and delete tasks  
- Task detail view  
- Shared UI using **Jetpack Compose**  
- Dependency injection with **Koin**  
- Modularized architecture for scalability  
- **Theming & Dark Mode**  
- **Screen Rotation Support**  

## 🏗️ Tech Stack  

- **Kotlin Multiplatform (KMP)** – Shared business logic across Android and iOS  
- **Jetpack Compose** – Declarative UI  
- **Koin** – Dependency injection  
- **ViewModel** – State management  
- **Room** – Local database  
- **Coroutines + Flow** – Asynchronous programming  
- **Gradle KMP Plugin** – Build system  

## 🏛️ Architecture  

KMP Tasks follows a **clean architecture** approach, ensuring separation of concerns:  

```
composeApp/src/commonMain/kotlin/io/dubiansky/kmptasks
├── core
│   ├── common        # Shared logic (data, domain, presentation)
│   ├── di            # Dependency Injection (Koin)
│   ├── entrypoint    # App entry point
│   └── navigation    # Navigation management
└── feature
    ├── addtask       # Feature: Add Task
    ├── taskdetail    # Feature: Task Detail
    └── tasklist      # Feature: Task List
```

### 🛠 Layers  

- **Data Layer** (`data/`): Handles repositories and database interactions  
- **Domain Layer** (`domain/`): Business logic and use cases  
- **Presentation Layer** (`presentation/`): UI components and state management  

## 📱 Supported Platforms  

- ✅ **Android**  
- ✅ **iOS**  

## 🚀 Getting Started  

### Prerequisites  

- Android Studio **Giraffe** or newer  
- Xcode for iOS builds  
- Kotlin Multiplatform plugin enabled  

### Running on Android  

```sh
./gradlew androidApp:installDebug
```

### Running on iOS  

```sh
./gradlew iosApp:deployIosSimulator
```

## 📌 Future Improvements  

- Cloud sync for tasks  
- Notifications & reminders  
- Geolocation tagging for tasks  
