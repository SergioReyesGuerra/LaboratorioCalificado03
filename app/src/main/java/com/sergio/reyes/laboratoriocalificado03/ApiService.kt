package com.sergio.reyes.laboratoriocalificado03

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher") // Esta es la URL del endpoint
    fun getTeachers(): Call<TeacherResponse> // Esta función devolverá la lista de profesores
}
