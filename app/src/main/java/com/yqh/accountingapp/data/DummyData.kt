package com.yqh.accountingapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

// 数据模型
data class TransactionItem(
    val icon: ImageVector,
    val color: Color,
    val category: String,
    val description: String,
    val amount: String
)

// --- 我们将 groupedTransactions作为唯一、最完整的数据源 ---
val groupedTransactions = mapOf(
    "06 / 13 周五" to listOf(
        TransactionItem(Icons.Filled.Restaurant, Color(0xFF2196F3), "三餐", "晚餐", "¥ 23.50"),
        TransactionItem(Icons.Filled.Theaters, Color(0xFFE91E63), "娱乐", "看电影", "¥ 45.00")
    ),
    "06 / 12 周四" to listOf(
        TransactionItem(Icons.Filled.ShoppingCart, Color(0xFF4CAF50), "购物", "充电模块", "¥ 39.96"),
        TransactionItem(Icons.Filled.ShoppingCart, Color(0xFF4CAF50), "购物", "鼠标保护壳", "¥ 12.90"),
        TransactionItem(Icons.Filled.Restaurant, Color(0xFF2196F3), "三餐", "午餐", "¥ 12.00")
    ),
    "06 / 11 周三" to listOf(
        TransactionItem(Icons.Filled.DirectionsBus, Color(0xFFFF9800), "交通", "地铁", "¥ 4.00"),
        TransactionItem(Icons.Filled.Restaurant, Color(0xFF2196F3), "三餐", "早餐", "¥ 8.50")
    ),
    "06 / 10 周二" to listOf(
        TransactionItem(Icons.Filled.Home, Color(0xFF795548), "居家", "水电费", "¥ 150.20")
    )
)

// --- 让 dummyTransactions 从 groupedTransactions 自动生成 ---
// .values 会获取 Map 中所有的值 (也就是所有的 List<TransactionItem>)
// .flatten() 会将这个包含多个列表的集合，合并成一个大的、扁平化的列表
val dummyTransactions = groupedTransactions.values.flatten()