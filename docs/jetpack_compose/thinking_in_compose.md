# Thinking in Compose 

## The declarative programming paradigm
- What <br> 
Jetpack Compose is a modern declarative UI Toolkit for Android. Compose is a declarative UI framework.
- Why  <br> 
 Historically, an Android view hierarchy has been representable as a tree of UI widgets. The most common way of updating the UI is to walk the tree. Manipulating views manually increases the likelihood of errors.
- How  <br> 
A declarative UI model works by conceptually regenerating the entire screen from scratch, then applying only the necessary changes. Compose intelligently chooses which parts of the UI need to be redrawn at any given time.

## A simple composable function
- What: A composable function that take in data and emit UI elements.

- How
``` Kotlin
@Composable
fun Greeting(name: String) {
    Text("Hello $name)
}
```

_Note_

    - All Composable functions must have @Composable annotation, this annotation informs the Compose compiler that this function is intended to convert data into UI.
    - The function doesn't return anything. Compose functions that emit UI do not need to return anything, because they describe the desired screen state instead of constructing UI widgets.

## The declarative paradigm shift
- What <br>
    XML: initialize the UI by inflating an XML layout file <br>
    Compose: initialize the UI by instantiating a tree of widgets

_Note_ 

     Widgets are relatively stateless and do not expose setter or getter functions. In fact, widgets are not exposed as objects. You update the UI by calling the same composable function with different arguments. 

- How (recomposition) <br> 
Data (App logic) -> Describe UI (Composable functions) -> Events (User interacts) -> Data change (App Logic) -> UI Redrawn (Compose function called)

## Dynamic content
- What <br>
Composable functions are written in Kotlin instead of XML, they can be as dynamic as any other Kotlin code.
``` Kotlin
@Composable
fun Greeting(names: List<String>) {
    for (name in names) {
        Text("Hello $name")
    }
}
```
## Recomposition
- What <br>
In Compose, you call the composable function again with new data, the widgets emitted by the function are redrawn <br>
The Compose framework can intelligently recompose only the components that changed. 

_Note_

    Never depend on side-effects from executing composable functions

    Composable functions can execute in any order.

    Composable functions can execute in parallel.

    Recomposition skips as many composable functions and lambdas as possible.

    Recomposition is optimistic and may be canceled.

    A composable function might be run quite frequently, as often as every frame of an animation.

