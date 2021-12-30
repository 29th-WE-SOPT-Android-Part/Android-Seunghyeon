package co.kr.soptandroidseminar.view.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.databinding.FragmentOnBoardingOneBinding

class OnBoardingOneFragment : Fragment() {
    private var _binding: FragmentOnBoardingOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingOneBinding.inflate(layoutInflater, container, false)
        skipOnBoarding()
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun skipOnBoarding() {
        binding.btnOnboardingOne.setOnClickListener {
            findNavController().navigate(R.id.action_frg_onboarding_one_to_frg_onboarding_two)
        }
    }
}