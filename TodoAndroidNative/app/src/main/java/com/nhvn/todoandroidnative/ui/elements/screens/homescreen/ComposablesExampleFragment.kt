package com.nhvn.todoandroidnative.ui.elements.screens.homescreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.nhvn.todoandroidnative.ui.elements.USERNOTE_STATE_KEY
import com.nhvn.todoandroidnative.ui.MyApp

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ComposablesExampleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComposablesExampleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var userNote: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userNote = savedInstanceState?.getString(USERNOTE_STATE_KEY)

        if (userNote != null)
            Log.i("onCreate", "$USERNOTE_STATE_KEY$userNote")

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(context = context!!).apply {
            setContent {
                val (title, setTitle) = remember { mutableStateOf(if (userNote != null) userNote else "") }
                Column() {
                    MyApp(modifier = Modifier.weight(1F))
                    TextField(value = title!!, onValueChange = {
                        setTitle(it)
                        userNote = it
                    })
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(USERNOTE_STATE_KEY, userNote)
        }
        if (userNote != null)
            Log.i("onSaveInstanceState", "$USERNOTE_STATE_KEY$userNote")
        super.onSaveInstanceState(outState)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ComposablesExampleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ComposablesExampleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}