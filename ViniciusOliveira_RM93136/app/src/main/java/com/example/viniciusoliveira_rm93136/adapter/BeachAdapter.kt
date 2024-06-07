package com.example.viniciusoliveira_rm93136.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viniciusoliveira_rm93136.R
import com.example.viniciusoliveira_rm93136.models.Beach

class BeachAdapter(
   private val beachList: MutableList<Beach>
) : RecyclerView.Adapter<BeachAdapter.BeachViewHolder>() {


   interface OnBeachClickListener {
      fun onExcludeClick(beach: Beach)
   }

   private var beachClickListener: OnBeachClickListener? = null

   fun setOnBeachClickListener(listener: OnBeachClickListener) {
      this.beachClickListener = listener
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeachViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.beach_item, parent, false)
      return BeachViewHolder(view)
   }

   override fun onBindViewHolder(holder: BeachViewHolder, position: Int) {
      val currentBeach = beachList[position]
      holder.beachInfo.text = "${currentBeach.name}, ${currentBeach.city}, ${currentBeach.estate}"
      holder.excludeBtn.setOnClickListener {
         beachClickListener?.onExcludeClick(currentBeach)
      }
   }

   fun removeBeach(beach: Beach) {
      beachList.indexOf(beach).takeIf { it != -1 }?.let { position ->
         beachList.removeAt(position)
         notifyItemRemoved(position)
      }
   }

   fun addBeach(beach: Beach) {
      beachList.run {
         add(beach)
         notifyItemInserted(size - 1)
      }
   }

   override fun getItemCount() = beachList.size



   class BeachViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val beachInfo: TextView = itemView.findViewById(R.id.beachInfo)
      val excludeBtn: Button = itemView.findViewById(R.id.excludeBtn)
   }



}