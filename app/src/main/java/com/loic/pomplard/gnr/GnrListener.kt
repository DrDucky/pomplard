package com.loic.pomplard.gnr

import com.loic.pomplard.gnr.models.Gnr

interface GnrListener {
    fun onItemClickListener(gnr:Gnr)
}