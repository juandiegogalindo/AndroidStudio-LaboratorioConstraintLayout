package co.edu.unipiloto.laboratorioconstraintlayouts;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FuelActivity extends AppCompatActivity {

    private EditText gallonsInput;
    private EditText pricePerGallonInput;
    private Spinner fuelSpinner;
    private Button submitButton;
    private Button backButton;
    private TextView historyText;

    private ArrayList<String> historyList = new ArrayList<>();

    private double currentPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);

        gallonsInput = findViewById(R.id.galones);
        pricePerGallonInput = findViewById(R.id.precio_galon);
        fuelSpinner = findViewById(R.id.fuel_spinner);
        submitButton = findViewById(R.id.register_button);
        backButton = findViewById(R.id.back_button);
        historyText = findViewById(R.id.historyText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.fuel_types,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuelSpinner.setAdapter(adapter);

        // 🔹 Aquí está la clave
        fuelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String fuelType = parent.getItemAtPosition(position).toString();

                switch (fuelType) {
                    case "Selecciones":
                        currentPrice = 0;
                        break;
                    case "Corriente":
                        currentPrice = 18500;
                        break;
                    case "Diesel":
                        currentPrice = 21600;
                        break;
                    case "Premium":
                        currentPrice = 25700;
                        break;
                }

                pricePerGallonInput.setText(String.valueOf(currentPrice));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        submitButton.setOnClickListener(view -> registerFuelRequest());

        backButton.setOnClickListener(v -> finish());
    }

    private void registerFuelRequest() {

        String gallonsStr = gallonsInput.getText().toString().trim();

        if (gallonsStr.isEmpty()) {
            Toast.makeText(this, "Ingrese los galones", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double gallons = Double.parseDouble(gallonsStr);
            double total = gallons * currentPrice;

            String fuelType = fuelSpinner.getSelectedItem().toString();

            String record = "Tipo: " + fuelType +
                    " | Galones: " + gallons +
                    " | Total: $" + total;

            historyList.add(record);
            updateHistory();

            Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
            gallonsInput.setText("");

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateHistory() {

        if (historyList.isEmpty()) {
            historyText.setText("Sin registros aún");
            return;
        }

        StringBuilder builder = new StringBuilder();

        for (String record : historyList) {
            builder.append(record).append("\n\n");
        }

        historyText.setText(builder.toString());
    }
}