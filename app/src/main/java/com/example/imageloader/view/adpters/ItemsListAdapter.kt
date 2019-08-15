package com.example.imageloader.view.adpters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imageloader.models.Item
import com.example.imageloader.R
import com.example.imageloader.inflate
import com.example.imageloader.loadImage
import kotlinx.android.synthetic.main.card_item_view.view.*
import javax.inject.Inject

class ItemsListAdapter @Inject constructor() : RecyclerView.Adapter<ItemsListAdapter.ItemsViewHolder>() {

    private val itemsList :ArrayList<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(parent.inflate(R.layout.card_item_view))
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item)
    }

    fun setItemsList(items:List<Item>){
        this.itemsList.clear()
        this.itemsList.addAll(items)
        notifyItemRangeChanged(0, this.itemsList.size)
    }

    inner class ItemsViewHolder(view: View):RecyclerView.ViewHolder(view){

        fun bind(item: Item){
            with(itemView){
                nameTextView.text = item.user.username
                profileImageView.loadImage(item.user.profile_image.small,R.drawable.person_placeholder)
                pinterestImageView.loadImage(item.urls.regular, R.drawable.placeholder)
            }
        }
    }
}