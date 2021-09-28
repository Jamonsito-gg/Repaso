package com.example.repasoprueba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ingresar_cafe.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ingresarCafe : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_cafe)

        var id_cafe_editar : Int? = null
        if (intent.hasExtra("cafe")) { //descripcion id cafe
            val cafe = intent.extras?.getSerializable("cafe") as Cafe
            id_cafe_editar = cafe.idCafe
            nombre_cafe.setText(cafe.nombre)
        }

        btnInsertar.setOnClickListener{   //it view
            val database = AppDatabase.getDatabase(this) //context
            var nombre = nombre_cafe.text.toString()

            val cafe = Cafe(nombre)

            if(id_cafe_editar != null){
                    CoroutineScope(Dispatchers.IO).launch {
                    cafe.idCafe = id_cafe_editar
                    database.cafes().update(cafe)
                    this@ingresarCafe.finish()
                    }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                database.cafes().InsertAll(cafe)
                this@ingresarCafe.finish()
                }

            }

         }

    }

}