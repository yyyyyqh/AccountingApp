package com.yqh.accountingapp.ui.features.budget

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
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
            // 页脚的蓝色按钮
            Button(
                onClick = { /* TODO: 切换到示例数据视图 */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("查看示例数据", modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    ) { paddingValues ->
        // 空状态的主内容
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // 让内容垂直居中
        ) {
            // 中间的占位符图标
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Chat,
                contentDescription = "空状态",
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.surfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 添加月预算按钮
            Button(
                onClick = { /* TODO */ },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    "添加月预算 >>",
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 添加分类预算按钮
            TextButton(onClick = { /* TODO */ }) {
                Text("添加分类预算 >>", fontSize = 14.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BudgetScreenEmptyPreview() {
    BudgetScreen(onNavigateBack = {})
}