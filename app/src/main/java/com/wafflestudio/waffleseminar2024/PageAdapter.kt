package com.wafflestudio.waffleseminar2024

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wafflestudio.waffleseminar2024.fragments.FirstFragment
import com.wafflestudio.waffleseminar2024.fragments.ForthFragment
import com.wafflestudio.waffleseminar2024.fragments.SearchFragment
import com.wafflestudio.waffleseminar2024.fragments.SecondFragment
import com.wafflestudio.waffleseminar2024.fragments.Tab1Fragment
import com.wafflestudio.waffleseminar2024.fragments.Tab2Fragment
import com.wafflestudio.waffleseminar2024.fragments.ThirdFragment
import com.wafflestudio.waffleseminar2024.fragments.UserInfoFragment

class PagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 4  // 총 3개의 Fragment

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tab1Fragment()
            1 -> Tab2Fragment()
            2 -> SearchFragment()
            3 -> UserInfoFragment()
            else -> FirstFragment()
        }
    }
}
