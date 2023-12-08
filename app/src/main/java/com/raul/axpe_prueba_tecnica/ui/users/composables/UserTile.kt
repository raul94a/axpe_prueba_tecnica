package com.raul.axpe_prueba_tecnica.ui.users.composables

import UserData
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage


@Composable
fun UserTile(navController: NavController, user: UserData) {
    key("user_key_${user.email}") {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 5.dp)
                .clickable {
                    navController.navigate("user_details/${user.email}")
                }
        ) {
            Spacer(modifier = Modifier.width(10.dp))

            AsyncImage(

                modifier = Modifier
                    .width(60.dp)
                    .height(height = 60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                model = user.picture.large,
                transform = {
                    it
                },
                contentDescription = "Picture of ${user.name}",

                )

            Spacer(modifier = Modifier.width(20.dp))
            UserNameAndEmailColumn(user)


        }
    }
}