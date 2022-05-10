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
# Implement 
 If your app targets API level 26 or higher, you cannot use the manifest to declare a receiver for implicit broadcasts (broadcasts that do not target your app specifically), except for a few implicit broadcasts that are exempted from that restriction. In most cases, you can use scheduled jobs instead.

 
## Manifest-declared receivers
1. Specify the <receiver> element in your app's manifest.
```Xml
<receiver android:name=".MyBroadcastReceiver"  android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED"/>
        <action android:name="android.intent.action.INPUT_METHOD_CHANGED" />
    </intent-filter>
</receiver>
```
2. Subclass BroadcastReceiver and implement onReceive(Context, Intent). The broadcast receiver in the following example logs and displays the contents of the broadcast:

```Kotlin
private const val TAG = "MyBroadcastReceiver"

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        StringBuilder().apply {
            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
            toString().also { log ->
                Log.d(TAG, log)
                Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            }
        }
    }
}
```

## Context-registered receivers
1. Create an instance of BroadcastReceiver.

```Kotlin

val br: BroadcastReceiver = MyBroadcastReceiver()
```

2. Create an IntentFilter and register the receiver by calling registerReceiver(BroadcastReceiver, IntentFilter):

```Kotlin
val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
    addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
}
registerReceiver(br, filter)
```