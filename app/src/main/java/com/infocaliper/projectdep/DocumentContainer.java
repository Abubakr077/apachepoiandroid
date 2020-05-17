package com.infocaliper.projectdep;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Muhammad Abubakar on 11/10/2017.
 * this is the class where all the document functionalities have been added here is doc formate is declared as well
 */

public class DocumentContainer {

    private static DocumentContainer mDocumentContainer;

    public static String D_bicycleSn = null;
    public static String D_offenceD =null;
    public static String D_offenceT =null;
    public static String D_loc =null;
    public static String D_comp;
    public static String D_rooOff;
    public static String D_rnOff;
    public static String D_rnTime;
    public static String D_rnDate;
    public static String D_remarks="Remarks: Failure to comply with LTA’s notice to remove article or thing within such time as may be specified in the Notice of Removal";
    public static String D_bike_info_1="GBIKES SG PTE LTD / 201716168D";
    public static String D_bike_info_2="OBIKE ASIA PTE LTD / 201632485G";
    public static String D_bike_info_3="OFO SG PTE LTD / 201631125K";
    public static String D_bike_info_4="SINGAPORE MOBIKE PTE LTD / 201625454H";

    private static XWPFDocument document ;
    public static FileOutputStream out = null;

    public static String fileName;
    public static File file;

    private Context mContext;

    public static TinyDB tinydb ;

    public static ArrayList<String> snprListCompnies;
    public static ArrayList<String> snprListRnOfficers;
    public static ArrayList<String> snprListRooOfficers;

    public static InputStream InputstreamGeneralPhotos1;
    public static InputStream InputstreamGeneralPhotos2;
    public static InputStream InputstreamGeneralPhotos3;
    public static InputStream InputstreamGeneralPhotos4;
    public static InputStream InputstreamGeneralPhotos5;
    public static InputStream InputstreamGeneralPhotos6;
    public static InputStream InputstreamBicyclePhotos1;
    public static InputStream InputstreamBicyclePhotos2;
    public static InputStream InputstreamFourhoursPhotos1;
    public static InputStream InputstreamFourhoursPhotos2;
    public static InputStream InputstreamFourhoursPhotos3;
    public static InputStream InputstreamFourhoursPhotos4;

    public DocumentContainer(Context context) {
        mContext = context;
        initProperties();
        tinydb= new TinyDB(context);
        if (loadCompnies().size()>0){
            snprListCompnies = loadCompnies();
        }else {
            snprListCompnies = new ArrayList<>();
            snprListCompnies.add("");
        }
        if (tinydb.getListString("MyRooOfficers").size()>0){
            snprListRooOfficers=tinydb.getListString("MyRooOfficers");
        }else {
            snprListRooOfficers = new ArrayList<>();
            snprListRooOfficers.add("");
        }
        if (tinydb.getListString("MyRnOfficers").size()>0){
            snprListRnOfficers=tinydb.getListString("MyRnOfficers");
        }else {
            snprListRnOfficers = new ArrayList<>();
            snprListRnOfficers.add("");
        }
    }
    public static DocumentContainer get(Context c){
        if(mDocumentContainer==null){
            mDocumentContainer = new DocumentContainer(c.getApplicationContext());
        }
        return mDocumentContainer;
    }

    public static boolean clearDocument(Context context){
        if (file.exists() && DocumentContainer.get(context).getDocument()!=null) {
            try {
                document = null;
                out.flush();
                out.close();
                InputstreamGeneralPhotos1=null;
                InputstreamGeneralPhotos2=null;
                InputstreamGeneralPhotos3=null;
                InputstreamGeneralPhotos4=null;
                InputstreamGeneralPhotos5=null;
                InputstreamGeneralPhotos6=null;
                InputstreamBicyclePhotos1=null;
                InputstreamBicyclePhotos2=null;
                InputstreamFourhoursPhotos1=null;
                InputstreamFourhoursPhotos2=null;
                InputstreamFourhoursPhotos3=null;
                InputstreamFourhoursPhotos4=null;
                D_bicycleSn = null;
                D_offenceD =null;
                D_offenceT =null;
                D_loc =null;
                D_comp=null;
                D_rooOff=null;
                D_rnOff=null;
                D_rnTime=null;
                D_rnDate=null;
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    private static ArrayList<String> loadCompnies(){
        return tinydb.getListString("MyCompanies");
    }
    public static void addCompony(String compony){
        snprListCompnies.add(compony.toUpperCase());
    }
    public static void addRooOfficer(String rooOfficer){
        snprListRooOfficers.add(rooOfficer.toUpperCase());
    }
    public static void addRnOfficer(String rnOfficer){
        snprListRnOfficers.add(rnOfficer.toUpperCase());
    }

    public static boolean deleteCompony(String compony){
        if (snprListCompnies.contains(compony.toUpperCase())){
            snprListCompnies.remove(compony.toUpperCase());
            return true;
        }
        return false;
    }

    public static boolean deleteRooOfficer(String rooOfficer){
        if (snprListRooOfficers.contains(rooOfficer.toUpperCase())){
            snprListRooOfficers.remove(rooOfficer.toUpperCase());
            return true;
        }
        return false;
    }
    public static boolean deleteRnOfficer(String rnOfficer){
        if (snprListRnOfficers.contains(rnOfficer.toUpperCase())){
            snprListRnOfficers.remove(rnOfficer.toUpperCase());
            return true;
        }
        return false;
    }

    public static ArrayList<String> getComponies(){
        return snprListCompnies;
    }
    public static ArrayList<String> getRooOfficers(){
        return snprListRooOfficers;
    }
    public static ArrayList<String> getRnOfficres(){
        return snprListRnOfficers;
    }




    public static XWPFDocument getDocument(){
        return document;
    }
    private static void initProperties(){
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");
    }
    public static boolean openDocument(Context context, LayoutInflater inflator) {
        initProperties();
        document = new XWPFDocument();


        fileName = D_bicycleSn;
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        fileName = fileName +".docx";
        try {
            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString();
            File myDir = new File(root + "/BSO_BLITZER");
            myDir.mkdirs();
            file = new File(myDir, fileName);
            if (file.exists()) {
                MessageHelper.showCustomToastSuccess(context,inflator,"Please wait updating "+fileName+". . . ");
                file.delete();
            }
            out = new FileOutputStream(file);

            XWPFParagraph paragraph = DocumentContainer.get(context).getDocument().createParagraph();

            paragraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun run = paragraph.createRun();
            run.setText("REPORT OF OFFENCE UNDER SWA Section 32A (4)");
            run.setFontSize(16);
            run.setFontFamily("Calibri (Body)");
            run.setBold(true);
            run.addBreak();
            return true;
        } catch (FileNotFoundException e) {
            MessageHelper.showCustomToastError(context,inflator,"Please Enable Permissions from settings");

            e.printStackTrace();
            return false;
        }
    }

    public static void closeDocument() {
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void scanMedia(Context context) {
        MediaScannerConnection.scanFile(context, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
    }

    public static void saveReportExternally(final Context context, final LayoutInflater inflater) {
        try {

            DocumentContainer.get(context).getDocument().write(out);
            DocumentContainer.closeDocument();
            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MessageHelper.showCustomToastSuccess(context, inflater, fileName + " written successfully");
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DocumentContainer.scanMedia(context);

    }

    public static void addPictureFirst(InputStream imageFile, Context context,String title) {//add first image with text paragrapth in the doc
        try {
            XWPFParagraph paragraphPIC = DocumentContainer.get(context).getDocument().createParagraph();
            paragraphPIC.setBorderBottom(Borders.BASIC_BLACK_DASHES);
/*
            paragraphPIC.setBorderLeft(Borders.BASIC_BLACK_DASHES);

            paragraphPIC.setBorderRight(Borders.BASIC_BLACK_DASHES);

            paragraphPIC.setBorderTop(Borders.BASIC_BLACK_DASHES);*/
            paragraphPIC.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun runPIC = paragraphPIC.createRun();
            int format = XWPFDocument.PICTURE_TYPE_JPEG;
            runPIC.setText(title);
            runPIC.setSubscript(VerticalAlign.BASELINE);
            runPIC.addBreak();
            runPIC.addPicture(imageFile, format, "logo.png", Units.toEMU(400), Units.toEMU(300)); // 200x200 pixels
            CTShd cTShd = runPIC.getCTR().addNewRPr().addNewShd();
            cTShd.setVal(STShd.CLEAR);
            cTShd.setColor("auto");
            cTShd.setFill("deeaf6");
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addPicture(InputStream imageFile, Context context) {//add picture selected from galary in the doc
        try {
            XWPFParagraph paragraphPIC = DocumentContainer.get(context).getDocument().createParagraph();
            paragraphPIC.setBorderBottom(Borders.BASIC_BLACK_DASHES);
/*
            paragraphPIC.setBorderLeft(Borders.BASIC_BLACK_DASHES);

            paragraphPIC.setBorderRight(Borders.BASIC_BLACK_DASHES);

            paragraphPIC.setBorderTop(Borders.BASIC_BLACK_DASHES);*/
            paragraphPIC.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun runPIC = paragraphPIC.createRun();
            int format = XWPFDocument.PICTURE_TYPE_JPEG;
            runPIC.addPicture(imageFile, format, "logo.png", Units.toEMU(400), Units.toEMU(300)); // 200x200 pixels
            CTShd cTShd = runPIC.getCTR().addNewRPr().addNewShd();
            cTShd.setVal(STShd.CLEAR);
            cTShd.setColor("auto");
            cTShd.setFill("deeaf6");
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eoInformationTable(Context context) {//eoinformation table formating

        XWPFTable table = DocumentContainer.get(context).getDocument().createTable();

        CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
        width.setType(STTblWidth.DXA);
        width.setW(BigInteger.valueOf(10000));
        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setColor("deeaf6");
        XWPFRun runRowOne = tableRowOne.getCell(0).addParagraph().createRun();
        runRowOne.setBold(true);
        runRowOne.setText("EO INFORMATION");


        //create second row
        XWPFTableRow tableRowTwo = table.createRow();
        tableRowTwo.getCell(0).setText("EO Name: " + D_rooOff);


        //create third row
        XWPFTableRow tableRowThree = table.createRow();
        tableRowThree.getCell(0).setText("BSO Bicycle Number: " + D_bicycleSn);

        XWPFRun addRun = DocumentContainer.get(context).getDocument().createParagraph().createRun();
        addRun.addBreak();
    }

    public static void offenceDetailsTable(Context context) {//offence table formating
        XWPFTable table = DocumentContainer.get(context).getDocument().createTable();

        CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
        width.setType(STTblWidth.DXA);
        width.setW(BigInteger.valueOf(10000));
        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setColor("deeaf6");
        XWPFRun runRowOne = tableRowOne.getCell(0).addParagraph().createRun();
        runRowOne.setBold(true);
        runRowOne.setText("OFFENCE DETAILS");


        //create second row
        XWPFTableRow tableRowTwo = table.createRow();
        tableRowTwo.getCell(0).setText("Date and Time: " + D_offenceD + " at " + D_offenceT);


        //create third row
        XWPFTableRow tableRowThree = table.createRow();
        tableRowThree.getCell(0).setText("Location (with landmark): " + D_loc);

        XWPFTableRow tableRowFour = table.createRow();
        XWPFRun runRowfour = tableRowFour.getCell(0).addParagraph().createRun();
        runRowfour.setBold(true);
        runRowfour.setText(D_remarks);

        XWPFRun addRun = DocumentContainer.get(context).getDocument().createParagraph().createRun();
        addRun.addBreak();
    }

    public static void bikeShareInfoTable(Context context) {//bike share table formating
        XWPFTable tabletemp = DocumentContainer.get(context).getDocument().createTable();

        CTTblWidth widthTable = tabletemp.getCTTbl().addNewTblPr().addNewTblW();
        widthTable.setType(STTblWidth.DXA);
        widthTable.setW(BigInteger.valueOf(10000));
        //create first row
        XWPFTableRow tableRowOnetemp = tabletemp.getRow(0);
        tableRowOnetemp.getCell(0).setColor("deeaf6");
        CTTblWidth cellwidth1=tableRowOnetemp.getCell(0).getCTTc().addNewTcPr().addNewTcW();
        cellwidth1.setW(BigInteger.valueOf(8000));
        cellwidth1.setType(STTblWidth.DXA);
        XWPFRun runRowOnetemp = tableRowOnetemp.getCell(0).addParagraph().createRun();
        runRowOnetemp.setBold(true);
        runRowOnetemp.setText("BIKE SHARE OPERATOR INFORMATION");

        tableRowOnetemp.createCell();
        CTTblWidth cellwidth2=tableRowOnetemp.getCell(1).getCTTc().addNewTcPr().addNewTcW();
        cellwidth2.setW(BigInteger.valueOf(2000));
        cellwidth2.setType(STTblWidth.DXA);
        tableRowOnetemp.getCell(1).setColor("deeaf6");

        XWPFRun runRowOnetemp2 = tableRowOnetemp.getCell(1).addParagraph().createRun();
        runRowOnetemp2.setBold(true);
        runRowOnetemp2.setText("Select (X)");

        //create second row
        XWPFTableRow tableRowTwotemp = tabletemp.createRow();
        tableRowTwotemp.getCell(0).setText(D_bike_info_1);
        tableRowTwotemp.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8000));
        tableRowTwotemp.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));


        //create third row
        XWPFTableRow tableRowThreetemp = tabletemp.createRow();
        tableRowThreetemp.getCell(0).setText(D_bike_info_2);
        tableRowThreetemp.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8000));
        tableRowThreetemp.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

        XWPFTableRow tableRowFourtemp = tabletemp.createRow();
        tableRowFourtemp.getCell(0).setText(D_bike_info_3);
        tableRowFourtemp.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8000));
        tableRowFourtemp.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

        XWPFTableRow tableRowFivetemp = tabletemp.createRow();
        tableRowFivetemp.getCell(0).setText(D_bike_info_4);
        tableRowFivetemp.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8000));
        tableRowFivetemp.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

        XWPFRun addRun = DocumentContainer.get(context).getDocument().createParagraph().createRun();
        addRun.addBreak();
    }


    public static String getFileName(Uri uri, ContentResolver contentResolver) {//get image name that have been selected from the galary
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
