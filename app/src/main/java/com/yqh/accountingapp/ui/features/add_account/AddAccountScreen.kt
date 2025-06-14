package com.yqh.accountingapp.ui.features.add_account

// ... import ...
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.PhoneIphone
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. 定义账户模板的数据模型
data class AccountTemplate(
    val name: String,
    val icon: ImageVector,
    val iconColor: Color = Color.Unspecified
)


// 2. 创建一个假的账户模板列表
// 注意：对于支付宝、微信等图标，Material Icons 没有提供，我们暂时用通用图标代替
// 在真实项目中，你需要自己找或制作这些 App 的图标 (SVG 或 PNG格式)
val accountTemplates = listOf(
    AccountTemplate("支付宝", Icons.Default.AccountBalanceWallet, Color(0xFF1677FF)),
    AccountTemplate("微信钱包", Icons.Default.AccountBalanceWallet, Color(0xFF09BB07)),
    AccountTemplate("QQ钱包", Icons.Default.AccountBalanceWallet, Color(0xFFFE767A)),
    AccountTemplate("信用卡", Icons.Default.CreditCard, Color(0xFF0C2E6E)),
    AccountTemplate("银行卡", Icons.Default.CreditCard, Color(0xFFD81E06)),
    AccountTemplate("花呗", Icons.Default.AccountBalanceWallet, Color(0xFF1677FF)),
    AccountTemplate("余额宝", Icons.Default.AccountBalanceWallet, Color(0xFFEB6709)),
    // ... 其他模板
    AccountTemplate("现金钱包", Icons.Default.Money, Color(0xFFFD5A2C))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAccountScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("新增账户") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                }
            )
        }
    ) { paddingValues ->
        // 使用 LazyColumn 创建可滚动的列表
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            items(accountTemplates) { template ->
                AccountTemplateItem(template = template, onClick = {
                    // TODO: 点击后跳转到具体的账户创建页
                })
            }
        }
    }
}

@Composable
fun AccountTemplateItem(template: AccountTemplate, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp)) // 使用带圆角的方形背景
                    .background(template.iconColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = template.icon,
                    contentDescription = template.name,
                    tint = template.iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = template.name, fontSize = 16.sp)
        }
        Divider() // 每个 item 下方的分隔线
    }
}

@Preview(showBackground = true)
@Composable
fun AddAccountScreenPreview() {
    AddAccountScreen(onNavigateBack = {})
}