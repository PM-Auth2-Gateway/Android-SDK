# PM Login Android Sdk

## PMAuth Login for Android – Quickstart

The PMAuth library for Android allows people to sign into your app with various social networks. When people log into your app with social networks they can grant permissions to your app so you can retrieve information from users profile.
For an example project that illustrates how to integrate Facebook Login into an Android app, see the PMAuthSampleLogin on GitHub. 
https://github.com/PM-Auth2-Gateway/Android-SDK

Follow the steps below to add PMAuth Login to your app.

## 1. Integrate the PMAuth library

1. In your project, open Files  >  New  >  Import Module  >  Select source directory with dowloaded PMAuth library  >  Check “Import” and set the Module Name for library  >  Finish.
2. In your project, open  your_app > Gradle Scripts > build.gradle (Module: app)  and add the following implementation statement to the dependencies{} section

```gradle
implementation project(path: ':module-name')
```

3. Build your  project.



## 2. Edit Your Manifest
1. Open the /app/manifest/AndroidManifest.xml file.
2. Add the following intent-filter element for Chrome Custom Tabs inside your application element:

```xml
<intent-filter>
    <action android:name="android.intent.action.VIEW" />
    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />
    <data
        android:host="test"
        android:scheme="pmlogintest" />
</intent-filter>
```

3. Set launchMode for your login activity:
android:launchMode="singleTop"


## 3. Setup PMOptions
Now create PmOptions instance in your Activity or Fragment to configure library.

```kotlin
val options = PmLogin.PmOptions(
    appId = "1", redirectUrl = "pmlogintest://test", requiredFields = listOf(
        ProfileContract.ID,
        ProfileContract.EMAIL,
    )
)
```

RequiredFields  you can request are listed in ProfileContract


## 4. Initiate PmClient
Initiate PmClient in your Activity or Fragment.

```kotlin
val client = PmLogin.PmClient(options)
```

## 5. Register a Callback
Now observe on a client.loginResult to handle login responses.

```kotlin
client.loginResult.observe(this) { result ->
    Toast.makeText(this, "I've got a result ${ result }", Toast.LENGTH_SHORT).show()
}
```


## 6. Add the Login Button
The simplest way to add PMAuth login to your app is to add PMAuthButton. When someone clicks on the button, the login is initiated.
To add the Login button, first add it to your layout XML file:

```xml
<com.example.pmLoginAndroid.ui.PMAuthButton
    android:id="@+id/btn_pmlogin"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```


## 7. Register a Listener
Set a click listener to open a Pop-Up with login through social networks. It is recommended to use onSingleClickListener , provided by library.

```kotlin
binding.btnPmlogin.onSingleClickListener {
    client.startLogin(this)
}
```

### Congrats, you've added PMAuth Login to your Android app!
