package co.kr.soptandroidseminar.camera

import android.Manifest
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
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.databinding.FragmentCameraBinding
import com.bumptech.glide.Glide

class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private val activityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK && it.data != null) {
                val imageUri = it.data?.data
                try {
                    Glide.with(this)
                        .load(imageUri)
                        .into(binding.imgCamera)
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "어라라?", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            } else if(it.resultCode == RESULT_CANCELED) {
                Toast.makeText(requireContext(), "사진 선택 취소", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "진짜 왜 와이 ?", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBinding.inflate(layoutInflater, container, false)

        btnClickEvent()

        return binding.root
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

    companion object {
        const val PERM_STORAGE = 9
        const val REQ_GALLERY = 12
    }
}