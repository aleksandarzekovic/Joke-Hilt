# JokeHilt

In the previous [Joke](https://github.com/aleksandarzek/Joke) app, we created an app that uses Dagger2 / Dagger-Android for dependency injection. 
In this project we migrate a Dagger2 / Dagger-Android to a Hilt.

Note: The application class in this project is called JokeHiltApplication and not JokeApplication, don't let that confuse you. 
In this project I will not explain how Hilt works, but I will focus only on migrating Dagger2 / Dagger-Android to Hilt.
<br />
<br />

<div align="center">
<img src="https://img.shields.io/badge/madeby-aleksandarzek-green"/>
<img src="https://img.shields.io/badge/SDK-21+-blue"/>
</div>
<br />

## Application layouts
![Joke Logo](/images/jokeapp.png)

## Step by Step
1) In build.gradle (Module: app) comment and add:
```//Dagger 2.x
def dagger_version = '2.29.1'
implementation "com.google.dagger:dagger:$dagger_version"
//implementation "com.google.dagger:dagger-android:$dagger_version"
//implementation "com.google.dagger:dagger-android-support:$dagger_version"
kapt "com.google.dagger:dagger-compiler:$dagger_version"
//kapt "com.google.dagger:dagger-android-processor:$dagger_version"

def hilt_version = '2.28-alpha'
implementation "com.google.dagger:hilt-android:$hilt_version"
kapt "com.google.dagger:hilt-android-compiler:$hilt_version"

def hilt_version_view = '1.0.0-alpha02'
implementation "androidx.hilt:hilt-lifecycle-viewmodel:$hilt_version_view"
kapt "androidx.hilt:hilt-compiler:$hilt_version_view"
```
and add plugin:

```
apply plugin: 'dagger.hilt.android.plugin'
```

<hr />

2) in build.gradle (Project: Joke-Hilt) add:
```
buildscript {
    ...
    dependencies {
        ...
        def hilt_version = "2.28-alpha"
        classpath "com.google.dagger:hilt-android-gradle-plugin:$hilt_version"
    }
}
```

<hr />  

3) **Application** - This code in JokeHiltApplication:
```kotlin
class JokeHiltApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}
```
replace with:
```kotlin
@HiltAndroidApp
class JokeHiltApplication : Application()
```

![#f03c15](https://via.placeholder.com/15/f03c15/000000?text=+) `@HiltAndroidApp annotation triggers Hilts code generation including a base class for your application that can use dependency injection. You can see how @HiltAndroidApp works at:` [@HiltAndroidApp](https://dagger.dev/hilt/quick-start.html)`


<hr />


4) **Application Context** - We will now show how the application context is passed using Hilt. We no longer need to use modules to binds an application context instance. Replace this code:
```kotlin
@Singleton
class NetManager @Inject constructor(private var applicationContext: Context) {
    ...
}
```

with:

```kotlin
@Singleton
class NetManager @Inject constructor(@ApplicationContext private var applicationContext: Context) {
    ...
}
```

![#f03c15](https://via.placeholder.com/15/f03c15/000000?text=+) `Hilt provides some predefined qualifiers. For example, as you might need the Context class from either the application or the activity, Hilt provides the @ApplicationContext and @ActivityContext qualifiers. You can see how @ApplicationContext and @ActivityContext works at:` [@ApplicationContext](https://developer.android.com/training/dependency-injection/hilt-android)

<hr />

5) **Activity/Fragment** - In the activity MainActivity and fragments of CategoryJokeFragment and JokeFragment, the following code:
```kotlin
class MainActivity : AppCompatActivity() {
    ...
}
```

```kotlin
class CategoryJokeFragment : DaggerFragment() {
    ...
}
```

```kotlin
class CategoryJokeFragment : DaggerFragment() {
    ...
}
```

replace with:

```kotlin
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    ...
}
```

```kotlin
@AndroidEntryPoint
class CategoryJokeFragment : Fragment() {
    ...
}
```
```kotlin
@AndroidEntryPoint
class JokeFragment : Fragment() {
    ...
}
```

![#f03c15](https://via.placeholder.com/15/f03c15/000000?text=+) `By attaching the AndroidEntryPoint annotation, the fields in the MainActivity, CategoryJokeFragment and JokeFragment can be injected instances by Hilt. We don’t need to write modules for activities/fragmets anymore. Once you have enabled members injection in your Application, you can start enabling members injection in your other Android classes using the @AndroidEntryPoint annotation. You can use @AndroidEntryPoint on the following types: Activity, Fragment, View, Service, BroadcastReceiver. You can see more detailes here:` [@AndroidEntryPoint](https://dagger.dev/hilt/android-entry-point.html)

<hr />

6) **ViewModel** - When we inject a parameter into a constructor in a viewmodel, we had to do it with help DaggerAwareViewModelFactory. Now it's much easier and reduces a lot of boilerplate code, ie we don't have to use DaggerAwareViewModelFactory anymore. Now we do it as follows
```kotlin
class JokeViewModel @ViewModelInject constructor(private var jokeRepository: JokeRepository, @Assisted private val savedStateHandle: SavedStateHandle) : ViewModel() {
    ...
}
```
, in the Joke fragment, delete this part of the code:
```kotlin
@Inject
lateinit var jokeViewModelFactory: DaggerAwareViewModelFactory
```

and replace:

```kotlin
viewModel = ViewModelProvider(this, jokeViewModelFactory).get(JokeViewModel::class.java)
```

with:

```kotlin
viewModel = ViewModelProvider(this).get(JokeViewModel::class.java)
```

![#f03c15](https://via.placeholder.com/15/f03c15/000000?text=+) `You can see how @ViewModelInject works at:` [@ViewModelInject](https://developer.android.com/training/dependency-injection/hilt-jetpack)

<hr />


7) We will now see how much we have reduced the code. Let's delete all the following files because we don't need them anymore.
```
me.aleksandarzekovic.joke_hilt.di -> file: AppComponent
me.aleksandarzekovic.joke_hilt.di.modules -> files: AppModule, CategoryJokeModule, JokeModule
me.aleksandarzekovic.joke.utils -> all folder: daggerawareviewmodel (folder and files inside)
```
![#f03c15](https://via.placeholder.com/15/f03c15/000000?text=+) `On such a small application, we removed as much files/code. Now you can imagine how much code we remove if the application is large. Long live Hilt!`

## Tech stack & Libraries 
- [MVVM](https://developer.android.com/jetpack/guide?gclid=CjwKCAjwq_D7BRADEiwAVMDdHvfBvr-S0K0zYp7kDOAvDxQoJRe6O0NMZ4gBoekFQeqOJ9ER_ilkPhoCXd4QAvD_BwE&gclsrc=aw.ds) Architecture
- [Retrofit2](https://square.github.io/retrofit/)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-jetpack)
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)

## Api
Use [JokeAPI - V2](https://sv443.net/jokeapi/v2/) api.

