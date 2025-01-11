package az.hamburg.management.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun ColorPicker(onColorChange: (Color) -> Unit) {
    var red by remember { mutableStateOf(255) }
    var green by remember { mutableStateOf(255) }
    var blue by remember { mutableStateOf(255) }

    val color = Color(red, green, blue)
    onColorChange(color)

    Column(
        modifier = Modifier
            .fillMaxWidth(),
            //.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Color Preview
        Canvas(
            modifier = Modifier
                .size(50.dp)
        ) {
            drawCircle(
                color = color,
                style = Stroke(width = 12.dp.toPx())
            )
        }

        //Spacer(modifier = Modifier.height(16.dp))

        // Red Slider
        ColorSlider(
            label = "Red",
            value = red,
            onValueChange = { red = it },
            activeColor = Color.Red
        )

        // Green Slider
        ColorSlider(
            label = "Green",
            value = green,
            onValueChange = { green = it },
            activeColor = Color.Green
        )

        // Blue Slider
        ColorSlider(
            label = "Blue",
            value = blue,
            onValueChange = { blue = it },
            activeColor = Color.Blue
        )
    }
}

@Composable
fun ColorSlider(label: String, value: Int, onValueChange: (Int) -> Unit, activeColor: Color) {
    Column {
        Text(text = label, style = MaterialTheme.typography.body2)
        Slider(
            value = value / 255f,
            onValueChange = { onValueChange((it * 255).toInt()) },
            colors = SliderDefaults.colors(thumbColor = activeColor, activeTrackColor = activeColor)
        )
    }
}
