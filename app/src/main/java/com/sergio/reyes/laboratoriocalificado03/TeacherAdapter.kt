package com.sergio.reyes.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sergio.reyes.laboratoriocalificado03.databinding.ItemTeacherBinding


class TeacherAdapter(
    private val teachers: List<Teacher>,
    private val onItemClick: (Teacher) -> Unit,
    private val onItemLongClick: (Teacher) -> Unit
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(teachers[position], onItemClick, onItemLongClick)
    }

    override fun getItemCount(): Int = teachers.size

    class TeacherViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: Teacher, onClick: (Teacher) -> Unit, onLongClick: (Teacher) -> Unit) {
            binding.textViewNombre.text = teacher.nombre
            binding.textViewApellido.text = teacher.apellido
            Picasso.get().load(teacher.fotoUrl).into(binding.imageViewProfile)

            binding.root.setOnClickListener { onClick(teacher) }
            binding.root.setOnLongClickListener {
                onLongClick(teacher)
                true
            }
        }
    }
}
