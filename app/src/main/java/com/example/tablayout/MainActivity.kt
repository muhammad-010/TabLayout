package com.example.tablayout

import SectionsPagerAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private var emailData = ""
    private var passwordData = ""
    private var nimData = ""
    private var nameData = ""

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabs, viewPager){
                tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        emailData = intent.getStringExtra("EXTRA_EMAIL").toString()
        nameData = intent.getStringExtra("EXTRA_NAME").toString()
        nimData = intent.getStringExtra("EXTRA_NIM").toString()
        passwordData = intent.getStringExtra("EXTRA_PASSWORD").toString()

        if((emailData.length > 0) && (nameData.length > 0) && (nimData.length > 0) && (passwordData.length > 0)){
            this.receiveUserData(emailData, passwordData, nimData, nameData)
        }
    }
    fun redirectToDashboard(){
        val intent = Intent(this@MainActivity, DashboardActivity::class.java)
        intent.putExtra("EXTRA_EMAIL", emailData)
        intent.putExtra("EXTRA_PASSWORD", passwordData)
        intent.putExtra("EXTRA_NAME", nameData)
        intent.putExtra("EXTRA_NIM", nimData)
        startActivity(intent)
    }
    fun receiveUserData(email: String, password: String, nim: String, name: String) {
        emailData = email
        passwordData = password
        nimData = nim
        nameData = name
    }
    fun getEmail(): String{
        return emailData
    }
    fun getPassword(): String{
        return passwordData
    }
}