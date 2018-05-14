package com.loic.pomplard.gnr

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loic.pomplard.gnr.models.Gnr
import android.view.LayoutInflater
import com.loic.pomplard.R
import kotlinx.android.synthetic.main.item_gnr.view.*


class GnrAdapter(val gnrList: List<Gnr>) : RecyclerView.Adapter<GnrViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GnrViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gnr, parent, false)
        return GnrViewHolder(view)
    }

    override fun getItemCount(): Int {
       return gnrList.size
    }

    override fun onBindViewHolder(holder: GnrViewHolder, position: Int) {
        val gnr : Gnr = gnrList.get(position)
        holder.bind(gnr)
    }

}

class GnrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(gnrObject: Gnr) {
        itemView.gnr_title.setText(gnrObject.title)
    }
}
