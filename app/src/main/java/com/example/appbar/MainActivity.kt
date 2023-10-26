package com.example.appbar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var tab:TabLayout
    lateinit var tool: Toolbar
    lateinit var pager:ViewPager

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tab=findViewById(R.id.tabLayout)
        tool=findViewById(R.id.toolbar)
        pager=findViewById(R.id.viewpager)

        setSupportActionBar(tool)
        val adapter=ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Call(),"Call")
        adapter.addFragment(Message(),"Message")
        adapter.addFragment(Email(),"Email")
        adapter.addFragment(Calllog(),"CallLogs")

        pager.adapter=adapter
        tab.setupWithViewPager(pager)
    }

}