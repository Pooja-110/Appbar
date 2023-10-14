package com.example.appbar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter (supportFragment:FragmentManager):FragmentStatePagerAdapter(supportFragment) {
    val mfragmentlist=ArrayList<Fragment>()
    val mfrgmentTitle=ArrayList<String>()


    override fun getCount(): Int {
       return mfragmentlist.size
    }

    override fun getItem(position: Int): Fragment {
       return mfragmentlist[position]
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return mfrgmentTitle[position]
    }
    fun addFragment(fragment:Fragment,title:String) {
        mfragmentlist.add(fragment)
        mfrgmentTitle.add(title)
    }
}