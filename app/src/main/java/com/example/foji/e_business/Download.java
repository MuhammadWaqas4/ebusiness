package com.example.foji.e_business;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.storage.UploadTask;

public class Download extends AppCompatActivity {

    public static final String TAG = "MyTag";
    Button upload, select, fetch;
    TextView notify;
    RecyclerView recyclerView;
    Uri pdfuri; // global var // actual url for storage
   // Toolbar toolbar;
    ProgressDialog progressDialog;

    String fileUrl;
    DatabaseReference dbRef;
    FirebaseDatabase mdatabase; // for file's urls
    FirebaseStorage mstorage; // for files storing
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mstorage = FirebaseStorage.getInstance();
        mdatabase = FirebaseDatabase.getInstance();

        fetch=findViewById(R.id.fetchfile);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Download.this, MyRecyclerViewActivity.class));
            }
        });

        notify = findViewById(R.id.notification);
        select =findViewById(R.id.selectfile);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(Download.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    selectPDF();
                }
                else {
                    ActivityCompat.requestPermissions(Download.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }
            }
        });
        upload = findViewById(R.id.uploadfile);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pdfuri!=null)
                    uploadfile(pdfuri);
                else Toast.makeText(Download.this, "plz in Upoad method select file", Toast.LENGTH_SHORT).show();
            }
        });
       /* toolbar = (Toolbar)findViewById(R.id.toolbarID);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Proposals");
        toolbar.setSubtitle("Download");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
*/
        //recyclerView= findViewById(R.id.proposal_recycleviewID);
    }
    private void uploadfile(Uri pdf){
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Upload file()");
        progressDialog.setProgress(0);
        progressDialog.show();

        final String filename= System.currentTimeMillis()+".pdf";
        final String filename1 = System.currentTimeMillis()+"";

        final StorageReference filePath = mstorage.getReference("upload").child(filename);

        dbRef= mdatabase.getReference("Files");


        //String filePath = reference.child(filename);
        filePath.putFile(pdf).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
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
                   Log.d(TAG, "onComplete: Url: "+ downUri.toString());
                }
            }
        });

    /*.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                  reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // getting image uri and converting into string
                        Uri downloadUrl = uri;
                        fileUrl = downloadUrl.toString();
                        dbRef= mdatabase.getReference("Files");
                        dbRef.child(filename1).setValue(fileUrl)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        // dbRef.child("Files").child(filename1).child("Title").setValue("FIle");
                                        // dbRef.child("Files").child(filename1).child("Intro").setValue("Intro");
                                        if (task.isSuccessful()){
                                            Toast.makeText(Download.this, "URL uploaded", Toast.LENGTH_SHORT).show();
                                        }
                                        else Toast.makeText(Download.this, "url not uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
               *//* String url = reference.getDownloadUrl().toString();
               // String rl = reference.child("upload").child(filename).getDownloadUrl().toString();
                dbRef= mdatabase.getReference("Files");
                //dbRef.child("Files").child(filename1).child("URL").setValue(url).addOnCompleteListener(
                 //.child("Files")     //.child("URL")
                dbRef.child(filename1).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       // dbRef.child("Files").child(filename1).child("Title").setValue("FIle");
                       // dbRef.child("Files").child(filename1).child("Intro").setValue("Intro");
                        if (task.isSuccessful()){
                            Toast.makeText(Download.this, "URL uploaded", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(Download.this, "url not uploaded", Toast.LENGTH_SHORT).show();
                    }
                });*//*
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Download.this, "On FAilure not uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int curerentprogress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(curerentprogress);
            }
        });*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPDF();
        }
        else Toast.makeText(this, "Plz onRequestPermissionsResult", Toast.LENGTH_SHORT).show();
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.download_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }*/
    private void selectPDF(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode ==86 && resultCode==RESULT_OK && data!=null)
        {
            pdfuri = data.getData(); // retun uri
        }
        else
            Toast.makeText(this, "plz  onActivityResult select file", Toast.LENGTH_SHORT).show();
    }
}
