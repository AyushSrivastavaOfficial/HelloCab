package com.jkm.hellocab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    DB ob;
    EditText e3,e4,e5,e6,e7;
    Button b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ob=new DB(this);
        process();
    }
    public void process()
    {
        e3=(EditText)findViewById(R.id.edt3);
        e4=(EditText)findViewById(R.id.edt4);
        e5=(EditText)findViewById(R.id.edt5);
        e6=(EditText)findViewById(R.id.edt6);
        e7=(EditText)findViewById(R.id.edt7);
        b3=(Button)  findViewById(R.id.btn3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn=e3.getText().toString();
                String ln=e4.getText().toString();
                String ei=e5.getText().toString();
                String p1=e6.getText().toString();
                String p2=e7.getText().toString();
                if(!ei.contains("@"))
                    Toast.makeText(MainActivity2.this,"INCORRECT E-Mail ID",Toast.LENGTH_LONG).show();
                else if(!(p1.equals(p2)))
                    Toast.makeText(MainActivity2.this,"Password didn't match",Toast.LENGTH_LONG).show();
                else
                {
                    Boolean flag=ob.dataInsert(fn,ln,ei,p1);
                    if(flag==true)
                        Toast.makeText(MainActivity2.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity2.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
