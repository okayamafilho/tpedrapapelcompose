package br.com.okayamafilho.tpedrapapelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.okayamafilho.tpedrapapelcompose.ui.theme.TPedraPapelComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TPedraPapelComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundImageButtonsRowPreview()
    }
}

@Composable
fun RoundImageButtonsRow(imagePapel: Painter,
                         imagePedra: Painter,
                         imageTesoura: Painter,
                         onClickImage1: () -> Unit,
                         onClickImage2: () -> Unit,
                         onClickImage3: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = imagePapel,
            contentDescription = "Imagem Papel",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .clickable { onClickImage1() }
        )
        Image(
            painter = imagePedra,
            contentDescription = "Imagem Pedra",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .clickable { onClickImage2() }
        )
        Image(
            painter = imageTesoura,
            contentDescription = "Imagem Tesoura",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape).clickable { onClickImage3() }
        )
    }
}

@Composable
fun RoundImageButtonsRowPreview() {
    RoundImageButtonsRow(
        imagePapel = painterResource(id = R.drawable.papel), // Substitua pelas suas imagens
        imagePedra = painterResource(id = R.drawable.pedra),
        imageTesoura = painterResource(id = R.drawable.tesoura),
        onClickImage1 = { /* Ação da imagem 1 */ },
        onClickImage2 = { /* Ação da imagem 2 */ },
        onClickImage3 = { /* Ação da imagem 3 */ }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TPedraPapelComposeTheme {
        HomeScreen()
    }
}