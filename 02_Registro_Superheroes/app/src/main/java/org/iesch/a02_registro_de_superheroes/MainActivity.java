package org.iesch.a02_registro_de_superheroes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import org.iesch.a02_registro_de_superheroes.databinding.ActivityMainBinding;
import org.iesch.a02_registro_de_superheroes.model.SuperHero;

public class MainActivity extends AppCompatActivity {

    private Bitmap foto;
    private ActivityMainBinding binding;

    private ActivityResultLauncher<Intent> lanzarCamaraDeFotosActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        if(data != null) {
                            foto = data.getExtras().getParcelable("data");
                            binding.heroImage.setImageBitmap(foto);
                        }
                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.saveButton.setOnClickListener(v -> {
            String superHeroName = binding.nombreHeroEdit.getText().toString();
            String alterEgo = binding.alterEgoEdit.getText().toString();
            String bio = binding.bioEdit.getText().toString();
            int rating = binding.powerBar.getNumStars();

            abrirDetailActivity(superHeroName,alterEgo,bio,rating);

        });
        binding.heroImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
    }

    private void abrirCamara() {
        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        lanzarCamaraDeFotosActivity.launch(camaraIntent);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(resultCode == Activity.RESULT_OK && requestCode == 1000) {
//            if(data != null) {
//                foto = data.getExtras().getParcelable("data");
//
//                binding.heroImage.setImageBitmap(foto);
//            } else {
//                Toast.makeText(this, "Error haciendo la foto", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    private void abrirDetailActivity(String superheroName, String alterEgo, String bio, int rating) {
        SuperHero superHero = new SuperHero(superheroName,alterEgo,bio,rating);
        Intent irADetalle = new Intent(this,DetailActivity.class);

        irADetalle.putExtra("superHero", superHero);
        irADetalle.putExtra("fotoSuperHeroe", foto);
        startActivity(irADetalle);
    }
}















