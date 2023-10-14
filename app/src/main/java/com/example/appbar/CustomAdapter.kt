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
import androidx.core.content.ContextCompat

class CustomAdapter(val context: Context, val modelClassArrayList: ArrayList<ModelClass>):
    BaseAdapter(){
    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun getCount(): Int {
        return modelClassArrayList.size
    }

    override fun getItem(p0: Int): Any {
        return modelClassArrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertView = p1
        val holder: ViewHolder
        if (convertView == null) {
            holder = ViewHolder()
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.formate, null, true)

            holder.lvname = convertView!!.findViewById(R.id.name) as TextView
            holder.lvnumber = convertView!!.findViewById(R.id.number) as TextView

            convertView.tag = holder

        } else {
            holder = convertView.tag as ViewHolder
        }
        val model=modelClassArrayList[p0]
        holder.lvname!!.text=model.getNames()
        holder.lvnumber!!.text=model.getNumber()
        holder.lvnumber!!.setOnClickListener {
            try {
                val number = model.getNumber()
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))

                if (ActivityCompat.checkSelfPermission(
                        context,
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

        return  convertView


    }
    inner class ViewHolder(){

        var lvname: TextView?=null
        var lvnumber: TextView?=null


    }

}