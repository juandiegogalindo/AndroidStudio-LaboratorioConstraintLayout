package co.edu.unipiloto.laboratorioconstraintlayouts;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EmailActivity extends AppCompatActivity {

    private EditText emailAddress;
    private EditText subject;
    private EditText message;
    private Button sendButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        // Referencias a los componentes
        emailAddress = findViewById(R.id.email_address);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        sendButton = findViewById(R.id.send_button);
        backButton = findViewById(R.id.back_button);


        // Evento del botón
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendEmail() {

        String email = emailAddress.getText().toString().trim();
        String emailSubject = subject.getText().toString().trim();
        String emailMessage = message.getText().toString().trim();

        // Validación básica
        if (email.isEmpty() || emailSubject.isEmpty() || emailMessage.isEmpty()) {
            Toast.makeText(EmailActivity.this,
                    "All fields are required",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Intent implícito para enviar correo
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, emailMessage);

        try {
            startActivity(Intent.createChooser(intent, "Enviar email"));
        } catch (Exception e) {
            Toast.makeText(EmailActivity.this,
                    "No se encontro aplicacion de email.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}