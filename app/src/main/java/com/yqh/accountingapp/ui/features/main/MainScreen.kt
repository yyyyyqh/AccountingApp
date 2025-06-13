package com.yqh.accountingapp.ui.features.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 这个数据类定义了交易列表“单行”所需的所有信息
data class TransactionItem(
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val color: androidx.compose.ui.graphics.Color,
    val category: String,
    val description: String,
    val amount: String
)

// 创建一个假的交易列表数据
val dummyTransactions = listOf(
    TransactionItem(
        icon = Icons.Filled.ShoppingCart,
        color = Color(0xFF4CAF50), // 绿色
        category = "购物",
        description = "充电模块",
        amount = "¥ 39.96"
    ),
    TransactionItem(
        icon = Icons.Filled.Restaurant, // 我们用 Restaurant 图标代替碗
        color = Color(0xFF2196F3), // 蓝色
        category = "三餐",
        description = "午餐",
        amount = "¥ 12.00"
    ),
    TransactionItem(
        icon = Icons.Filled.ShoppingCart,
        color = Color(0xFF4CAF50), // 绿色
        category = "购物",
        description = "鼠标",
        amount = "¥ 12.90"
    )
)

@Composable
fun SummaryCard() {
    // Card 组件提供了一个带有圆角和阴影的 Material Design 卡片
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), // 卡片距离屏幕边缘的间距
        shape = MaterialTheme.shapes.large, // 使用大一点的圆角
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary // 背景色用主题的主颜色
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // 卡片内部内容的间距
        ) {
            // "六月结余" 和下拉箭头
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "六月结余", color = Color.White)
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "选择月份",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp)) // 垂直间距

            // 结余金额
            Text(
                text = "-¥ 499",
                fontSize = 32.sp, // 更大的字号
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp)) // 更大的垂直间距

            // "月收入" 和 "月支出"
            Row(modifier = Modifier.fillMaxWidth()) {
                // 月收入部分
                SummaryItem(
                    title = "月收入",
                    amount = "¥ 0",
                    icon = Icons.Default.Add,
                    modifier = Modifier.weight(1f) // 占据一半宽度
                )
                // 月支出部分
                SummaryItem(
                    title = "月支出",
                    amount = "¥ 499",
                    icon = Icons.Filled.Remove, // Material Icons 里没有横线，我们用 Remove 代替
                    modifier = Modifier.weight(1f) // 占据另一半宽度
                )
            }
        }
    }
}

@Composable
fun TransactionRow(item: TransactionItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp), // 设置item的内外边距
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 左侧的圆形图标
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(item.color.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = item.icon, contentDescription = item.category, tint = item.color)
        }

        Spacer(modifier = Modifier.width(12.dp))

        // 中间的分类和描述
        Column(modifier = Modifier.weight(1f)) { // weight(1f)让它占据所有剩余空间
            Text(text = item.category, fontWeight = FontWeight.Bold)
            Text(text = item.description, color = Color.Gray, fontSize = 14.sp)
        }

        // 右侧的金额
        Text(text = item.amount, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
    }
}



@Composable
fun SummaryItem(title: String, amount: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        // 圆形图标
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f)), // 半透明白色背景
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = title, tint = Color.White)
        }

        Spacer(modifier = Modifier.width(8.dp)) // 水平间距

        // 文字部分
        Column {
            Text(text = title, color = Color.White, fontSize = 14.sp)
            Text(text = amount, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        }
    }
}


// 为了方便单独预览和调试这个卡片，我们也给它加一个 @Preview
@Preview(showBackground = true)
@Composable
fun SummaryCardPreview() {
    // 假设的主题包裹
    // AccountingAppTheme {
    SummaryCard()
    // }
}

// 同样，为这个小组件创建一个独立的预览
@Preview(showBackground = true)
@Composable
fun TransactionRowPreview() {
    // 把我们的第一条假数据传进去看看效果
    TransactionRow(item = dummyTransactions[0])
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    // Scaffold 是 Material Design 的布局结构。
    // 它为最常见的顶层 UI 组件提供了槽位，如 TopAppBar, FloatingActionButton 等。
    Scaffold(
        topBar = {
            // 这里我们放置顶部的应用栏
            TopAppBar(
                title = { Text("2025 / 06") }, // 暂时用静态文本
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary, // 设置背景为主题的主颜色
                    titleContentColor = Color.White // 设置标题文字为白色
                )
                // 稍后我们会在这里添加汉堡菜单和搜索图标
            )
        },
        floatingActionButton = {
            // 这里我们放置右下角的悬浮操作按钮
            FloatingActionButton(onClick = { /* 暂时什么都不做 */ }) {
                Icon(Icons.Filled.Add, contentDescription = "添加交易")
            }
        }
    ) { innerPadding ->
        // 这是 Scaffold 的主内容区域。
        // innerPadding 包含了顶栏等组件的高度，可以避免我们的内容被遮挡。
        MainContent(paddingValues = innerPadding)
    }
}

@Composable
fun MainContent(paddingValues: PaddingValues) {
    // 将 Box 改为 Column，因为之后我们还想在卡片下面放一个列表
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        // 在这里调用我们刚刚创建的卡片组件
        SummaryCard()

        // TODO: 之后在这里添加交易明细列表
        // 使用 LazyColumn 来创建一个垂直滚动的列表
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            // items 是一个辅助函数，它会遍历你提供的数据列表
            // 并为每一项数据调用你指定的 Composable 函数
            items(items = dummyTransactions) { item ->
                // 现在 lambda 表达式里的 `item` 就是我们想要的 TransactionItem 对象了
                TransactionRow(item = item)
            }
        }
    }
}

// @Preview 注解可以让我们在 Android Studio 中直接预览 Composable 函数的样子
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    // 建议在预览时包裹一层主题，以确保颜色、字体等样式正确
    // MaterialTheme { // 假设你有一个主题设置
    MainScreen()
    // }
}