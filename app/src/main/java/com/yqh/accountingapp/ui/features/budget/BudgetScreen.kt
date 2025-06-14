package com.yqh.accountingapp.ui.features.budget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp


// --- 新增的数据模型和假数据 ---

// 用于顶部“本月剩余”、“剩余日均”、“剩余天数”的数据模型
data class BudgetOverallSummary(
    val remainingAmount: String,
    val dailyAverage: String,
    val daysLeft: String
)

// 用于列表中每一项预算的数据模型
data class BudgetItem(
    val category: String,
    val icon: ImageVector,
    val color: Color,
    val remainingAmount: String,
    val budgetAmount: String,
    val progress: Float // 进度，从 0.0 到 1.0
)

// 示例数据
val sampleBudgetSummary = BudgetOverallSummary(
    remainingAmount = "¥ 2000",
    dailyAverage = "¥ 117.65",
    daysLeft = "17 天"
)

val sampleBudgetItems = listOf(
    BudgetItem("月预算", Icons.Default.AccountBalance, Color.Gray, "¥ 2000", "¥ 5000", 0.6f),
    BudgetItem("三餐", Icons.Default.Fastfood, Color(0xFF2196F3), "¥ 2276", "¥ 2797", 0.18f),
    BudgetItem("购物", Icons.Default.ShoppingCart, Color(0xFF4CAF50), "¥ 2385", "¥ 2476", 0.04f),
    BudgetItem("住房", Icons.Default.Home, Color(0xFF795548), "¥ 2091", "¥ 2328", 0.1f),
    BudgetItem("交通", Icons.Default.DirectionsBus, Color(0xFFFF9800), "¥ 1650", "¥ 2287", 0.28f)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(onNavigateBack: () -> Unit) {
    // 👇 1. 创建一个状态来控制显示哪个视图
    var showSampleData by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            // 👇 补上这部分
            TopAppBar(
                title = { Text("2025 / 06") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                // 👇 2. 按钮的点击事件现在用来切换状态
                onClick = { showSampleData = !showSampleData },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                // 👇 3. 按钮的文字也根据状态变化
                Text(
                    text = if (showSampleData) "隐藏示例数据" else "查看示例数据",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // 👇 4. 根据状态，决定显示哪个内容
            if (showSampleData) {
                BudgetListContent() // 有数据时的视图
            } else {
                BudgetEmptyContent() // 空状态时的视图
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BudgetScreenEmptyPreview() {
    BudgetScreen(onNavigateBack = {})
}

// 1. 顶部的三栏汇总信息
@Composable
fun BudgetSummaryRow(summary: BudgetOverallSummary) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "本月剩余", style = MaterialTheme.typography.bodyMedium)
            Text(text = summary.remainingAmount, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "剩余日均", style = MaterialTheme.typography.bodyMedium)
            Text(text = summary.dailyAverage, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "剩余天数", style = MaterialTheme.typography.bodyMedium)
            Text(text = summary.daysLeft, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        }
    }
}

// 2. 列表中带进度条的单行预算
@Composable
fun BudgetItemRow(item: BudgetItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = item.icon, contentDescription = item.category, tint = item.color, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = item.category, style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))
            // 进度条和文字
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "剩余: ${item.remainingAmount}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "预算: ${item.budgetAmount}", style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { item.progress },
                    modifier = Modifier.fillMaxWidth(),
                    trackColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                    color = item.color
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BudgetListContentPreview() {
    BudgetListContent()
}

// 3. 将所有组件组合成完整的“有数据”的列表内容
@Composable
fun BudgetListContent() {
    Column {
        BudgetSummaryRow(summary = sampleBudgetSummary)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            // 告诉 LazyColumn 列表的总长度
            items(count = sampleBudgetItems.size) { index ->
                // 在循环体内部，用索引从列表中获取当前项
                val item = sampleBudgetItems[index]
                BudgetItemRow(item = item)
            }
        }
    }
}



// 👇 我们把之前的空状态 UI 也封装成一个独立的函数，让代码更清晰
@Composable
fun BudgetEmptyContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Chat,
            contentDescription = "空状态",
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colorScheme.surfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { /* TODO */ },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
        ) {
            Text("添加月预算 >>", modifier = Modifier.padding(vertical = 8.dp), fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { /* TODO */ }) {
            Text("添加分类预算 >>", fontSize = 14.sp)
        }
    }
}