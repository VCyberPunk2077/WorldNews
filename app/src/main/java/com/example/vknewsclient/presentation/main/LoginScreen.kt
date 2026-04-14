package com.example.vknewsclient.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vknewsclient.R

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onPrivateKeyLoginClicked: (key: String) -> Unit,
    onSharedKeyLoginClicked: () -> Unit,
) {
    Box(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .systemBarsPadding()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier.wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.app_logo),
                contentDescription = stringResource(R.string.app_logo)
            )

            Spacer(modifier.height(50.dp))

            val textFieldState = rememberTextFieldState()
            TextField(
                state = textFieldState,
                modifier = modifier.fillMaxWidth(),
                label = {
                    Text("Enter your's API key:")
                }
            )

            Button(
                modifier = modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSurface,
                    contentColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                onClick = {
                    val userKey = textFieldState.text.toString()
                    onPrivateKeyLoginClicked(userKey)
                }
            ) {
                Text(stringResource(R.string.login_with_your_api_key))
            }

            Spacer(modifier.height(20.dp))

            Button(
                modifier = modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSurface,
                    contentColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                onClick = {
                    onSharedKeyLoginClicked()
                }
            ) {
                Text(stringResource(R.string.login_with_author_s_api_key))
            }
        }
    }
}