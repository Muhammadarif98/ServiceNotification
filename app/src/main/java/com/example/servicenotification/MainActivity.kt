package com.example.servicenotification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.servicenotification.ui.theme.ServiceNotificationTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServiceNotificationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        notif()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        setContent {
//            ServiceNotificationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    notif()
//                }
//            }
        }
    }

}

//@Composable
//fun Main() {
//    val context = LocalContext.current
////    Button(onClick = {
////        val intent = Intent(context, MyService::class.java)
////        context.startService(intent)
////    }) {
////        Text("Нажмите на кнопку")
////    }
//}


//@Composable
//fun notif() {
//    val context = LocalContext.current
//    val intent = Intent(context, MyService::class.java).apply {
//        putExtra("titleKey", "")
//        putExtra("descriptionKey", "")
//    }
//    val pendingIntent = PendingIntent.getBroadcast(
//        context,
//        "titleKey".hashCode(),
//        intent,
//        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//    )
//    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//    val calendar = Calendar.getInstance()
//    Button(onClick = {
//        calendar.add(Calendar.MINUTE, + 10)
//        context.startService(intent)
//
//        alarmManager.setExact(
//            AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
//            pendingIntent
//        )
//    }) {
//        Text("Нажмите на кнопку")
//    }
//}
@Composable
fun notif() {
    val context = LocalContext.current
    val intent = Intent(context, MyBroadcastReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.SECOND, + 60 * 2)
    Button(onClick = {
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }) {
        Text("Нажмите на кнопку")
    }
}

//@Composable
//fun notif() {
//    val context = LocalContext.current
//    val intent = Intent(context, MyService::class.java).apply {
//        putExtra("titleKey", "")
//        putExtra("descriptionKey", "")
//    }
//    val pendingIntent = PendingIntent.getBroadcast(
//        context,
//        "titleKey".hashCode(),
//        intent,
//        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//    )
//    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//    val calendar = Calendar.getInstance()
//    val futureInMillis = SystemClock.elapsedRealtime() + 5 * 60 * 1000
//    Button(onClick = {
//            calendar.add(Calendar.SECOND, + 30)
//            context.startService(intent)
//            alarmManager.setExact(
//                    AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
//            pendingIntent
//            )
//
//    }) {
//        Text("Нажмите на кнопку")
//    }
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ServiceNotificationTheme {
        notif()
    }
}