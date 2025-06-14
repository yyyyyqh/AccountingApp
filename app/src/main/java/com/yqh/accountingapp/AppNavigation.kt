// 文件路径: com/yqh/accountingapp/AppNavigation.kt
package com.yqh.accountingapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yqh.accountingapp.ui.features.accounts.AccountsScreen
import com.yqh.accountingapp.ui.features.add_account.AddAccountScreen
import com.yqh.accountingapp.ui.features.add_transaction.AddTransactionScreen
import com.yqh.accountingapp.ui.features.main.MainScreen
import com.yqh.accountingapp.ui.features.reports.ReportsScreen

@Composable
fun AppNavigation() {
    // 创建一个导航控制器，它负责所有导航操作（如跳转、返回）
    val navController = rememberNavController()

    // NavHost 是一个容器，它会根据当前路由，显示对应的 Composable 屏幕
    NavHost(navController = navController, startDestination = "main") {

        // 定义“主页”路由
        composable("main") {
            MainScreen(
                onNavigateToAddTransaction = {
                    // 👇 2. 在跳转前，先打印一条日志
                    Log.d("NavigationCheck", "FAB clicked, navigating to add_transaction")
                    // 当需要跳转时，调用 navigate 函数
                    navController.navigate("add_transaction")
                },
                onNavigateToAccounts = { // <-- 添加这个缺失的参数
                    navController.navigate("accounts")
                },
                onNavigateToReports = {
                    navController.navigate("reports")
                } // 👈 传递事件

            )
        }

        // 定义“添加交易”页面的路由
        composable("add_transaction") {
            // TODO: 在这里放置我们即将创建的 AddTransactionScreen
            AddTransactionScreen(
                onNavigateBack = {
                    // popBackStack() 是官方的返回上一页的方法
                    navController.popBackStack()
                }
            )
        }

        // 👇 新增：账户页路由
        composable("accounts") {
            AccountsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAddAccount = { navController.navigate("add_account") }
            )
        }

        // 👇 新增：新增账户页路由
        composable("add_account") {
            AddAccountScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // 👇 新增报表页的路由
        composable("reports") {
            ReportsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

