package com.example.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import DAO.RealmDatabase;
import DAO.Variables;

public class DetailsActivity extends AppCompatActivity {
    private String poster_url;
    private TextView title, overview, popularity;
    private Bitmap bitmap;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        setUI();
    }

    private void init() {
        imageView = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        overview = findViewById(R.id.overview);
        popularity = findViewById(R.id.popularity);
    }

    private void setUI() {
        Intent intent = getIntent();
        title.setText(intent.getStringExtra(Variables.TITLE));
        overview.setText(intent.getStringExtra(Variables.OVERVIEW));
        popularity.setText(intent.getStringExtra(Variables.POPULARITY));

        byte[] array = intent.getByteArrayExtra(Variables.BITMAP);
        bitmap = BitmapFactory.decodeByteArray(array , 0, array.length);

        imageView.setImageBitmap(bitmap);
    }
}
