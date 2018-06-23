package com.jkm.hellocab;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DB obj;
    EditText e1,e2;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obj=new DB(this);
        process();
    }
    public void process()
    {
        e1=(EditText)findViewById(R.id.edt1);
        e2=(EditText)findViewById(R.id.edt2);
        b1=(Button)  findViewById(R.id.btn1);
        b2=(Button)  findViewById(R.id.btn2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=obj.dataShow();
                String e=e1.getText().toString();
                String p=e2.getText().toString();
                if(c.getCount()==0)
                    Toast.makeText(MainActivity.this,"No Data Exists",Toast.LENGTH_SHORT).show();
                else if(e.equals("")&&p.equals(""))
                    Toast.makeText(MainActivity.this,"Please enter your e-mail ID and password",Toast.LENGTH_SHORT).show();
                else if(e.equals(""))
                    Toast.makeText(MainActivity.this,"Please enter your e-mail ID",Toast.LENGTH_SHORT).show();
                else if(p.equals(""))
                    Toast.makeText(MainActivity.this,"Please enter your password",Toast.LENGTH_SHORT).show();
                else
                {
                    int flag=0;
                    while(c.moveToNext())
                    {
                        String dbn=c.getString(0)+" "+c.getString(1);
                        String dbe=c.getString(2);
                        String dbp=c.getString(3);
                        if(dbe.equals(e)&&dbp.equals(p)) {
                            Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("name",dbn);
                            bundle.putString("email",dbe);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            flag=1;
                        }
                    }
                    if(flag==0)
                        Toast.makeText(MainActivity.this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
            }
        });
    }
}
