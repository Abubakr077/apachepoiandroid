package com.infocaliper.projectdep;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.infocaliper.projectdep.DocumentContainer.D_bicycleSn;
import static com.infocaliper.projectdep.DocumentContainer.D_bike_info_1;
import static com.infocaliper.projectdep.DocumentContainer.D_bike_info_2;
import static com.infocaliper.projectdep.DocumentContainer.D_bike_info_3;
import static com.infocaliper.projectdep.DocumentContainer.D_bike_info_4;
import static com.infocaliper.projectdep.DocumentContainer.D_comp;
import static com.infocaliper.projectdep.DocumentContainer.D_loc;
import static com.infocaliper.projectdep.DocumentContainer.D_offenceD;
import static com.infocaliper.projectdep.DocumentContainer.D_offenceT;
import static com.infocaliper.projectdep.DocumentContainer.D_remarks;
import static com.infocaliper.projectdep.DocumentContainer.D_rnDate;
import static com.infocaliper.projectdep.DocumentContainer.D_rnOff;
import static com.infocaliper.projectdep.DocumentContainer.D_rnTime;
import static com.infocaliper.projectdep.DocumentContainer.D_rooOff;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamBicyclePhotos1;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamBicyclePhotos2;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamFourhoursPhotos1;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamFourhoursPhotos2;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamFourhoursPhotos3;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamFourhoursPhotos4;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos1;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos2;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos3;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos4;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos5;
import static com.infocaliper.projectdep.DocumentContainer.InputstreamGeneralPhotos6;
import static com.infocaliper.projectdep.DocumentContainer.snprListCompnies;
import static com.infocaliper.projectdep.DocumentContainer.snprListRnOfficers;
import static com.infocaliper.projectdep.DocumentContainer.snprListRooOfficers;

/**
 * Created by Muhammad Abubakar on 11/11/2017.
 * This is the first activity where we select all the info and give all the info here
 *
 */

public class MainActivity extends AppCompatActivity {
    TextView txt_bicycle,txt_location,txt_offence_date,txt_offence_time,txt_time,txt_rn_date;
    Spinner snpr_roo,snpr_rn,snpr_compony;
    ProgressBar progressBar_main;


    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date,rnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar_main= findViewById(R.id.progressBar_main);

        txt_bicycle = findViewById(R.id.txt_bicycle);
        txt_location = findViewById(R.id.txt_location);
        txt_offence_date = findViewById(R.id.txt_offence_date);
        txt_offence_time = findViewById(R.id.txt_offence_time);
        txt_time = findViewById(R.id.txt_time);
        txt_rn_date= findViewById(R.id.txt_rn_date);

        snpr_compony = findViewById(R.id.snpr_company);
        snpr_rn = findViewById(R.id.spnr_rn_officer);
        snpr_roo = findViewById(R.id.roo_snpr);


        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // This is the date picker of offence date
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        rnDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // This is the date picker of rn date
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabe2();
            }

        };

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DocumentContainer.get(this).getComponies());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snpr_compony.setAdapter(dataAdapter);
        snpr_compony.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //this is the combo box of company

                String item = adapterView.getItemAtPosition(i).toString();
                D_comp = item;
                //Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> rnAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (DocumentContainer.get(this)).getRnOfficres());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snpr_rn.setAdapter(rnAdapter);
        snpr_rn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //this is the combo box of rn officer

                String item = adapterView.getItemAtPosition(i).toString();
                D_rnOff = item;
                //Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> rooAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DocumentContainer.get(this).getRooOfficers());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snpr_roo.setAdapter(rooAdapter);
        snpr_roo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //this is the combo box of roo officer

                String item = adapterView.getItemAtPosition(i).toString();
                D_rooOff = item;
                //Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void deleteReport(View view) {
        openDocumentFromFileManager();
    }

    private void updateViews(){
        txt_bicycle.setText(D_bicycleSn);
        txt_location.setText(D_loc);
        txt_offence_date.setText(D_offenceD);
        txt_offence_time.setText(D_offenceT);
        txt_time.setText(D_rnTime);
        txt_rn_date.setText(D_rnDate);


        for (int i=0;i<snprListCompnies.size();i++){
            if (snprListCompnies.get(i).equals(D_comp)){
                snpr_compony.setSelection(i);
            }
        }

        for (int i=0;i<snprListRooOfficers.size();i++){
            if (snprListRooOfficers.get(i).equals(D_rooOff)){
                snpr_roo.setSelection(i);
            }
        }

        for (int i=0;i<snprListRnOfficers.size();i++){
            if (snprListRnOfficers.get(i).equals(D_rnOff)){
                snpr_rn.setSelection(i);
            }
        }

    }

    //photo button clicks


    public void generalPhotos(View view) {

        //this is the button action of next from main activity

        progressBar_main.setVisibility(View.VISIBLE);

        if (txt_bicycle.getText().length()>0){
            D_bicycleSn = txt_bicycle.getText().toString();
        }
        if (txt_location.getText().length()>0){
            D_loc = txt_location.getText().toString();
        }
        if (txt_offence_time.getText().length()>0){
            D_offenceT =txt_offence_time.getText().toString();
        }
        if (txt_offence_date.getText().length()>0){
            D_offenceD =txt_offence_date.getText().toString();
        }
        if (txt_time.getText().length()>0){
            D_rnTime =txt_time.getText().toString();
        }
        if (txt_rn_date.getText().length()>0){
            D_rnDate=txt_rn_date.getText().toString();
        }

        Intent intent = new Intent(MainActivity.this,GeneralPhotosActivity.class);
        progressBar_main.setVisibility(View.GONE);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.handle_snipers:
                //this is the action that is performed when box button is clicke to go to combo boxes activity
                Intent intent = new Intent(this,HandleSnipersActivity.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.handle_old_doc:
                openDocumentFromFileManager();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openDocumentFromFileManager() {
        //this is the action to open doc file from file manager
        Intent i = new Intent();
        i.setType("application/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        if (PermissionsHelper.getPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, R.string.title_storage_permission
                , R.string.text_storage_permission, 1111)) {

            startActivityForResult(Intent.createChooser(i, "Select Document"), 111);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode==RESULT_OK){
                switch (requestCode){
                    case 111:
                        //this is action performed after openDocumentFromFileManager() when doc is selected
                        FileInputStream inputStream = (FileInputStream) getContentResolver().openInputStream(data.getData());
                        XWPFDocument docx = new XWPFDocument(inputStream);

                        extractImages(docx);

                        List<XWPFParagraph> paragraphList = docx.getParagraphs();
                        int paragrapthCount = 0;
                        for (XWPFParagraph paragraph:paragraphList){
                            if (paragraph.getText().contains("Photos showing the ")){
                                String paragrapthText = paragraph.getText();
                                if (paragrapthCount==0){
                                    ///"Photos showing the general views of the obstruction or inconvenience caused when "+DocumentContainer.D_rnOff +" arrived at "+DocumentContainer.D_loc +" on "+ D_rnDate +".RN was issued at "+ D_rnTime

                                    String rnof = paragrapthText.substring(paragrapthText.indexOf("when ")+5,paragrapthText.indexOf(" arrived at "));
                                    //String location = paragrapthText.substring(paragrapthText.indexOf(" arrived at ")+12,paragrapthText.indexOf(" on "));
                                    String rndate = paragrapthText.substring(paragrapthText.indexOf(" on ")+4,paragrapthText.indexOf(".RN"));
                                    String rntime = paragrapthText.substring(paragrapthText.indexOf("issued at ")+10,paragrapthText.length()-1);

                                    D_rnOff = rnof;
                                    D_rnDate = rndate;
                                    D_rnTime = rntime;

                                    System.out.println("Rn oof:"+rnof+rndate+rntime);
                                }
                                else if (paragrapthCount == 2){

                                    String company = paragrapthText.substring(paragrapthText.indexOf("Operator ")+9,paragrapthText.indexOf(" has failed"));

                                    D_comp = company;
                                    System.out.println(company);
                                }
                                paragrapthCount++;
                            }
                        }

                        List<XWPFTable> tableList = docx.getTables();

                        int count=0;
                        for (XWPFTable table:tableList){

                            for (int i=0;i<table.getNumberOfRows();i++){
                                String cell = table.getRows().get(i).getCell(0).getText();
                                switch (count){
                                    case 0:
                                        if (i==1){
                                            System.out.println("EO INFORMATION: "+cell);
                                            cell = cell.substring(cell.indexOf(": ")+2,cell.length());
                                            D_rooOff = cell;
                                        }
                                        if (i==2){
                                            System.out.println("EO INFORMATION: "+cell);
                                            cell = cell.substring(cell.indexOf(": ")+2,cell.length());
                                            D_bicycleSn = cell;
                                        }
                                        break;
                                    case 1:
                                        if (i==1){
                                            System.out.println("OFFENCE DETAILS: "+cell);  ///D_offenceD + " at " + D_offenceT
                                            cell = cell.substring(cell.indexOf(": ")+2,cell.length());
                                            D_offenceD = cell.substring(0,cell.indexOf(" at "));
                                            D_offenceT = cell.substring(cell.indexOf(" at ")+4,cell.length());

                                        }
                                        if (i==2){
                                            System.out.println("OFFENCE DETAILS: "+cell);
                                            cell = cell.substring(cell.indexOf(": ")+2,cell.length());
                                            D_loc = cell;
                                        }
                                        if (i==3){
                                            System.out.println("OFFENCE DETAILS: "+cell);
                                            D_remarks = cell;
                                        }
                                        break;
                                    case 2:
                                        if (i==1){
                                            System.out.println("BIKE SHARE OPERATOR INFORMATION: "+cell);
                                            D_bike_info_1 = cell;
                                        }
                                        if (i==2){
                                            System.out.println("BIKE SHARE OPERATOR INFORMATION: "+cell);
                                            D_bike_info_2 = cell;
                                        }
                                        if (i==3){
                                            System.out.println("BIKE SHARE OPERATOR INFORMATION: "+cell);
                                            D_bike_info_3 = cell;
                                        }
                                        if (i==4){
                                            System.out.println("BIKE SHARE OPERATOR INFORMATION: "+cell);
                                            D_bike_info_4 = cell;
                                        }
                                        break;
                                }
                            }
                            count++;
                        }
                        updateViews();

                        break;
                }
            }else {
                MessageHelper.showCustomToastError(this, getLayoutInflater(), "Your image is not loaded");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MessageHelper.showCustomToastError(this, getLayoutInflater(), "SomeThing went wrong");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_snipers, menu);
        return true;
    }

    public void offenceDatePicker(View view) {
        //this is the offence date picker updateLabel() is also included in it
        new DatePickerDialog(MainActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txt_offence_date.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabe2() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txt_rn_date.setText(sdf.format(myCalendar.getTime()));
    }

    public void offenceTimePicker(View view) {
        //offence time picker
        int mHour,mMinute;
        final String myFormat = "hh:mm a"; //In which you need put here
        final SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        txt_offence_time.setText(sdf.format(c.getTime()));
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void rnTimerPicker(View view) {
        //rn time picker action
        final int mHour,mMinute;
        final String myFormat = "hh:mm a"; //In which you need put here
        final SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        txt_time.setText(sdf.format(c.getTime()));

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void rnDatePicker(View view) {
        new DatePickerDialog(MainActivity.this, rnDate, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public static void extractImages(XWPFDocument docx){
        try {

            //function to retrieve all the images from the doc file that have been selected
//
//            HWPFDocument wordDoc = new HWPFDocument(new FileInputStream(fileName));
//
//            XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
            List<XWPFPictureData> picList = docx.getAllPackagePictures();

            int i = 1;

            for (XWPFPictureData pic : picList) {

                //System.out.print(pic.getPictureType());
                //System.out.print(pic.getData());
                System.out.println("Image Number: " + i + " " + pic.getFileName());
                switch (i){
                    case 1:
                        InputstreamGeneralPhotos1 = new ByteArrayInputStream(pic.getData());
                        break;
                    case 2:
                        InputstreamGeneralPhotos2 = new ByteArrayInputStream(pic.getData());
                        break;
                    case 3:
                        InputstreamGeneralPhotos3 = new ByteArrayInputStream(pic.getData());
                        break;
                    case 4:
                        InputstreamGeneralPhotos4 = new ByteArrayInputStream(pic.getData());
                        break;
                    case 5:
                        InputstreamGeneralPhotos5 = new ByteArrayInputStream(pic.getData());
                        break;
                    case 6:
                        InputstreamGeneralPhotos6 = new ByteArrayInputStream(pic.getData());
                        break;
                    case 7:
                        InputstreamBicyclePhotos1 = new ByteArrayInputStream(pic.getData());
                        break;
                    case 8:
                        InputstreamBicyclePhotos2 = new ByteArrayInputStream(pic.getData());
                        break;
                    case 9:
                        InputstreamFourhoursPhotos1 = new ByteArrayInputStream(pic.getData());
                        break;
                    case 10:
                        InputstreamFourhoursPhotos2 = new ByteArrayInputStream(pic.getData());
                        break;
                    case 11:
                        InputstreamFourhoursPhotos3 = new ByteArrayInputStream(pic.getData());
                        break;
                    case 12:
                        InputstreamFourhoursPhotos4 = new ByteArrayInputStream(pic.getData());
                        break;
                }
                i++;
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
