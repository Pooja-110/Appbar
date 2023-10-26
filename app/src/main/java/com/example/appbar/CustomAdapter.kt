package com.example.appbar

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat


class CustomAdapter(val context: Context, val modelClassArrayList: ArrayList<ModelClass>) : BaseAdapter() {
    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getCount(): Int {
        return modelClassArrayList.size
    }

    override fun getItem(position: Int): Any {
        return modelClassArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val holder: ViewHolder
        var rowView = convertView

        if (rowView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = inflater.inflate(R.layout.formate, parent, false)
            holder = ViewHolder()
            holder.lvname = rowView.findViewById(R.id.name) as TextView
            holder.lvnumber = rowView.findViewById(R.id.number) as TextView
            rowView.tag = holder
        } else {
            holder = rowView.tag as ViewHolder
        }

        val model = modelClassArrayList[position]
        holder.lvname!!.text = model.getNames()
        holder.lvnumber!!.text = model.getNumber()

        holder.lvnumber!!.setOnClickListener {
            try {
                val number = model.getNumber()
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))

                if (ActivityCompat.checkSelfPermission(
                        context as Activity,
                        Manifest.permission.CALL_PHONE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    context.startActivity(intent)
                } else {
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        123
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Failed: " + e.message, Toast.LENGTH_SHORT).show()
            }
        }

        return rowView
    }

    inner class ViewHolder {
        var lvname: TextView? = null
        var lvnumber: TextView? = null
    }
}
