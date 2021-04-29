package com.jozamo.casopractico2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController


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

        setFragmentResultListener("requestKey") { _, bundle ->
            val result = bundle.getString("bundleKey")
            context.toast("$result")

        }
    }

    private fun navegarOperar(operation: String) {
        val action = MenuFragmentDirections.actionMenuFragmentToOperarFragment(operation)
        findNavController().navigate(action)
    }
}