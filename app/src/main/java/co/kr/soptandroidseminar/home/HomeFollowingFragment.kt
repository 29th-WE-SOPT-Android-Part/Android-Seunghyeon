package co.kr.soptandroidseminar.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.databinding.FragmentHomeFollowingBinding

class HomeFollowingFragment : Fragment() {
    private var _binding: FragmentHomeFollowingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeFollowingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}