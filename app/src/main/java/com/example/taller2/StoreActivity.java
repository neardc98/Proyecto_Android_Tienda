package com.example.taller2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClothingAdapter clothingAdapter;
    private List<ClothingItem> clothingItems;
    private TextView exitText;
    private TextView usernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        exitText = findViewById(R.id.exit_text);

        usernameText = findViewById(R.id.user_name);

        // Obtener el nombre de usuario del Intent
        String username = getIntent().getStringExtra("username");
        if (username != null) {
            usernameText.setText("Bienvenido "+username);
        }

        exitText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPasswordIntent = new Intent(StoreActivity.this, LoginActivity.class);
                startActivity(forgotPasswordIntent);
            }
        });

        // Crear una lista de artículos de ropa
        clothingItems = new ArrayList<>();
        clothingItems.add(new ClothingItem("Vestido", "$50", "XS - M - XL", R.drawable.gothic_dress));
        clothingItems.add(new ClothingItem("Arnes", "$25", "XS - M - L", R.drawable.gothic_arnes));
        clothingItems.add(new ClothingItem("Choker", "$20", "Talla: S a la XL",R.drawable.gothic_coat));
        clothingItems.add(new ClothingItem("botas", "$90", "Talla: 36 - 40" ,R.drawable.gothic_boots));


        clothingItems.add(new ClothingItem("Ligero", "$15", "M - L - XL",R.drawable.gothic_ligero));
        clothingItems.add(new ClothingItem("Cinturon", "$10", "Talla: S a la XL" ,R.drawable.gothic_cinturon));



        clothingAdapter = new ClothingAdapter(clothingItems, this);
        recyclerView.setAdapter(clothingAdapter);
    }


}