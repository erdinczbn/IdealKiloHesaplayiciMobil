package com.example.fitlife;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etBoy, etKilo;
    private RadioGroup rgCinsiyet;
    private RadioButton rbErkek;
    private Button btnHesapla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBoy = findViewById(R.id.etBoy);
        etKilo = findViewById(R.id.etKilo);
        rgCinsiyet = findViewById(R.id.rgCinsiyet);
        rbErkek = findViewById(R.id.rbErkek);
        btnHesapla = findViewById(R.id.btnHesapla);

        btnHesapla.setOnClickListener(v -> {
            String boy = etBoy.getText().toString();
            String kilo = etKilo.getText().toString();

            String secilenCinsiyet = rbErkek.isChecked() ? "Erkek" : "Kadın";

            if (boy.isEmpty() || kilo.isEmpty()) {
                Toast.makeText(MainActivity.this, "Lütfen tüm bilgileri eksiksiz girin!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                intent.putExtra("USER_BOY", boy);
                intent.putExtra("USER_KILO", kilo);
                intent.putExtra("USER_CINSIYET", secilenCinsiyet);
                startActivity(intent);
            }
        });
    }
}