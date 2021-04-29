package com.jozamo.casopractico2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class OperarFragment : Fragment() {

    private var operation: String? = ""
    private val args: OperarFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*//Only assign if argument is not null
        arguments?.let { bundle ->
        operation = bundle.getString(OPERATION)
        }*/
        operation = args.operation

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
            findNavController().navigate(R.id.action_operarFragment_to_menuFragment2)
            /*val mensaje = "El resultado de la $op es ${result.text}"
            setFragmentResult("requestKey", bundleOf("bundleKey" to mensaje))

            requireActivity().supportFragmentManager.commit {
                replace(R.id.fragment_container_view, MenuFragment())
                //addToBackStack("operar")
            }*/
        }

        btnCalculate.setOnClickListener {
            if (value1.text.isEmpty() || value2.text.isEmpty()) {
                context?.toast("Introduce valores en los campos")

            } else {
                val v1: Double = value1.text.toString().toDouble()
                val v2: Double = value2.text.toString().toDouble()

                if ( v2 == 0.0 && operation == "/") {
                    context?.toast("División por cero no permitida")
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

    /*companion object {
        private const val OPERATION = "operation"

        fun newInstance(operation: String) =
                OperarFragment().apply {
                    arguments = bundleOf(OPERATION to operation)
                }
    }*/

}
