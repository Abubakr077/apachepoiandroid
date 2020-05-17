package com.infocaliper.projectdep;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

import static com.infocaliper.projectdep.DocumentContainer.D_offenceD;
import static com.infocaliper.projectdep.DocumentContainer.D_rnTime;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos1;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos2;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos3;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos4;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos5;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos6;

/**
 * Created by Muhammad Abubakar on 11/11/2017.
 * this is the secod activity where we selected 6 photos
 */

public class GeneralPhotosActivity extends AppCompatActivity implements View.OnClickListener {
    Button generalPhotos1,generalPhotos2,generalPhotos3,generalPhotos4,generalPhotos5,generalPhotos6,btn_goTobicycleActivity
            ,generalPhotosUnSelect1,generalPhotosUnSelect2,generalPhotosUnSelect3,generalPhotosUnSelect4,generalPhotosUnSelect5
            ,generalPhotosUnSelect6;
    ProgressBar progressBar_general;
    ImageView img_generalPhotos1,img_generalPhotos2,img_generalPhotos3,img_generalPhotos4,img_generalPhotos5,img_generalPhotos6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_photos);
        progressBar_general =findViewById(R.id.progressBar_general);
        btn_goTobicycleActivity = findViewById(R.id.btn_goTobicycleActivity);


        initilizeButton(generalPhotos1,R.id.generalPhotos1);
        initilizeButton(generalPhotos2,R.id.generalPhotos2);
        initilizeButton(generalPhotos3,R.id.generalPhotos3);
        initilizeButton(generalPhotos4,R.id.generalPhotos4);
        initilizeButton(generalPhotos5,R.id.generalPhotos5);
        initilizeButton(generalPhotos6,R.id.generalPhotos6);


        generalPhotosUnSelect1 = findViewById(R.id.generalPhotosUnSelect1);
        generalPhotosUnSelect1.setOnClickListener(this);
        generalPhotosUnSelect2 = findViewById(R.id.generalPhotosUnSelect2);
        generalPhotosUnSelect2.setOnClickListener(this);
        generalPhotosUnSelect3 = findViewById(R.id.generalPhotosUnSelect3);
        generalPhotosUnSelect3.setOnClickListener(this);
        generalPhotosUnSelect4 = findViewById(R.id.generalPhotosUnSelect4);
        generalPhotosUnSelect4.setOnClickListener(this);
        generalPhotosUnSelect5 = findViewById(R.id.generalPhotosUnSelect5);
        generalPhotosUnSelect5.setOnClickListener(this);
        generalPhotosUnSelect6 = findViewById(R.id.generalPhotosUnSelect6);
        generalPhotosUnSelect6.setOnClickListener(this);

        img_generalPhotos1 = findViewById(R.id.img_generalPhotos1);
        img_generalPhotos2 = findViewById(R.id.img_generalPhotos2);
        img_generalPhotos3 = findViewById(R.id.img_generalPhotos3);
        img_generalPhotos4 = findViewById(R.id.img_generalPhotos4);
        img_generalPhotos5 = findViewById(R.id.img_generalPhotos5);
        img_generalPhotos6 = findViewById(R.id.img_generalPhotos6);

        initView();
    }


    private void initilizeButton(Button button,int id){
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView(){
        if (InputstreamGeneralPhotos1 != null){
            Bitmap selectedImage = BitmapFactory.decodeStream(InputstreamGeneralPhotos1);
            img_generalPhotos1.setImageBitmap(selectedImage);
            generalPhotosUnSelect1.setVisibility(View.VISIBLE);
        }
        if (InputstreamGeneralPhotos2 != null){
            Bitmap selectedImage = BitmapFactory.decodeStream(InputstreamGeneralPhotos2);
            img_generalPhotos2.setImageBitmap(selectedImage);
            generalPhotosUnSelect2.setVisibility(View.VISIBLE);
        }
        if (InputstreamGeneralPhotos3 != null){
            Bitmap selectedImage = BitmapFactory.decodeStream(InputstreamGeneralPhotos3);
            img_generalPhotos3.setImageBitmap(selectedImage);
            generalPhotosUnSelect3.setVisibility(View.VISIBLE);
        }
        if (InputstreamGeneralPhotos4 != null){
            Bitmap selectedImage = BitmapFactory.decodeStream(InputstreamGeneralPhotos4);
            img_generalPhotos4.setImageBitmap(selectedImage);
            generalPhotosUnSelect4.setVisibility(View.VISIBLE);
        }
        if (InputstreamGeneralPhotos5 != null){
            Bitmap selectedImage = BitmapFactory.decodeStream(InputstreamGeneralPhotos5);
            img_generalPhotos5.setImageBitmap(selectedImage);
            generalPhotosUnSelect5.setVisibility(View.VISIBLE);
        }
        if (InputstreamGeneralPhotos6 != null){
            Bitmap selectedImage = BitmapFactory.decodeStream(InputstreamGeneralPhotos6);
            img_generalPhotos6.setImageBitmap(selectedImage);
            generalPhotosUnSelect6.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        if (PermissionsHelper.getPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, R.string.title_storage_permission
                , R.string.text_storage_permission, 1)) {

            switch (view.getId()) {
                case R.id.generalPhotos1:
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1001);
                    break;
                case R.id.generalPhotos2:
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1002);
                    break;
                case R.id.generalPhotos3:
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1003);
                    break;
                case R.id.generalPhotos4:
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1004);
                    break;
                case R.id.generalPhotos5:
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1005);
                    break;
                case R.id.generalPhotos6:
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1006);
                    break;
                case R.id.generalPhotosUnSelect1:
                    InputstreamGeneralPhotos1 = null;
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),"UnSelected Successfully");
                    img_generalPhotos1.setImageBitmap(null);
                    break;
                case R.id.generalPhotosUnSelect2:
                    InputstreamGeneralPhotos2 = null;
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),"UnSelected Successfully");
                    img_generalPhotos2.setImageBitmap(null);
                    break;
                case R.id.generalPhotosUnSelect3:
                    InputstreamGeneralPhotos3 = null;
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),"UnSelected Successfully");
                    img_generalPhotos3.setImageBitmap(null);
                    break;
                case R.id.generalPhotosUnSelect4:
                    InputstreamGeneralPhotos4 = null;
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),"UnSelected Successfully");
                    img_generalPhotos4.setImageBitmap(null);
                    break;
                case R.id.generalPhotosUnSelect5:
                    InputstreamGeneralPhotos5 = null;
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),"UnSelected Successfully");
                    img_generalPhotos5.setImageBitmap(null);
                    break;
                case R.id.generalPhotosUnSelect6:
                    InputstreamGeneralPhotos6 = null;
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),"UnSelected Successfully");
                    img_generalPhotos6.setImageBitmap(null);
                    break;
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode==RESULT_OK){
                InputStream tempInputStream=  getContentResolver().openInputStream(data.getData());
                Bitmap selectedImage = BitmapFactory.decodeStream(tempInputStream);
                switch (requestCode){
                    case 1001:
                        InputstreamGeneralPhotos1 =  getContentResolver().openInputStream(data.getData());
                        img_generalPhotos1.setImageBitmap(selectedImage);
                        generalPhotosUnSelect1.setVisibility(View.VISIBLE);
                        break;
                    case 1002:
                        InputstreamGeneralPhotos2 =  getContentResolver().openInputStream(data.getData());
                        img_generalPhotos2.setImageBitmap(selectedImage);
                        generalPhotosUnSelect2.setVisibility(View.VISIBLE);
                        break;
                    case 1003:
                        InputstreamGeneralPhotos3 =  getContentResolver().openInputStream(data.getData());
                        img_generalPhotos3.setImageBitmap(selectedImage);
                        generalPhotosUnSelect3.setVisibility(View.VISIBLE);
                        break;

                    case 1004:
                        InputstreamGeneralPhotos4 =  getContentResolver().openInputStream(data.getData());
                        img_generalPhotos4.setImageBitmap(selectedImage);
                        generalPhotosUnSelect4.setVisibility(View.VISIBLE);
                        break;
                    case 1005:
                        InputstreamGeneralPhotos5 =  getContentResolver().openInputStream(data.getData());
                        img_generalPhotos5.setImageBitmap(selectedImage);
                        generalPhotosUnSelect5.setVisibility(View.VISIBLE);
                        break;
                    case 1006:
                        InputstreamGeneralPhotos6 =  getContentResolver().openInputStream(data.getData());
                        img_generalPhotos6.setImageBitmap(selectedImage);
                        generalPhotosUnSelect6.setVisibility(View.VISIBLE);
                        break;

                    default:
                        MessageHelper.showCustomToastError(this, getLayoutInflater(), "You haven't picked Image");
                        break;
                }
            }else {
                MessageHelper.showCustomToastError(this, getLayoutInflater(), "Your image is not loaded");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MessageHelper.showCustomToastError(this, getLayoutInflater(), "SomeThing went wrong");
        }

    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
               finish();
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


    public void goBack(View view) {
        //if(DocumentContainer.get(this).clearDocument(this,getLayoutInflater())){
            finish();
        /*}else {
            MessageHelper.showCustomToastError(this,getLayoutInflater(),"Something Went Wrong.");
            finish();
        }*/
    }

    public void goTobicycleActivity(View view) {
        Intent intent = new Intent(this,BicyclesActivity.class);
        startActivity(intent);
    }
}
