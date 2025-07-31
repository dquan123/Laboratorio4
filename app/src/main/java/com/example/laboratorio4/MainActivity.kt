package com.example.laboratorio4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.laboratorio4.ui.theme.Laboratorio4Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight


//Clase para la Mascota
data class Mascota(
    val nombre: String,
    val raza: String,
    val imagen: Int,
    var adoptado: Boolean = false
)

//Lista de mascotas iniciales
val mascotas_iniciales = listOf(
    Mascota("Max", "Labrador", R.drawable.im1),
    Mascota("Bella", "Rottweiler", R.drawable.im2),
    Mascota("Luna", "Bulldog", R.drawable.im3),
    Mascota("Jack", "Chihuahua", R.drawable.im4),
    Mascota("Yuki", "Akita Inu", R.drawable.im5)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4DiegoQuan()
        }
    }
}

@Composable
fun Lab4DiegoQuan() {
    var mascotas by remember { mutableStateOf(mascotas_iniciales) }

    LazyColumn {
        items(mascotas.size) { index ->
            MascotaCard(
                mascota = mascotas[index],
                onAdoptar = {
                    mascotas = mascotas.toMutableList().apply {
                        this[index] = this[index].copy(adoptado = true)
                    }
                }
            )
        }
    }
}

@Composable
fun MascotaCard(mascota: Mascota, onAdoptar: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(20.dp)) {
            Image(
                painter = painterResource(id = mascota.imagen),
                contentDescription = mascota.nombre,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(mascota.nombre, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Text(mascota.raza, fontSize = 20.sp, color = Color.Gray)
            }

            Button(
                onClick = { onAdoptar() },
                enabled = !mascota.adoptado
            ) {
                Text(if (mascota.adoptado) "¡Adoptado! ❤" else "Adoptar")
            }
        }
    }
}
