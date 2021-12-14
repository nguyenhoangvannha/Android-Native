package com.nhvn.todoandroidnative

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.navigation.compose.rememberNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.nhvn.todoandroidnative.ui.MyApp
import com.nhvn.todoandroidnative.ui.navigation.AppNavHost
import com.nhvn.todoandroidnative.ui.observers.MyActivityLifecycleObserver
import com.nhvn.todoandroidnative.ui.screens.homescreen.ComposablesExampleFragment
import com.nhvn.todoandroidnative.ui.screens.homescreen.XmlExampleFragment

const val EXTRA_MESSAGE = "com.nhvn.todoandroidnative.MESSAGE"
const val USERNOTE_STATE_KEY = "com.nhvn.todoandroidnative.USERNOTE_STATE_KEY"

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        lifecycle.addObserver(MyActivityLifecycleObserver(lifecycle))

        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(this)

        viewPager.adapter = pagerAdapter

        //In the previous example, note that the fragment transaction is only created when savedInstanceState is null. This is to ensure that the fragment is added only once, when the activity is first created. When a configuration change occurs and the activity is recreated, savedInstanceState is no longer null, and the fragment does not need to be added a second time, as the fragment is automatically restored from the savedInstanceState.
//        if (savedInstanceState == null) {
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<ComposablesExampleFragment>(R.id.fragmentContainerView)
//                addToBackStack("ComposablesExampleFragment")
//            }
//
//        }

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.i("TABID", "${tab!!.id}");
                when (tab.position) {
                    (0) -> {
//                        Log.i("TABID", "R.id.tabItem");
//                        supportFragmentManager.commit {
//                            replace<ComposablesExampleFragment>(R.id.fragmentContainerView)
//                            setReorderingAllowed(true)
//                            addToBackStack("ComposablesExampleFragment") // name can be null
//                        }
                        viewPager.currentItem = 0
                    }
                    (1) -> {
                        Log.i("TABID", "R.id.tabItem2");
//                        supportFragmentManager.commit {
//                            setReorderingAllowed(true)
//                            replace<XmlExampleFragment>(
//                                R.id.fragmentContainerView,
//                                args = bundleOf("some_int" to 0)
//                            )
//                            addToBackStack("XmlExampleFragment")
//                        }
                        viewPager.currentItem = 1
                    }
                }

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

        })
    }

    override fun onStart() {


        super.onStart()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

//    // After onStart
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        userNote = savedInstanceState.getString(USERNOTE_STATE_KEY)
//        if (userNote != null)
//            Log.i("onRestoreInstanceState", "$USERNOTE_STATE_KEY$userNote")
//        super.onRestoreInstanceState(savedInstanceState)
//    }

    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val message = editText.text.toString();
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            when (position) {
                (1) -> return XmlExampleFragment()
            }
            return ComposablesExampleFragment()
        }
    }
}

private const val NUM_PAGES = 2