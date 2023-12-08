package com.raul.axpe_prueba_tecnica.ui.details.composables

import UserData
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.raul.axpe_prueba_tecnica.R
import com.raul.axpe_prueba_tecnica.ui.theme.oswald

@Composable
fun DetailsHeader(navController: NavController, user: UserData){
    val imageHeight = 180.dp
    val avatarWidth = 75.dp
    val outerCircleExtraWidth = 5.dp
    val boxHeight = imageHeight + (avatarWidth / 2)
    val avatarTopPadding = imageHeight - ((avatarWidth + outerCircleExtraWidth * 2) / 2)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(boxHeight),
        contentAlignment = Alignment.TopStart
    )
    {
        Row(
            modifier = Modifier
                .zIndex(2f)
                .padding(top = 30.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                modifier = Modifier
                    .zIndex(2f),
                onClick = {
                    val route = navController.currentBackStackEntry!!.destination.route
                    if(route != "user_details/{email}"){
                        return@IconButton
                    }
                    navController.popBackStack() }) {
                Icon(

                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Arrow Back",
                    tint = colorResource(
                        id = R.color.white
                    )
                )
            }
            Text(
                modifier = Modifier.zIndex(1f),
                text = user.getFullName(),
                color = Color.White,
                fontFamily = oswald,
                fontSize = 21.sp
            )
        }

        Image(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(imageHeight),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.details_header),
            contentDescription = "details header"
        )
        Box(
            modifier = Modifier
                .zIndex(3f)
                .padding(top = avatarTopPadding, start = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White, CircleShape)
                    .width(avatarWidth + outerCircleExtraWidth)
                    .height(avatarWidth + outerCircleExtraWidth)
            )
            AsyncImage(

                modifier = androidx.compose.ui.Modifier
                    .width(avatarWidth)
                    .height(avatarWidth)

                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                model = user.picture.large,
                transform = {
                    it
                },
                contentDescription = "Picture of ${user.name}",

                )
        }
        Box(
            modifier = Modifier
                .zIndex(3f)
                .padding(top = boxHeight - avatarWidth / 2)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Row {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(R.drawable.camera),
                        contentDescription = "edit"
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.Edit, contentDescription = "edit")
                }
            }
        }
    }
}