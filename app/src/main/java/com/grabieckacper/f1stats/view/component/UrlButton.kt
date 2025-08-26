package com.grabieckacper.f1stats.view.component

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.grabieckacper.f1stats.ui.theme.F1StatsTheme
import androidx.core.net.toUri

@Composable
fun UrlButton(
    uriString: String,
    text: String,
    modifier: Modifier = Modifier,
    intentErrorCallback: (() -> Unit)? = null,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
) {
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, uriString.toUri())

            try {
                context.startActivity(intent)
            } catch (e: Exception) {
                Log.e("[INTENT ERROR]", e.message ?: "")

                intentErrorCallback?.invoke()
            }
        },
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource
    ) {
        Text(text = text)
    }
}

@Composable
@Preview(showBackground = true)
fun UrlButtonPreview() {
    F1StatsTheme {
        UrlButton(
            uriString = "",
            text = ""
        )
    }
}
