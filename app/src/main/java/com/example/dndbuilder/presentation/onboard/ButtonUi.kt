
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dndbuilder.R

@Composable
fun ButtonUi(
    text :String = "Next",
    backgroundColor : Color = colorResource(R.color.greendark),
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    fontSize : Int = 14,
    onClick : () -> Unit
) {
    Button(onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),shape = RoundedCornerShape(10.dp)){

            Text(text = text, fontSize = fontSize.sp, style = textStyle)
        }
}



@Preview
@Composable
fun NextButton() {
    ButtonUi{

    }
}
@Preview
@Composable
fun BackButton() {
    ButtonUi(
        text = "Back",
        textStyle = MaterialTheme.typography.bodySmall,
        textColor =  Color.Gray,
        backgroundColor = Color.Transparent,
        fontSize = 13
    ){

    }
}