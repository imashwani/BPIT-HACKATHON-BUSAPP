package com.example.ashwani.buswaliapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Buspass extends AppCompatActivity {
    int whichPhoto=-1;
    int IMAGE_GALLERY_REQUEST = 20;
    int CAMERA_PERMISSION_REQUEST_CODE = 4192;
    Button camera, gallery;
    String mCurrentPhotoPath;
    int CAMERA_PIC_REQUEST = 8, SELECT_IMAGE = 4;
    Button collegeIDBt,FeeslipBt;
    ImageView collegeIDimg,Feeslipimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buspass);
        collegeIDimg=findViewById(R.id.collegeId_pic);
        Feeslipimg=findViewById(R.id.feeslip_pic);

        collegeIDBt=findViewById(R.id.collegeid_button);
        FeeslipBt=findViewById(R.id.feeslipButton);

        collegeIDBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whichPhoto=1;
                onclick();
            }
        });
        Feeslipimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whichPhoto=2;
                onclick();
            }
        });
    }

    public void onclick(){
        String[] listOptions = new String[]{"Camera", "Gallery"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(Buspass.this);
        builder.setTitle("Select any one").setCancelable(true);
        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setSingleChoiceItems(listOptions, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // Do something
                if (which == 0) {

                        invokeCamera();
                }
                if (which == 1) {
                    Toast.makeText(Buspass.this, "Gallery ke dhikhaoge", Toast.LENGTH_SHORT).show();
                    onImageGalleryClicked();
                }
            }
        });

        // Show the AlertDialog
        final AlertDialog dialog = builder.show();
    }
    void invokeCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(Buspass.this.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.d("", "invokeCamera: " + ex);

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(Buspass.this,
                        "com.example.android.complaintbox",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                startActivityForResult(takePictureIntent, 5);
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 5) {
                File imgFile = new File(mCurrentPhotoPath);
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    ImageView myImage=null;
                    if(whichPhoto==1)
                    {
                        myImage = findViewById(R.id.collegeId_pic);

                    }else if(whichPhoto==2)
                    {
                        myImage = findViewById(R.id.feeslip_pic);
                    }

                    myImage.setImageBitmap(myBitmap);
                }
            }
            // if we are here, everything processed successfully.
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                // if we are here, we are hearing back from the image gallery.

                // the address of the image on the SD Card.
                Uri imageUri = data.getData();

                // declare a stream to read the image data from the SD Card.
                InputStream inputStream;

                // we are getting an input stream, based on the URI of the image.
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    // get a bitmap from the stream.
                    Bitmap image = BitmapFactory.decodeStream(inputStream);

                    ImageView myImage=null;
                    // show the image to the userImageView myImage=null;
                    if(whichPhoto==1)
                    {
                        myImage = findViewById(R.id.collegeId_pic);

                    }else if(whichPhoto==2)
                    {
                        myImage = findViewById(R.id.feeslip_pic);
                    }
                    myImage.setImageBitmap(image);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    // show a message to the user indictating that the image is unavailable.
                    Toast.makeText(Buspass.this, "Unable to open image", Toast.LENGTH_LONG).show();
                }

            }
        }}
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            // we have heard back from our request for camera and write external storage.
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                invokeCamera();
            } else {
                Toast.makeText(Buspass.this,"Can't open camera", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void onImageGalleryClicked() {
        // invoke the image gallery using an implict intent.
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        // where do we want to find the data?
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        // finally, get a URI representation
        Uri data = Uri.parse(pictureDirectoryPath);

        // set the data and type.  Get all image types.
        photoPickerIntent.setDataAndType(data, "image/*");

        // we will invoke this activity, and get something back from it.
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }
    private File createImageFile() throws IOException {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
