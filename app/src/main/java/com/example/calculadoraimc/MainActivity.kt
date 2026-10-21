package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.calculadoraimc.ui.theme.CalculadoraImcTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraImcTheme {
                    CalcLayout()
                }
            }
        }
    }


@Composable
fun CalcLayout() {
    var pesoInput by remember { mutableStateOf("") }
    var alturaInput by remember { mutableStateOf("") }
    var vararredondarImc by remember { mutableStateOf(false) }

    val pesoValue = pesoInput.toDoubleOrNull() ?: 0.0
    val alturaValue = alturaInput.toDoubleOrNull() ?: 0.0

    Column(modifier = Modifier
        .fillMaxSize()
        .safeDrawingPadding()
        .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Calculadora de IMC",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp).align(alignment = Alignment.Start)
        )

        EditField(
            label = "Peso (Kg)",
            value = pesoInput,
            onValueChange = {pesoInput = it},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        EditField(
            label = "Altura (m)",
            value = alturaInput,
            onValueChange = {alturaInput = it},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )


        var imc by remember { mutableStateOf(0.0) }
        Text(
            text = "IMC: $imc",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = Classificar(imc),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        ArredondarImc(
            arredondar = vararredondarImc,
            onArredondarChanged = {vararredondarImc = it},
            modifier = Modifier
        )

        Button(
            onClick = {imc = calculoImc(pesoValue, alturaValue, vararredondarImc)},
            enabled = pesoInput.isNotEmpty(),
            modifier = Modifier
        ) {
            Text(
                text = "Calcular IMC"
            )
        }
    }


}

@Composable
fun EditField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = {Text(label)},
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

fun calculoImc(
    peso: Double,
    altura: Double,
    arredondar: Boolean
): Double {
    var calculo = peso / (altura * altura)
    if (arredondar){
        calculo = ceil(calculo)
    }

    return calculo
}

@Composable
fun ArredondarImc(
    arredondar: Boolean,
    onArredondarChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Arredondar IMC?")
        Switch(
            checked = arredondar,
            onCheckedChange = onArredondarChanged
        )
    }
}

@Composable
fun Classificar(imc: Double): String = when (imc) {
    in 0.0 .. 18.4 -> "Abaixo do Peso"
    in 18.5 .. 24.9 -> "Peso Normal"
    in 25.0 .. 29.9 -> "Sobrepeso"
    in 30.0 .. 999.9 -> "Obesidade"
    else -> "?"
}





