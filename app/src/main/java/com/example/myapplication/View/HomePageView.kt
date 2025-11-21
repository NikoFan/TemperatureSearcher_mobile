package com.example.myapplication.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.Model.Card
import com.example.myapplication.View.ui.theme.MyApplicationTheme
import com.example.myapplication.ViewModel.HomePageVM
import com.example.myapplication.View.Widgets.UiWidgets
import com.example.myapplication.R
import kotlinx.coroutines.delay

class HomePageView : ComponentActivity() {
    private val view_model: HomePageVM = HomePageVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view_model.generate_card()


        enableEdgeToEdge()
        setContent {
            UiConstructor()
        }
    }

    @Composable
    private fun UiConstructor() {
        // Индекс карточки
        var index_of_card by remember { mutableStateOf(0) }

        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(9.dp),

        ) {
            UiWidgets.Title("Погода в городах")

            DisplayCard(
                view_model = view_model,
                index_of_current_card = index_of_card
            )

            NextCardButton(
                count_of_cities = view_model.take_cards_count(),
                current_index = index_of_card,
                index_increase = {
                    state_index->
                    index_of_card = state_index
                })
        }
    }
}

@Composable
fun DisplayCard(
    view_model: HomePageVM,
    index_of_current_card: Int
){
    // Получаем card БЕЗОПАСНО
    val card = view_model.getCardSafe(index_of_current_card)

    // Если данных ещё нет — показываем загрузку
    if (card == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
            // или Text("Загрузка погоды...")
        }
        return
    }

    val card_icon: Int = when (card.card_temperature.toInt()) {
        in -100..0 -> R.drawable.snow
        in 1..14 -> R.drawable.rain
        in 15..100 -> R.drawable.sun
        else -> R.drawable.sun
    }

    val border_color: Color = when (card.card_temperature.toInt()) {
        in -100..0 -> Color.Blue
        in 1..14 -> Color.DarkGray
        in 15..100 -> Color.Yellow
        else -> Color.Magenta
    }

    // Карточки погоды
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = 10.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
            .fillMaxHeight(0.9f)
            .border(width = 5.dp, color = border_color, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth(),

        contentAlignment = Alignment.Center,


    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UiWidgets.CardTitle(title_text = card.card_city_name)
            UiWidgets.CardIcon(id_of_current_icon = card_icon)
            UiWidgets.CardBodyText(
                main_text = card.card_temperature.toString(),
                annotation = "Температура",
                symbol = "°C"
            )
            UiWidgets.CardBodyText(
                main_text = card.card_feels_like.toString(),
                annotation = "Ощущается как",
                symbol = "°C"
            )
            UiWidgets.CardBodyText(
                main_text = card.card_wind_speed.toString(),
                annotation = "Скорость ветра",
                symbol = "м/с"
            )

        }

    }


}

@Composable
fun NextCardButton(
    count_of_cities: Int,
    current_index: Int,
    index_increase: (Int) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        IconButton(
            onClick = {
                if (current_index - 1 >= 0 ) {
                    index_increase(current_index - 1)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Назад"
            )
        }
        Spacer(modifier = Modifier.width(8.dp)) // Отступ между кнопками
        IconButton(
            onClick = {
                if (current_index + 1 < count_of_cities) {
                    index_increase(current_index + 1)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Вперёд"
            )
        }


    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        UiComposable()
    }
}