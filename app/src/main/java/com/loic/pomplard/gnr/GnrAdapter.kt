package com.loic.pomplard.gnr

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loic.pomplard.gnr.models.Gnr
import android.view.LayoutInflater
import com.loic.pomplard.R
import com.loic.pomplard.constants.FilesConstants
import com.loic.pomplard.utils.DataUtils
import kotlinx.android.synthetic.main.item_gnr.view.*


class GnrAdapter(val gnrList: List<Gnr>, val listener: GnrListener) : RecyclerView.Adapter<GnrViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GnrViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gnr, parent, false)
        return GnrViewHolder(view)
    }

    override fun getItemCount(): Int {
       return gnrList.size
    }

    override fun onBindViewHolder(holder: GnrViewHolder, position: Int) {
        val gnr : Gnr = gnrList.get(position)

        gnr.isInLocal = DataUtils.getLocalFile(gnr.srcPdf, FilesConstants.PDF_EXTENSION).exists()

        holder.bind(gnr)
        holder.itemView.gnr_download.setOnClickListener({
            listener.onItemClickListener(gnr)
        })

        holder.itemView.gnr_see.setOnClickListener({
            listener.onItemClickListener(gnr) //Calling same method if download||see
        })
    }

}

class GnrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(gnrObject: Gnr) {
        if(gnrObject.isInLocal){
            itemView.gnr_download.visibility = View.GONE
            itemView.gnr_see.visibility = View.VISIBLE
        }else {
            itemView.gnr_download.visibility = View.VISIBLE
            itemView.gnr_see.visibility = View.GONE
        }
        itemView.gnr_title.setText(gnrObject.title)
        itemView.gnr_image.setImageDrawable(gnrObject.image)
    }
}
