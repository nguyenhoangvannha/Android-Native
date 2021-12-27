# Intent

- What:
    - Intent is a messaging you can use to request an action from another app component.

- When
    - Start an activity
        ```Kotlin
        const val EXTRA_MESSAGE = "com.nhvn.todoandroidnative.MESSAGE"
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
        ```
    - Start a service
    - Delivering a broadcast
    - ... more
- Intent type
    - Explicit intent
        - What: This intent specify which application will satisfy the intent
        - How: Supplying either the target app's package name or a fully-qualified component class name.
        - When: You'll typically use an explicit intent to start a component in your own app.
    - Implicit intent
        - What: Do not name a specific component, but instead declare a general action to perform, which allows a component from another app to handle it.
        - The OS compare intent to the intent filters in other apps on the device.      
            - If the intent matches an intent filter, the system starts that component and delivers it the Intent object. 
            - If multiple intent filters are compatible, the system displays a dialog so the user can pick which app to use.

## Intent filter
- What
    - An intent filter is an expression in an app's manifest file that specifies the type of intents that the component would like to receive.

## Building an intent
### Component name
- What
    - The name of the component to start.
    - Optional field
        - If include: Explicit intent
        - If not include: Implicit intent
### Action
- What
    - A string that specifies the generic action to perform (such as view or pick).
- How
    - Can use pre-defined actions constant by Intent class or framework class
        - Ex: ACTION_VIEW, ACTION_SEND
    - Define your action
        - Ex: ```const val ACTION_TIMETRAVEL = "com.example.action.TIMETRAVEL"```
### Data
- What
    - The URI (a Uri object) that references the data to be acted on and/or the MIME type of that data. 
    - The type of data supplied is generally dictated by the intent's action.
### Category

- What
    - A string containing additional information about the kind of component that should handle the intent.
    - Optional and no limit
- Ex: CATEGORY_BROWSABLE
- How:
    - You can specify a category with addCategory().
### Extras
- What
    - Key-value pairs additional information that does not affect how it is resolved to an app component. 
- How:
    - putExtra: Key-value
    - putExtras: Bundle
- Example:
    - ```const val EXTRA_GIGAWATTS = "com.example.EXTRA_GIGAWATTS"```
### Flag
- What
    - Flags are defined in the Intent class that function as metadata for the intent. 
- When:
    - The flags may instruct the Android system how to launch an activity (for example, which task the activity should belong to) and how to treat it after it's launched (for example, whether it belongs in the list of recent activities)
- How    
    - setFlags() method.
### Force to show an app chooser
- What
    - When more than one app can handle your intent you can force to show an app chooser
    - Prevent user from choosing a default app to handle your intent
- How
```Kotlin
val chooser: Intent = Intent.createChooser(intent, "Please choose an app(Forced)")
startActivity(chooser)
```
### Detect unsafe intent launches(nested intent)
 https://medium.com/androiddevelopers/android-nesting-intents-e472fafc1933
 
### Intent filter
### Pending intent
- What
    - A container to wrap an intent which will execute later by another app
- Example:
    - An pending intent contain an intent will execute when
        - User click notification
        - User click app widget
        - Execute at a specific time
- Specify mutability(>= Android 12)
    - Specify mutability to control other app modify your intent
    - If PendingIntent.FLAG_IMMUTABLE: other app cannot adjust result by modify intent
    - Set flag: PendingIntent.FLAG_IMMUTABLE, PendingIntent.FLAG_MUTABLE