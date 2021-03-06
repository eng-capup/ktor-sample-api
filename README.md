# ktor sample api project

This is a sample project to test if [Ktor](https://ktor.io/) is useful.

## To run

### create database
In this sample, `samplektor` database needs to be created before the run. You can name your DB any kind of. 
When you name the original database, user, and password, you have to change the config database name in `application.conf` and `build.gradle`. Default info is below.

* database name: samplektor
* user name: root
* password: root

### migration
Run below code to create table and sample data.
```aidl
$ ./gradlew flywayMigrate
```

### run
```aidl
$ ./gradlew run
```

You may want to set auto-reload when you change some codes. You can refer to [this article](https://support.samuraism.com/jetbrains/ide/idea-auto-compilation).
- In my Intellij IDEA, there is no `compiler.automake.allow.when.app.running`, so run below code on another console.
```aidl
$ ./gradlew -t build
```


