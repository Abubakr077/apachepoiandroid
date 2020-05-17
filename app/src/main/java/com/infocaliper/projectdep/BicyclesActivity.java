package com.infocaliper.projectdep;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.infocaliper.projectdep.DocumentContainer.InputstreamBicyclePhotos1;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamBicyclePhotos2;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos1;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos2;

/**
 * Created by Muhammad Abubakar on 11/11/2017.
 * This is the 2nd last activity where we selected 2 photos
 *
 */

public class BicyclesActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar_bicycle;
    Button BicyclePhotos1, BicyclePhotos2,btn_gotogenerateActivity,BicyclePhotosUnSelect1,BicyclePhotosUnSelect2;

    private ImageView img_BicyclePhotos1,img_BicyclePhotos2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicyle);
        progressBar_bicycle = findViewById(R.id.progressBar_bicycle);
        btn_gotogenerateActivity=findViewById(R.id.btn_gotogenerateActivity);

        initilizeButton(BicyclePhotos1, R.id.BicyclePhotos1);
        initilizeButton(BicyclePhotos2, R.id.BicyclePhotos2);

        BicyclePhotosUnSelect1 = findViewById(R.id.BicyclePhotosUnSelect1);
        BicyclePhotosUnSelect1.setOnClickListener(this);
        BicyclePhotosUnSelect2 = findViewById(R.id.BicyclePhotosUnSelect2);
        BicyclePhotosUnSelect2.setOnClickListener(this);


        img_BicyclePhotos1 = findViewById(R.id.img_BicyclePhotos1);
        img_BicyclePhotos2 = findViewById(R.id.img_BicyclePhotos2);


        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initViews(){
        if (InputstreamBicyclePhotos1 != null){
            Bitmap selectedImage = BitmapFactory.decodeStream(InputstreamBicyclePhotos2);
            img_BicyclePhotos1.setImageBitmap(selectedImage);
            BicyclePhotosUnSelect1.setVisibility(View.VISIBLE);
        }
        if (InputstreamBicyclePhotos2 != null){
            Bitmap selectedImage = BitmapFactory.decodeStream(InputstreamBicyclePhotos2);
            img_BicyclePhotos2.setImageBitmap(selectedImage);
            BicyclePhotosUnSelect2.setVisibility(View.VISIBLE);
        }
    }

    private void initilizeButton(Button button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        if (PermissionsHelper.getPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, R.string.title_storage_permission
                , R.string.text_storage_permission, 1)) {
            switch (view.getId()) {
                case R.id.BicyclePhotos1:
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2001);
                    break;
                case R.id.BicyclePhotos2:
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2002);
                    break;
                case R.id.BicyclePhotosUnSelect1:
                    InputstreamBicyclePhotos1 = null;
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),"UnSelected Successfully");
                    img_BicyclePhotos1.setImageBitmap(null);
                    break;
                case R.id.BicyclePhotosUnSelect2:
                    InputstreamBicyclePhotos2 = null;
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),"UnSelected Successfully");
                    img_BicyclePhotos2.setImageBitmap(null);
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == RESULT_OK) {
                InputStream tempInputStream=  getContentResolver().openInputStream(data.getData());
                Bitmap selectedImage = BitmapFactory.decodeStream(tempInputStream);
                switch (requestCode) {
                    case 2001:
                        DocumentContainer.InputstreamBicyclePhotos1 =  getContentResolver().openInputStream(data.getData());
                        img_BicyclePhotos1.setImageBitmap(selectedImage);
                        BicyclePhotosUnSelect1.setVisibility(View.VISIBLE);
                        break;
                    case 2002:
                        DocumentContainer.InputstreamBicyclePhotos2 =  getContentResolver().openInputStream(data.getData());
                        img_BicyclePhotos2.setImageBitmap(selectedImage);
                        BicyclePhotosUnSelect2.setVisibility(View.VISIBLE);
                        break;
                    default:
                        MessageHelper.showCustomToastError(this, getLayoutInflater(), "You haven't picked Image");
                }
            } else {
                MessageHelper.showCustomToastError(this, getLayoutInflater(), "You haven't picked Image");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MessageHelper.showCustomToastError(this, getLayoutInflater(), "SomeThing went wrong");
        }

    }

    public void gotoGenerateActivity(View view) {
        Intent intent = new Intent(this,GenerateReportActivity.class);
        startActivity(intent);
    }

    public void goBackFromBicycle(View view) {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }
}