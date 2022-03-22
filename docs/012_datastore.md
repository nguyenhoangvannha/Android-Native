# DataStore

What
- DataStore uses to store key-value pairs or typed objects with protocol buffers.

When to use
- small, simple datasets and does not support partial updates or referential integrity.

- DataStore uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactionally.

How

- Preferences DataStore:
    - Key-value data store.
    - Does not provide type safety.

- Proto DataStore:
    - Typed objects using protocol buffers. 
    - Support type safety.