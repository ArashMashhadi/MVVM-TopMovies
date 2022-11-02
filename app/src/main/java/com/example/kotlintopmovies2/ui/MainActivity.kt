package com.example.kotlintopmovies2.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.kotlintopmovies2.R
import com.example.kotlintopmovies2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    //Binding
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    //Navigation
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //InitView
        binding.apply {

            home.setOnClickListener(this@MainActivity)
            search.setOnClickListener(this@MainActivity)
            favorit.setOnClickListener(this@MainActivity)
            paging.setOnClickListener(this@MainActivity)

            initNavigation()
        }
    }

    //Init Navigation
    private fun initNavigation() {
        binding.apply {
            navHostFragment =
                supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
            navController = navHostFragment.navController
            //appBarConfiguration = AppBarConfiguration(setOf(R.id.splashFragment))
            //NavigationUI.setupActionBarWithNavController(this@MainActivity, navController)
            //bottomNavigationView.setupWithNavController(navController)
            //NavigationUI.setupWithNavController(navigationView, navController)

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

    override fun onClick(v: View) {
        binding.apply {
            when (v.id) {
                R.id.home -> {
                    bottomNavigation(R.id.homeFragment,
                        R.color.crayola,
                        R.color.primary,
                        R.color.primary,
                        R.color.primary,
                        2,
                        0,
                        0,
                        0)
                }
                R.id.search -> {
                    bottomNavigation(R.id.searchFragment,
                        R.color.primary,
                        R.color.crayola,
                        R.color.primary,
                        R.color.primary,
                        0,
                        2,
                        0,
                        0)
                }
                R.id.favorit -> {
                    bottomNavigation(R.id.favoriteFragment,
                        R.color.primary,
                        R.color.primary,
                        R.color.crayola,
                        R.color.primary,
                        0,
                        0,
                        2,
                        0)
                }
                R.id.paging -> {
                    bottomNavigation(R.id.pagingFragment,
                        R.color.primary,
                        R.color.primary,
                        R.color.primary,
                        R.color.crayola,
                        0,
                        0,
                        0,
                        2)
                }
            }
        }
    }

    private fun bottomNavigation(
        fragment: Int,
        colorHomeImg: Int,
        colorSearchImg: Int,
        colorFavoritImg: Int,
        colorPagingImg: Int,
        intHome: Int,
        intSearch: Int,
        intFavorit: Int,
        intPaging: Int,
    ) {
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
