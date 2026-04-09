# 🚗 AutoCare — Car Service Booking System

> Spring Boot • MongoDB • Thymeleaf

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green) ![MongoDB](https://img.shields.io/badge/MongoDB-7.0-brightgreen) ![Maven](https://img.shields.io/badge/Maven-3.9.x-red)

---

## 📋 Project Overview

AutoCare is a full-stack car service booking web application. Users can submit their name, car model, and desired service type through a clean, dark-themed UI. All bookings are persisted in a MongoDB database and displayed on the same page in real time.

| Property | Value |
|---|---|
| Framework | Spring Boot 3.2.0 |
| Database | MongoDB (localhost:27017) |
| Language | Java 17 |
| Port | http://localhost:8080 |
| Template Engine | Thymeleaf |
| Build Tool | Apache Maven |

---

## 📁 Project Structure
```
carservice-final/
├── pom.xml
└── src/main/
    ├── java/com/example/
    │   ├── CarServiceApplication.java
    │   ├── model/Booking.java
    │   ├── repository/BookingRepository.java
    │   ├── service/BookingService.java
    │   └── controller/BookingController.java
    └── resources/
        ├── application.properties
        └── templates/index.html
```

---

## ⚙️ Prerequisites

| Tool | Version |
|---|---|
| Java JDK | 17 or higher — adoptium.net |
| Apache Maven | 3.9.x or higher |
| MongoDB | 7.0 Community Edition |
| Internet | Required on first run (Maven downloads deps) |

---

## 🚀 Installation & Setup

### Step 1 — Install Java 17

1. Go to https://adoptium.net and download Java 17 LTS (`.msi` for Windows)
2. Run the installer → Next → Next → Finish
3. Verify in Command Prompt:
```
java -version
```

### Step 2 — Install Maven

1. Download from https://maven.apache.org/download.cgi (`apache-maven-3.9.x-bin.zip`)
2. Extract to `C:\Program Files\Maven`
3. Add `C:\Program Files\Maven\bin` to your System PATH environment variable
4. Verify:
```
mvn -version
```

### Step 3 — Install MongoDB

1. Go to https://www.mongodb.com/try/download/community
2. Select: **Version 7.0** • **Platform: Windows** • **Package: msi**
3. Run the installer → choose **Complete** setup
4. ✅ Check **"Install MongoD as a Service"** on the Service Configuration screen
5. Click Install → Finish

### Step 4 — Verify MongoDB is Running

1. Press `Win + R`, type `services.msc`, press Enter
2. Find **MongoDB** in the list — Status must say **Running**

Or start manually (run Command Prompt as Administrator):
```
net start MongoDB
```

### Step 5 — Extract the Project

Download and extract `carservice-final.zip` to any folder, e.g.:
```
C:\Projects\carservice-final
```

### Step 6 — Run the Application

Open Command Prompt and navigate to the project folder:
```
cd C:\Projects\carservice-final
```
Then run:
```
mvn spring-boot:run
```

Maven will download all dependencies on first run (~2 minutes).
When you see this, the app is live:
```
Started CarServiceApplication in 3.4 seconds
```

Open your browser and go to:
```
http://localhost:8080
```

---

## 🗄️ MongoDB Configuration

The database connection is in `src/main/resources/application.properties`:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/carservice
server.port=8080
```

MongoDB **automatically creates** the `carservice` database on the first booking — no manual setup needed.

To add authentication:
```properties
spring.data.mongodb.uri=mongodb://username:password@localhost:27017/carservice
```

---

## 🌐 Application Routes

| Method | Route | Description |
|---|---|---|
| GET | `/` | Home page — shows booking form + all bookings |
| POST | `/save` | Saves a new booking to MongoDB and redirects to `/` |

---

## 🆘 Troubleshooting

| Problem | Fix |
|---|---|
| `mongod` not recognized | Restart your PC after MongoDB install |
| MongoDB service not starting | Run Command Prompt as Administrator → `net start MongoDB` |
| Port 8080 already in use | Change `server.port=8081` in `application.properties` |
| `mvn` command not found | Restart PC after adding Maven to PATH |
| Cannot connect to MongoDB | Check `services.msc` — MongoDB must be Running |
| White page / template error | Ensure `templates/index.html` exists and Thymeleaf is in `pom.xml` |

---

## ⚡ Quick Command Reference
```bash
mvn spring-boot:run       # Start the app
Ctrl + C                  # Stop the app
net start MongoDB         # Start MongoDB (Windows, Admin)
net stop MongoDB          # Stop MongoDB (Windows, Admin)
```

Then open → **http://localhost:8080**

---

*AutoCare — Built with Spring Boot & MongoDB*