package com.example.yoshiki.mywatsonapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yoshiki.mywatsonapp.api.WatsonInterface;
import com.example.yoshiki.mywatsonapp.model.Result;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button action;
    private static final int RESULT_LOAD_IMAGE = 1;

    private static final String API = "https://gateway-a.watsonplatform.net/visual-recognition/api/v3";
    private static final String KEY = "ea12c779247456952efc7ca12ca075c4046ce105";
    private static final String version = "2016-05-19";
    private static final String URL = "http://cdn77.sadanduseless.com/wp-content/uploads/2016/05/funny-animals1.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.imageToUpload);
    }

    public void choosePhoto(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
//            Uri selectedImage = data.getData();
//            imageView.setImageURI(selectedImage);
        }
    }

    public void askWhatIsThis(View view) {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        WatsonInterface request = adapter.create(WatsonInterface.class);
        request.classifyImage(KEY, URL, version, new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                Log.e("yes", "connection success");

                String body = new String(((TypedByteArray) response.getBody()).getBytes());
                Log.e("name", body);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("no", "connection failed");
            }
        });
    }
}