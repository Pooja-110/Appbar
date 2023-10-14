package com.example.appbar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView

class Email: Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var emailEditSubject: EditText
    private lateinit var emailEditMsg: EditText
    private lateinit var button: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailEditSubject = view.findViewById(R.id.sub)
        emailEditText = view.findViewById(R.id.eml)
        emailEditMsg = view.findViewById(R.id.masg)
        button = view.findViewById(R.id.send)
        button.setOnClickListener {
            sendEmail()
        }
    }

    private fun sendEmail() {
        val recipient: String = emailEditText.text.toString()
        val subject: String = emailEditSubject.text.toString()
        val message: String = emailEditMsg.text.toString()
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data= Uri.parse("mailto:$recipient")
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)


            startActivity(Intent.createChooser(intent,"Select you E_mail App"))

    }
}
