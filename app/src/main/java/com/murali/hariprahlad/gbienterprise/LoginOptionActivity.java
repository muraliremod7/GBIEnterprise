package com.murali.hariprahlad.gbienterprise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginOptionActivity extends AppCompatActivity implements View.OnClickListener{
    Button employee,partneradmin,partneremployee;
    String employeelogin_url = "http://mygbi.tech/";
    String partneradminlogin_url = "http://clientadmin.mygbi.tech/";
    String partneremployeelogin_url ="http://clientemployee.mygbi.tech/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);
        employee = (Button)findViewById(R.id.employee_login);
        employee.setOnClickListener(this);
        partneradmin = (Button)findViewById(R.id.partneradmin_login);
        partneradmin.setOnClickListener(this);
        partneremployee = (Button)findViewById(R.id.partneremployee_login);
        partneremployee.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.employee_login:
                Intent employeelogin = new Intent(LoginOptionActivity.this,LoginActivity.class);
                employeelogin.putExtra("loginOption",employeelogin_url);
                startActivity(employeelogin);
                break;
            case R.id.partneradmin_login:
                Intent partneradminlogin = new Intent(LoginOptionActivity.this,LoginActivity.class);
                partneradminlogin.putExtra("loginOption",partneradminlogin_url);
                startActivity(partneradminlogin);
                break;
            case R.id.partneremployee_login:
                Intent partneremployeelogin = new Intent(LoginOptionActivity.this,LoginActivity.class);
                partneremployeelogin.putExtra("loginOption",partneremployeelogin_url);
                startActivity(partneremployeelogin);
                break;
            default:
        }
    }
}
