package az.hamburg.management.custom_view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import az.hamburg.management.presentation.monthOrder

@Composable
fun TableHeader(){
    val tableLargeHeaders = listOf(
        "Ad", "Nömrə"
    )
    val tableMediumHeaders = listOf(
        "Ödəniş", "Başlama",
        "Səviyyə", "Gün", "Saat"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        TableCellSmall("Sıra", isHeader = true)

        tableLargeHeaders.forEach {
            TableCellLarge(it, isHeader = true)
        }
        tableMediumHeaders.forEach {
            TableCellMedium(it, isHeader = true)
        }
        monthOrder.forEach {
            TableCellSmall(it.substring(0, 3), isHeader = true)
        }
    }
}
