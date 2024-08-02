package com.example.taller2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText username, identityCard, firstName, lastName, age, email, password, address, phone;
    private RadioGroup genderGroup;
    private RadioButton selectedGender;
    private Spinner country;
    private Spinner rol;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        identityCard = findViewById(R.id.identity_card);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        genderGroup = findViewById(R.id.gender);
        country = findViewById(R.id.country);
        rol= findViewById(R.id.rol);
        registerButton = findViewById(R.id.register_button);

        // Lista de Items paises
        List<String> countries = new ArrayList<>();
        countries.add(getString(R.string.select_country));
        countries.addAll(Arrays.asList(getResources().getStringArray(R.array.countries_array)));

        // Configurar el Spinner Paises
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.style_spinner, countries);
        adapter.setDropDownViewResource(R.layout.style_spinner);

        country.setAdapter(adapter);


        //Lista de Items roles
        List<String> rolies = new ArrayList<>();
        rolies.add(getString(R.string.select_rol));
        rolies.addAll(Arrays.asList(getResources().getStringArray(R.array.roles_array)));

        // Configurar el Spinner Paises
        ArrayAdapter<String> adapter_rol = new ArrayAdapter<>(this, R.layout.style_spinner, rolies);
        adapter.setDropDownViewResource(R.layout.style_spinner);

        rol.setAdapter(adapter_rol);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    // Procesar el registro (e.g., guardar en base de datos, llamar a una API, etc.)
                    // Aquí se inicia la actividad de la tienda después de un registro exitoso

                    Toast.makeText(RegisterActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, StoreActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean validateInput() {
        if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Nombre de usuario requerido");
            return false;
        }

        if (TextUtils.isEmpty(identityCard.getText().toString())) {
            identityCard.setError("Cédula de identidad requerida");
            return false;
        }

        if (TextUtils.isEmpty(firstName.getText().toString())) {
            firstName.setError("Nombres requeridos");
            return false;
        }

        if (TextUtils.isEmpty(lastName.getText().toString())) {
            lastName.setError("Apellidos requeridos");
            return false;
        }

        if (TextUtils.isEmpty(age.getText().toString())) {
            age.setError("Edad requerida");
            return false;
        }

        if (Integer.parseInt(age.getText().toString()) < 18) {
            age.setError("Debe ser mayor de 18 años");
            return false;
        }

        if (TextUtils.isEmpty(email.getText().toString()) || !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Correo electrónico inválido");
            return false;
        }

        if (TextUtils.isEmpty(password.getText().toString()) || password.getText().toString().length() < 6) {
            password.setError("Contraseña debe tener al menos 6 caracteres");
            return false;
        } else {
            String passwordStr = password.getText().toString();
            if (!passwordStr.matches(".*[A-Z].*")) {
                password.setError("Contraseña debe contener al menos una letra mayúscula");
                return false;
            }
            if (!passwordStr.matches(".*[a-z].*")) {
                password.setError("Contraseña debe contener al menos una letra minúscula");
                return false;
            }
            if (!passwordStr.matches(".*\\d.*")) {
                password.setError("Contraseña debe contener al menos un número");
                return false;
            }
            if (!passwordStr.matches(".*[!@#\\$%^&*+=?-].*")) {
                password.setError("Contraseña debe contener al menos un carácter especial");
                return false;
            }
        }


        if (TextUtils.isEmpty(address.getText().toString())) {
            address.setError("Dirección requerida");
            return false;
        }

        if (TextUtils.isEmpty(phone.getText().toString()) || !Patterns.PHONE.matcher(phone.getText().toString()).matches()) {
            phone.setError("Teléfono inválido");
            return false;
        }

        int selectedGenderId = genderGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Seleccione su sexo", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            selectedGender = findViewById(selectedGenderId);
        }
        return true;
    }
}
