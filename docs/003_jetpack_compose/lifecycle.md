# Lifecycle
- A Composition is a tree-structure of the composables that describe your UI.

## Initial composition and Recomposition
- How
    - When Jetpack Compose runs your composables for the first time, during initial composition, it will keep track of the composables that you call to describe your UI in a Composition. 
    - Then, when the state of your app changes, Jetpack Compose schedules a recomposition. 
        - Recomposition is when Jetpack Compose re-executes the composables that may have changed in response to state changes, and then updates the Composition to reflect any changes.
    - Recomposition is typically triggered by a change to a State<T> object. Compose tracks these and runs all composables in the Composition that read that particular State<T>, and any composables that they call that cannot be skipped.
