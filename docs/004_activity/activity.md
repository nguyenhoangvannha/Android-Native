# Activity

- What
    - Activity serves as the entry point for an app's interaction with user.
    - An app can have multi activity
- How
    - An activity provides the window in which the app draws its UI
- Setup
    1. Create a subclass of Activity
    2. Define that activity to Manifest file
    3. Add intent-filter if you want this is first activity when open app
    4. Add intent-filter if you want other app can start this activity
    5. Add activity permission if you require other app request permission to start this activity