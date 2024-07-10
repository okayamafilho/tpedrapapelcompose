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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.okayamafilho.tpedrapapelcompose.ui.theme.TPedraPapelComposeTheme
import java.util.Random

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
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EscolhaApp("Escolha o App")
        ResultadoEscolhaApp()
        EscolhaApp( "Escolha uma opcao")
        LinhaImagemButton()
    }
}

@Composable
fun EscolhaApp(text1: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text1, fontSize = 18.sp)
    }
}

@Composable
fun ResultadoEscolhaApp() {
    var escolhaApp by remember { mutableStateOf("") }
    escolhaApp =  gerarEscolhaAleatoriaApp()

    val painterImage = when (escolhaApp) {
        "Pedra" -> painterResource(id = R.drawable.pedra)
        "Papel" -> painterResource(R.drawable.papel)
        "Tesoura" -> painterResource(R.drawable.tesoura)
        else -> painterResource(id = R.drawable.padrao)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterImage,
            contentDescription = "Imagem Papel",
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun LinhaImagemButton() {

    val imagePapel = painterResource(id = R.drawable.papel)
    val imagePedra = painterResource(id = R.drawable.pedra)
    val imageTesoura = painterResource(id = R.drawable.tesoura)
    var resultado by remember { mutableStateOf("Resultado final") }
    
    fun verificarGanhador(valorResultado: String) {
        val escolhaApp: String = gerarEscolhaAleatoriaApp()

        if ((escolhaApp === "Pedra" && valorResultado === "Tesoura") ||
            (escolhaApp === "Papel" && valorResultado === "Pedra") ||
            (escolhaApp === "Tesoura" && valorResultado === "Papel")
        ) { // App é ganhador
            resultado = "Você perdeu :("
        } else if ((valorResultado === "Pedra" && escolhaApp === "Tesoura") ||
            (valorResultado === "Papel" && escolhaApp === "Pedra") ||
            (valorResultado === "Tesoura" && escolhaApp === "Papel")
        ) { // Usuário é ganhador
            resultado= "Você ganhou :("
        } else { //Empatou
            resultado = "Empatamos :)"
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botão

        Image(
            painter = imagePapel,
            contentDescription = "Imagem Papel",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable { verificarGanhador("Papel") }
        )
        Image(
            painter = imagePedra,
            contentDescription = "Imagem Pedra",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable { verificarGanhador("Pedra") }
        )
        Image(
            painter = imageTesoura,
            contentDescription = "Imagem Tesoura",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable { verificarGanhador("Tesoura")  }
        )
    }

    LinhaTextoOpcao("Papel", "Pedra", "Tesoura")

    Resultado(resultado)
}

@Composable
fun LinhaTextoOpcao(text1: String, text2: String, text3: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text1)
        Text(text = text2)
        Text(text = text3)
    }
}

@Composable
fun Resultado(text1: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 24.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text1, fontSize = 24.sp, fontWeight = FontWeight(600))
    }
}

fun gerarEscolhaAleatoriaApp(): String {
    val opcoes = arrayOf("Pedra", "Papel", "Tesoura")
    val numeroAleatorio = Random().nextInt(3)
    val escolhaApp = opcoes[numeroAleatorio]
    return escolhaApp
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TPedraPapelComposeTheme {
        HomeScreen()
    }
}