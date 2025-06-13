// 文件路径: com/yqh/accountingapp/ui/features/add_transaction/AddTransactionScreen.kt
package com.yqh.accountingapp.ui.features.add_transaction

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. 定义分类的数据模型
data class Category(
    val name: String,
    val icon: ImageVector,
    val color: Color
)

// 2. 创建一个假的分类列表
val dummyCategories = listOf(
    Category("三餐", Icons.Default.Fastfood, Color(0xFF2196F3)),
    Category("购物", Icons.Default.ShoppingCart, Color(0xFF4CAF50)),
    Category("住房", Icons.Default.Home, Color(0xFF795548)),
    Category("交通", Icons.Default.DirectionsBus, Color(0xFFFF9800)),
    Category("水果", Icons.Default.LocalBar, Color(0xFFCDDC39)),
    Category("通信", Icons.Default.PhoneAndroid, Color(0xFF9C27B0)),
    Category("水电", Icons.Default.Receipt, Color(0xFF00BCD4)),
    Category("理发", Icons.Default.ContentCut, Color(0xFF607D8B)), // 假设有 ContentCut 图标
    Category("娱乐", Icons.Default.Movie, Color(0xFFE91E63)),
    Category("娱乐", Icons.Default.Movie, Color(0xFFE91E63)),
    Category("娱乐", Icons.Default.Movie, Color(0xFFE91E63)),
    Category("娱乐", Icons.Default.Movie, Color(0xFFE91E63)),
    Category("娱乐", Icons.Default.Movie, Color(0xFFE91E63)),
    Category("娱乐", Icons.Default.Movie, Color(0xFFE91E63)),
)

// 👇 1. 将分类列表切分成多个子列表，每个子列表包含10个分类，代表一页
val paginatedCategories = dummyCategories.chunked(10)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    onNavigateBack: () -> Unit
) {
    // 为输入金额和选择分类创建状态
    var amount by remember { mutableStateOf("0") }
    var selectedCategory by remember { mutableStateOf(dummyCategories.first()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* 暂时留空，之后做收入/支出切换 */ },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "返回")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // --- 金额和分类显示行 ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 显示选中分类的图标
                Icon(
                    imageVector = selectedCategory.icon,
                    contentDescription = selectedCategory.name,
                    modifier = Modifier.size(40.dp),
                    tint = selectedCategory.color
                )
                Spacer(modifier = Modifier.width(16.dp))
                // 显示选中分类的名称
                Text(text = selectedCategory.name, fontSize = 20.sp)
                // 金额显示
                Text(
                    text = "¥ $amount",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End
                )
            }

            Divider() // 分隔线

            // --- 分类选择器 ---
            // 👇 把分页后的数据传进去
            CategorySelector(
                paginatedCategories = paginatedCategories,
                selectedCategory = selectedCategory,
                onCategorySelected = { category ->
                    selectedCategory = category
                }
            )

            // TODO: 在这里添加备注/图片、数字键盘和保存按钮
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddTransactionScreenPreview() {
    AddTransactionScreen(onNavigateBack = {})
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class) // 👈 2. 添加 Pager 的 OptIn
@Composable
fun CategorySelector(
    // 注意：这里我们接收的是分页后的数据
    paginatedCategories: List<List<Category>>,
    selectedCategory: Category,
    onCategorySelected: (Category) -> Unit
) {
    // 3. 创建并记住 Pager 的状态，它能告诉我们当前在哪一页
    val pagerState = rememberPagerState(pageCount = { paginatedCategories.size })

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // 4. 使用 HorizontalPager 组件
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            // Pager 的每一页内容
            // 我们在每一页内部使用 FlowRow 来将10个图标排列成两行
            FlowRow(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                maxItemsInEachRow = 5
            ) {
                // 获取当前页的分类数据
                paginatedCategories[page].forEach { category ->
                    CategoryIcon(
                        category = category,
                        isSelected = category == selectedCategory,
                        onClick = { onCategorySelected(category) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 5. 添加下方的页面指示器 (小圆点)
        Row(
            Modifier.height(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}

@Composable
fun CategoryIcon(
    category: Category,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                // 👇 根据是否被选中，显示不同的背景色
                .background(if (isSelected) category.color else Color.LightGray.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = category.name,
                // 👇 根据是否被选中，显示不同的图标颜色
                tint = if (isSelected) Color.White else category.color,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = category.name, fontSize = 12.sp)
    }
}