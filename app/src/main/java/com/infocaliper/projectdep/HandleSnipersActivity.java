package com.infocaliper.projectdep;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.collections4.BagUtils;

import static com.infocaliper.projectdep.DocumentContainer.tinydb;

/**
 * Created by Muhammad Abubakar on 11/12/2017.
 * this is the activity where all the combo boxes or drop downes have been handled
 */

public class HandleSnipersActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_add_company,btn_delete_company,btn_add_rn,btn_delete_rn,btn_add_roo,btn_delete_roo;
    EditText txt_compony_name,txt_rn_officer_name,txt_roo_officer_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_snipers);
        btn_add_company = findViewById(R.id.btn_add_company);
        btn_delete_company= findViewById(R.id.btn_delete_company);
        btn_add_rn= findViewById(R.id.btn_add_rn);
        btn_delete_rn= findViewById(R.id.btn_delete_rn);
        btn_add_roo= findViewById(R.id.btn_add_roo);
        btn_delete_roo= findViewById(R.id.btn_delete_roo);

        txt_compony_name=findViewById(R.id.txt_compony_name);
        txt_rn_officer_name = findViewById(R.id.txt_rn_officer_name);
        txt_roo_officer_name = findViewById(R.id.txt_roo_officer_name);


        btn_add_company.setOnClickListener(this);
        btn_delete_company.setOnClickListener(this);
        btn_add_rn.setOnClickListener(this);
        btn_delete_rn.setOnClickListener(this);
        btn_add_roo.setOnClickListener(this);
        btn_delete_roo.setOnClickListener(this);
    }

    public void updateLists(View view) {
        finishActivity();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivity();
    }

    private void finishActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        tinydb.putListString("MyCompanies",DocumentContainer.get(this).getComponies());
        tinydb.putListString("MyRnOfficers",DocumentContainer.get(this).getRnOfficres());
        tinydb.putListString("MyRooOfficers",DocumentContainer.get(this).getRooOfficers());
        finish();
        startActivity(intent);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_company:
                if (txt_compony_name.getText().length()>0 ){
                    DocumentContainer.addCompony(txt_compony_name.getText().toString());
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),txt_compony_name.getText().toString()+" added successfully");
                    txt_compony_name.setText("");
                }else {
                MessageHelper.showCustomToastError(this,getLayoutInflater(),"Please Enter company Name First");
                }
                break;
            case R.id.btn_delete_company:
                if (txt_compony_name.getText().length()>0 ){
                    if (DocumentContainer.deleteCompony(txt_compony_name.getText().toString())){
                        MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),txt_compony_name.getText().toString()+" deleted successfully");
                        txt_compony_name.setText("");
                    }else {
                    MessageHelper.showCustomToastError(this,getLayoutInflater(),txt_compony_name.getText().toString()+" Does not exist");
                    }
                }else {
                MessageHelper.showCustomToastError(this,getLayoutInflater(),"Please Enter company Name First");
                }
                break;
            case R.id.btn_add_rn:
                if (txt_rn_officer_name.getText().length()>0 ){
                    DocumentContainer.addRnOfficer(txt_rn_officer_name.getText().toString());
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),txt_rn_officer_name.getText().toString()+" added successfully");
                    txt_rn_officer_name.setText("");
                }else {
                    MessageHelper.showCustomToastError(this, getLayoutInflater(), "Please Enter Rn Officer Name First");
                }
                break;
            case R.id.btn_delete_rn:
                if (txt_rn_officer_name.getText().length()>0 ){
                    if (DocumentContainer.deleteRnOfficer(txt_rn_officer_name.getText().toString())){
                        MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),txt_rn_officer_name.getText().toString()+" deleted successfully");
                        txt_rn_officer_name.setText("");
                    }else {
                    MessageHelper.showCustomToastError(this,getLayoutInflater(),txt_rn_officer_name.getText().toString()+" does not exist");
                    }
                }else {
                MessageHelper.showCustomToastError(this,getLayoutInflater(),"Please Enter Rn Officer Name First");
                }
                break;
            case R.id.btn_add_roo:
                if (txt_roo_officer_name.getText().length()>0 ){
                    DocumentContainer.addRooOfficer(txt_roo_officer_name.getText().toString());
                    MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),txt_roo_officer_name.getText().toString()+" added successfully");
                    txt_roo_officer_name.setText("");
                }else {
                    MessageHelper.showCustomToastError(this,getLayoutInflater(),"Please Enter Roo Officer Name First");
                }
                break;
            case R.id.btn_delete_roo:
                if (txt_roo_officer_name.getText().length()>0 ){
                    if (DocumentContainer.deleteRooOfficer(txt_roo_officer_name.getText().toString())){
                        MessageHelper.showCustomToastSuccess(this,getLayoutInflater(),txt_roo_officer_name.getText().toString()+" deleted successfully");
                        txt_roo_officer_name.setText("");
                    }else {
                        MessageHelper.showCustomToastError(this, getLayoutInflater(), txt_roo_officer_name.getText().toString() + " does not exist");
                    }
                }else {
                    MessageHelper.showCustomToastError(this,getLayoutInflater(),"Please Enter Roo Officer Name First");
                }
                break;
        }
    }
}
