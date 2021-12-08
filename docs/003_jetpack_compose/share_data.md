# Data sharing between composables
https://developer.android.com/guide/navigation/navigation-pass-data
https://developer.android.com/jetpack/compose/state#managing-state
- What
    - Navigation allows you to attach data to a navigation operation by defining arguments for a destination.
        - For example, a user profile destination might take a user ID argument to determine which user to display.
- How
    - In general, you should strongly prefer passing only the minimal amount of data between destinations. 
        - For example, you should pass a key to retrieve an object rather than passing the object itself
    - The total space for all saved states is limited on Android. 
    - If you need to pass large amounts of data, consider using a ViewModel.