package com.dbins.android.hellowandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dbins.android.hellowandroid.util.validator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText_id, editText_pwd;
    Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkPreference()){
            Intent intent = new Intent(MainActivity.this, MainContentActivity.class);
            startActivity(intent);
        } else {
            editText_id = findViewById(R.id.et_id);
            editText_pwd = findViewById(R.id.et_pwd);
            bt_login = findViewById(R.id.bt_login);
            bt_login.setOnClickListener(this);
    //        editText_id.setOnKeyListener(mKeylistener);
    //        editText_pwd.setOnKeyListener(mKeylistener);
            editText_id.addTextChangedListener(mWatcher);
            editText_pwd.addTextChangedListener(mWatcher);
        }
    }

    private boolean checkPreference() {
        return ((MainApplication)getApplicationContext()).getPref().getPrefString("ID")!=null;
    }

    TextWatcher mWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            invalidateForm();
        }
    };
//
//    View.OnKeyListener mKeylistener = new View.OnKeyListener(){
//
//        @Override
//        public boolean onKey(View view, int i, KeyEvent keyEvent) {
//            invalidateForm();
//            return false;
//        }
//    };

    private void invalidateForm() {
        Log.d(getPackageName(), "id : " + editText_id.getText().toString());
        Log.d(getPackageName(), "pwd : " + editText_pwd.getText());
        boolean status = validator.isValidEmailForm(editText_id.getText()) && !TextUtils.isEmpty(editText_pwd.getText());
        bt_login.setEnabled(status);
        bt_login.setAlpha(status ? 1.0f : 0.6f);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login :
                ((MainApplication)getApplicationContext()).getPref().saveString("ID",editText_id.getText().toString());
                showDialog();
                break;
        }
    }

    private void showDialog() {
        String id = editText_id.getText().toString();
        String pwd = editText_pwd.getText().toString();
        Log.d(getPackageName(),"id : " + id + "  pwd : " + pwd);
        Toast.makeText(this, "id : " + id + "  pwd : " + pwd, Toast.LENGTH_SHORT).show();

        new AlertDialog.Builder(this).setTitle(getString(R.string.alert))
                .setMessage(getString(R.string.loginMsg)).setCancelable(true).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.idbins.com"));
                        Intent intent = new Intent(MainActivity.this, MainContentActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton(android.R.string.cancel, null)
                .show();
     }

}
