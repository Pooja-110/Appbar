import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.appbar.CustomAdapter
import com.example.appbar.ModelClass
import com.example.appbar.R

class Calllog : Fragment() {

    var listView: ListView? = null
    lateinit var customAdapter: CustomAdapter
    lateinit var modelclassArrayList: ArrayList<ModelClass>
    lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_blank4, container, false)

    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view.findViewById(R.id.listvw)
        modelclassArrayList = ArrayList()
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.READ_CONTACTS),
                123
            )
        } else {
            contactFatch()
        }


    }

    @SuppressLint("Range", "UseRequireInsteadOfGet")
    private fun contactFatch() {
        var contentResolver = requireActivity().contentResolver
        val phone = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )

        while (phone!!.moveToNext()) {
            val name =
                phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phonenumber =
                phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val modelClass = ModelClass()
            modelClass.setNames(name)
            modelClass.setNumber(phonenumber)
            modelclassArrayList!!.add(modelClass)
        }
        phone.close()

        customAdapter = CustomAdapter(context!!, modelclassArrayList)
        listView!!.adapter = customAdapter

    }

}