package com.example.hiep.bds.view.postAD;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.hiep.bds.R;
import java.io.File;

public class ADInforActivity extends AppCompatActivity {
    Toolbar mToolbar;
    ImageView mImageView;
    TextInputEditText mEditTextTitleAD, mEditTextPriceAD, mEditTextAreaAD, mEditTextDescribeAD,
            mEditTextToletAD, mEditTextFloorAD, mEditTextBedRoom, mEditTextBath, mEditTextBalancy;
    CheckBox mCheckBox;
    Button mButtonContinueAD;
    Spinner mSpinner;
    TextView txtUnit;
    int NT, NVS, P, BC, T;
    int yes = 0;
    private final int REQUEST_CODE_CAMERA = 1;
    public static String IMAGE_PATH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_info);
        init();
        setToolbar();
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermistion();
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
        mButtonContinueAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(mEditTextToletAD)) {
                    NVS = 0;
                } else {
                    NVS = Integer.parseInt(mEditTextToletAD.getText().toString());
                }

                if (isEmpty(mEditTextBath)) {
                    NT = 0;
                } else {
                    NT = Integer.parseInt(mEditTextBath.getText().toString());
                }
                if (isEmpty(mEditTextFloorAD)) {
                    T = 0;
                } else {
                    T = Integer.parseInt(mEditTextFloorAD.getText().toString());
                }
                if (isEmpty(mEditTextBalancy)) {
                    BC = 0;
                } else {
                    BC = Integer.parseInt(mEditTextBalancy.getText().toString());
                }
                if (isEmpty(mEditTextBedRoom)) {
                    P = 0;
                } else {
                    P = Integer.parseInt(mEditTextBedRoom.getText().toString());
                }
                if (mCheckBox.isChecked()) {
                    yes = 1;
                }
                if (isEmpty(mEditTextTitleAD)) {
                    Toast.makeText(ADInforActivity.this, "Vui lòng không để trống chủ đề !",
                            Toast.LENGTH_SHORT).show();
                } else if (isEmpty(mEditTextPriceAD)) {
                    Toast.makeText(ADInforActivity.this, "Vui lòng không để trống giá !",
                            Toast.LENGTH_SHORT).show();
                } else if (isEmpty(mEditTextAreaAD)) {
                    Toast.makeText(ADInforActivity.this, "Vui lòng không để trống diện tích  !",
                            Toast.LENGTH_SHORT).show();
                } else if (isEmpty(mEditTextDescribeAD)) {
                    Toast.makeText(ADInforActivity.this, "Vui lòng không để trống mô tả !",
                            Toast.LENGTH_SHORT).show();
                } else if (mEditTextDescribeAD.length() < 50) {
                    Toast.makeText(ADInforActivity.this, "Yêu cầu mô tả trên 50 ký tự",
                            Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor =
                            getSharedPreferences("AD", MODE_PRIVATE).edit();
                    editor.putString("title", mEditTextTitleAD.getText().toString());
                    editor.putFloat("gia", Float.parseFloat(mEditTextPriceAD.getText().toString()));
                    editor.putString("unit", mSpinner.getSelectedItem().toString());
                    editor.putFloat("dientich",
                            Float.parseFloat(mEditTextAreaAD.getText().toString()));
                    editor.putString("mota", mEditTextDescribeAD.getText().toString());
                    editor.putInt("T", T);
                    editor.putInt("P", P);
                    editor.putInt("NVS", NVS);
                    editor.putInt("NT", NT);
                    editor.putInt("BC", BC);
                    editor.putInt("yes", yes);
                    editor.apply();
                    Intent intent = new Intent(ADInforActivity.this, AmentiesActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin bất động sản");
        mToolbar.setNavigationIcon(R.drawable.ic_back_detail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));
    }

    private void init() {
        txtUnit = findViewById(R.id.txtUnit);
        mSpinner = findViewById(R.id.spinnerThue);
        mToolbar = findViewById(R.id.toolbarAD);
        mImageView = findViewById(R.id.imgad);
        mEditTextTitleAD = findViewById(R.id.edtTitleAD);
        mEditTextPriceAD = findViewById(R.id.edtGiaAD);
        mEditTextAreaAD = findViewById(R.id.edtSAD);
        mEditTextDescribeAD = findViewById(R.id.edtMotaAD);
        mButtonContinueAD = findViewById(R.id.btnTTAD);
        mEditTextBalancy = findViewById(R.id.edtBanCong);
        mEditTextToletAD = findViewById(R.id.edtNhaVeSinh);
        mEditTextBath = findViewById(R.id.edtNhaTam);
        mEditTextBedRoom = findViewById(R.id.edtSoPhong);
        mEditTextFloorAD = findViewById(R.id.edtSoTang);
        mCheckBox = findViewById(R.id.yes);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (PorposeActivity.HIHI == true) {
            txtUnit.setText("Đơn vị theo giá");
            String[] testArray = getResources().getStringArray(R.array.ban);
            ArrayAdapter<String> spinnerArrayAdapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, testArray);
            mSpinner.setAdapter(spinnerArrayAdapter);
        } else {
            txtUnit.setText("Giá thuê theo");
            String[] testArray = getResources().getStringArray(R.array.thue);
            ArrayAdapter<String> spinnerArrayAdapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, testArray);
            mSpinner.setAdapter(spinnerArrayAdapter);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == ADInforActivity.this.RESULT_OK) {
            IMAGE_PATH = ReadPathUtil.getPath(ADInforActivity.this, data.getData());
            Uri uri = Uri.fromFile(new File(IMAGE_PATH));
            // Load image
            Glide.with(ADInforActivity.this).load(uri).override(420, 594).centerCrop().into(
                    mImageView);
            Toast.makeText(this, IMAGE_PATH, Toast.LENGTH_LONG).show();
        }
    }

    private boolean isEmpty(TextInputEditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private void checkPermistion() {
        if (ContextCompat.checkSelfPermission(ADInforActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ADInforActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(ADInforActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
    }
    public static class ReadPathUtil {

        /// get path API >19 KITKAT
        public static String getPath(final Context context, final Uri uri) {

            // DocumentProvider
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[] {
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }

            return null;
        }

        /**
         * Get the value of the data column for this Uri. This is useful for
         * MediaStore Uris, and other file-based ContentProviders.
         *
         * @param context The context.
         * @param uri The Uri to query.
         * @param selection (Optional) Filter used in the query.
         * @param selectionArgs (Optional) Selection arguments used in the query.
         * @return The value of the _data column, which is typically a file path.
         */
        public static String getDataColumn(Context context, Uri uri, String selection,
                String[] selectionArgs) {

            Cursor cursor = null;
            final String column = "_data";
            final String[] projection = {
                    column
            };

            try {
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                        null);
                if (cursor != null && cursor.moveToFirst()) {
                    final int column_index = cursor.getColumnIndexOrThrow(column);
                    return cursor.getString(column_index);
                }
            } finally {
                if (cursor != null)
                    cursor.close();
            }
            return null;
        }


        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is ExternalStorageProvider.
         */
        public static boolean isExternalStorageDocument(Uri uri) {
            return "com.android.externalstorage.documents".equals(uri.getAuthority());
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is DownloadsProvider.
         */
        public static boolean isDownloadsDocument(Uri uri) {
            return "com.android.providers.downloads.documents".equals(uri.getAuthority());
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is MediaProvider.
         */
        public static boolean isMediaDocument(Uri uri) {
            return "com.android.providers.media.documents".equals(uri.getAuthority());
        }
    }
}
