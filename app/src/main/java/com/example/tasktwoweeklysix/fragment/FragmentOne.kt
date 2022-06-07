package com.example.tasktwoweeklysix.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tasktwoweeklysix.databinding.FragmentOneBinding
import kotlinx.coroutines.*
import java.math.BigDecimal


class FragmentOne : Fragment() {
    private var resultOne = BigDecimal(3)
    private var resultTwo = BigDecimal(4)
    private var counterOne: Double = 0.0
    private var counterTwo: Double = 0.0
    private var showOne = ""
    private var showTwo = ""
    private var formula: Double = 0.0
    private var countTwo = 0
    private var divider = 1
    private lateinit var binding: FragmentOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        main()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentOne()
    }
    private fun main() {
        GlobalScope.launch(Dispatchers.IO) {
            oneCoroutine()
        }
        GlobalScope.launch(Dispatchers.IO) {
            twoCoroutine()
        }
    }
    private suspend fun oneCoroutine() = coroutineScope {  // this: CoroutineScope
        launch {
            while (true) {
                counterOne += 1
                if (counterOne % 2 == 1.0) {
                    formula = counterOne * 2 * (counterOne * 2 + 1) * (counterOne * 2 + 2)
                    resultOne += (BigDecimal(4).divide(BigDecimal(formula), 300, 0))
                } else {
                    formula = counterOne * 2 * (counterOne * 2 + 1) * (counterOne * 2 + 2)
                    resultOne -= (BigDecimal(4).divide(BigDecimal(formula), 300, 0))
                }
                showOne = resultOne.toString()
                if (counterOne % 1000 == 0.0) {
                    binding.tvOne.text = showOne
                }
            }
        }.join()
    }
    private suspend fun twoCoroutine() = coroutineScope {  // this: CoroutineScope
        launch {
            while (true) {
                counterTwo += 1
                if (countTwo%2 ==0) {
                    resultTwo -= (BigDecimal(4).divide(BigDecimal(divider+2),300,0))
                } else {
                    resultTwo += (BigDecimal(4).divide(BigDecimal(divider+2),300,0))
                }
                showTwo = resultTwo.toString()
                divider += 2
                if (counterTwo % 1000 == 0.0) {
                    binding.tvTwo.text = showTwo
                }
                countTwo++
            }
        }.join()
    }
}