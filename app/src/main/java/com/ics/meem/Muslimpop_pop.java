package com.ics.meem;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ics.meem.R;

import java.util.ArrayList;

public class Muslimpop_pop extends AppCompatActivity {

    Spinner spinner;
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5;
    Button button;

        	    @Override
  protected void onCreate(Bundle savedInstanceState) {
        	        super.onCreate(savedInstanceState);
        	        setContentView(R.layout.custom_muslimshare_pop);

                    checkBox1 = findViewById(R.id.checkb1);
                    checkBox2 = findViewById(R.id.checkb2);
                    checkBox3 = findViewById(R.id.checkb3);
                    checkBox4= findViewById(R.id.checkb4);
                    checkBox5= findViewById(R.id.checkb5);
                    button= findViewById(R.id.share_mail);

                    button.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
                         	                if(checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked() && checkBox5.isChecked()){
                                                 Toast.makeText(getApplicationContext(),""+checkBox1.getText() +" , "+ checkBox2.getText() +" , "+checkBox3.getText()+" , "+checkBox4.getText()+" , "+checkBox5.getText(), Toast.LENGTH_SHORT).show();

                                             }else if (checkBox1.isChecked()&& checkBox2.isChecked()){
                             	                    Toast.makeText(getApplicationContext(),""+checkBox1.getText()+" , "+ checkBox2.getText(),Toast.LENGTH_SHORT).show();

                              	                }else if (checkBox1.isChecked()&& checkBox3.isChecked()) {
                              	                    Toast.makeText(getApplicationContext(), "" + checkBox1.getText() + ", " + checkBox3.getText(), Toast.LENGTH_SHORT).show();

                               	                }else if (checkBox2.isChecked()&& checkBox3.isChecked()) {
                                                   Toast.makeText(getApplicationContext(), "" + checkBox2.getText() + " , " + checkBox3.getText(), Toast.LENGTH_SHORT).show();

                                                }else if(checkBox1.isChecked()){
                                                   Toast.makeText(getApplicationContext(),""+checkBox1.getText(),Toast.LENGTH_SHORT).show();

                                                }else if(checkBox2.isChecked()) {
                                	                    Toast.makeText(getApplicationContext(), "" + checkBox2.getText(), Toast.LENGTH_SHORT).show();

                                	                }else if(checkBox3.isChecked()) {
                                	                    Toast.makeText(getApplicationContext(), "" + checkBox3.getText(), Toast.LENGTH_SHORT).show();
                                	                }
                                        }
        });





        	        spinner = findViewById(R.id.spinnerpop);
        	        final ArrayList<String> time = new ArrayList <>();
        	        time.add("01"); time.add("02");time.add("03");time.add("04");time.add("05");//add.add("06");add.add("07");add.add("08");add.add("09");add.add("10");
                   // add.add("11");add.add("12");add.add("13");add.add("14");add.add("15");add.add("16");add.add("17");add.add("18");add.add("19");add.add("20");
                    //add.add("21");add.add("22");add.add("23");add.add("24");add.add("25");add.add("26");add.add("27");add.add("28");add.add("29");add.add("30");
                    //add.add("31");add.add("32");add.add("33");add.add("34");add.add("35");add.add("36");add.add("37");add.add("38");add.add("39");add.add("40");
                    //add.add("41");add.add("42");add.add("43");add.add("44");add.add("45");add.add("46");add.add("47");add.add("48");add.add("49");add.add("50");
                    //add.add("51");add.add("52");add.add("53");add.add("54");add.add("55");add.add("56");add.add("57");add.add("58");add.add("59");add.add("60");
                    ArrayAdapter<String> adapter = new ArrayAdapter <String>(this, R.layout.support_simple_spinner_dropdown_item,time);
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	            @Override
	            public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
	                Toast.makeText(getApplicationContext(), "You Selected "+
                            time.get(position), Toast.LENGTH_SHORT).show();
	            }

	            @Override
	            public void onNothingSelected(AdapterView <?> parent) {

                	            }
	        });
	    }

}