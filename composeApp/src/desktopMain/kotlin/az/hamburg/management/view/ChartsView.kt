package az.hamburg.management.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication
import org.jetbrains.letsPlot.*
import org.jetbrains.letsPlot.geom.*
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.themes.themeMinimal
import javax.swing.JPanel
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.jetbrains.letsPlot.awt.plot.PlotSvgExport
import java.awt.BorderLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.scale.scaleFillManual
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PieChart(data: Map<String, Int>, title: String) {
    val plot = letsPlot(mapOf("Year" to data.keys, "Count" to data.values)) +
            geomPie(stat = Stat.identity, size = 15.0) { fill = "Year"; weight = "Count" } +
            scaleFillManual(values = listOf("#FF5733", "#33FF57", "#3357FF")) + // Custom Colors
            ggtitle(title) + themeMinimal()

    PlotContainer(plot)
}

@Composable
fun BarChart(data: Map<String, Int>, title: String) {
    val plot = letsPlot(mapOf("Day" to data.keys, "Count" to data.values)) +
            geomBar(stat = Stat.identity) { x = "Day"; y = "Count"; fill = "Day" } +
            scaleFillManual(values = listOf("#FF5733", "#33FF57", "#3357FF")) + // Custom Colors
            ggtitle(title) + themeMinimal()

    PlotContainer(plot)
}

@Composable
fun PlotContainer(plot: Plot) {
    SwingPanel(
        factory = {
            val svg = PlotSvgExport.buildSvgImageFromRawSpecs(plot.toSpec())
            val panel = JPanel(BorderLayout())
            panel.add(JLabel(ImageIcon(svg)), BorderLayout.CENTER)
            panel
        },
        modifier = Modifier.fillMaxWidth().height(400.dp)
    )
}



@Composable
fun PieChartWithLabels(data: Map<String, Float>) {
    val total = data.values.sum()
    var startAngle = 0f
    val sortedData = data.entries.sortedByDescending { it.value }
    val colors = listOf(
        Color(0xFF4CAF50), Color(0xFFFF9800), Color(0xFF2196F3),
        Color(0xFFE91E63), Color(0xFFFFC107), Color(0xFF9C27B0)
    )

    Column{
        Box(modifier = Modifier.size(400.dp)) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val radius = size.minDimension / 2
                val center = Offset(size.width / 2, size.height / 2)

                sortedData.forEachIndexed { index, entry ->
                    val sweepAngle = (entry.value / total) * 360f
                    drawArc(
                        color = colors[index % colors.size],
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = true,
                        size = Size(size.width, size.height)
                    )
                    startAngle += sweepAngle
                }
            }

            // Add Labels on top of Canvas
            startAngle = 0f // Reset for accurate label positioning
            sortedData.forEachIndexed { index, entry ->
                val sweepAngle = (entry.value / total) * 360f
                val midAngle = Math.toRadians((startAngle + sweepAngle / 2).toDouble())
                val xOffset = cos(midAngle).toFloat() * 80
                val yOffset = sin(midAngle).toFloat() * 80
                startAngle += sweepAngle

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(xOffset.dp, yOffset.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${entry.key}-${entry.value.toInt()} | ${(entry.value / total * 100).toInt()}%",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color =  Color.Black //colors[index % colors.size] // Set text color to match the slice
                    )
                }
            }
        }
        Text(
            text = "Total: ${total.toInt()}",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 50.dp)
        )
    }
}

@Composable
fun BarChartWithLabels(data: Map<String, Float>) {
    val maxValue = data.values.maxOrNull() ?: 1f
    val sortedData = data.entries.sortedByDescending { it.value }
    val colors = listOf(
        Color(0xFF4CAF50), Color(0xFFFF9800), Color(0xFF2196F3),
        Color(0xFFE91E63), Color(0xFFFFC107), Color(0xFF9C27B0)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        sortedData.forEachIndexed { index, entry ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    text = entry.key,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(80.dp)
                )
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .fillMaxWidth()
                        .weight(1f)
                        .background(colors[index % colors.size])
                ) {
                    Text(
                        text = "${entry.value.toInt()} | ${(entry.value / maxValue * 100).toInt()}%",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun VerticalBarChart(data: Map<String, Float>) {
    val maxValue = data.values.maxOrNull() ?: 1f
    val sortedData = data.entries.sortedBy { it.key } // Sort by category name
    val colors = listOf(
        Color(0xFF4CAF50), Color(0xFFFF9800), Color(0xFF2196F3),
        Color(0xFFE91E63), Color(0xFFFFC107), Color(0xFF9C27B0)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            val barWidth = size.width / (data.size * 1.5f)
            sortedData.forEachIndexed { index, entry ->
                val barHeight = (entry.value / maxValue) * size.height
                drawRect(
                    color = colors[index % colors.size],
                    topLeft = Offset(index * barWidth * 1.5f + barWidth / 4, size.height - barHeight),
                    size = Size(barWidth, barHeight)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Labels under the bars
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            sortedData.forEach { entry ->
                Text(
                    text = "${entry.key} | ${entry.value.toInt()}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(70.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }
    }
}
