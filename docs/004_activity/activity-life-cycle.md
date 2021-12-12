# The Activity Lifecycle 
- What
    - As a user navigates through, out of, and back to your app, the Activity instances in your app transition through different states in their lifecycle.

- Why
    - We need to know that a state has changed to declare how your activity behaves when the user leaves and re-enters the activity.
- How
    - Use lifecycle callbacks
    - Use a lifecycle aware component for reusable component

## Restrictions on starting activities from the background
- What
    - Android 10 (API level 29) and higher place restrictions on when apps can start activities when the app is running in the background. These restrictions help minimize interruptions for the user and keep the user more in control of what's shown on their screen.

- How
    - Display notifications instead
    - [Exceptions to the restriction](https://developer.android.com/guide/components/activities/background-starts)

## Activity state and ejection from memory
- What
    - The system kills processes when it needs to free up RAM
- How https://developer.android.com/guide/components/activities/activity-lifecycle#asem

## [Handling Lifecycles with Lifecycle-Aware Components](./lifecycle-aware-components.md)