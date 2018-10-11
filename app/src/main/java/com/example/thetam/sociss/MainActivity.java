package com.example.thetam.sociss;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;

public class MainActivity extends AppCompatActivity {
    Button Date;
    Button Time,them;
    TextView text1,text2;
    TextView text3,text4;
    ListView list;
    EditText edt1,edt2;
    ArrayList<String> ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1=(EditText) findViewById(R.id.edt1);
        edt2=(EditText) findViewById(R.id.edt2);
        text3= (TextView) findViewById(R.id.text3);
        text4=(TextView) findViewById(R.id.text4);
        Date=(Button) findViewById(R.id.btt1);
        Time=(Button) findViewById(R.id.btt2);
        text1=(TextView) findViewById(R.id.text1);
        list=(ListView) findViewById(R.id.danhsach);
        them=(Button) findViewById(R.id.btt3);
        ds=new ArrayList<>();

        final ArrayAdapter adapter= new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,ds);
        list.setAdapter(adapter);
        //tao calendar
        Calendar calendar= Calendar.getInstance();
        //text3.setText("Ngay  HT: "+calendar.getTime());

        //tao su kien cho Date va Time
        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay();

            }
        });
        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGio();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String congviec =edt1.getText().toString();
                 ds.add(congviec+"-"+ text3.getText()+"-"+ text4.getText());
                adapter.notifyDataSetChanged();
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,ds.get(position),Toast.LENGTH_LONG).show();
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ds.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
    private void chonNgay(){
        final Calendar calendar= Calendar.getInstance();
        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yy");
                text3.setText(simpleDateFormat.format(calendar.getTime()));

            }
        },2017,01,01);
        datePickerDialog.show();

    }
    private void chonGio(){
        final Calendar calendar= Calendar.getInstance();
        int gio=calendar.get(Calendar.HOUR_OF_DAY);
        int phut=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0,0,0,hourOfDay,minute);
                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("HH:mm:ss");
                text4.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },gio,phut,true);
        timePickerDialog.show();
    }


}
