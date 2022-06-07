package com.example.tasktwoweeklysix.fragment

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tasktwoweeklysix.R
import com.example.tasktwoweeklysix.databinding.FragmentTwoBinding

class FragmentTwo : Fragment() {
    private lateinit var binding: FragmentTwoBinding
    private var count = 0
    private var i = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        binding.chronometer.start()
        binding.btnPlay.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.start()
        }
        binding.btnPause.setOnClickListener {
            binding.chronometer.stop()
        }
        binding.btnReset.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime()
        }
        binding.chronometer.setOnChronometerTickListener {
            val elapsedMillis: Long = (SystemClock.elapsedRealtime() - binding.chronometer.base)
            if (elapsedMillis / i in 20001..20999) {
                i++
                if (count == 0) {
                    count = 1
                    binding.constLayout.setBackgroundResource(R.color.black)
                } else {
                    count = 0
                    binding.constLayout.setBackgroundResource(R.color.white)
                }
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentTwo()
    }
}