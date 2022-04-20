# Background task

## Why
- Doing work on the main thread can lead to poor performance and therefore a poor user experience.

## When
- In general, you should take any blocking tasks off the UI thread. Common long-running tasks include things like decoding a bitmap, accessing storage, working on a machine learning (ML) model, or performing network requests.

## [An app is running in the background vs An app is running in the foreground](https://www.plantuml.com/plantuml/uml/hP0nRiCm34NdwXH_Ixpq0EsINg1TUW55PcAY3cMGb8tSleG3TUkMHH10lV-V-99EvbcztUQKR6Cl4uuVopAaaermn6SLGht09q9UbeCX5-ULdIckIYXDbSJdErOsFiy4h_lhPgIlGmBU8Wziq2R2SWNBFZ_dydFLscHyJoHZB_n-50KW1_zZijnngKeF04QwSY6BeEpOVq0ZjeddWAKh1gBrrjMnPNkIurVCT6EZ0V-pNRUpwHHdxFaN)

![An app is running in the background when both the following conditions are satisfied:None of the app's activities are currently visible to the user. The app isn't running any foreground services that started while an activity from the app was visible to the user. Otherwise, the app is running in the foreground.](./016_background_tasks/bgvsfg.png)

## Common types of background work

!['016_background_tasks'](./016_background_tasks/background.svg)


## Approaches to background work
!['Immediate'](./016_background_tasks/Immediate.png)
!['Long-running'](./016_background_tasks/Long-running.png)
!['Deferrable'](./016_background_tasks/Deferrable.png)
<!-- |Category	|Persistent	|Impersistent|
|----------------|---------|------------|
|Immediate|	WorkManager	|Coroutines|
Long running	|WorkManager	|Not recommended. Instead, perform work persistently using WorkManager.
Deferrable	|WorkManager|	Not recommended. Instead, perform work persistently using WorkManager. -->

## [Alarms](https://www.plantuml.com/plantuml/uml/SyfFKj2rKt3CoKnELR1Io4ZDoSa70000)
![when-use-alarms](./016_background_tasks/when-use-alarms.png)

- Alarms are a special use case that are not a part of background work. 
- AlarmManager wakes the device from Doze mode and its use can therefore have a negative impact on battery life and overall system health.


## Replacing foreground services

![Replacing foreground services](./016_background_tasks/fgrsv.png)

Android 12 restricts launching foreground services from the background. For most cases, you should use setForeground() from WorkManager rather than handle foreground services yourself. This allows WorkManager to manage the lifecycle of the foregound service, ensuring efficiency.

You still should use foreground services to perform tasks that are long running and need to notify the user that they are ongoing. If you use foreground services directly, ensure you shut down the service correctly to preserve resource efficiency.

Some use cases for using foreground services directly are as follows:

- Media playback
- Activity tracking
- Location sharing
- Voice or video calls

# [Background Thread](./016_background_tasks/background_threads.md)
