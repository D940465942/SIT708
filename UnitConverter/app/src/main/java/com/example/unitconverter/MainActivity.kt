package com.example.unitconverter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var spinner_source: Spinner
    private lateinit var spinner_destination: Spinner
    private lateinit var text_source_unit: TextView
    private lateinit var text_input_value: EditText
    private lateinit var text_destination_unit: TextView
    private lateinit var button_convert: Button
    private lateinit var text_result_value: TextView

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner_source = findViewById(R.id.spinner_source)
        spinner_destination = findViewById(R.id.spinner_destination)
        text_source_unit = findViewById(R.id.text_source_unit)
        text_input_value = findViewById(R.id.text_input_source_value)
        text_destination_unit = findViewById(R.id.text_destination_unit)
        button_convert = findViewById(R.id.btn_convert)
        text_result_value = findViewById(R.id.text_result)

        val unitAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.unit_array,
            android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_source.adapter = unitAdapter
        spinner_destination.adapter = unitAdapter

        button_convert.setOnClickListener {
            val sourceUnit = spinner_source.selectedItem.toString()
            val destinationUnit = spinner_destination.selectedItem.toString()
            val value = text_input_value.text.toString().toDoubleOrNull()

            if (value != null) {
                val convertedValue = convertUnits(sourceUnit, destinationUnit, value)
                text_result_value.text = convertedValue
            } else {
                text_result_value.text = "Input value is invalid!!!!!"
            }
        }
    }

    private fun convertUnits(sourceUnit: String, destinationUnit: String, value: Double) : String {
        if(sourceUnit == destinationUnit) {
            return value.toString()
        }

        return when {
            sourceUnit == "inch" && destinationUnit == "cm" -> (value * 2.54).toString()
            sourceUnit == "foot" && destinationUnit == "cm" -> (value * 30.48).toString()
            sourceUnit == "yard" && destinationUnit == "cm" -> (value * 91.44).toString()
            sourceUnit == "mile" && destinationUnit == "km" -> (value * 1.60934).toString()

            sourceUnit == "pound" && destinationUnit == "kg" -> (value * 0.453592).toString()
            sourceUnit == "ounce" && destinationUnit == "g" -> (value * 28.3495).toString()
            sourceUnit == "ton" && destinationUnit == "kg" -> (value * 907.185).toString()

            sourceUnit == "Celsius" && destinationUnit == "Fahrenheit" -> ((value * 1.8) + 32).toString()
            sourceUnit == "Fahrenheit" && destinationUnit == "Celsius" -> ((value - 32) / 1.8).toString()
            sourceUnit == "Celsius" && destinationUnit == "Kelvin" -> (value + 273.15).toString()
            sourceUnit == "Kelvin" && destinationUnit == "Celsius" -> (value - 273.15).toString()

            else -> "Input value is invalid!"
        }
    }
}