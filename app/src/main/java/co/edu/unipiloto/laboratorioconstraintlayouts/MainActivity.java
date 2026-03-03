package co.edu.unipiloto.laboratorioconstraintlayouts;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnEmail;
    private Button btnFuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEmail = findViewById(R.id.btn_email);
        btnFuel = findViewById(R.id.btn_fuel);

        btnEmail.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EmailActivity.class);
            startActivity(intent);
        });

        btnFuel.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FuelActivity.class);
            startActivity(intent);
        });
    }
}