# View Binding

## What
- View binding generates a binding class for each XML layout that can used to interact with that ui
## Why
- Null safety: view binding create direct references to views
- Type safe:  The fields in each binding class have types matching the views they reference in the XML file
- Safe time: no need findViewById

### Pros:
- Faster compilation than data binding because no annotation processing
- No required specially tagged xml layout files

### Cons: 
- No layout variables or expressions
- No two ways data binding

## How
Module build.gradle

``` Grovy
android {
    ...
    buildFeatures {
        viewBinding true
    }
}
```

Ignore view binding in a layout
```XML
<LinearLayout
        ...
        tools:viewBindingIgnore="true" >
    ...
</LinearLayout>
```

Add id to your views
```XML
<LinearLayout ... >
    <TextView android:id="@+id/name" />
    <ImageView android:cropToPadding="true" />
    <Button android:id="@+id/button"
        android:background="@drawable/rounded_button" />
</LinearLayout>
```

Setup view binding in activity(or fragment)
```Kotlin
private lateinit var binding: ResultProfileBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ResultProfileBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
}
```

Use binding class
```Kotlin
binding.name.text = viewModel.name
binding.button.setOnClickListener { viewModel.userClicked() }
```