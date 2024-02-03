package com.ferdialif.testapplication.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ferdialif.testapplication.R
import com.ferdialif.testapplication.ui.theme.DefaultColor

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    onBack:()->Unit
) {
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var birthData by remember {
        mutableStateOf("")
    }
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RectangleShape
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = DefaultColor,
                    modifier = Modifier.clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {

                        })
                )
                Text(
                    text = stringResource(R.string.save),
                    color = DefaultColor,
                    modifier = Modifier.clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {

                        })
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.size(150.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(DefaultColor)
        ) {

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.main_information_title), fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.first_name), fontWeight = FontWeight.SemiBold, maxLines = 1,
                modifier = Modifier.weight(4F)
            )
            Spacer(modifier = Modifier.weight(1F))
            OutlinedTextField(value = firstName, onValueChange = {
                firstName = it
            }, maxLines = 1, modifier = Modifier.weight(9F))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.last_name),
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                modifier = Modifier.weight(4F)
            )
            Spacer(modifier = Modifier.weight(1F))
            OutlinedTextField(value = firstName, onValueChange = {
                lastName = it
            }, maxLines = 1, modifier = Modifier.weight(9F))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.email), fontWeight = FontWeight.SemiBold, maxLines = 1,
                modifier = Modifier.weight(4F)
            )
            Spacer(modifier = Modifier.weight(1F))
            OutlinedTextField(value = firstName, onValueChange = {
                email = it
            }, maxLines = 1, modifier = Modifier.weight(9F))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.birth_date), fontWeight = FontWeight.SemiBold, maxLines = 1,
                modifier = Modifier.weight(4F)
            )
            Spacer(modifier = Modifier.weight(1F))
            OutlinedTextField(value = firstName, onValueChange = {
                birthData = it
            }, maxLines = 1, modifier = Modifier.weight(9F), trailingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null
                    )
                }
            })
        }
    }
}