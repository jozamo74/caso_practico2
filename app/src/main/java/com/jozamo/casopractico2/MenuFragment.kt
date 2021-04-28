package com.jozamo.casopractico2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit


class MenuFragment : Fragment(R.layout.fragment_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAdd = view.findViewById<Button>(R.id.add)
        val btnSub = view.findViewById<Button>(R.id.sub)
        val btnMul = view.findViewById<Button>(R.id.mul)
        val btnDiv = view.findViewById<Button>(R.id.div)

        btnAdd.setOnClickListener {
            navegarOperar("+")
        }

        btnSub.setOnClickListener {
            navegarOperar("-")
        }

        btnMul.setOnClickListener {
            navegarOperar("*")
        }

        btnDiv.setOnClickListener {
            navegarOperar("/")
        }
    }

    private fun navegarOperar(c: String) {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.fragment_container_view, OperarFragment.newInstance(c))
            //addToBackStack("menu")
        }
    }
}