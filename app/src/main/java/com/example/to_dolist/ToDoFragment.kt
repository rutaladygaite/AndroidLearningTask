package com.example.to_dolist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import timber.log.Timber

open class ToDoFragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main, container, false)
        val btn: Button = view.findViewById(R.id.add_another_button)
        btn.setOnClickListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("Message: Fragment")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.add_another_button -> {
                val intent = Intent(getActivity(), AddAnotherActivity::class.java)
                getActivity()?.startActivity(intent)
                Timber.d("Message: Button clicked")
            }
        }
    }

    companion object {
        fun newInstance() = ToDoFragment()
    }
}
