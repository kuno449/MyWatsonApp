package com.example.yoshiki.mywatsonapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yoshiki.mywatsonapp.api.WatsonInterface;
import com.example.yoshiki.mywatsonapp.model.Result;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;


public class MainActivity extends AppCompatActivity {

    ImageView imageToUpload;
    Button rotateRight;
    Button rotateLeft;
    Button action;
    private static final int RESULT_LOAD_IMAGE = 1;

    HttpURLConnection connection = null;
    private static final String UPLOAD_URL = "http://www.kunoyoshiki.de1.biz/SavePicture.php";

    private static final String API = "https://gateway-a.watsonplatform.net/visual-recognition/api/v3";
    private static final String KEY = "ea12c779247456952efc7ca12ca075c4046ce105";
    private static final String version = "2016-05-19";
    private static final String SERVER_URL = "http://www.pic.kunoyoshiki.de1.biz/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageToUpload = (ImageView)findViewById(R.id.imageToUpload);
        rotateLeft = (Button) findViewById(R.id.rotateLeft);
        rotateRight = (Button) findViewById(R.id.rotateRight);
    }

    public void choosePhoto(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
            rotateLeft.setVisibility(View.VISIBLE);
            rotateRight.setVisibility(View.VISIBLE);
        }
    }

    public void rotatePhotoLeft(View view){
        Bitmap image = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();
        rotatePhoto(image, 270);
    }

    public void rotatePhotoRight(View view){
        Bitmap image = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();
        rotatePhoto(image, 90);
    }

    public void rotatePhoto (Bitmap source, float angle){

        int imageWidth = source.getWidth();
        int imageHeight = source.getHeight();

        Matrix matrix = new Matrix();
        matrix.setRotate(angle, imageWidth/2, imageHeight/2);
        Bitmap rotatedPhoto = Bitmap.createBitmap(source, 0, 0, imageWidth, imageHeight, matrix, true);
        imageToUpload.setImageBitmap(rotatedPhoto);
    }

    public void uploadPhoto(View view) {

        Bitmap image = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        HashMap<String, String> postData = new HashMap<>();
        postData.put("image", encodedImage);

        PostResponseAsyncTask task = new PostResponseAsyncTask(MainActivity.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String id) {
                Toast.makeText(getApplicationContext(), "Image uploaded successfully.",
                            Toast.LENGTH_SHORT).show();
                Log.e("id", id);
                submitQuestion(id);
            }
        });
        task.execute(UPLOAD_URL);
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                Toast.makeText(getApplicationContext(), "Cannot connect to Server.",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                Toast.makeText(getApplicationContext(), "Cannot connect to Server.",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                Toast.makeText(getApplicationContext(), "Cannot connect to Server.",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                Toast.makeText(getApplicationContext(), "Cannot connect to Server.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void submitQuestion(String id){

        RestAdapter adapter = new RestAdapter.Builder()
        .setEndpoint(API)
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .build();

        WatsonInterface request = adapter.create(WatsonInterface.class);

        String url = SERVER_URL + id + ".jpeg";
        request.classifyImage(KEY, url, version, new Callback<Result>() {

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