import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diagnofish.ui.theme.Grey
import com.example.diagnofish.ui.theme.InterFontFamily
import com.example.diagnofish.ui.theme.Primary
import com.example.diagnofish.ui.theme.Light
import com.example.diagnofish.ui.theme.TextDark

@Preview
@Composable
fun TextButton(modifier: Modifier = Modifier, text: String = "Button", onClick: () -> Unit = {}) {
    Button(shape = RoundedCornerShape(20), onClick = onClick, modifier = modifier.shadow(elevation = 8.dp, ambientColor = Primary, spotColor = Primary), colors = ButtonDefaults.buttonColors(containerColor = Primary, contentColor = Light)) {
        Text(text = text, fontFamily = InterFontFamily, fontWeight = FontWeight.Bold)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
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
                containerColor = Light,
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
fun SectionTitle(modifier: Modifier = Modifier, text: String = "Section Title") {
    Text(text = text, fontFamily = InterFontFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = modifier)
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