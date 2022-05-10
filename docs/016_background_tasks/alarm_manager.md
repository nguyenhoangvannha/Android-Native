# Alarms

Alarms (based on the AlarmManager class) give you a way to perform time-based operations outside the lifetime of your application. For example, you could use an alarm to initiate a long-running operation, such as starting a service once a day to download a weather forecast.

Alarms have these characteristics:

They let you fire Intents at set times and/or intervals.
You can use them in conjunction with broadcast receivers to start services and perform other operations.
They operate outside of your application, so you can use them to trigger events or actions even when your app is not running, and even if the device itself is asleep.
They help you to minimize your app's resource requirements. You can schedule operations without relying on timers or continuously running background services.