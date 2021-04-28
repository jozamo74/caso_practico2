package com.jozamo.casopractico2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.commit

class OperarFragment : Fragment() {

    private var operation: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        operation = requireArguments().getString(OPERATION)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_operar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val operation = operation

        val title = view.findViewById<TextView>(R.id.tv_title)

        val value1 = view.findViewById<EditText>(R.id.value1)
        val value2 = view.findViewById<EditText>(R.id.value2)
        val result = view.findViewById<TextView>(R.id.tv_result)

        val exitOk = view.findViewById<Button>(R.id.btn_exit)
        val new = view.findViewById<Button>(R.id.btn_new)
        val btnCalculate = view.findViewById<Button>(R.id.btn_result)

        value1.requestFocus()


        val op = when(operation){
            "+" -> "suma"
            "-" -> "resta"
            "*" -> "multiplicación"
            "/" -> "División"
            else -> "Operacion desconocida"
        }
        "Introduce valores para la  $op".also { title.text = it }

        exitOk.setOnClickListener {
            activity?.finish()
        }
        new.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                replace(R.id.fragment_container_view, MenuFragment())
                //addToBackStack("operar")
            }
        }

        btnCalculate.setOnClickListener {
            if (value1.text.isEmpty() || value2.text.isEmpty()) {
                Toast.makeText(context,"Introduce valores en los campos", Toast.LENGTH_SHORT).show()

            } else {
                val v1: Double = value1.text.toString().toDouble()
                val v2: Double = value2.text.toString().toDouble()

                if ( v2 == 0.0 && operation == "/") {
                    Toast.makeText(context,"No se puede dividir por cero", Toast.LENGTH_SHORT).show()
                } else {
                    val operationValues: Double = when(operation){
                        "+" -> { v1 + v2 }
                        "-" -> { v1 - v2 }
                        "*" -> { v1 * v2 }
                        "/" -> { v1 / v2 }
                        else -> {0.0}
                    }
                    result.text = operationValues.roundToDecimalPlace().toString()
                    exitOk.isVisible = true
                    new.isVisible = true

                }
            }
        }
    }

    companion object {
        private const val OPERATION = "operation"

        fun newInstance(operation: String) =
                OperarFragment().apply {
                    arguments = bundleOf(OPERATION to operation)
                }
    }

}
