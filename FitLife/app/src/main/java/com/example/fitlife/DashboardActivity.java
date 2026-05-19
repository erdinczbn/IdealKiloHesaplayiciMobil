package com.example.fitlife;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvGirilenler, tvVki, tvIdealKilo, tvYagOrani, tvYagsizKutle, tvDurumYazisi;
    private Button btnTekrarHesapla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvGirilenler = findViewById(R.id.tvGirilenler);
        tvVki = findViewById(R.id.tvVki);
        tvIdealKilo = findViewById(R.id.tvIdealKilo);
        tvYagOrani = findViewById(R.id.tvYagOrani);
        tvYagsizKutle = findViewById(R.id.tvYagsizKutle);
        tvDurumYazisi = findViewById(R.id.tvDurumYazisi);
        btnTekrarHesapla = findViewById(R.id.btnTekrarHesapla);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String boyStr = extras.getString("USER_BOY", "175");
            String kiloStr = extras.getString("USER_KILO", "75");
            String cinsiyet = extras.getString("USER_CINSIYET", "Erkek");

            double boyCm = Double.parseDouble(boyStr);
            double kilo = Double.parseDouble(kiloStr);
            double boyMetre = boyCm / 100.0;

            // 1. VKİ Hesaplama
            double vki = kilo / (boyMetre * boyMetre);
            tvVki.setText(String.format(Locale.US, "Vücut Kitle İndeksi (VKİ): %.1f", vki));

            // Durum Belirleme
            String durum;
            if (vki < 18.5) {
                durum = "Zayıf";
            } else if (vki < 24.9) {
                durum = "Normal / Sağlıklı";
            } else if (vki < 29.9) {
                durum = "Hafif Kilolu (Balık Etli)";
            } else {
                durum = "Obezite";
            }
            tvDurumYazisi.setText("Durum: " + durum);

            // 2. İdeal Kilo Hesaplama
            double idealKilo;
            if (cinsiyet.equals("Erkek")) {
                idealKilo = 50.0 + 2.3 * ((boyCm / 2.54) - 60);
            } else {
                idealKilo = 45.5 + 2.3 * ((boyCm / 2.54) - 60);
            }
            tvIdealKilo.setText(String.format(Locale.US, "İdeal Kilonuz: %.1f kg", idealKilo));

            // 3. Yağ Oranı Hesaplama
            int cinsiyetKatsayi = cinsiyet.equals("Erkek") ? 1 : 0;
            double yagOrani = (1.20 * vki) + (0.23 * 22) - (10.8 * cinsiyetKatsayi) - 5.4 - 4.0;

            if (yagOrani < 5) yagOrani = 8.0; // Mantıksal alt sınır
            tvYagOrani.setText(String.format(Locale.US, "Tahmini Yağ Oranı: %%%.1f", yagOrani));

            // 4. Yağsız Vücut Kütlesi Hesaplama
            double yagKutlesi = kilo * (yagOrani / 100.0);
            double yagsizKutle = kilo - yagKutlesi;
            tvYagsizKutle.setText(String.format(Locale.US, "Yağsız Vücut Kütlesi: %.1f kg", yagsizKutle));

            tvGirilenler.setText("Boy: " + boyStr + " cm  |  Kilo: " + kiloStr + " kg  |  " + cinsiyet);
        }

        // Tekrar Hesapla Buton
        btnTekrarHesapla.setOnClickListener(v -> {
            finish(); //
        });
    }
}