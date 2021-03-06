package com.example.whattocook.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.R
import com.example.whattocook.currentUser
import com.example.whattocook.presenters.FragmentPresenter

private val presenter = FragmentPresenter()
private lateinit var recycler: RecyclerView

class FragmentMyAppliances:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_additions,container,false)
        recycler = view.findViewById(R.id.recyclerFragmentAdditions)
        presenter.getAppliances(recycler, activity!!, currentUser.userId)
        return view
    }
}