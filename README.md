# PM Auth
## _Social networks authorization with few lines of code_

PM Auth is an authorization service available for Android, iOS and WEB.

- Multi-platrofrm
- Reliable
- Intuitive
- ✨Magical ✨

## Features

- Authorization with multiple social networks
- Verification of required fieds
- Informative user feedback

As Bogdan Khrysanfov says

> It's like uLogin, but better

## Tech

Dillinger uses a number of open source projects to work properly:

- [Retrofit] - Networking done right
- [Dagger2] - Powerful DI library
- [Chrome Custom Tab] - Best of WebView and browser combined
- [Kotlin Coroutines] - YES
- [Glide] - Easy and reliable way to work with images


## Installation

PM Auth requires Android SDK 21+ to work.

1. Download library source from this page.

2. Import library module to your project

3. Go to app level build.gradle file and add following dependency

```groovy
implementation project (path: ':module_name')
```

## Quick Start

Add deep link intent filter to your activity

```xml
    <intent-filter>

        <action android:name="android.intent.action.VIEW" />

        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />

        <data
            android:host="your_host"
            android:scheme="your_scheme" />
            
    </intent-filter>
```

Set activity launch mode to "singleTop"

```xml
    <activity
        android:name=".YourActivity"
        android:launchMode="singleTop">
```

Initialize library client

```kotlin
    val requiredFields = listOf(
        ProfileContract.ID,
        ProfileContract.FIRST_NAME,
        ProfileContract.LAST_NAME
    )
    
    val options = PmLogin.PmOption(APP_ID, REDIRECT_URL, requiredFieds)
    
    val client = PmLogin.PmClient(options)
```

Now you can start login with single line of code

```kotlin
    client.startLogin(activity) 
```

To get the result, observe loginResult property

```kotlin
    client.loginResult.observe(this) { result ->
        val msg = when (result) {
            is LoginResult.Success -> "Your name is ${result[ProfileContract.FIRST_NAME}"
            is LoginResult.Error -> "Something went wrong"
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
```

We also provide stylized button, simply add it like a usual button

```xml
    <com.example.pmLoginAndroid.ui.PMAuthButton
    android:id="@+id/btn_pmlogin"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```
 __Congratulations, you're all set__



   [Retrofit]: <https://square.github.io/retrofit/>
   [Dagger2]: <https://dagger.dev/>
   [Chrome Custom Tab]: <https://developer.chrome.com/docs/android/custom-tabs/overview/>
   [Kotlin Coroutines]: <https://kotlinlang.org/docs/coroutines-overview.html>
   [Glide]: <https://github.com/bumptech/glide>

   
