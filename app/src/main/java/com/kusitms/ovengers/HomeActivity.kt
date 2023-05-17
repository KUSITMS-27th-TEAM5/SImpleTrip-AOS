package com.kusitms.ovengers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kusitms.ovengers.view.HomeFragment
import com.kusitms.ovengers.view.StorageFragment
import com.kusitms.ovengers.view.StoreFragment


class HomeActivity : AppCompatActivity() {

    private lateinit var storeFragment : StoreFragment
    private lateinit var homeFragment : HomeFragment
    private lateinit var storageFragment : StorageFragment

    private val TAG = HomeActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 처음 뜨는 fragment 설정
        homeFragment = HomeFragment().newInstance()
        supportFragmentManager.beginTransaction().add(R.id.Main_Frame, homeFragment).commit()


        // 바텀 네비게이션 아이템 클릭 리스터 설정
        val onBottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener{
            val ap = true
            when (it.itemId) {
                R.id.navigation_store -> {
                    storeFragment = StoreFragment().newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.Main_Frame, storeFragment).commit()
                    Log.d("Nav : ","Store Fragment")
                }
                R.id.navigation_home -> {
                    homeFragment = HomeFragment().newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.Main_Frame, homeFragment).commit()
                    Log.d("Nav : ","Home Fragment")

                }
                R.id.navigation_storage -> {
                    storageFragment = StorageFragment().newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.Main_Frame, storageFragment).commit()
                    Log.d("Nav : ","Storage Fragment")

                }
            }
            true
        }

        // 바텀네비게이션 변수 실행
        val bottomNavi = findViewById<BottomNavigationView>(R.id.bottomNavi)
        bottomNavi.setOnNavigationItemSelectedListener(onBottomNavItemSelectedListener)

    }
}