package com.aotter.trek.android.kotlin.demo.trek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aotter.net.trek.ads.TrekMediaView
import com.aotter.trek.android.kotlin.demo.trek.supr_ad.OnSuprAdViewRegisteredListener
import com.aotter.trek.demo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class SuprAdAdapter() : RecyclerView.Adapter<SuprAdAdapter.ViewHolder>() {

    private var list = mutableListOf<LocalSuprAdData>()

    private var onSuprAdViewRegisteredListener: OnSuprAdViewRegisteredListener? = null

    fun setOnSuprAdViewRegisteredListener(onSuprAdViewRegisteredListener: OnSuprAdViewRegisteredListener) {

        this.onSuprAdViewRegisteredListener = onSuprAdViewRegisteredListener

    }

    fun update(list: MutableList<LocalSuprAdData>) {

        this.list = list

        notifyDataSetChanged()

    }

    override fun getItemViewType(position: Int): Int {

        return list[position].trekAd?.let {
            0
        } ?: kotlin.run {
            1
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return when (viewType) {
            0 -> {

                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_supr_ad, parent, false)

                ViewHolder(view)

            }
            1 -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_local_ad, parent, false)
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_local_ad, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(list[position])

    }

    override fun getItemCount(): Int {
        return list.count()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val adTitle = itemView.findViewById<TextView>(R.id.adTitle)

        private val adImg = itemView.findViewById<ImageView>(R.id.adImg)

        private val trekMediaView3 = itemView.findViewById<TrekMediaView>(R.id.trekMediaView3)

        fun bind(item: LocalSuprAdData) {

            item.trekAd?.let { trek ->
                item.adData?.let { adData ->
                    trek.registerSuprAd(itemView.context, trekMediaView3, adData)
                }
            } ?: kotlin.run {

                adTitle.text = item.title

                Glide.with(itemView.context)
                    .load(item.img)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(adImg)

            }

        }

    }

}