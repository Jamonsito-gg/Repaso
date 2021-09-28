package com.example.repasoprueba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class NuevoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo)

        var listadocafes = emptyList<Cafe>()
        val database = AppDatabase.getDatabase(this) //context
        database.cafes().getAll().observe(this,{listadocafes = it  //owner
            val adapter = cafeAdapter(this, listadocafes) // mContext
            listado.adapter = adapter
        })

        listado.setOnItemClickListener{ parent, view, position, id->
            val intent = Intent(this, descripcionCafe::class.java)
            intent.putExtra("id",listadocafes[position].idCafe) //falta
            startActivity(intent)
        }

        fun volver(view : View){
            Toast.makeText(this, "Vamos a el main Activity", Toast.LENGTH_SHORT ).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}