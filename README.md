# 📱 High School Tutoring Booking App

A centralized Android application that helps high school students find, book, and manage tutoring sessions easily from one place.

---

## ❗ Problem Statement

High school students face multiple challenges when booking tutoring sessions:

- Visiting many tutoring centers to find available slots
- No unified platform for all teachers and centers
- Wasted time and effort
- Payment issues and booking confirmation problems

---

## 💡 Solution Overview

A single mobile application that allows students to:

- Browse teachers
- View available schedules
- Book lessons
- Pay online
- Receive lesson reminders
- Track all registered subjects and sessions

---

## 🧩 App Features

### 📋 Teacher List Screen
- Display teachers using **LazyColumn**
- Search and filter by subject or availability
- Material Design UI with icons and images

### 📄 Teacher Detail Screen
- View teacher details (subjects, schedule, center)
- Navigate using **NavHost**
- Book a lesson directly from the detail screen

### 📝 Booking Form
- Add / edit booking information
- Input validation for required fields
- Save data locally using Room

### 🔔 Notifications
- Receive reminders before each lesson
- Background scheduling using WorkManager

---

## 🗄️ Local Database (Room + SQLite)

Stores teachers, bookings, and schedules locally with full CRUD support.

- Persistent data across app restarts
- Entity relationships between teachers and bookings

---

## 🌐 Network Integration


---

## 🧠 State Management

- ViewModel per screen
- StateFlow / LiveData for UI state
- Proper handling of configuration changes

---

## 🧪 Testing

- Unit Tests for ViewModels
- UI Test for navigation and booking flow

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVVM
- **Navigation:** NavHost
- **Local Database:** Room (SQLite)
- **Networking:** Retrofit
- **Background Tasks:** WorkManager
- **Testing:** JUnit, Espresso

---

## 👥 Team Members

- **Malak SObhy** – Android Developer
- **Roaa Ahmed** – Android Developer
- **Lekaa Fouad** – Android Developer
- **Rahma Mostafa** – Android Developer

---

## 🚀 Installation

```bash
git clone <repository-url>
