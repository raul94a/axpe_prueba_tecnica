package com.raul.axpe_prueba_tecnica.ui.users.composables

import UserData
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raul.axpe_prueba_tecnica.R
import com.raul.axpe_prueba_tecnica.ui.theme.sfProText


@Composable
fun UserNameAndEmailColumn(user: UserData) {
    Column(
        verticalArrangement = Arrangement.Center,

        modifier = Modifier
            .padding(top = 5.dp)


    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = user.getFullName(),
                    fontSize = 18.sp,
                    fontFamily = sfProText,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    user.email,
                    fontFamily = sfProText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.gray_medium)
                )

            }

            Icon(
                modifier = Modifier.padding(end = 10.dp),
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = "navigate_button",
                tint = colorResource(
                    id = R.color.grey_light
                )
            )

        }

        Spacer(modifier = Modifier.height(20.dp))

        Divider(color = colorResource(id = R.color.grey_light))

    }
}