package com.raul.axpe_prueba_tecnica.ui.details

import UserData
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.raul.axpe_prueba_tecnica.R
import com.raul.axpe_prueba_tecnica.ui.details.composables.DetailsHeader
import com.raul.axpe_prueba_tecnica.ui.details.composables.UserInformationRow
import com.raul.axpe_prueba_tecnica.ui.details.composables.UserLocationMap
import com.raul.axpe_prueba_tecnica.ui.theme.oswald
import com.raul.axpe_prueba_tecnica.ui.theme.sfProText


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomUserDetails(user: UserData, navController: NavController) {
    val datumPadding = 30.dp
    val iconSize = 25.dp
    val mapPadding = datumPadding + iconSize + 20.dp
    Scaffold { values ->
        Column(
            modifier = Modifier
                .padding(values)
                .verticalScroll(rememberScrollState())
        ) {

            DetailsHeader(navController = navController, user = user)

            UserInformationRow(
                contentDescription = "user_name",
                imageVector = Icons.Outlined.AccountCircle,
                title = "Nombre y apellidos",
                iconSize = iconSize,

                data = user.getFullName()
            )
            UserInformationRow(
                contentDescription = "Email",
                imageVector = Icons.Outlined.Email,
                title = "Email",
                iconSize = iconSize,
                data = user.email
            )
            UserInformationRow(
                contentDescription = "Gender",
                imageVector = null,
                painter = if (user.isFemale()) R.drawable.female else R.drawable.male,
                title = "Género",
                iconSize = iconSize,

                data = user.getGenderInSpanish()
            )
            UserInformationRow(
                contentDescription = "register",
                imageVector = Icons.Outlined.DateRange,
                iconSize = iconSize,

                title = "Fecha de Registro",
                data = user.dob.getDateFormatted()
            )
            UserInformationRow(
                contentDescription = "phone",
                imageVector = Icons.Outlined.Phone,
                iconSize = iconSize,

                title = "Teléfono",
                data = user.phone
            )

            UserLocationMap(mapPadding = mapPadding)
        }


    }


}



