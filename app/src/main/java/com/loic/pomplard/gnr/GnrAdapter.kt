package com.loic.pomplard.gnr

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loic.pomplard.gnr.models.Gnr
import android.view.LayoutInflater
import android.widget.Filterable
import com.loic.pomplard.R
import com.loic.pomplard.constants.FilesConstants
import com.loic.pomplard.utils.DataUtils
import kotlinx.android.synthetic.main.item_gnr.view.*
import android.widget.Filter


class GnrAdapter(val originalData: List<Gnr>, val listener: GnrListener) : RecyclerView.Adapter<GnrViewHolder>(), Filterable {
    private val mFilter = GnrFilter()
    private var filteredData: List<Gnr>? = null

    init {
        filteredData = originalData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GnrViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gnr, parent, false)
        return GnrViewHolder(view)
    }

    override fun getItemCount(): Int {
       return filteredData!!.size
    }

    override fun onBindViewHolder(holder: GnrViewHolder, position: Int) {
        val gnr : Gnr = filteredData!!.get(position)

        gnr.isInLocal = DataUtils.getLocalFile(gnr.srcPdf, FilesConstants.PDF_EXTENSION).exists()

        holder.bind(gnr)
        holder.itemView.gnr_download.setOnClickListener({
            listener.onItemClickListener(gnr)
        })

        holder.itemView.gnr_open.setOnClickListener({
            listener.onItemClickListener(gnr) //Calling same method if download||see
        })
    }

    override fun getFilter(): Filter {
        return mFilter
    }

    private inner class GnrFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): Filter.FilterResults {

            val filterString = constraint.toString()

            val results = Filter.FilterResults()

            val list = originalData

            val count = list.size
            val nlist = ArrayList<Gnr>(count)

            var filterableGnr: Gnr

            for (i in 0 until count) {
                filterableGnr = list.get(i)
                if (filterString.contains(filterableGnr.category)) {
                    nlist.add(filterableGnr)
                }

                if(filterString.equals(FilesConstants.ALL_CATEGORY)) {
                    nlist.add(filterableGnr)
                }
            }

            results.values = nlist
            results.count = nlist.size

            return results
        }

        override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
            filteredData = results.values as ArrayList<Gnr>
            notifyDataSetChanged()
        }

    }

}

class GnrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(gnrObject: Gnr) {
        if (gnrObject.isInLocal) {
            itemView.gnr_download.visibility = View.GONE
            itemView.gnr_open.visibility = View.VISIBLE
        } else {
            itemView.gnr_download.visibility = View.VISIBLE
            itemView.gnr_open.visibility = View.GONE
        }
        itemView.gnr_description.setText(gnrObject.description)
        itemView.gnr_title.setText(gnrObject.title)
        itemView.gnr_image.setImageDrawable(gnrObject.image)
    }

}