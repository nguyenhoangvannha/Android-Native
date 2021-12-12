# Handling Lifecycles with Lifecycle-Aware Components
- What
    - Lifecycle-aware components perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
- Why
    - These components help you produce better-organized, and often lighter-weight code, that is easier to maintain.
- How
    - Create an observer class that implement LifecycleObserver/DefaultLifecycleObserver
    - Register observer inside a class that implement LifecycleOwner
        - myLifecycleOwner.getLifecycle().addObserver(MyObserver())
