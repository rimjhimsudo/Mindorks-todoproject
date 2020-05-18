package com.example.todoapp.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewParent
import androidx.viewpager.widget.ViewPager
import com.example.todoapp.R
import com.example.todoapp.utils.PrefConstants
import com.example.todoapp.view.LoginActivity

class OnBoardingActivity : AppCompatActivity() , OnBoardingOneFragment.onNextClick , OnBoardingtwoFragment.onOptionClick{
    lateinit var viewpager : ViewPager
    lateinit var sharedPreferences: SharedPreferences
    lateinit  var editor : SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        bindview()
        setupsharedpref()
    }
    private  fun setupsharedpref(){
        sharedPreferences = getSharedPreferences(PrefConstants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)


    }

    private fun bindview() {

        viewpager =findViewById(R.id.viewpager)
        val adapter =FragmentAdapter(supportFragmentManager)
        viewpager.adapter=adapter
    }

    override fun onClick() {
        viewpager.currentItem=1
        Log.d("BOARDCLC","clcked")
    }

    override fun onOptionNext() {
        editor=sharedPreferences.edit()
        editor.putBoolean(PrefConstants.ON_BOARDED_SUCCESS,true)
        editor.apply()
        val intent=Intent(this@OnBoardingActivity,LoginActivity::class.java)
        startActivity(intent)
        Log.d("BOARDCLC","clcked next two")

    }

    override fun onOptionBack() {
        viewpager.currentItem=0
        Log.d("BOARDCLC","clcked bac two")
    }


}
/*Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>*/
