package com.example.foji.e_business;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class UploadProposal extends AppCompatActivity {

    public static final String TAG = "MyTag";
    Button uploadboth;
    ImageView chooseimg, choosefile;
    TextView fileName;
    EditText title, intro;
    DatabaseReference dbRef;
    FirebaseStorage mstorage;
    FirebaseDatabase databse;
    StorageReference mStorageRef;

    public Uri pdfuri;

    ProgressDialog progressDialog;
    public Uri imguri;

    public StorageTask tast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_proposal);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // imgview = (ImageView) findViewById(R.id.imgview);
        title = findViewById(R.id.titleID);
        intro = findViewById(R.id.introID);

        fileName = (TextView) findViewById(R.id.filename);
        choosefile = (ImageView) findViewById(R.id.filePicker);
        chooseimg = (ImageView) findViewById(R.id.imagePicker);
        uploadboth = (Button) findViewById(R.id.uploadporposalID);

        mStorageRef = FirebaseStorage.getInstance().getReference("Image");


        //**********************comonnnnnnnnnnnnnnnnnnnnnnnnnmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm***************
        uploadboth.setOnClickListener(new View.OnClickListener() {
            /*String titleview = title.getText().toString();
            String inroview = intro.getText().toString();*/
            @Override
            public void onClick(View view) {

//do something
                //if (title == null || intro ==null)
                //if (TextUtils.isEmpty(titleview) || TextUtils.isEmpty(inroview))
                //Toast.makeText(UploadProposal.this, "Enter a little inroduction & Title", Toast.LENGTH_SHORT).show();
                if (title.getText().toString().trim().equalsIgnoreCase("")) {
                    title.setError("This field can not be blank");
                }
                else if (intro.getText().toString().trim().equalsIgnoreCase("")) {
                    intro.setError("This field can not be blank");
                }
                else {
                       if (pdfuri == null && tast == null)
                        Toast.makeText(UploadProposal.this, "Select Image and Pdf file", Toast.LENGTH_SHORT).show();
                    else {
                        if (pdfuri != null) uploadPDF();
                        else
                            Toast.makeText(UploadProposal.this, "Select File first", Toast.LENGTH_SHORT).show();

                        /////////////////////////////////////////////
                        if (tast != null && tast.isInProgress()) {
                            Toast.makeText(UploadProposal.this, "Uploading in progress", Toast.LENGTH_SHORT).show();
                        } else FileUploader();
                    }
                    //progressDialog.dismiss();
                }
            }
        });
        //*****************************
        //////////////////////////////////////////////////////image choosinggggggggggggggggggg
        chooseimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileChooser();
            }
        });
        //////////////////////////////////////////////////////image choosennnnnnnnn////////////////////////
        mstorage = FirebaseStorage.getInstance();
        databse = FirebaseDatabase.getInstance();


///////////////////////////////////file function startttttttttttttttttttttttttttttttttttt
        choosefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(UploadProposal.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED ) {
                    selectPDF();
                }else {
                    ActivityCompat.requestPermissions(UploadProposal.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }

            }
        });////////////////////////file endeddddddddddddddddddddddddd////////////////////////////////
    }////////////////////////file uploadddddddddddddddddddddddddd////////////////////////////////
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPDF();
        }
        else Toast.makeText(this, "Please Provide Permissions", Toast.LENGTH_SHORT).show();
    }
    private void selectPDF(){
        Intent i = new Intent();
        i.setType("application/pdf");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, 86);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==86 && resultCode == RESULT_OK && data !=null )
        {
            pdfuri = data.getData();
            fileName.setText(data.getData().getLastPathSegment());
        }
        else Toast.makeText(this, "Select File", Toast.LENGTH_SHORT).show();

        if(requestCode == 1 && resultCode == RESULT_OK && data !=null && data.getData()!=null){
            imguri = data.getData();
            chooseimg.setImageURI(imguri);
        }
    }
    private void uploadPDF() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.setTitle("Uploading in progress");
        progressDialog.show();

        final String filename = System.currentTimeMillis()+".pdf";
        final String filename1 = System.currentTimeMillis()+"";
        dbRef= databse.getReference("Files");
        //final StorageReference filePath = mstorage.getReference("upload").child(filename);
        final StorageReference filePath = mstorage.getReference("Files").child(filename);
        filePath.putFile(pdfuri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()){
                    throw task.getException();
                }
                return filePath.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()){
                    Uri downUri = task.getResult();
                    String url = String.valueOf(downUri);
                    dbRef.child(filename1).setValue(url);
                    progressDialog.dismiss();
                    Log.d(TAG, "onComplete: Url: "+ downUri.toString());

                }
            }
        });// returns path and save in obj variable
        /*ref.child("Files").child(filename).putFile(pdfuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url = ref.child("Files").child(filename).getDownloadUrl().toString();
                dbRef = databse.getReference();
                dbRef.child("Files").child(filename).child("url").setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String titleview = title.getText().toString();
                        String inroview = intro.getText().toString();
                        dbRef.child("Files").child(filename).child("Title").setValue(titleview);
                        dbRef.child("Files").child(filename).child("Intro").setValue(inroview);
                        if (task.isSuccessful()){
                            Toast.makeText(UploadProposal.this, "file uploaded", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(UploadProposal.this, "not uploaded", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        })
                *//*.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.toString();
                        DatabaseReference dbRef= databse.getReference();
                        dbRef.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(Uploadfile.this, "File Saved", Toast.LENGTH_SHORT).show();
                                else Toast.makeText(Uploadfile.this, "File Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                        Toast.makeText(Uploadfile.this, "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Uploadfile.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                })*//*
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                // progress bar
                int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
                //Toast.makeText(Uploadfile.this, " FIle is in progress", Toast.LENGTH_SHORT).show();
            }
        });*/
    }///////////////////////////////////////////////////////////////////////////////////////
    //*************************************IMagggggggggggggggggggggggggggggeeeee
    private void FileChooser(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, 1);
    }
    private String getExtension(Uri uri){
        ContentResolver cr= getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void FileUploader(){
        final StorageReference ref = mStorageRef.child(System.currentTimeMillis()+"."+getExtension(imguri));
        tast=ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // //final String filename= System.currentTimeMillis()+"";
                      // // String imurl = ref.getDownloadUrl().toString();
                        //dbRef = databse.getReference();
                        //dbRef.child("Files").child(filename).child("Image").setValue(imgurl);
                      // // dbRef.child("Files").child(filename).child("image").setValue(imurl);
                        // Get a URL to the uploaded content
                        // Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        Toast.makeText(UploadProposal.this, "Image uploaded Successfully", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

}