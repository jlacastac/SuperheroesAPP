package org.iesch.a02_registro_de_superheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import org.iesch.a02_registro_de_superheroes.databinding.ActivityDetailBinding;
import org.iesch.a02_registro_de_superheroes.databinding.ActivityMainBinding;
import org.iesch.a02_registro_de_superheroes.model.SuperHero;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        SuperHero superHero = extras.getParcelable("superHero");
        Bitmap foto = extras.getParcelable("fotoSuperHeroe");

        binding.setSuperhero(superHero);
        if(foto != null) binding.imageView.setImageBitmap(foto);
    }
}












