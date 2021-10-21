package co.kr.soptandroidseminar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.soptandroidseminar.databinding.ItemFollowerBinding

class FollowerAdapter(val itemClick: (FollowerData) -> Unit) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    val itemList = mutableListOf<FollowerData>()

    class FollowerViewHolder(
        private val binding: ItemFollowerBinding,
        val itemClick: (FollowerData) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            binding.imgFollowerProfile.setImageResource(data.image)
            binding.tvFollowerName.text = data.name
            binding.tvFollowerInfo.text = data.info

            binding.root.setOnClickListener {
                itemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return FollowerViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}