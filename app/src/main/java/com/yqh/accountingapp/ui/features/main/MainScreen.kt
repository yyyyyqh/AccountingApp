package com.yqh.accountingapp.ui.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

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
    // Box 是一个简单的布局组件，像一个盒子，可以把子组件叠放在一起。
    // 我们用它来占据整个屏幕，并应用内边距。
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        // 为了看清楚内容区域的位置，我们先放一个居中的文本
        Text(
            text = "这里将是摘要卡片和交易列表",
            modifier = Modifier.align(Alignment.Center)
        )
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