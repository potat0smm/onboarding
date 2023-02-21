package com.example.onboarding

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.onboarding.databinding.ActivityMainBinding
import com.example.onboarding.databinding.FragmentLoginBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var itemList = ArrayList<OnBoardingData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewPager()
    }
    private fun setUpViewPager(){
        itemList = getItems()
        binding.viewPager.adapter = PagerAdapter(itemList)
        binding.wormDot.setViewPager2(binding.viewPager)
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private var pageChangeCallback: ViewPager2.OnPageChangeCallback = object : ViewPager2.OnPageChangeCallback(){
        @SuppressLint("SetTextI18n")
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.imgPrevious.visibility = View.INVISIBLE.takeIf { position == 0 }?:View.VISIBLE

            if(position == itemList.size -1){
                binding.txtNext.text = "Start"
                binding.txtSkip.visibility = View.GONE
            }
            else{
                binding.txtNext.text = "Next"
                binding.txtSkip.visibility = View.VISIBLE
            }
        }
    }

    private fun getItems(): ArrayList<OnBoardingData>{
        val items  = ArrayList<OnBoardingData>()
        items.add(
            OnBoardingData(
                "a",
                "f",
                R.drawable.img_1
            )
        )
        items.add(
            OnBoardingData(
                "f",
                "a",
                R.drawable.img_15
            )
        )
        items.add(
            OnBoardingData(
                "A",
                "A",
                R.drawable.img
            )
        )
        return items
    }


    fun onClick(view:View){
        when(view){
            binding.imgPrevious -> {
                val currentItemPosition = binding.viewPager.currentItem
                if(currentItemPosition == 0)return
                binding.viewPager.setCurrentItem(currentItemPosition - 1, true)
            }
            binding.txtNext -> {
                val currentItemPosition = binding.viewPager.currentItem
                if (currentItemPosition == itemList.size - 1) {
                        val intent = Intent(this@MainActivity, FragmentLoginBinding::class.java)
                        startActivity(intent)
                }
                binding.viewPager.setCurrentItem(currentItemPosition +1,true)
            }
            binding.txtSkip -> {
                Toast.makeText(this,"Start Next Process",Toast.LENGTH_SHORT).show()
            }
        }
    }
}