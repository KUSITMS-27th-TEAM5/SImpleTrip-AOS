package com.kusitms.ovengers

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.transaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kusitms.ovengers.view.*


class HomeActivity : AppCompatActivity() {

    private lateinit var storeFragment : StoreFragment
    private lateinit var homeFragment : HomeFragment
    private lateinit var storageFragment : StorageFragment
    private lateinit var chooseDateFragment: ChooseDateFragment
    private lateinit var chooseDestinationFragment: ChooseDestinationFragment
    private lateinit var createNameFragment: CreateNameFragment

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

    fun HideBottomNav(state:Boolean) {
        if(state) {
            val bottomNavi = findViewById<BottomNavigationView>(R.id.bottomNavi)


            bottomNavi.visibility = View.GONE
        } else {
            val bottomNavi = findViewById<BottomNavigationView>(R.id.bottomNavi)
            bottomNavi.visibility = View.VISIBLE
        }
    }


    //캐리어 생성 후 날짜 선택 이동
    fun homeToStep1() {
        chooseDateFragment = ChooseDateFragment().newInstance()


        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.Main_Frame,chooseDateFragment)
        transaction.addToBackStack(null)
        transaction.commit()
//        supportFragmentManager.beginTransaction().replace(R.id.Main_Frame,chooseDateFragment).commit()

    }

    //날짜 선택 후 목적지 선택 이동
    fun  step1Step2(){
        chooseDestinationFragment = ChooseDestinationFragment().newInstance()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.Main_Frame,chooseDestinationFragment)
        transaction.addToBackStack(null)
        transaction.commit()
//        supportFragmentManager.beginTransaction().replace(R.id.Main_Frame,chooseDestinationFragment).commit()
    }

    //목적지 선택 후 캐리어 이름 생성 이동

    fun step2Step3 () {
        createNameFragment = CreateNameFragment().newInstance()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.Main_Frame,createNameFragment)
        transaction.addToBackStack(null)
        transaction.commit()
//        supportFragmentManager.beginTransaction().replace(R.id.Main_Frame,createNameFragment).commit()
    }

    fun carrierMakeSuccess() {
        homeFragment = HomeFragment().newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.Main_Frame, homeFragment).commit()
        HideBottomNav(false)
    }
} // 커밋용