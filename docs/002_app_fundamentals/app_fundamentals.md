# Index
- [Application Fundamentals](#application-fundamentals)
    - [App components](#app-components)
        - [Activities](#activities)
        - [Services](#services)
        - [Broadcast receivers](#broadcast-receivers)
        - [Content provider](#content-providers)
    - [Manifest file](#manifest-file)
    - [Declaring components](#declaring-components)
    - [Declaring component capabilities](#declaring-component-capabilities)
    - [Declaring app requirements](#declaring-app-requirements)
    - [App resources](#app-resources)

# Application Fundamentals

- Android apps can be written using Kotlin, Java, and C++ languages.

- An Android package
    - Is an archive file with an .apk suffix, contains the contents of an Android app that are required at runtime .
    - It is the file that Android-powered devices use to install the app.

- Android App Bundle
    - Is an archive file with an .aab suffix, contains the contents of an Android app project including some additional metadata that is not required at runtime.
    - An AAB is a publishing format and is not installable on Android devices, it defers APK generation and signing to a later stage.

- Each Android app lives in its own security sandbox, protected by the following Android security features:

    - The Android operating system is a multi-user Linux system in which each app is a different user.
    - By default, the system assigns each app a unique Linux user ID (the ID is used only by the system and is unknown to the app). The system sets permissions for all the files in an app so that only the user ID assigned to that app can access them.
    - Each process has its own virtual machine (VM), so an app's code runs in isolation from other apps.
    - By default, every app runs in its own Linux process. The Android system starts the process when any of the app's components need to be executed, and then shuts down the process when it's no longer needed or when the system must recover memory for other apps.

- The Android system implements the principle of least privilege.
    - By default, each app has access only to the components that it requires to do its work and no more.
    - For an app to share data with other apps and for an app to access system services:
        - Arrange for two apps to share the same Linux user ID and signed with the same certificate. They can access each others files and can run in the same Linux process and share the same VM.
        - An app can request permission to access device data such as the device's location, camera, and Bluetooth connection. The user has to explicitly grant these permissions.

- The rest of this document introduces the following concepts:

    - The core framework [components](#app-components) that define your app.
    - The [manifest file](#manifest-file) in which you declare the components and the required device features for your app.
    - [Resources](#resources) that are separate from the app code and that allow your app to gracefully optimize its behavior for a variety of device configurations.

## App components

- What
    - App components are the essential building blocks of an Android app. 
- There are four different types of app components:
    - [Activities](#activities)
    - [Services](#services)
    - [Broadcast receivers](#broadcast-receivers)
    - [Content providers](#content-providers)
- How
    - Each component is an entry point through which the system or a user can enter your app. 
    - Some components depend on others. ??
    - Each type serves a distinct purpose and has a distinct lifecycle that defines how the component is created and destroyed.
    - A unique aspect of the Android system design is that any app can start another app’s component.
        - For example, if you want the user to capture a photo with the device camera, there's probably another app that does that and your app can use it instead of developing an activity to capture a photo yourself. You don't need to incorporate or even link to the code from the camera app. Instead, you can simply start the activity in the camera app that captures a photo. When complete, the photo is even returned to your app so you can use it. To the user, it seems as if the camera is actually a part of your app.

    - When the system starts a component, it starts the process for that app if it's not already running and instantiates the classes needed for the component.
        - For example, if your app starts the activity in the camera app that captures a photo, that activity runs in the process that belongs to the camera app, not in your app's process. 
    - Therefore, unlike apps on most other systems, Android apps don't have a single entry point (there's no main() function).

    - Because the system runs each app in a separate process with file permissions that restrict access to other apps, your app cannot directly activate a component from another app. However, the Android system can. To activate a component in another app, deliver a message to the system that specifies your intent to start a particular component. The system then activates the component for you.

- Activating components
    - What
        -Three of the four component types—activities, services, and broadcast receivers—are activated by an asynchronous message called an intent.
    - How
        - Intents bind individual components to each other at runtime. You can think of them as the messengers that request an action from other components, whether the component belongs to your app or another.

        - An intent is created with an Intent object, which defines a message to activate either a specific component (explicit intent) or a specific type of component (implicit intent).

        - For activities and services
            - An intent defines the action to perform (for example, to view or send something) and may specify the URI of the data to act on, among other things that the component being started might need to know.

        - For broadcast receivers
            - The intent simply defines the announcement being broadcast. 
                - For example, a broadcast to indicate the device battery is low includes only a known action string that indicates battery is low.

        - Content providers are not activated by intents. 
            - They are activated when targeted by a request from a ContentResolver. The content resolver handles all direct transactions with the content provider so that the component that's performing transactions with the provider doesn't need to and instead calls methods on the ContentResolver object. This leaves a layer of abstraction between the content provider and the component requesting information (for security).

### Activities

- What
    - An activity is the entry point for interacting with the user. It represents a single screen with a user interface. 
- How
    - Activities work together to form a cohesive user experience if needed(e.g Activity A start activity B). 
    - An activity facilitates the following key interactions between system and app:
        - Keeping track of what the user currently cares about (what is on screen) to ensure that the system keeps running the process that is hosting the activity.
        - Knowing that previously used processes contain things the user may return to (stopped activities), and thus more highly prioritize keeping those processes around.
        - Helping the app handle having its process killed so the user can return to activities with their previous state restored.
        - Providing a way for apps to implement user flows between each other, and for the system to coordinate these flows. (The most classic example here being share.)

### Services

- What
    - A service is an entry point for keeping an app running in the background for all kinds of reasons. 
    - It is a component that runs in the background to perform long-running operations or to perform work for remote processes.
    - A service does not provide a user interface. 
    - For example, a service might play music in the background while the user is in a different app, or it might fetch data over the network without blocking user interaction with an activity.
    - Another component, such as an activity, can start the service and let it run or bind to it in order to interact with it.
- How
    - There are two types of services that tell the system how to manage an app: 
        - [Started services](#started-services)
        - [Bound services](#bound-services)

    - #### Started services
        - What
            - Started services tell the system to keep them running until their work is completed. 
        - How <br>
            - Two different types of started services that modify how the system handles them:
                - Foreground service
                    - Example: Music playback
                    - User is directly aware of with a notification to tell the user about it
                    - The system knows that it should try really hard to keep that service's process running, because the user will be unhappy if it goes away.
                - Background service
                    - The user is not directly aware of this service running 
                    - The system has more freedom in managing its process( E.g kill)
    - #### Bound services
        - Why
            - Bound services run because some other app (or the system) has said that it wants to make use of the service. 
        - What
            - This is basically the service providing an API to another process. 
        - How
            - The system thus knows there is a dependency between these processes, so if process A is bound to a service in process B, it knows that it needs to keep process B (and its service) running for A. Further, if process A is something the user cares about, then it also knows to treat process B as something the user also cares about.

### Broadcast receivers
- What
    - Broadcast receivers are another entry into the app
    - A broadcast receiver is a component that enables the system to deliver events to the app outside of a regular user flow, allowing the app to respond to system-wide broadcast announcements.
    - Although broadcast receivers don't display a user interface, they may create a status bar notification to alert the user when a broadcast event occurs.
- How 
    - The system can deliver broadcasts even to apps that aren't currently running. 
    - Apps can also initiate broadcasts.
    
- Example
    - Broadcast announcing that
        - The screen has turned off
        - The battery is low
        — App let other apps know that some data has been downloaded to the device and is available for them to use. 
        - More commonly, though, a broadcast receiver is just a gateway to other components and is intended to do a very minimal amount of work. For instance, it might schedule a JobService to perform some work based on the event with JobScheduler

### Content providers
- What
    - A content provider manages a shared set of app data that you can store in the file system, in a SQLite database, on the web, or on any other persistent storage location that your app can access. 
    - Content providers are also useful for reading and writing data that is private to your app and not shared.
    
- Why
    - Through the content provider, other apps can query or modify the data if the content provider allows it. 

- Example
    - The Android system provides a content provider that manages the user's contact information. 
    
- How
    - As such, any app with the proper permissions can query the content provider.
    - To the system, a content provider is an entry point into an app for publishing named data items, identified by a URI scheme. 
    - There are a few particular things this allows the system to do in managing an app:
        - Assigning a URI doesn't require that the app remain running, so URIs can persist after their owning apps have exited. 
        - The system only needs to make sure that an owning app is still running when it has to retrieve the app's data from the corresponding URI.
        - These URIs also provide an important fine-grained security model.
            - For example, an app can place the URI for an image it has on the clipboard, but leave its content provider locked up so that other apps cannot freely access it. When a second app attempts to access that URI on the clipboard, the system can allow that app to access the data via a temporary URI permission grant so that it is allowed to access the data only behind that URI, but nothing else in the second app.
    - A content provider is implemented as a subclass of ContentProvider and must implement a standard set of APIs that enable other apps to perform transactions.

## Manifest file
- What 
    - Before the Android system can start an app component, the system must know that the component exists by reading the app's manifest file, AndroidManifest.xml.
        - Your app must declare all its components in this file, which must be at the root of the app project directory.
- How
    - The manifest does a number of things in addition to declaring the app's components, such as the following:

        - Identifies any user permissions the app requires, such as Internet access or read-access to the user's contacts.
        - Declares the minimum API Level required by the app, based on which APIs the app uses.
        - Declares hardware and software features used or required by the app, such as a camera, bluetooth services, or a multitouch screen.
        - Declares API libraries the app needs to be linked against (other than the Android framework APIs), such as the Google Maps library.
### Declaring components
- What
    - The primary task of the manifest is to inform the system about the app's components. 
    - Example, a manifest file can declare an activity as follows:
        ```XML
        <?xml version="1.0" encoding="utf-8"?>
        <manifest ... >
            <application android:icon="@drawable/app_icon.png" ... >
                <activity android:name="com.example.project.ExampleActivity"
                        android:label="@string/example_label" ... >
                </activity>
                ...
            </application>
        </manifest>
        ```
- How
    - [Activities](#activities), [services](#services), and [content providers](#content-providers) that you include in your source but do not declare in the manifest are not visible to the system and, consequently, can never run.
    - [Broadcast receivers](#broadcast-receivers) can be either declared in the manifest or created dynamically in code as BroadcastReceiver objects and registered with the system by calling registerReceiver().

### Declaring component capabilities
- What
    - We can use an Intent to start activities, services, and broadcast receivers. The system identifies the components that can respond to an intent by comparing the intent received to the intent filters provided in the manifest file of other apps on the device.
- How
    - When you declare an activity in your app's manifest, you can optionally include intent filters that declare the capabilities of the activity so it can respond to intents from other apps. You can declare an intent filter for your component by adding an < intent-filter> element as a child of the component's declaration element.
        - For example, if you build an email app with an activity for composing a new email, you can declare an intent filter to respond to "send" intents (in order to send a new email), as shown in the following example, If another app creates an intent with the ACTION_SEND action and passes it to startActivity(), the system may start your activity so the user can draft and send an email.
            ```xml
            <manifest ... >
                ...
                <application ... >
                    <activity android:name="com.example.project.ComposeEmailActivity">
                        <intent-filter>
                            <action android:name="android.intent.action.SEND" />
                            <data android:type="*/*" />
                            <category android:name="android.intent.category.DEFAULT" />
                        </intent-filter>
                    </activity>
                </application>
            </manifest>       
            ```
### Declaring app requirements
- Why
    - There are a variety of devices powered by Android and not all of them provide the same features and capabilities.
    - To prevent your app from being installed on devices that lack features needed by your app, it's important that you clearly define a profile for the types of devices your app supports by declaring device and software requirements in your manifest file.
    - Most of these declarations are informational only and the system does not read them, but external services such as Google Play do read them in order to provide filtering for users when they search for apps from their device.
- How
    - Example
        - Declare the camera feature directly in your app's manifest file:
            ```xml
            <manifest ... >
                <uses-feature android:name="android.hardware.camera.any"
                            android:required="true" />
                ...
            </manifest>
            ```
        - The values for minSdkVersion and targetSdkVersion are set in your app module's build.gradle file:
            ```gradle
            android {
            ...
            defaultConfig {
                ...
                minSdkVersion 26
                targetSdkVersion 29
            }
            }           
            ```

For example, if your app requires a camera and uses APIs introduced in Android 8.0 (API Level 26), you must declare these requirements.
## App resources
- What
    - An Android app is composed of more than just code—it requires resources that are separate from the source code, such as images, audio files, and anything relating to the visual presentation of the app.
- Why
    - Using app resources makes it easy to update various characteristics of your app without modifying code.
    - Providing sets of alternative resources enables you to optimize your app for a variety of device configurations, such as different languages and screen sizes.
- Example, you can define animations, menus, styles, colors, and the layout of activity user interfaces with XML files. 
- How
    - For every resource that you include in your Android project, the SDK build tools define a unique integer ID, which you can use to reference the resource from your app code or from other resources defined in XML. 
