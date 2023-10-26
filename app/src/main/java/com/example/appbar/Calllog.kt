package com.example.appbar

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class Calllog : Fragment() {

    var listView: ListView? = null
    lateinit var customAdapter: CustomAdapter
    lateinit var modelclassArrayList: ArrayList<ModelClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank4, container, false)
        listView = view.findViewById(R.id.listvw)
        modelclassArrayList = ArrayList()
        return view
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_CONTACTS),
                123
            )
        } else {
            contactFetch()
        }
    }

    @SuppressLint("Range", "UseRequireInsteadOfGet")
    private fun contactFetch() {
        var contentResolver = requireActivity().contentResolver
        val phone = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )

        while (phone!!.moveToNext()) {
            val name =
                phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber =
                phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val modelClass = ModelClass()
            modelClass.setNames(name)
            modelClass.setNumber(phoneNumber)
            modelclassArrayList.add(modelClass)
        }
        phone.close()

        customAdapter = CustomAdapter(requireContext(), modelclassArrayList)
        listView!!.adapter = customAdapter
    }
}
