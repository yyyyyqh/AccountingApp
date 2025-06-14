package com.yqh.accountingapp.ui.features.reports

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("2025 / 06") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "返回")
                    }
                },
                actions = {
                    DateToggleButtons()
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.MoreVert, "更多")
                    }
                }
            )
        }
    ) { paddingValues ->
        // 因为内容很多，我们让整个页面可以垂直滚动
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            ReportSummary()
            Spacer(modifier = Modifier.height(24.dp))
            LineChartPlaceholder()
            Spacer(modifier = Modifier.height(24.dp))
            DonutChartPlaceholder()

            // TODO: 在这里添加下方的列表
        }
    }
}

@Composable
fun DateToggleButtons() {
    var selectedIndex by remember { mutableStateOf(1) } // 0=周, 1=月, 2=年
    val options = listOf("周", "月", "年")

    OutlinedButton(onClick = { /*TODO*/ }, shape = MaterialTheme.shapes.small) { // Just a placeholder for the group
        Row {
            options.forEachIndexed { index, text ->
                TextButton(
                    onClick = { selectedIndex = index },
                    colors = if (selectedIndex == index) ButtonDefaults.textButtonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer) else ButtonDefaults.textButtonColors()
                ) {
                    Text(text)
                }
            }
        }
    }
}

@Composable
fun ReportSummary() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            SummaryItem("总收入", "¥ 0")
            SummaryItem("总支出", "¥ 515.7")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            SummaryItem("结余", "-¥ 515.7")
            SummaryItem("总笔数", "40")
            SummaryItem("日均额", "¥ 36.84")
        }
    }
}

@Composable
fun SummaryItem(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun LineChartPlaceholder() {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)) {
        val path = Path().apply {
            moveTo(0f, size.height * 0.8f)
            lineTo(size.width * 0.1f, size.height * 0.7f)
            lineTo(size.width * 0.3f, size.height * 0.5f)
            lineTo(size.width * 0.5f, size.height * 0.2f)
            lineTo(size.width * 0.7f, size.height * 0.4f)
            lineTo(size.width * 0.9f, size.height * 0.3f)
            lineTo(size.width, size.height * 0.5f)
        }
        drawPath(path, color = Color.Gray, style = Stroke(width = 3.dp.toPx()))
    }
}

@Composable
fun DonutChartPlaceholder() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(180.dp)) {
            val strokeWidth = 50f
            drawArc(
                color = Color(0xFF4CAF50),
                startAngle = 0f,
                sweepAngle = 160f, // 44.9% * 360
                useCenter = false,
                style = Stroke(width = strokeWidth)
            )
            drawArc(
                color = Color(0xFF2196F3),
                startAngle = 160f,
                sweepAngle = 136f, // 38% * 360
                useCenter = false,
                style = Stroke(width = strokeWidth)
            )
            drawArc(
                color = Color(0xFFFF9800),
                startAngle = 296f,
                sweepAngle = 64f, // a bit more for the rest
                useCenter = false,
                style = Stroke(width = strokeWidth)
            )
        }
        Text("支出", style = MaterialTheme.typography.titleMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun ReportsScreenPreview() {
    ReportsScreen(onNavigateBack = {})
}