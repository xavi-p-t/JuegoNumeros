package com.xavi.juegonumeros;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
int cont = 0;
int numRan = 0;
TextView scrollText; //intentCounter;
ScrollView sc;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Inicialitzem variables
    numRan = generarNum();
    scrollText = findViewById(R.id.res);
    sc = findViewById(R.id.scroll);
    //intentCounter = findViewById(R.id.intentCounter); // Comptador visual
    Button button = findViewById(R.id.button);

    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    button.setOnClickListener(v -> {
        EditText et = findViewById(R.id.editTextNumber);
        String edit = et.getText().toString();

        // Validació de l'entrada
        if (edit.isEmpty() || !edit.matches("\\d+")) {
            Toast.makeText(MainActivity.this, "Introdueix un número vàlid!", Toast.LENGTH_SHORT).show();
            return;
        }

        int numSel = Integer.parseInt(edit);
        cont++;

        if (numSel > numRan) {
            scrollText.append("\nEl número és més petit");
            showToast("El número és més petit");
        } else if (numSel < numRan) {
            scrollText.append("\nEl número és més gran");
            showToast("El número és més gran");
        } else {
            // L'usuari encerta
            builder.setTitle("Has guanyat!")
                    .setMessage("Has encertat amb " + cont + " intents.")
                    .setPositiveButton("Jugar de nou", (dialogInterface, i) -> {
                        cont = 0;
                        numRan = generarNum();
                        scrollText.setText(""); // Esborrem historial
                        //intentCounter.setText("Intents: 0");
                    })
                    .setCancelable(false);

            builder.create().show();
        }

        // Actualitzem el comptador d'intents
       // intentCounter.setText("Intents: " + cont);

        // Desplacem el ScrollView fins al final
        sc.post(() -> sc.fullScroll(View.FOCUS_DOWN));
    });
}

public int generarNum() {
    return (int) (Math.random() * (100 - 1) + 1);
}

private void showToast(String message) {
    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
}
}

