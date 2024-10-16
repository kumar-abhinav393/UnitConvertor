package de.cispa.unitconvertor

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun UnitConvertorApp(){
    // States
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var iConversionFactor by remember { mutableDoubleStateOf(1.00) }
    var oConversionFactor by remember { mutableStateOf(1.00) }

    //Below function accept only numeric values
    val onValueChange: (String) -> Unit = {
        if(it.all { it.isDigit() }){
            inputValue = it
        }
    }

    //below function converts the units
    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * iConversionFactor * 100.0 / oConversionFactor).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Convertor",
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = onValueChange, label = { Text(text = "Enter Value")})
        convertUnits()
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        inputUnit = "Centimeter"
                        iExpanded = false
                        iConversionFactor = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        inputUnit = "Meter"
                        iExpanded = false
                        iConversionFactor = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        inputUnit = "Feet"
                        iExpanded = false
                        iConversionFactor = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = {
                        inputUnit = "Millimeter"
                        iExpanded = false
                        iConversionFactor = 0.001
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        outputUnit = "Centimeter"
                        oExpanded = false
                        oConversionFactor = 0.01
                        convertUnits()

                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        outputUnit = "Meter"
                        oExpanded = false
                        oConversionFactor = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        outputUnit = "Feet"
                        oExpanded = false
                        oConversionFactor = .3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = {
                        outputUnit = "Millimeter"
                        oExpanded = false
                        oConversionFactor = 0.001
                        convertUnits()
                    })
                }
            } 
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium)
    }
}