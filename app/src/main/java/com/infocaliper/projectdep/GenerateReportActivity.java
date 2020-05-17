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
import java.io.IOException;
import java.io.InputStream;

import static com.infocaliper.projectdep.DocumentContainer.D_comp;
import static com.infocaliper.projectdep.DocumentContainer.D_offenceD;
import static com.infocaliper.projectdep.DocumentContainer.D_offenceT;
import static com.infocaliper.projectdep.DocumentContainer.D_rnDate;
import static com.infocaliper.projectdep.DocumentContainer.D_rnTime;
import static com.infocaliper.projectdep.DocumentContainer.D_rooOff;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamFourhoursPhotos1;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamFourhoursPhotos2;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamFourhoursPhotos3;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamFourhoursPhotos4;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos1;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos2;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos3;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos4;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos5;
import static com.infocaliper.projectdep.DocumentContainer.clearDocument;
import static com.infocaliper.projectdep.DocumentContainer.file;
import static com.infocaliper.projectdep.DocumentContainer.fileName;
import static com.infocaliper.projectdep.DocumentContainer.get;
import static com.infocaliper.projectdep.DocumentContainer.out;

/**
 * Created by Muhammad Abubakar on 11/11/2017.
 * this is the last actitvity where we generate the report
 */

public class GenerateReportActivity extends AppCompatActivity implements View.OnClickListener {
    Button fourhoursPhotos1,fourhoursPhotos2,fourhoursPhotos3,fourhoursPhotos4,btn_generate_report,btn_back,
            fourhoursPhotosUnSelect1,fourhoursPhotosUnSelect2,fourhoursPhotosUnSelect3,fourhoursPhotosUnSelect4;
    ProgressBar progressBar_final;
    boolean isReportWritten;

    private ImageView img_fourhoursPhotos1,img_fourhoursPhotos2,img_fourhoursPhotos3,img_fourhoursPhotos4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report);
        progressBar_final =findViewById(R.id.progressBar_final);
        btn_generate_report = findViewById(R.id.btn_generate_report);
        btn_back = findViewById(R.id.btn_back);

        initilizeButton(fourhoursPhotos1,R.id.fourhoursPhotos1);
        initilizeButton(fourhoursPhotos2,R.id.fourhoursPhotos2);
        initilizeButton(fourhoursPhotos3,R.id.fourhoursPhotos3);
        initilizeButton(fourhoursPhotos4,R.id.fourhoursPhotos4);

        fourhoursPhotosUnSelect1 = findViewById(R.id.fourhoursPhotosUnSelect1);
        fourhoursPhotosUnSelect1.setOnClickListener(this);

        fourhoursPhotosUnSelect2 = findViewById(R.id.fourhoursPhotosUnSelect2);
        fourhoursPhotosUnSelect2.setOnClickListener(this);

        fourhoursPhotosUnSelect3 = findViewById(R.id.fourhoursPhotosUnSelect3);
        fourhoursPhotosUnSelect3.setOnClickListener(this);

        fourhoursPhotosUnSelect4 = findViewById(R.id.fourhoursPhotosUnSelect4);
        fourhoursPhotosUnSelect4.setOnClickListener(this);

        img_fourhoursPhotos1 = findViewById(R.id.img_fourhoursPhotos1);
        img_fourhoursPhotos2 = findViewById(R.id.img_fourhoursPhotos2);
        img_fourhoursPhotos3 = findViewById(R.id.img_fourhoursPhotos3);
        img_fourhoursPhotos4 = findViewById(R.id.img_fourhoursPhotos4);

        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initViews(){
        if (InputstreamFourhoursPhotos1 != null){
            Bitmap selectedImage = BitmapFactory.decodeStream(InputstreamFourhoursPhotos1);
            img_fourhoursPhotos1.setImageBitmap(selectedImage);
            fourhoursPhotosUnSelect1.setVisibility(View.VISIBLE);
        }
        if (InputstreamFourhoursPhotos2 != null){
            Bitmap selectedImage = BitmapFactory.decodeStream(InputstreamFourhoursPhotos2);
            img_fourhoursPhotos2.setImageBitmap(selectedImage);
            fourhoursPhotosUnSelect2.setVisibility(View.VISIBLE);
        }
        if (InputstreamFourhoursPhotos3 != null){
            Bitmap selectedImage = BitmapFactory.decodeStream(InputstreamFourhoursPhotos3);
            img_fourhoursPhotos3.setImageBitmap(selectedImage);
            fourhoursPhotosUnSelect3.setVisibility(View.VISIBLE);
        }
        if (InputstreamFourhoursPhotos4 != null){
            Bitmap selectedImage = BitmapFactory.decodeStream(InputstreamFourhoursPhotos4);
            img_fourhoursPhotos4.setImageBitmap(selectedImage);
            fourhoursPhotosUnSelect4.setVisibility(View.VISIBLE);
        }
    }

    private void initilizeButton(Button button,int id){
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
                case R.id.fourhoursPhotos1:
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3001);
                    break;
                case R.id.fourhoursPhotos2:
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3002);
                    break;
                case R.id.fourhoursPhotos3:
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3003);
                    break;
                case R.id.fourhoursPhotos4:
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3004);
                    break;
                case R.id.fourhoursPhotosUnSelect1:
                    InputstreamFourhoursPhotos1 = null;
                    img_fourhoursPhotos1.setImageBitmap(null);
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),"UnSelected Successfully");
                    break;
                case R.id.fourhoursPhotosUnSelect2:
                    InputstreamFourhoursPhotos2 = null;
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),"UnSelected Successfully");
                    img_fourhoursPhotos2.setImageBitmap(null);
                    break;
                case R.id.fourhoursPhotosUnSelect3:
                    InputstreamFourhoursPhotos3 = null;
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),"UnSelected Successfully");
                    img_fourhoursPhotos3.setImageBitmap(null);
                    break;
                case R.id.fourhoursPhotosUnSelect4:
                    InputstreamFourhoursPhotos4 = null;
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),"UnSelected Successfully");
                    img_fourhoursPhotos4.setImageBitmap(null);
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
                    case 3001:
                        DocumentContainer.InputstreamFourhoursPhotos1 = getContentResolver().openInputStream(data.getData());
                        img_fourhoursPhotos1.setImageBitmap(selectedImage);
                        fourhoursPhotosUnSelect1.setVisibility(View.VISIBLE);
                        break;
                    case 3002:
                        DocumentContainer.InputstreamFourhoursPhotos2 =  getContentResolver().openInputStream(data.getData());
                        img_fourhoursPhotos2.setImageBitmap(selectedImage);
                        fourhoursPhotosUnSelect2.setVisibility(View.VISIBLE);
                        break;
                    case 3003:
                        DocumentContainer.InputstreamFourhoursPhotos3 =  getContentResolver().openInputStream(data.getData());
                        img_fourhoursPhotos3.setImageBitmap(selectedImage);
                        fourhoursPhotosUnSelect3.setVisibility(View.VISIBLE);
                        break;

                    case 3004:
                        DocumentContainer.InputstreamFourhoursPhotos4 =  getContentResolver().openInputStream(data.getData());
                        img_fourhoursPhotos4.setImageBitmap(selectedImage);
                        fourhoursPhotosUnSelect4.setVisibility(View.VISIBLE);
                        break;

                    default:
                        MessageHelper.showCustomToastError(this, getLayoutInflater(), "You haven't picked Image");
                }
            }else {
                MessageHelper.showCustomToastError(this, getLayoutInflater(), "You haven't picked Image");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MessageHelper.showCustomToastError(this, getLayoutInflater(), "SomeThing went wrong");
        }

    }

    public void generateReport(View view) {
        //action that is performed when you click generate report button
        btn_generate_report.setEnabled(false);
        btn_back.setEnabled(false);
        progressBar_final.setVisibility(View.VISIBLE);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if (DocumentContainer.openDocument(GenerateReportActivity.this,getLayoutInflater())){//create doc in file manager and open document first
                    DocumentContainer.eoInformationTable(GenerateReportActivity.this);//add first table in doc
                    DocumentContainer.offenceDetailsTable(GenerateReportActivity.this);//add 2nd table in doc
                    DocumentContainer.bikeShareInfoTable(GenerateReportActivity.this);//add 3rd table in doc
                    uploadAllPhotos();//add all the images that youhave selected before


                    DocumentContainer.saveReportExternally(GenerateReportActivity.this,getLayoutInflater());//save doc file in the file manager
                    isReportWritten=true;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar_final.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });


    }

    public void goBakcFromGenerateReport(View view) {

        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isReportWritten){
            if (DocumentContainer.clearDocument(this)){//go to main activity / first activity again by clicking home button
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }else {
                MessageHelper.showCustomToastError(this,getLayoutInflater(),"Please Restarts the application");
            }
        }
    }

    private void uploadAllPhotos(){

                if (DocumentContainer.InputstreamGeneralPhotos1 != null){
                    DocumentContainer.addPictureFirst(DocumentContainer.InputstreamGeneralPhotos1,GenerateReportActivity.this,"Photos showing the general views of the obstruction or inconvenience caused when "+DocumentContainer.D_rnOff +" arrived at "+DocumentContainer.D_loc +" on "+ D_rnDate +".RN was issued at "+ D_rnTime);
                }
                if (DocumentContainer.InputstreamGeneralPhotos2 != null){
                    DocumentContainer.addPicture(DocumentContainer.InputstreamGeneralPhotos2,GenerateReportActivity.this);
                }
                if (DocumentContainer.InputstreamGeneralPhotos3 != null){
                    DocumentContainer.addPicture(DocumentContainer.InputstreamGeneralPhotos3,GenerateReportActivity.this);
                }
                if (DocumentContainer.InputstreamGeneralPhotos4 != null){
                    DocumentContainer.addPicture(DocumentContainer.InputstreamGeneralPhotos4,GenerateReportActivity.this);
                }
                if (DocumentContainer.InputstreamGeneralPhotos5 != null){
                    DocumentContainer.addPicture(DocumentContainer.InputstreamGeneralPhotos5,GenerateReportActivity.this);
                }
                if (DocumentContainer.InputstreamGeneralPhotos6 != null){
                    DocumentContainer.addPicture(DocumentContainer.InputstreamGeneralPhotos6,GenerateReportActivity.this);
                }
                if (DocumentContainer.InputstreamBicyclePhotos1 != null) {
                    DocumentContainer.addPictureFirst(DocumentContainer.InputstreamBicyclePhotos1, GenerateReportActivity.this,"Photos showing the bicycles being moved to ease the obstruction or inconvenience caused.");
                }
                if (DocumentContainer.InputstreamBicyclePhotos2 != null) {
                    DocumentContainer.addPicture(DocumentContainer.InputstreamBicyclePhotos2, GenerateReportActivity.this);
                }
                if (DocumentContainer.InputstreamFourhoursPhotos1 != null){
                    DocumentContainer.addPictureFirst(DocumentContainer.InputstreamFourhoursPhotos1,GenerateReportActivity.this,"Photos showing the situation when "+ D_rooOff +" returned after a minimum of 4 hours. Bike-Share Operator "+ D_comp +" has failed to remove the bicycle(s) as stipulated in the Notice of Removal.");
                }
                if (DocumentContainer.InputstreamFourhoursPhotos2 != null){
                    DocumentContainer.addPicture(DocumentContainer.InputstreamFourhoursPhotos2,GenerateReportActivity.this);
                }
                if (DocumentContainer.InputstreamFourhoursPhotos3 != null){
                    DocumentContainer.addPicture(DocumentContainer.InputstreamFourhoursPhotos3,GenerateReportActivity.this);
                }
                if (DocumentContainer.InputstreamFourhoursPhotos4 != null) {
                    DocumentContainer.addPicture(DocumentContainer.InputstreamFourhoursPhotos4, GenerateReportActivity.this);
                }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                if (isReportWritten){
                    if (DocumentContainer.clearDocument(this)){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                    }else {
                        MessageHelper.showCustomToastError(this,getLayoutInflater(),"Please Restarts the application");
                    }
                }else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);
                }
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

