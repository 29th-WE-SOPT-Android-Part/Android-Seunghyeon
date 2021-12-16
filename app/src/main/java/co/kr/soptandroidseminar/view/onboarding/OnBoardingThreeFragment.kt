package co.kr.soptandroidseminar.view.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.databinding.FragmentOnBoardingThreeBinding
import co.kr.soptandroidseminar.view.signin.SignInActivity

class OnBoardingThreeFragment : Fragment() {
    private var _binding: FragmentOnBoardingThreeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingThreeBinding.inflate(layoutInflater, container, false)
        skipOnBoarding()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun skipOnBoarding() {
        binding.btnOnboardingThree.setOnClickListener {
            findNavController().navigate(R.id.action_frg_onboarding_three_to_activity_signin)
            requireActivity().finish()
        }
    }
}