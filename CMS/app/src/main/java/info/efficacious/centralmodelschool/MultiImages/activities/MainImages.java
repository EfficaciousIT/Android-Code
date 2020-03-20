package info.efficacious.centralmodelschool.MultiImages.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import id.zelory.compressor.Compressor;
import info.efficacious.centralmodelschool.Interface.DataService;
import info.efficacious.centralmodelschool.MultiImages.helpers.Constants;
import info.efficacious.centralmodelschool.MultiImages.models.Image;
import info.efficacious.centralmodelschool.R;
import info.efficacious.centralmodelschool.activity.MainActivity;
import info.efficacious.centralmodelschool.dialogbox.Gallery_dialogBox;
import info.efficacious.centralmodelschool.fragment.Gallery_fragment;
import info.efficacious.centralmodelschool.webApi.RetrofitInstance;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MainImages extends AppCompatActivity {
        public static ImageView img1, img2, img3, img4;
        public static ArrayList<String> FilePath = new ArrayList<String>();
        public static ArrayList<String> FileName = new ArrayList<String>();
    String pathname,EventDescription;
    Button Open_Gallery, Submit;
    private ProgressDialog progressDoalog;
    private String args;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_images);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        Open_Gallery = (Button) findViewById(R.id.btn_select);
        Submit = (Button) findViewById(R.id.btnSubmit);
        Submit.setVisibility(View.GONE);
        Bundle bundle=getIntent().getExtras();
        try {
            args = bundle.getString("tag");
        }catch (Exception ex){
            Log.d("CMS","MainImages : "+ex);
        }

        Open_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainImages.this, AlbumSelectActivity.class);
                    intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 4);
                    startActivityForResult(intent, Constants.REQUEST_CODE);
                } catch (Exception ex) {

                }

            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (FilePath.size() > 0) {
                        for (int i = 0; i < FilePath.size(); i++) {
                            UploadAsync(i);
                        }
                    } else {
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainImages.this);
                        alert.setMessage("Please Select Images");
                        alert.setPositiveButton("OK", null);
                        alert.show();
                    }
                } catch (Exception ex) {
                    ex.getMessage();
                }


            }
        });
        try {
            Intent intent = new Intent(MainImages.this, AlbumSelectActivity.class);
            intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 4);
            startActivityForResult(intent, Constants.REQUEST_CODE);
        } catch (Exception ex) {

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
            StringBuffer stringBuffer = new StringBuffer();
            try {
                if (args.equals("fromnoticeboard"))
                {
                    if (images.size()==1) {
                        img1.setImageBitmap(BitmapFactory.decodeFile(images.get(0).path));
                        pathname = images.get(0).path;
                        FilePath.add(pathname);
                        FileName.add(images.get(0).name);
                    }else {
                        Toast.makeText(this, "Select only one image", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if (images.size() < 5) {
                        if (images.size() == 0) {
                            Submit.setVisibility(View.GONE);
                        } else {
                            Submit.setVisibility(View.VISIBLE);
                            for (int i = 0, l = images.size(); i < l; i++) {
                                stringBuffer.append(images.get(i).path + "\n");
                                if (i == 0) {
                                    img1.setImageBitmap(BitmapFactory.decodeFile(images.get(i).path));

                                } else if (i == 1) {
                                    img2.setImageBitmap(BitmapFactory.decodeFile(images.get(i).path));

                                } else if (i == 2) {
                                    img3.setImageBitmap(BitmapFactory.decodeFile(images.get(i).path));
                                } else {
                                    img4.setImageBitmap(BitmapFactory.decodeFile(images.get(i).path));
                                }
                                pathname = images.get(i).path;
                                FilePath.add(pathname);
                                FileName.add(images.get(i).name);
                            }
                        }

                    } else {
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainImages.this);
                        alert.setMessage("You Cannot Share more than 4 files");
                        alert.setPositiveButton("OK", null);
                        alert.show();
                    }
                }
            } catch (Exception ex) {
                Log.d("CMS","MainImages: onActivityResult: "+ex);
            }


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void UploadAsync(int i) {
            try {
                EventDescription=Gallery_dialogBox.EventDescriptin;
                    try {
                        String extension = "";
                        progressDoalog = new ProgressDialog(MainImages.this);
                        progressDoalog.setCancelable(false);
                        progressDoalog.setCanceledOnTouchOutside(false);
                        progressDoalog.setMessage("uploading_test...");
                        progressDoalog.show();
                        String fileName = FileName.get(i);
                        String filename = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss_SS", Locale.getDefault()).format(new Date());
                        extension = fileName.substring(fileName.lastIndexOf("."));
                        extension = filename + extension;
                        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
                        String pathname = FilePath.get(i);
                        File file = new File(pathname);
                        File compressedImage = new Compressor(this)
                                .setMaxWidth(250)
                                .setMaxHeight(250)
                                .setQuality(75)
                                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
                                .compressToFile(file);
                        RequestBody requestFile =
                                RequestBody.create(
                                        MediaType.parse("image/*"),
                                        compressedImage
                                );
//                        String path=compressImage(pathname);
//                        File file = new File(compressImage(pathname));
//                        File compressedImgFile = new Compressor(this).compressToFile(file);
//                        File compressedImage = new Compressor(this)
//                                .setMaxWidth(150)
//                                .setMaxHeight(150)
//
//                                .setCompressFormat(Bitmap.CompressFormat.WEBP)
//                                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
//                                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
//                                .compressToFile(file);
//                        RequestBody requestFile =
//                                RequestBody.create(
//                                        MediaType.parse("image/*"),
////                                        compressedImage
//                                        path
//                                );

                        MultipartBody.Part body =
                                MultipartBody.Part.createFormData("picture",  file.getName() , requestFile);
                        Observable<ResponseBody> call = service.upload(body, extension,EventDescription);
                        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(Disposable disposable) {
                                progressDoalog.show();
                            }

                            @Override
                            public void onNext(ResponseBody body) {
                                try {

                                } catch (Exception ex) {
                                    Log.d("Tag","In Catch onNext"+ex.toString());
                                    progressDoalog.dismiss();
                                    Toast.makeText(MainImages.this, "Response Taking Time,Seems Network issue!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable t) {
                                Log.d("Tag","In Catch onError"+t.toString());
                                progressDoalog.dismiss();
                                Toast.makeText(MainImages.this, "Response Taking Time,Seems Network issue!", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onComplete() {
                                progressDoalog.dismiss();
                                if(i==(FilePath.size()-1))
                                {
                                    progressDoalog.dismiss();
                                    Toast.makeText(MainImages.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                    Gallery_fragment gallery_fragment = new Gallery_fragment();
                                    MainActivity.fragmentManager.beginTransaction().replace(R.id.content_main, gallery_fragment).commitAllowingStateLoss();
                                    finish();
                                }
                            }
                        });

                    } catch (Exception ex) {
                        progressDoalog.dismiss();
                        Log.d("Tag","In Catch"+ex.toString());
                        Toast.makeText(this, "in Catch", Toast.LENGTH_SHORT).show();

                    }

        } catch (Exception ex) {
                progressDoalog.dismiss();
                Toast.makeText(this, "in UploadAsync Catch", Toast.LENGTH_SHORT).show();
        }
    }
    public String compressImage(String imageUri) {

        String filePath = getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
//            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 200, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;

    }

    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "MyFolder/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }

    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor =  getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }
}
