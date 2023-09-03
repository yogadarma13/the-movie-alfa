# The Movie Alfa
The Movie Alfa is simple application to display the most popular movie data

## Output Application
![vid30 (1)](https://github.com/yogadarma13/the-movie-alfa/assets/37299231/b5f7d74b-ffed-4e4b-9345-6629f8bdb629)


## App Development Proccess

### Tools and resource
- Android studio Giraffe 2022.3.1
- Java version 17
- [The Movie Database](https://www.themoviedb.org/documentation/api/)

### Specification
- MVVM Design Pattern
- Kotlin Flow and Coroutines
- Paging 3
- Online-Offline Storage Management
- Unit test and Integration test

## App Running Proccess

### Instructions for running the app
- Run the application in Android Studio with the minimum java version is version 17
- Make sure you have a stable internet connection

### Instructions for running Unit Test
- In this application there are several unit test in each module
- In the main-list and movie-detail modules there is a unit test to test the ViewModel
- In the core module there are unit tests to test core functions such as UseCase, Repository, LocalDataSource, and RemoteDataSource.

:warning: If you want to run unit test of class **RemoteDataSource**, you need to **comment** ```EspressoIdlingResource``` first.
```
// EspressoIdlingResource.increment()
```
```
// EspressoIdlingResource.decrement()
```

### Instructions for running Instrumentation Test
- You can run the instrumentation test on app module

:warning: If you want to run instrumentation test, you need to **uncomment** ```EspressoIdlingResource``` on class **RemoteDataSource** first.
```
EspressoIdlingResource.increment()
```
```
EspressoIdlingResource.decrement()
```

