//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.Email
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.KeyboardArrowRight
//import androidx.compose.material.icons.filled.Lock
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.TabRow
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SettingsScreen() {
//    val tabs = listOf("Account", "Social", "Devices")
//    var selectedTab by remember { mutableIntStateOf(0) }
//
//    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
//        TopAppBar(
//            title = { Text(text = "Settings") },
//            navigationIcon = {
//                IconButton(onClick = { /* handle back */ }) {
//                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//                }
//            },
//            backgroundColor = Color.White,
//            elevation = 0.dp
//        )
//
//        ProfileSection()
//
//        TabRow(selectedTabIndex = selectedTab) {
//            tabs.forEachIndexed { index, title ->
//                Tab(
//                    selected = selectedTab == index,
//                    onClick = { selectedTab = index },
//                    text = { Text(title) }
//                )
//            }
//        }
//
//        if (selectedTab == 0) {
//            AccountSettings()
//        } else {
//            // Placeholder for Social or Devices tab
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                Text("Coming Soon...")
//            }
//        }
//
//        BottomNavigationBar()
//    }
//}
//
//@Composable
//fun ProfileSection() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.placeholder), // Replace with actual image
//            contentDescription = "Profile",
//            modifier = Modifier
//                .size(64.dp)
//                .clip(CircleShape)
//                .background(Color.Gray)
//        )
//        Spacer(modifier = Modifier.width(12.dp))
//        Column {
//            Text("Thomas edison", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//            Text("thomas_e=mc²", fontSize = 14.sp, color = Color.Gray)
//        }
//    }
//}
//
//@Composable
//fun AccountSettings() {
//    val settings = listOf(
//        "Email" to Icons.Default.Email,
//        "Notification" to Icons.Default.Notifications,
//        "Privacy" to Icons.Default.Lock,
//        "Security" to Icons.Default.Settings,
//        "Display mode" to Icons.Default.Settings,
//        "Text size" to Icons.Default.Settings,
//        "Language" to Icons.Default.Settings,
//        "Terms of services" to Icons.Default.Settings
//    )
//
//    LazyColumn(modifier = Modifier.fillMaxWidth()) {
//        items(settings) { (title, icon) ->
//            SettingItem(title, icon)
//        }
//    }
//}
//
//@Composable
//fun SettingItem(title: String, icon: ImageVector) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { /* Navigate */ }
//            .padding(horizontal = 16.dp, vertical = 12.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(icon, contentDescription = title, tint = Color(0xFF007AFF), modifier = Modifier.size(24.dp))
//        Spacer(modifier = Modifier.width(16.dp))
//        Text(title, modifier = Modifier.weight(1f))
//        Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Arrow")
//    }
//}
