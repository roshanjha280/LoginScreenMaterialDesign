package com.roshanjha.loginscreenmd;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    EditText _nameText3;
    EditText _addressText3;
    EditText _emailText3;
    EditText _mobileText3;
    EditText _bloodGroup3;
    TextView _lastDonated3;
    EditText _passwordText3;
    EditText _reEnterPasswordText3;
    Button _signupButton3;
    TextView _loginLink3;
    Switch _switch3;

    DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        _nameText3 = (EditText)findViewById(R.id.input_name3);
        _addressText3 = (EditText)findViewById(R.id.input_address3);
        _emailText3 = (EditText)findViewById(R.id.input_email3);
        _mobileText3 = (EditText)findViewById(R.id.input_mobile3);
        _bloodGroup3 = (EditText)findViewById(R.id.input_bloodgroup3);
        _lastDonated3 = (TextView)findViewById(R.id.input_lastdonated3);
        _passwordText3 =(EditText)findViewById(R.id.input_password3);
        _reEnterPasswordText3 = (EditText)findViewById(R.id.input_reEnterPassword3);
        _signupButton3 = (Button)findViewById(R.id.btn_signup3);
        _loginLink3 = (TextView)findViewById(R.id.link_login3);
        _switch3 = (Switch)findViewById(R.id.switch3);

        _signupButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        _switch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(_switch3.isChecked()){
                    startActivity(new Intent(SignupActivity.this, EmpSignupActivity.class));
                }
            }
        });

        _lastDonated3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SignupActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                _lastDonated3.setText(date);
            }
        };

    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton3.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

       /* String name = _nameText3.getText().toString();
        String address = _addressText3.getText().toString();
        String email = _emailText3.getText().toString();
        String mobile = _mobileText3.getText().toString();
        String password = _passwordText3.getText().toString();
        String reEnterPassword = _reEnterPasswordText3.getText().toString();
        */

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onSignupSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton3.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton3.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText3.getText().toString();
        String address = _addressText3.getText().toString();
        String email = _emailText3.getText().toString();
        String mobile = _mobileText3.getText().toString();
        String bloodGroup = _bloodGroup3.getText().toString();
        String lastDonated = _lastDonated3.getText().toString();
        String password = _passwordText3.getText().toString();
        String reEnterPassword = _reEnterPasswordText3.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText3.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText3.setError(null);
        }

        if (address.isEmpty()) {
            _addressText3.setError("Enter Valid Address");
            valid = false;
        } else {
            _addressText3.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText3.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText3.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            _mobileText3.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText3.setError(null);
        }

        if (bloodGroup.isEmpty() || bloodGroup != "O+ve" || bloodGroup != "O-ve" || bloodGroup != "A+ve" || bloodGroup != "A-ve" || bloodGroup != "B+ve" || bloodGroup != "B-ve" || bloodGroup != "AB+ve" || bloodGroup != "AB-ve") {
            _bloodGroup3.setError("Enter Valid Blood Group(O+,O-,A+,A-,B+,B-,AB+,AB-)");
            valid = false;
        } else {
            _bloodGroup3.setError(null);
        }

        if (lastDonated.isEmpty()) {
            _bloodGroup3.setError("Enter Valid Blood Group(O+,O-,A+,A-,B+,B-,AB+,AB-)");
            valid = false;
        } else {
            _bloodGroup3.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText3.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText3.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText3.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText3.setError(null);
        }

        return valid;
    }
}
