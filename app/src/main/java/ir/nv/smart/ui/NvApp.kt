package ir.nv.smart.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val NvNavy = Color(0xFF061725)
private val NvPanel = Color(0xE60B2740)
private val NvCyan = Color(0xFF1FD1FF)
private val NvGreen = Color(0xFF79F53B)

@Composable
fun NvApp() {
    var query by remember { mutableStateOf("") }
    var selectedRoute by remember { mutableIntStateOf(0) }

    MaterialTheme(colorScheme = darkColorScheme(primary = NvCyan, secondary = NvGreen, background = NvNavy)) {
        Box(
            Modifier.fillMaxSize().background(
                Brush.verticalGradient(listOf(Color(0xFF0A3554), Color(0xFF08243B), NvNavy))
            )
        ) {
            Box(Modifier.fillMaxSize().padding(bottom = 84.dp)) {
                Text(
                    "نقشه زنده NV",
                    color = Color.White.copy(alpha = .55f),
                    fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Column(
                Modifier.fillMaxWidth().statusBarsPadding().padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                SearchBar(query = query, onQueryChange = { query = it })
                Spacer(Modifier.height(12.dp))
                NextTurnCard()
            }

            RoutePanel(
                selectedRoute = selectedRoute,
                onSelectRoute = { selectedRoute = it },
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 12.dp).widthIn(max = 330.dp)
            )

            DrivingHud(
                modifier = Modifier.align(Alignment.BottomStart).navigationBarsPadding().padding(start = 14.dp, bottom = 98.dp)
            )

            BottomNavigation(
                modifier = Modifier.align(Alignment.BottomCenter).navigationBarsPadding().padding(12.dp)
            )
        }
    }
}

@Composable
private fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    Surface(color = NvPanel, shape = RoundedCornerShape(24.dp), tonalElevation = 6.dp) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text("نام، کد مکان یا مقصد را جستجو کنید…") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NvCyan,
                unfocusedBorderColor = Color.White.copy(alpha = .2f),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
    }
}

@Composable
private fun NextTurnCard() {
    Surface(color = Color(0xE60A5B58), shape = RoundedCornerShape(22.dp), modifier = Modifier.widthIn(max = 290.dp)) {
        Row(Modifier.padding(horizontal = 18.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("↱", color = Color.White, fontSize = 42.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(14.dp))
            Column {
                Text("۵۰۰ متر", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("خروجی بعدی", color = Color.White.copy(alpha = .9f), fontSize = 16.sp)
            }
        }
    }
}

@Composable
private fun RoutePanel(selectedRoute: Int, onSelectRoute: (Int) -> Unit, modifier: Modifier = Modifier) {
    Surface(color = NvPanel, shape = RoundedCornerShape(26.dp), modifier = modifier) {
        Column(Modifier.padding(14.dp)) {
            Text("مسیرهای پیشنهادی", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(Modifier.height(8.dp))
            val routes = listOf(
                Triple("۲۸ دقیقه", "۱۴ کیلومتر", "ترافیک سبک"),
                Triple("۳۴ دقیقه", "۱۶ کیلومتر", "ترافیک متوسط"),
                Triple("۴۲ دقیقه", "۱۸ کیلومتر", "ترافیک سنگین")
            )
            routes.forEachIndexed { index, r ->
                val selected = selectedRoute == index
                Surface(
                    onClick = { onSelectRoute(index) },
                    color = if (selected) Color(0xFF0F4566) else Color.Transparent,
                    shape = RoundedCornerShape(18.dp),
                    border = if (selected) BorderStroke(1.dp, NvCyan) else null,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text(if (index == 0) "مسیر پیشنهادی" else "مسیر ${index + 1}", color = if (selected) NvCyan else Color.White.copy(alpha=.75f), fontSize = 13.sp)
                        Text(r.first, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text("${r.second}  •  ${r.third}", color = Color.White.copy(alpha=.75f), fontSize = 13.sp)
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(54.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NvCyan, contentColor = Color(0xFF002437))
            ) { Text("شروع حرکت", fontSize = 18.sp, fontWeight = FontWeight.Bold) }
        }
    }
}

@Composable
private fun DrivingHud(modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Surface(color = Color(0xE6092434), shape = RoundedCornerShape(50)) {
            Column(Modifier.padding(horizontal = 18.dp, vertical = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("۹۲", color = NvGreen, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text("km/h", color = Color.White, fontSize = 12.sp)
            }
        }
        Spacer(Modifier.width(10.dp))
        Surface(color = Color.White, shape = RoundedCornerShape(50)) {
            Text("100", color = Color.Red, modifier = Modifier.padding(14.dp), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun BottomNavigation(modifier: Modifier = Modifier) {
    Surface(color = NvPanel, shape = RoundedCornerShape(26.dp), modifier = modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceAround) {
            listOf("مسیریابی", "جستجو", "علاقه‌مندی", "مسیرها", "آب‌وهوا", "تنظیمات").forEachIndexed { i, title ->
                TextButton(onClick = { }) {
                    Text(title, color = if (i == 0) NvCyan else Color.White.copy(alpha=.8f), fontSize = 12.sp)
                }
            }
        }
    }
}
