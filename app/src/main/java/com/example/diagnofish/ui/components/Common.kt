import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diagnofish.R
import com.example.diagnofish.ui.theme.Grey
import com.example.diagnofish.ui.theme.InterFontFamily
import com.example.diagnofish.ui.theme.Primary
import com.example.diagnofish.ui.theme.Lighter
import com.example.diagnofish.ui.theme.StatusDanger
import com.example.diagnofish.ui.theme.StatusSuccess
import com.example.diagnofish.ui.theme.TextDark

@Preview
@Composable
fun TextButton(modifier: Modifier = Modifier, text: String = "Button", shape: Shape = RoundedCornerShape(20), shadowElevation: Dp = 8.dp, onClick: () -> Unit = {}) {
    Button(shape = shape, onClick = onClick, modifier = modifier.shadow(elevation = shadowElevation, ambientColor = Primary, spotColor = Primary), colors = ButtonDefaults.buttonColors(containerColor = Primary)) {
        Text(text = text, fontFamily = InterFontFamily, fontWeight = FontWeight.Bold, color = Lighter)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TextFieldWithLabel(modifier: Modifier = Modifier, label: String = "Label", placeholder: String = "Placeholder", keyboardOptions: KeyboardOptions = KeyboardOptions.Default) {
    var value by remember { mutableStateOf("") }
    Column {
        Text(text = label, fontFamily = InterFontFamily, fontWeight = FontWeight.Medium, fontSize = 20.sp)
        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            modifier = modifier
                .clip(RoundedCornerShape(20))
                .border(BorderStroke(1.dp, Grey), RoundedCornerShape(20)),
            placeholder = { Text(text = placeholder, fontFamily = InterFontFamily) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = TextDark,
                focusedBorderColor = Grey,
                unfocusedBorderColor = Grey,
                containerColor = Lighter,
            ),
            keyboardOptions = keyboardOptions,
            visualTransformation = if (keyboardOptions.keyboardType == KeyboardType.Password) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
        )
    }
}

@Preview
@Composable
fun SectionTitle(modifier: Modifier = Modifier, text: String = "Section Title", fontSize: TextUnit = 24.sp) {
    Text(text = text, fontFamily = InterFontFamily, fontWeight = FontWeight.Bold, fontSize = fontSize, modifier = modifier)
}

@Preview
@Composable
fun LinkText(text: String = "Click me", onClick: () -> Unit = {}, modifier: Modifier = Modifier, color: Color = TextDark) {
    ClickableText(text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = color, fontFamily = InterFontFamily, fontWeight = FontWeight.Medium)) {
            append(text)
        }
    }, onClick = { onClick() }, modifier = modifier)
}

@Preview
@Composable
fun TextAndLink(text: String = "Text", linkText: String = "Link", onClick: () -> Unit = {}, modifier: Modifier = Modifier) {
    ClickableText(text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = TextDark, fontSize = 14.sp, fontFamily = InterFontFamily, fontWeight = FontWeight.Medium)) {
            append(text)
        }
        append(" ")
        withStyle(style = SpanStyle(color = Primary, fontSize = 14.sp, fontFamily = InterFontFamily, fontWeight = FontWeight.Medium)) {
            append(linkText)
        }
    }, onClick = {onClick()}, modifier = modifier)
    
}

@Preview
@Composable
fun ScreenTitle(text: String = "Title") {
    Text(text = text, fontFamily = InterFontFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp)
}

@Composable
fun BasicText(modifier: Modifier = Modifier, text: String = "Text", fontSize: TextUnit = 16.sp, color: Color = TextDark, fontWeight: FontWeight = FontWeight.Medium, lineHeight: TextUnit? = null) {
    Text(text = text, style = TextStyle(lineHeight = lineHeight ?: fontSize), fontFamily = InterFontFamily, fontWeight = fontWeight, fontSize = fontSize, color = color, modifier = modifier)
}

@Preview
@Composable
fun StatusBadge(status: String = "Sehat") {
    Text(modifier = Modifier
        .clip(RoundedCornerShape(20))
        .background(
            if (status.equals(stringResource(id = R.string.status_infected)))
                StatusDanger
            else
                StatusSuccess
        )
        .padding(2.dp), text = status
    )
}

@Preview
@Composable
fun ButtonWithIcon(icon: Painter = painterResource(id = R.drawable.icon_camera), text: String = "Button", modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Button(onClick = onClick, modifier = Modifier
        .clip(RoundedCornerShape(20))
        , colors = ButtonDefaults.buttonColors(containerColor = Primary)) {
        Row {
            Image(painter = icon, contentDescription = text, modifier = Modifier.size(24.dp))
            Text(text = text, fontFamily = InterFontFamily, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Lighter, modifier = Modifier.padding(start = 8.dp))
        }
    }
}