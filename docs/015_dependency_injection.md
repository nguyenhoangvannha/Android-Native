# Dependency Injection

## What
- Dependency injection is a pattern we can use to implement IoC, where the control being inverted is setting an object's dependencies.

## How
- Connecting objects with other objects, or “injecting” objects into other objects, is done by an assembler rather than by the objects themselves.
- Dependency Injection can be done through
    - constructors
    - setters
    - fields
### Constructor-Based Dependency Injection
```Java
class Store {
    private final ItemInterface item;

    Store(ItemInterface item) {
        this.item = item;
    }
}

final store = Store(ItemImpl());
```
### Setter-Based Dependency Injection
```Java
class Store {
    private var ItemInterface item;

    public void setItem(ItemInterface item){
        this.item = item;
    }
}

final store = Store();
store.setItem(ItemImpl());
```
## Field-Based Dependency Injection
- Done through reflection
```Java
class Store {
    @Inject
    private final ItemInterface item;
}

final store = Store();
```

## IoC Container

- An IoC container is a common characteristic of frameworks that implement IoC.

- The IoC container is responsible for instantiating, configuring and assembling objects, as well as managing their life cycles.


https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring#:~:text=Inversion%20of%20Control%20is%20a,context%20of%20object%2Doriented%20programming.