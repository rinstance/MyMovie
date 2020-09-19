package network;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mymovies.MainActivity;

import DAO.Variables;

public class DownloadImageHelper {
    private ImageView imageView;
    private Context context;
    private String imagePath;

    public DownloadImageHelper(ImageView imageView,  String imagePath) {
        this.imageView = imageView;
        this.imagePath = imagePath;
        setImage();
    }

    private void setImage() {
        Glide
                .with(imageView.getContext())
                .load(Variables.IMG_URL + imagePath)
                .into(imageView);
    }
}
