package co.kr.soptandroidseminar.view.main.camera

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import co.kr.soptandroidseminar.databinding.FragmentCameraBinding
import com.bumptech.glide.Glide

class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBinding.inflate(layoutInflater, container, false)
        btnClickEvent()
        return binding.root
    }

    private val activityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK && it.data != null) {
                val imageUri = it.data?.data
                runCatching {
                    Glide.with(this)
                        .load(imageUri)
                        .into(binding.imgCamera)
                }.onFailure {
                    makeToast("사진 첨부 실패")
                }
            } else if(it.resultCode == RESULT_CANCELED) {
                makeToast("사진 선택 취소")
            }
        }

    private fun btnClickEvent() {
        binding.btnCamera.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        activityLauncher.launch(intent)
    }

    private fun makeToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}