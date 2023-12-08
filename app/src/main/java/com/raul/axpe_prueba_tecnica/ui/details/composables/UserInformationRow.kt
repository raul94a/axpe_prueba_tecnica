package com.raul.axpe_prueba_tecnica.ui.details.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raul.axpe_prueba_tecnica.R
import com.raul.axpe_prueba_tecnica.ui.theme.sfProText


@Composable
fun UserInformationRow(
    contentDescription: String,
    imageVector: ImageVector?,
    painter: Int? = null,
    title: String,
    iconSize: Dp,
    data: String
) {
    assert(imageVector != null || painter != null) {
        "painter or imageVector must be setted"
    }
    val isImageVectorSetted = imageVector != null;
    val datumPadding = 30.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
    ) {

        if (isImageVectorSetted) Icon(
            imageVector = imageVector!!,
            contentDescription = contentDescription,
            Modifier.size(iconSize)
        ) else {
            Icon(
                painter = painterResource(id = painter!!),
                contentDescription = contentDescription,
                Modifier.size(iconSize)
            )
        }
        Spacer(modifier = Modifier.width(datumPadding))
        Column(modifier = Modifier.padding(end = 10.dp)) {
            Text(
                text = title,
                color = colorResource(id = R.color.gray_medium),
                fontFamily = sfProText,
                fontSize = 11.sp, fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = data,
                color = Color.Black,
                fontFamily = sfProText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = colorResource(id = R.color.grey_light))
        }
    }
}