package com.sergio.reyes.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergio.reyes.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Ejercicio01 : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        binding.recyclerViewTeachers.layoutManager = LinearLayoutManager(this)

        // Llamar al servicio de API para obtener la lista de profesores
        RetrofitInstance.api.getTeachers().enqueue(object : Callback<TeacherResponse> {
            override fun onResponse(call: Call<TeacherResponse>, response: Response<TeacherResponse>) {
                if (response.isSuccessful) {
                    val teachers = response.body()?.data ?: emptyList()
                    // Configurar el RecyclerView con los datos obtenidos
                    binding.recyclerViewTeachers.adapter = TeacherAdapter(
                        teachers,
                        onItemClick = { teacher ->
                            // Llamar al número del profesor
                            val phoneNumber = teacher.telefono
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.data = Uri.parse("tel:$phoneNumber")
                            startActivity(intent)
                        },
                        onItemLongClick = { teacher ->
                            // Enviar correo al profesor
                            val email = teacher.email
                            val intent = Intent(Intent.ACTION_SENDTO)
                            intent.data = Uri.parse("mailto:$email")
                            startActivity(intent)
                        }
                    )
                } else {
                    Toast.makeText(this@Ejercicio01, "Error al obtener los datos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TeacherResponse>, t: Throwable) {
                Toast.makeText(this@Ejercicio01, "Error en la solicitud", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

