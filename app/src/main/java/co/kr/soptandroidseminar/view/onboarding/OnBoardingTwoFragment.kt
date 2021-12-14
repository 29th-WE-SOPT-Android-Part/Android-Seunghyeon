package co.kr.soptandroidseminar.view.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.databinding.FragmentOnBoardingTwoBinding

class OnBoardingTwoFragment : Fragment() {
    private var _binding: FragmentOnBoardingTwoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingTwoBinding.inflate(layoutInflater, container, false)
        skipOnBoarding()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun skipOnBoarding() {
        binding.btnOnboardingTwo.setOnClickListener {
            findNavController().navigate(R.id.action_frg_onboarding_two_to_frg_onboarding_three)
        }
    }
}