package com.dicoding.picodiploma.myrecyclerview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.myrecyclerview.adapter.ListHeroAdapter
import com.dicoding.picodiploma.myrecyclerview.model.Hero

class MainActivity : AppCompatActivity() {
    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Force light theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(R.layout.activity_main)

        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        // TODO: 1. Data source of the Recycler View
        list.addAll(getListOfHeroes())
        showRecyclerList()
    }

    // TODO: 1. Data source of the Recycler View
    @SuppressLint("Recycle")
    private fun getListOfHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listHero = ArrayList<Hero>()
        for (i in dataName.indices) {
            val hero = Hero(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {

        // TODO: 2. Show Adapter instantiation and how we pass the Data Source into it
        //using lambda
        val listHeroAdapter = ListHeroAdapter(list) { data ->
            showSelectedHero(data)
        }

        //using interface
        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })

        // TODO: 5. Show the Layout Manager of the RecycerView
        rvHeroes.layoutManager = LinearLayoutManager(this)

        // TODO: 6. Show how the adapter that we created be connected with the RecyclerView
        rvHeroes.adapter = listHeroAdapter

        rvHeroes.setHasFixedSize(true)
    }

    // TODO: 7. Briefly show how to send object (Hero data class) using Parcelable to DetailActivity
    private fun showSelectedHero(hero: Hero) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("HERO_OBJECT", hero)
        startActivity(intent)
    }
}