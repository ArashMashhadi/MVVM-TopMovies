package com.example.kotlintopmovies2.ui


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.kotlintopmovies2.R
import com.example.kotlintopmovies2.databinding.ActivityMainBinding

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(),View.OnClickListener {

    //Binding
    private lateinit var binding: ActivityMainBinding

    //Navigation
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //InitView
        binding.apply {

            home.setOnClickListener(this@MainActivity)
            search.setOnClickListener(this@MainActivity)
            favorit.setOnClickListener(this@MainActivity)
            paging.setOnClickListener(this@MainActivity)

            //Navigation
            navController = findNavController(R.id.navHost)
            //bottomNavigationView.setupWithNavController(navController)

            //Show Bottom Navigation
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.splashFragment || destination.id == R.id.registerFragment
                    || destination.id == R.id.detailFragment
                ) {
                    bottomNavigationView.visibility = View.GONE
                } else {
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }

        }
    }

    //BackFragment
    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }


    override fun onClick(v: View?) {
        binding.apply {
            when (v?.id) {
                R.id.home -> {
                    bottomNav(R.id.homeFragment,R.color.crayola,R.color.primary,R.color.primary,R.color.primary,2,0,0,0)
/*                    homeImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.crayola), android.graphics.PorterDuff.Mode.SRC_IN);
                    searchImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
                    favoritImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
                    pagingImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
                    navController.navigateUp()
                    navController.navigate(R.id.homeFragment);
                    home.setShapeType(2)
                    search.setShapeType(0)
                    favorit.setShapeType(0)
                    paging.setShapeType(0)*/
                }
                R.id.search -> {
                    bottomNav(R.id.searchFragment,R.color.primary,R.color.crayola,R.color.primary,R.color.primary,0,2,0,0)
/*                    homeImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
                    searchImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.crayola), android.graphics.PorterDuff.Mode.SRC_IN);
                    favoritImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
                    pagingImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
                    navController.navigateUp()
                    navController.navigate(R.id.searchFragment);
                    home.setShapeType(0)
                    search.setShapeType(2)
                    favorit.setShapeType(0)
                    paging.setShapeType(0)*/
                }
                R.id.favorit -> {
                    bottomNav(R.id.favoriteFragment,R.color.primary,R.color.primary,R.color.crayola,R.color.primary,0,0,2,0)
/*                    homeImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
                    searchImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
                    pagingImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
                    favoritImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.crayola), android.graphics.PorterDuff.Mode.SRC_IN);
                    navController.navigateUp()
                    navController.navigate(R.id.favoriteFragment);
                    home.setShapeType(0)
                    search.setShapeType(0)
                    favorit.setShapeType(2)
                    paging.setShapeType(0)*/
                }
                R.id.paging -> {
                    bottomNav(R.id.pagingFragment,R.color.primary,R.color.primary,R.color.primary,R.color.crayola,0,0,0,2)
/*                    homeImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
                    searchImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
                    favoritImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
                    pagingImg.setColorFilter(ContextCompat.getColor(applicationContext, R.color.crayola), android.graphics.PorterDuff.Mode.SRC_IN);
                    navController.navigateUp()
                    navController.navigate(R.id.pagingFragment);
                    home.setShapeType(0)
                    search.setShapeType(0)
                    favorit.setShapeType(0)
                    paging.setShapeType(2)*/

                }
            }
        }
    }

    fun bottomNav(fragment:Int,colorHomeImg: Int,colorSearchImg: Int,colorFavoritImg: Int,colorPagingImg: Int,intHome: Int,intSearch: Int,intFavorit: Int,intPaging: Int ) {
        binding.apply {
            homeImg.setColorFilter(
                ContextCompat.getColor(applicationContext, colorHomeImg),
                android.graphics.PorterDuff.Mode.SRC_IN
            );
            searchImg.setColorFilter(
                ContextCompat.getColor(applicationContext, colorSearchImg),
                android.graphics.PorterDuff.Mode.SRC_IN
            );
            favoritImg.setColorFilter(
                ContextCompat.getColor(applicationContext, colorFavoritImg),
                android.graphics.PorterDuff.Mode.SRC_IN
            );
            pagingImg.setColorFilter(
                ContextCompat.getColor(applicationContext, colorPagingImg),
                android.graphics.PorterDuff.Mode.SRC_IN
            );
            navController.navigateUp()
            navController.navigate(fragment);
            home.setShapeType(intHome)
            search.setShapeType(intSearch)
            favorit.setShapeType(intFavorit)
            paging.setShapeType(intPaging)
        }
    }
}
