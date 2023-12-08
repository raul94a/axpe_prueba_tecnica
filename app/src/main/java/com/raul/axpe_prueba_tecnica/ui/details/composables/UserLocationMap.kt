package com.raul.axpe_prueba_tecnica.ui.details.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raul.axpe_prueba_tecnica.R


@Composable
fun UserLocationMap(mapPadding: Dp) {
    // 30 de datumPaddin, 25 del Icon y 20 del start de UserInformationRow
    Column(
        modifier = Modifier
            .padding(start = mapPadding, top = 10.dp, bottom = 5.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Dirección",
            fontSize = 11.sp,
            color = colorResource(id = R.color.gray_medium),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Image(
            modifier = Modifier.padding(end = 10.dp, bottom = 10.dp).fillMaxWidth(),
            painter = painterResource(id = R.drawable.maps),
            contentDescription = "maps",
            contentScale = ContentScale.FillWidth
        )
    }

}
