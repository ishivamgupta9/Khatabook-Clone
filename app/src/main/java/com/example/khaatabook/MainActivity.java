package com.example.khaatabook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {

    static final int PICK_CONTACT=1;
    static final int CONTACT_PERMISSION_CODE=1;
    TextView set;
    FloatingActionButton add;
    EditText set2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        add = findViewById(R.id.addcustomer);
        set = findViewById(R.id.set);
        set2 = findViewById(R.id.set2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//
//                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
//                startActivityForResult(intent , PICK_CONTACT
//
//                );


          if (checkContactPermission()){
              pickcontact();
          }
          else
          {
              requestContactPermission();
          }












            }
        });

    }


 private boolean checkContactPermission(){
        boolean result= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)==(PackageManager.PERMISSION_GRANTED);

    return result;
    }


 private   void requestContactPermission(){
        String[] permission={Manifest.permission.READ_CONTACTS};
        ActivityCompat.requestPermissions(this,permission,CONTACT_PERMISSION_CODE);
    }





    private void pickcontact() {

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent,PICK_CONTACT);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==CONTACT_PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                pickcontact();
            }
        }


    }








    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            //call when user click contact from list

            if(requestCode==PICK_CONTACT){
                set.setText("");
            }

            Cursor cursor1,cursor2;


            //get data from intent
            Uri uri=data.getData();
            cursor1=getContentResolver().query(uri,null,null,null,null);

            if(cursor1.moveToFirst()){
                //get contact details
                String contactId= cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts._ID));
                String contactName= cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                String idResults= cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
//                String mobile=
//
//
//                int idResultHold=Integer.parseInt(idResults);








               set.append("ID"+contactId);
               set.append("\nnane"+contactName);


                }


            cursor1.close();

            }


    }



        }





