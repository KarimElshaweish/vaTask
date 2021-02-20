package com.karim.vatask.Adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.karim.vatask.R

class Adapter(var mCtx: Context, var resource:Int, var items:List<String>) :
    ArrayAdapter<String>(mCtx,resource,items) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
            val view : View = layoutInflater.inflate(resource , null )
            val operationText: TextView =view.findViewById(R.id.opText)
            operationText.text=items[position]
            return view
        }
}