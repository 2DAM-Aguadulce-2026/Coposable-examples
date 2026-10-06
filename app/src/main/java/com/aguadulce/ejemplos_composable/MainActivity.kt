package com.aguadulce.ejemplos_composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.aguadulce.ejemplos_composable.ui.theme.Ejemplos_composableTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejemplos_composableTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
//                        AppBarExample()
                        ExampleBadge()
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
//                            .padding(10.dp)
                    ) {
                        LazyColumn(
                            content = {
                                item { //Normalmente cada elemento de las lazyColumn tiene que ir
                                       // envuelto en un item{}
                                    ExampleText()
                                    ExampleIcon()
                                    ExampleImage()
                                    ExampleButton()
                                    ExampleFlotatingActionButton { print("Hola") }
                                    ExampleSwitch()
                                    ExampleTextField()
                                    ExampleCard()
                                    ExampleProgressBarIndicator()
                                    ExampleCheckBox()
                                    ExampleRadioButton()
                                    ExampleDatePicker()
                                    ExampleTimePicker()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleTimePicker() {
    val timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 30,
        is24Hour = true
    )

// Normalmente esto se envuelve dentro de un componente Dialog o AlertDialog
// para que flote sobre la UI principal cuando el usuario pulsa un botón.
    TimePicker(
        state = timePickerState
    )
}

@Composable
fun ExampleDatePicker() {
    val datePickerState = rememberDatePickerState()

    DatePicker(
        state = datePickerState,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun ExampleRadioButton() {
    var opcionSeleccionada by remember { mutableStateOf(1) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = (opcionSeleccionada == 1),
            onClick = { opcionSeleccionada = 1 }
        )
        Text("Opción 1")
    }
}

@Composable
fun ExampleCheckBox() {
    var aceptado by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = aceptado,
            onCheckedChange = { aceptado = it }
        )
        Text("Acepto los términos")
    }
}

@Composable
fun ExampleProgressBarIndicator() {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.secondary,
        strokeWidth = 4.dp
    )
}

@Composable
fun ExampleCard() {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Título de la Tarjeta", fontWeight = FontWeight.Bold)
            Text("Contenido descriptivo dentro del contenedor.")
        }
    }

}

@Composable
fun ExampleTextField() {
    var nombre by remember { mutableStateOf("") }

    OutlinedTextField(
        value = nombre,
        onValueChange = { nombre = it },
        label = { Text("Nombre completo") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    )
}

@Composable
fun ExampleSwitch() {
    var isChecked by remember { mutableStateOf(false) }
    Switch(
        checked = isChecked,
        onCheckedChange = { nuevoEstado -> isChecked = nuevoEstado }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleBadge() {
    TopAppBar(
        title = { Text("Listado de Alumnos") },
        navigationIcon = {
            IconButton(onClick = { /* Abrir menú */ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menú principal")
            }
        },
        actions = {
            BadgedBox(
                badge = {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text("3")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notificaciones"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.LightGray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarExample() {
    TopAppBar(
        title = { Text("Listado de Alumnos") },
        navigationIcon = {
            IconButton(onClick = { /* Abrir menú */ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menú principal")
            }
        },
        actions = {
            BadgedBox(
                badge = {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text("3")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notificaciones"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.LightGray
        )
    )
}

@Composable
fun ExampleFlotatingActionButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.primary,
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    ) {
        Icon(Icons.Filled.Add, "Añadir elemento.")
    }
}

@Composable
fun ExampleButton() {
    Button(
        onClick = { /* Lógica al pulsar */ },
        enabled = true,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Guardar Datos")
    }
}

@Composable
fun ExampleImage() {
    Image(
        painter = painterResource(id = R.drawable.profile_picture),
        contentDescription = "Fotografía del alumno",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
    )
}

@Composable
fun ExampleIcon() {
    Icon(
        painter = painterResource(R.drawable.home_icono2),
        contentDescription = "Añadir a favoritos",
        tint = Color.Red,
        modifier = Modifier.size(32.dp)
    )

    Icon(
        painter = painterResource(R.drawable.home_icono),
        contentDescription = "Añadir a favoritos",
        tint = Color.Red,
        modifier = Modifier.size(32.dp)
    )
}

@Composable
fun ExampleText() {
    Text(
        text = "¡Bienvenidos al módulo de DAM!",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )
}
