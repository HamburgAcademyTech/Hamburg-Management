package az.hamburg.management.custom_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TableCellLarge(text: String, isHeader: Boolean = false) {
    Text(
        modifier = Modifier
            .width(200.dp)
            .padding(16.dp),
        text = text,
        fontSize = if (isHeader) 16.sp else 14.sp,
        fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal
    )
}

@Composable
fun TableCellMedium(
    text: String,
    isHeader: Boolean = false,
    isTime: Boolean = false
) {
    Text(
        modifier = Modifier
            .width(120.dp)
            .padding(16.dp)
            .background(if (isTime) Color.Red else Color.Transparent),
        text = text,
        fontSize = if (isHeader) 16.sp else 14.sp,
        fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal
    )
}

@Composable
fun TableCellSmall(text: String, isHeader: Boolean = false) {
    Text(
        modifier = Modifier
            .width(70.dp)
            .padding(16.dp),
        text = text,
        fontSize = if (isHeader) 16.sp else 14.sp,
        fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal
    )
}
