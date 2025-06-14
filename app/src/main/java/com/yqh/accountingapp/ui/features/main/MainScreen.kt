package com.yqh.accountingapp.ui.features.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Theaters
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

import androidx.compose.foundation.clickable // 👈 新增 import
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.* // 👈 新增 import
import androidx.compose.material3.DropdownMenu // 👈 新增 import
import androidx.compose.material3.DropdownMenuItem // 👈 新增 import
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Switch
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.yqh.accountingapp.R
import kotlinx.coroutines.launch

import com.yqh.accountingapp.data.TransactionItem
import com.yqh.accountingapp.data.groupedTransactions
import com.yqh.accountingapp.data.dummyTransactions


//// 这个数据类定义了交易列表“单行”所需的所有信息
//data class TransactionItem(
//    val icon: androidx.compose.ui.graphics.vector.ImageVector,
//    val color: androidx.compose.ui.graphics.Color,
//    val category: String,
//    val description: String,
//    val amount: String
//)

//// 创建一个假的交易列表数据
//val dummyTransactions = listOf(
//    TransactionItem(
//        icon = Icons.Filled.ShoppingCart,
//        color = Color(0xFF4CAF50), // 绿色
//        category = "购物",
//        description = "充电模块",
//        amount = "¥ 39.96"
//    ),
//    TransactionItem(
//        icon = Icons.Filled.Restaurant, // 我们用 Restaurant 图标代替碗
//        color = Color(0xFF2196F3), // 蓝色
//        category = "三餐",
//        description = "午餐",
//        amount = "¥ 12.00"
//    ),
//    TransactionItem(
//        icon = Icons.Filled.ShoppingCart,
//        color = Color(0xFF4CAF50), // 绿色
//        category = "购物",
//        description = "鼠标",
//        amount = "¥ 12.90"
//    )
//)

//private val groupedTransactions = mapOf(
//    "06 / 13 周五" to listOf(
//        TransactionItem(
//            icon = Icons.Filled.Restaurant,
//            color = Color(0xFF2196F3), // 蓝色
//            category = "三餐",
//            description = "午餐",
//            amount = "¥ 12.00"
//        ),
//        TransactionItem(
//            icon = Icons.Filled.Restaurant,
//            color = Color(0xFF2196F3),
//            category = "三餐",
//            description = "晚餐",
//            amount = "¥ 23.50"
//        )
//    ),
//    "06 / 12 周四" to listOf(
//        TransactionItem(
//            icon = Icons.Filled.ShoppingCart,
//            color = Color(0xFF4CAF50), // 绿色
//            category = "购物",
//            description = "充电模块",
//            amount = "¥ 39.96"
//        ),
//        TransactionItem(
//            icon = Icons.Filled.ShoppingCart,
//            color = Color(0xFF4CAF50),
//            category = "购物",
//            description = "鼠标保护壳",
//            amount = "¥ 12.90"
//        )
//    ),
//    // --- 以下是新添加的内容 ---
//    "06 / 11 周三" to listOf(
//        TransactionItem(
//            icon = Icons.Filled.DirectionsBus, // 用公交车图标代表交通
//            color = Color(0xFFFF9800), // 橙色
//            category = "交通",
//            description = "地铁",
//            amount = "¥ 4.00"
//        ),
//        TransactionItem(
//            icon = Icons.Filled.Theaters, // 用影院图标代表娱乐
//            color = Color(0xFFE91E63), // 粉色
//            category = "娱乐",
//            description = "电影票",
//            amount = "¥ 45.00"
//        ),
//        TransactionItem(
//            icon = Icons.Filled.Restaurant,
//            color = Color(0xFF2196F3),
//            category = "三餐",
//            description = "早餐",
//            amount = "¥ 8.50"
//        )
//    ),
//    "06 / 10 周二" to listOf(
//        TransactionItem(
//            icon = Icons.Filled.Home, // 用房子图标代表居家
//            color = Color(0xFF795548), // 棕色
//            category = "居家",
//            description = "水电费",
//            amount = "¥ 150.20"
//        )
//    )
//)

@Composable
fun SummaryCard(balance: String, income: String, expense: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            // 👇 这里是我们修正后的颜色，直接指定了一个蓝色
            containerColor = Color(0xFF2196F3)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        // 1. 添加状态来控制菜单的展开/收起和当前选中的标题
        var menuExpanded by remember { mutableStateOf(false) }
        var selectedTitle by remember { mutableStateOf("六月结余") }

        // 准备好我们的菜单项列表
        val menuItems = listOf(
            "总结余 (总收入-总支出)",
            "当日结余",
            "当月结余",
            "当年结余",
            "净资产",
            "总资产",
            "总负债"
        )

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // 2. 使用 Box 包裹触发器，以便正确定位菜单
            Box {
                // "六月结余" 和下拉箭头，现在是菜单的触发器
                Row(
                    modifier = Modifier.clickable { menuExpanded = true }, // 3. 让 Row 可点击，用来展开菜单
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = selectedTitle, color = Color.White) // 使用 state 来显示标题
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "选择结余类型",
                        tint = Color.White
                    )
                }

                // 4. 添加 DropdownMenu 组件
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false } // 当点击菜单外部时，收起菜单
                ) {
                    // 5. 遍历菜单项并创建 DropdownMenuItem
                    menuItems.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                // 6. 在 onClick 中更新状态并关闭菜单
                                selectedTitle = item
                                menuExpanded = false
                                // TODO: 未来在这里添加根据选择更新下方金额的逻辑
                            }
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(8.dp))

            // 结余金额 (暂时还是静态的)
            Text(
                text = balance,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            // ... 月收入和月支出的部分不变 ...
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                SummaryItem(
                    title = "月收入",
                    amount = income,
                    icon = Icons.Default.Add,
                    modifier = Modifier.weight(1f)
                )
                SummaryItem(
                    title = "月支出",
                    amount = expense,
                    icon = Icons.Filled.Remove,
                    modifier = Modifier.weight(1f)
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
    SummaryCard(
        balance = "-¥ 499.00",
        income = "¥ 0.00",
        expense = "¥ 499.00"
    )
    // }
}

// 同样，为这个小组件创建一个独立的预览
@Preview(showBackground = true)
@Composable
fun TransactionRowPreview() {
    // 把我们的第一条假数据传进去看看效果
    TransactionRow(item = dummyTransactions[0])
}


// --- UI 组件 ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNavigateToAddTransaction: () -> Unit,
    onNavigateToAccounts: () -> Unit, // <-- 新增这个参数
    onNavigateToReports: () -> Unit
) {
    // 1. 创建并记住抽屉的状态 (打开/关闭)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    // 2. 创建一个协程作用域，用于异步打开抽屉
    val scope = rememberCoroutineScope()

    // 3. 使用 ModalNavigationDrawer 作为最外层容器
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // 4. 将我们创建的 AppDrawer 作为抽屉的内容
            AppDrawer(onNavigateToAccounts = onNavigateToAccounts, onNavigateToReports=onNavigateToReports)
        }
    ) {
        // ModalNavigationDrawer 的主内容区域，就是我们之前的 Scaffold
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("2025 / 06") },
                    // 5. 添加汉堡菜单按钮作为导航图标
                    navigationIcon = {
                        IconButton(onClick = {
                            // 点击时，使用协程打开抽屉
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "打开菜单")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    ),
                    // 👇 右侧的“更多”图标
                    actions = {
                        IconButton(onClick = { /* TODO */ }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "更多选项", tint = Color.White)
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = onNavigateToAddTransaction) {
                    Icon(Icons.Filled.Add, contentDescription = "添加交易")
                }
            }
        ) { innerPadding ->
            MainContent(paddingValues = innerPadding)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(paddingValues: PaddingValues) {
    // 将 Box 改为 Column，因为之后我们还想在卡片下面放一个列表
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        // 在这里调用我们刚刚创建的卡片组件
        SummaryCard(
            balance = "-¥ 499.00",
            income = "¥ 0.00",
            expense = "¥ 499.00"
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            // 1. Iterate through each entry (date and list of transactions) in your map.
            groupedTransactions.forEach { (date, transactionsOnThatDay) ->

                // 2. For each date, create a "sticky header".
                // This header will stick to the top as you scroll through its items.
                stickyHeader {
                    Text(
                        text = date,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background) // Give it a background to cover items scrolling under it
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }

                // 3. For the list of transactions associated with that date, create the item rows.
                items(items = transactionsOnThatDay) { transaction ->
                    TransactionRow(item = transaction)
                }
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
    MainScreen(
        onNavigateToAddTransaction = {},
        onNavigateToAccounts = {},
        onNavigateToReports = {}
    )
    // }
}

@Composable
fun AppDrawer(
    onNavigateToAccounts: () -> Unit, // <-- 新增这个参数
    onNavigateToReports: () -> Unit // 👈 新增参数
) {
    ModalDrawerSheet {
        // 抽屉顶部的 Header 部分
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.drawer_header_background), // 假设你有一张背景图
                contentDescription = "抽屉背景",
                contentScale = ContentScale.Crop, // 裁剪图片以填充
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = "登录账号",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }

        Spacer(Modifier.height(12.dp))

        // --- 菜单项 ---
        // 为了演示，我们只创建几个关键的
        NavigationDrawerItem(
            label = { Text("账本") },
            selected = false,
            onClick = { /* TODO: Close drawer or navigate to main */ },
            icon = { Icon(Icons.Default.Book, contentDescription = "账本") }, // 假设有 Book 图标
            badge = { Text("日常") } // 右侧的文字
        )
        NavigationDrawerItem(
            label = { Text("账户") },
            selected = false,
            onClick = onNavigateToAccounts,
            icon = { Icon(Icons.Default.AccountBalanceWallet, contentDescription = "账户") }
        )
        NavigationDrawerItem(
            label = { Text("报表") },
            selected = false,
            onClick = onNavigateToReports,
            icon = { Icon(Icons.Default.BarChart, contentDescription = "报表") }
        )

        Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))

        // --- 带开关的菜单项 ---
        var isNightMode by remember { mutableStateOf(false) }
        NavigationDrawerItem(
            label = { Text("夜间") },
            selected = false,
            onClick = { isNightMode = !isNightMode },
            icon = { Icon(Icons.Default.NightsStay, contentDescription = "夜间模式") },
            badge = { // 在右侧的 badge 位置放一个 Switch 开关
                Switch(
                    checked = isNightMode,
                    onCheckedChange = { isNightMode = it }
                )
            }
        )
        NavigationDrawerItem(
            label = { Text("设置") },
            selected = false,
            onClick = { /* TODO */ },
            icon = { Icon(Icons.Default.Settings, contentDescription = "设置") }
        )
    }
}