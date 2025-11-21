package com.example.myapplication.View.Widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

object UiWidgets {

    @Composable
    public fun Title(title_text: String){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(

                text = title_text,
                fontSize = stringResource(id=R.string.size_of_title_text).toInt().sp,
                fontWeight = FontWeight.Bold

            )
        }
        Spacer(modifier = Modifier.height(10.dp))

    }

    @Composable
    public fun CardTitle(title_text: String){

        Text (
            modifier = Modifier,
            text = title_text,
            fontSize = stringResource(id=R.string.size_of_card_title_text).toInt().sp,
            fontWeight = FontWeight.Bold
        )

        // Разделитель
        Spacer(modifier = Modifier.height(10.dp))

    }

    @Composable
    public fun CardIcon(id_of_current_icon: Int){
        Image(
            painterResource(id_of_current_icon),
            contentDescription = "Статус",
            modifier = Modifier
                .size(100.dp)
        )
    }


    @Composable
    public fun CardBodyText(
        main_text: String,
        annotation: String,
        symbol: String = ""){
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "$annotation: $main_text $symbol",
            fontSize = stringResource(id=R.string.size_of_card_body_text).toInt().sp,

            )
        Spacer(modifier = Modifier.height(5.dp))

    }


}