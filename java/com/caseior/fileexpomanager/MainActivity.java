package com.caseior.fileexpomanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {
Toolbar toolbar;
TextView tv_path;
ListView lv_files;
Button Bt_addFile,bt_back;
String s1;
Spinner sp;
View v;
List <File>files;
List<String> items;
fileAdapter fileadapter;
int pos;
EditText enter_filename;
File[] innerFiles;
ArrayAdapter<String> spAdapter;
File curPath,temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.customtoolbar);
        tv_path=findViewById(R.id.curDir_text);
        lv_files=findViewById(R.id.lv_listItems);
        bt_back=findViewById(R.id.bt_back);

        Bt_addFile=findViewById(R.id.Button_addFile);

        files=new Vector<>();

        setSupportActionBar(toolbar);
         fileadapter=new fileAdapter(this,files);
        lv_files.setAdapter(fileadapter);
        curPath = Environment.getRootDirectory();
        init();
        lv_files.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos=i;
                curPath=files.get(pos);
               init();
            }
        });
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curPath=curPath.getParentFile();
                init();
            }
        });
        Bt_addFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Add File");
                adb.setMessage("Select Type--> File/Folder:");



                items=new Vector<String>();


                try {



                    LayoutInflater li=LayoutInflater.from(MainActivity.this);
                    v=li.inflate(R.layout.addfolder,null);
                    sp=v.findViewById(R.id.spinner);
                    spAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, items);
                    sp.setAdapter(spAdapter);

                     enter_filename=v.findViewById(R.id.EditFolder);

                    adb.setView(R.layout.addfolder);




                }catch (Exception e){
                    Log.d("TAG","spAdapter Exception");
                }

                adb.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        File s=new File(curPath.getAbsolutePath()+"/abcde");
                                //+enter_filename.getText().toString();
                       // tv_path.setText(s);
                        Toast.makeText(MainActivity.this, s+"", Toast.LENGTH_SHORT).show();
                     //  File fmkdir=new File(s);
                       s.mkdir();

                   fileadapter.notifyDataSetChanged();
                    }

                });

                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
               AlertDialog ad=adb.create();
               ad.show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        curPath=curPath.getParentFile();
        init();
        //super.onBackPressed();
    }

    public void init() {
          files.clear();
          fileadapter.notifyDataSetChanged();
          try {


              tv_path.setText(curPath.getAbsolutePath());
              Log.d("TAG","Exception11");
         innerFiles=curPath.listFiles();
              Log.d("TAG","Exception111");
              if(innerFiles!=null) {
                  for (File ff : innerFiles) {
                      files.add(ff);
                  }

              }
          }catch (Exception e)
          {
              Log.d("TAG","Exception1");
          }
          fileadapter.notifyDataSetChanged();
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=new MenuInflater(this);
        mi.inflate(R.menu.menu_actionbarmenu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    */
}
