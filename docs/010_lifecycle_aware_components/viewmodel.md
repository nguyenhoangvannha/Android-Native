# ViewModel

## What
The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations

## ViewModel lifecycle
The ViewModel exists from when you first request a ViewModel (for example onCreate method) until the activity is finished and destroyed.

!['viewmodel-lifecycle'](viewmodel-lifecycle.png)
## AndroidViewModel
Use AndroidViewModel If the ViewModel needs the Application context, for example to find a system service.

## When
- Use ViewModel when store large and complex data not survives system-initiated process death - save to memory
- Use save instance state to save small data because it survives system-initiated process death - serialized to disk