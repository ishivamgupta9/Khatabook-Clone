package com.example.khaatabook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalculateAmount extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener{

   TextView titlecal,selectdate,attachbills;
   EditText amount,billdetails;
   Button save;
   RelativeLayout rec;
   String customername;
   String money;

   String data;

    int day, month, year;
    int myYear,myday,myMonth;


    String finalamount,finaldetails,finaldate,finalcustomername;


    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_amount);
        titlecal=findViewById(R.id.titlecalculate);
        amount=findViewById(R.id.amount1);
        save=findViewById(R.id.save1);
        rec=findViewById(R.id.rec);

       ////
        selectdate=findViewById(R.id.selectdate);
        attachbills=findViewById(R.id.attachbills);
        billdetails=findViewById(R.id.billdetails);



        selectdate.setVisibility(View.INVISIBLE);
        attachbills.setVisibility(View.INVISIBLE);
        billdetails.setVisibility(View.INVISIBLE);

        data = getIntent().getExtras().getString("customer");
        titlecal.setText("You gave ₹ " + 0 + " to " + data);


        //select date
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = (String) amount.getText().toString();
                if (!money.equals("")) {
                    titlecal.setText("You gave ₹ " + money + " to " + data);
                }



                    Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(CalculateAmount.this, (DatePickerDialog.OnDateSetListener) CalculateAmount.this, year, month, day);
                datePickerDialog.show();

            }
        });










//        Intent intent = getIntent();
//       titlecal.setText(intent.getStringExtra("customer"));





//       titlecal.setText("You gave ₹ "+money+" to "+data);








//        save.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                String money = (String) amount.getText().toString();
////
////

////            }
////        });





//
//
//                    }
//                }




//      rec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String money = (String) amount.getText().toString();
//                if (!money.equals("")) {
//                    titlecal.setText("You gave ₹ "+money+" to "+data);
//            }}
//        });






        amount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    /* Write your logic here that will be executed when user taps next button */
                    String money = (String) amount.getText().toString();
             if (!money.equals("")) {
                 titlecal.setText("You gave ₹ " + money + " to " + data);








                }
                    handled = true;
                }
                return handled;
            }
        });



        if(amount.getText().toString().length()>0){
           // if (selectdate.getVisibility() == View.INVISIBLE) {
                selectdate.setVisibility(View.VISIBLE);
            }
           // if (attachbills.getVisibility() == View.INVISIBLE){
              //  attachbills.setVisibility(View.VISIBLE);}

           // if (billdetails.getVisibility() == View.INVISIBLE){
                //billdetails.setVisibility(View.VISIBLE);

           // }
        //}


        amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                        // if (selectdate.getVisibility() == View.INVISIBLE) {
                        selectdate.setVisibility(View.VISIBLE);
                    attachbills.setVisibility(View.VISIBLE);
                    billdetails.setVisibility(View.VISIBLE);

            }}
        });


        billdetails.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String money = (String) amount.getText().toString();
                if (!money.equals("")) {
                    titlecal.setText("You gave ₹ " + money + " to " + data);}
            }
        });








        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = (String) amount.getText().toString();
                finalamount = money;

                finaldate = myday + "/" + myMonth + "/" + myYear;
                finaldetails = billdetails.getText().toString();
                finalcustomername = data;


                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Customer");


                UserHelperClass helperClass = new UserHelperClass(finalcustomername, finalamount, finaldate, finaldetails);



                reference= FirebaseDatabase.getInstance().getReference("Customer");
             reference
                        .orderByChild("customername")
                        .equalTo(finalcustomername)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getValue() != null) {

                                    List<UserHelperClass> listItems = new ArrayList<>();

                                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                                        String customername = ds.child("customername").getValue(String.class);
                                        String amount = ds.child("amount").getValue(String.class);
                                        String date = ds.child("date").getValue(String.class);
                                        String billdetail = ds.child("billdetails").getValue(String.class);

                                        UserHelperClass newItem = new UserHelperClass(customername,amount,date,billdetail);
                                        listItems.add(newItem);

                                        Toast.makeText(CalculateAmount.this,"exists"+amount,Toast.LENGTH_LONG).show();

                                       Integer a=Integer.parseInt(finalamount);
                                       Integer b=Integer.parseInt(amount);
                                       Integer c=a+b;




                                        UserHelperClass helperClass1 = new UserHelperClass(finalcustomername,c.toString(), finaldate, finaldetails);



                                        reference.child(finalcustomername).setValue(helperClass1);


                                    }







                                } else {
//                                    Intent intent = new Intent(getApplicationContext(), OTPVerifyActivity.class);
//                                    intent.putExtra("phonenumber", mMobile.getText().toString());
//                                    startActivity(intent);



                                    reference.child(finalcustomername).setValue(helperClass);
                                    Toast.makeText(CalculateAmount.this,"done",Toast.LENGTH_LONG).show();



                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


//
//                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("Customer");

//                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//                DatabaseReference userNameRef = rootRef.child("Customer").child("customername");
//
//                ValueEventListener eventListener = new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if(!dataSnapshot.exists()) {
//                            //create new user
//
//
////                            String amount=  dataSnapshot.getValue().toString();
//                            Toast.makeText(CalculateAmount.this, "exisssts", Toast.LENGTH_LONG).show();
//
//
//                        }else {
//                            Toast.makeText(CalculateAmount.this, "existsa", Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
////                        Log.d(TAG, databaseError.getMessage()); //Don't ignore errors!
//                    }
//                };
//                userNameRef.addListenerForSingleValueEvent(eventListener);
//










           }
      });

    }








    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = dayOfMonth;
        myMonth = month + 1;
        selectdate.setText(myday + "/" + myMonth + "/" + myYear);





    }


}