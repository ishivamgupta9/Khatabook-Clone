package com.example.khaatabook;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;
import android.content.Context;
import android.content.ContentResolver;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment {
    private final int REQUEST_CODE=99;
    View v;


    static final int CONTACT_PERMISSION_CODE=1;
    TextView set;
    EditText set2;
    String Customername;


   TextView add,viewreport;
    static final int PICK_CONTACT=1;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        add = v.findViewById(R.id.addcustomer);
        viewreport = v.findViewById(R.id.viewreport);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkContactPermission()){
                    pickcontact();
                }
                else
                {
                    requestContactPermission();
                }
            }
        });

        return v;











    }

    private boolean checkContactPermission(){
        boolean result= ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)==(PackageManager.PERMISSION_GRANTED);

        return result;
    }


    private   void requestContactPermission(){
        String[] permission={Manifest.permission.READ_CONTACTS};
        ActivityCompat.requestPermissions(getActivity(),permission,CONTACT_PERMISSION_CODE);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            //call when user click contact from list

            if(requestCode==PICK_CONTACT){
//                viewreport.setText("");
            }

            Cursor cursor1,cursor2;


            //get data from intent
            Uri uri=data.getData();
            cursor1=getContext().getContentResolver().query(uri,null,null,null,null);

            if(cursor1.moveToFirst()){
                //get contact details
                String contactId= cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts._ID));
                String contactName= cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                String idResults= cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
//                String mobile=
//
//
//                int idResultHold=Integer.parseInt(idResults);


             Customername=contactName;
                Intent intent = new Intent(getContext(), messagepage.class);
                intent.putExtra("customer", Customername); // getText() SHOULD NOT be static!!!
                startActivity(intent);






//
//              viewreport.append("ID"+contactId);
//              viewreport.append("\nnane"+contactName);


            }


            cursor1.close();

        }}}

