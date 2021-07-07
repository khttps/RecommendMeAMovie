package com.example.recommendmeamovie.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.util.Resource
import com.google.android.material.composethemeadapter.MdcTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: LoginViewModel by viewModels()
        return ComposeView(requireContext()).apply {
            setContent {
                MdcTheme {
                    LoginScreen(viewModel)
                }
            }
        }
    }

    @Composable
    fun LoginScreen(viewModel: LoginViewModel) {
        val token by viewModel.token.observeAsState()
        token?.let {
            if (it is Resource.Success) {
                val intent = Intent(Intent.ACTION_VIEW, viewModel.getAuthUrl(it.data!!))
                startActivity(intent)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_tmdb_logo),
                contentDescription = stringResource(id = R.string.tmdb_logo_description),
                modifier = Modifier
                    .height(160.dp)
                    .width(240.dp)
            )
            Text(
                text = stringResource(id = R.string.login_header),
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 24.sp,
                color = Color.White
            )
            Text(
                text = stringResource(id = R.string.login_details),
                color = Color.White
            )
            Button(
                onClick = { viewModel.getRequestToken() },
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.login_button_text))
            }

        }
    }
}