# NewsNest

NewsNest is a modern Android news application that allows users to browse, search, and manage news articles with a personalized experience. The app supports keyword, date, and category-based search, AI-powered news summarization, and features for collection and history management.

## Features

- **Tabbed News Browsing**: Browse news by categories with a smooth tabbed interface.
- **Advanced Search**: Search news by keyword, date range, and category.
- **Personalized Preferences**: Customize visible news categories.
- **Favorites & History**: Collect favorite articles and view reading history.
- **AI Summarization**: Generate concise news summaries using a large language model (GLM-4).
- **Rich Media Support**: Display images and play videos within news details.
- **Offline Caching**: Local SQLite database for history, favorites, and summaries.
- **Modern UI**: Material Design, ViewPager2, TabLayout, and RecyclerView for a responsive experience.

## Tech Stack

- **Language**: Java
- **Framework**: Android SDK
- **UI**: Material Components, ViewPager2, TabLayout, RecyclerView
- **Networking**: OkHttp, Gson
- **Image Loading**: Glide
- **Database**: SQLite (via custom DbHelper classes)
- **AI Integration**: GLM-4 API for news summarization

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/app/luoxueheng/
│   │   │   ├── MainActivity.java
│   │   │   ├── TabNewsFragment.java
│   │   │   ├── SearchActivity.java
│   │   │   ├── SearchResultActivity.java
│   │   │   ├── NewsDetailsActivity.java
│   │   │   ├── HistoryListActivity.java
│   │   │   ├── CollectionListActivity.java
│   │   │   ├── SettingActivity.java
│   │   │   ├── adapter/NewsListAdapter.java
│   │   │   ├── db/ (SQLite helpers)
│   │   │   └── entity/ (POJOs)
│   │   └── res/layout/ (UI layouts)
│   └── test/
├── build.gradle.kts
└── local.properties
```

## How to Build & Run

1. **Clone the repository** and open it in Android Studio or VS Code.
2. Make sure you have Android SDK 24+ installed.
3. Configure your `local.properties` with the correct SDK path.
4. Build the project:
    ```sh
    ./gradlew assembleDebug
    ```
5. Install the APK on your device or emulator:
    ```sh
    adb install app-debug.apk
    ```


## Resume Highlights

- Developed a full-featured Android news app with tabbed browsing, advanced search, and personalized content filtering.
- Implemented local data caching, favorites, and history management using SQLite.
- Integrated AI-powered news summarization for enhanced user experience.
- Utilized modern Android UI components and best practices for robust, maintainable code.

---

**Author:** 罗雪蘅  
**Student ID:** 2023010877
