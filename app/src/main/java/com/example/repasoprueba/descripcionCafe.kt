package com.example.repasoprueba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import com.drackdesign.repasoprueba.com.example.repasoprueba.Cafe
import kotlinx.android.synthetic.main.activity_descripcion_cafe.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class descripcionCafe : AppCompatActivity() {

    private lateinit var database : AppDatabase
    private lateinit var cafe : Cafe
    private lateinit var cafeLiveData: LiveData<Cafe>

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cafe, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion_cafe)

        database = AppDatabase.getDatabase(this) //context
        val id_cafe = intent.getIntExtra("id",0)

        cafeLiveData = database.cafes().get(id_cafe)
        cafeLiveData.observe(this,{
            cafe = it
            nombre_cafe_descripcion.text = cafe.nombre

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.editar -> {
                val intent = Intent(this, ingresarCafe::class.java)
                intent.putExtra("cafe", cafe)
                startActivity(intent)
            }

            R.id.eliminar -> {
                cafeLiveData.removeObservers(this)
                CoroutineScope(Dispatchers.IO).launch {
                    database.cafes().delete(cafe)
                    this@descripcionCafe.finish()
                }
            }


        }




        return super.onOptionsItemSelected(item)
    }
}