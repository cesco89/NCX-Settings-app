package com.cesco.cpusettings;


import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.cesco.cpusettings.util.*;

public class MainActivity extends Activity implements OnCheckedChangeListener, OnItemSelectedListener {
	
	public Switch switch1;
	public Switch switch2;
	public Switch switch3;
	public Switch switch4;
	public Switch switch5;
	public Switch switch6;
	public SeekBar seekBar1;
	public TextView userfreq;
	public Spinner spinner1;
	public int position;
	public String value;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Switch switch1 = (Switch) findViewById(R.id.switch1);
		switch1.setOnCheckedChangeListener(this);
		
		Switch switch2 = (Switch) findViewById(R.id.switch2);
		switch2.setOnCheckedChangeListener(this);
		
		Switch switch3 = (Switch) findViewById(R.id.switch3);
		switch3.setOnCheckedChangeListener(this);
		
		Switch switch4 = (Switch) findViewById(R.id.switch4);
		switch4.setOnCheckedChangeListener(this);
		
		Switch switch5 = (Switch) findViewById(R.id.switch5);
		switch5.setOnCheckedChangeListener(this);
		
		Switch switch6 = (Switch) findViewById(R.id.switch6);
		switch6.setOnCheckedChangeListener(this);
		
		Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(this);
		
}
	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		// TODO Auto-generated method stub
		
		//Toast.makeText(parent.getContext(), "frequency selected : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
		value = parent.getItemAtPosition(position).toString();
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch(buttonView.getId()){
		
		case R.id.switch1:
			if (isChecked){
				CMDProcessor cmd = new CMDProcessor();
		    	   cmd.su.runWaitFor("busybox echo '520 520 520 520 484 400 304 247' > sys/devices/system/cpu/cpu0/cpufreq/gpu_oc");
		    	Toast.makeText(this, "GPU OC ENABLED!! GPU is now Running @520MHz " , Toast.LENGTH_SHORT).show();
			}
			else {
				CMDProcessor cmd = new CMDProcessor();
		    	   cmd.su.runWaitFor("busybox echo '416 416 416 416 416 400 304 247' > sys/devices/system/cpu/cpu0/cpufreq/gpu_oc");
		    	Toast.makeText(this, "GPU OC DISABLED!! GPU is now Running @416MHz " , Toast.LENGTH_SHORT).show();
				
			}
			break;
		case R.id.switch2:
			if (isChecked){
				CMDProcessor cmd = new CMDProcessor();
		    	   cmd.su.runWaitFor("busybox echo '416 416 416 416 416 400 304 304' > sys/devices/system/cpu/cpu0/cpufreq/gpu_oc");
				Toast.makeText(this, "MIN GPU OC ENABLED!! " , Toast.LENGTH_SHORT).show();
				Toast.makeText(this, "GPU Min freq now is 304MHz " , Toast.LENGTH_SHORT).show();
			}
			else {
				CMDProcessor cmd = new CMDProcessor();
		    	   cmd.su.runWaitFor("busybox echo '416 416 416 416 416 400 304 247' > sys/devices/system/cpu/cpu0/cpufreq/gpu_oc");
				Toast.makeText(this, "MIN GPU OC DISABLED!! " , Toast.LENGTH_SHORT).show();
				Toast.makeText(this, "GPU Min freq now is 247MHz " , Toast.LENGTH_SHORT).show();
				
			}
			break;
		case R.id.switch3:
			if (isChecked){
				CMDProcessor cmd = new CMDProcessor();
		    	   cmd.su.runWaitFor("busybox echo '1' > /sys/devices/tegradc.0/smartdimmer/enable");
				Toast.makeText(this, "SMARTDIMMER ENABLED!! " , Toast.LENGTH_SHORT).show();
			}
			else {
				CMDProcessor cmd = new CMDProcessor();
		    	   cmd.su.runWaitFor("busybox echo '0' > /sys/devices/tegradc.0/smartdimmer/enable");
				Toast.makeText(this, "SMARTDIMMER DISABLED!! " , Toast.LENGTH_SHORT).show();
				
			}
			break;
		case R.id.switch4:
			if (isChecked){
				CMDProcessor cmd = new CMDProcessor();
		    	   cmd.su.runWaitFor("busybox echo '#' > /sys/devices/tegradc.0/smartdimmer/aggressiveness");
				Toast.makeText(this, "AGGRESSIVE SMARTDIMMER!! " , Toast.LENGTH_SHORT).show();
			}
			else {
				CMDProcessor cmd = new CMDProcessor();
		    	   cmd.su.runWaitFor("busybox echo '1' > /sys/devices/tegradc.0/smartdimmer/aggressiveness");
				Toast.makeText(this, "DEFAULT AGGRESSIVENESS!! " , Toast.LENGTH_SHORT).show();
				
			}
			break;
		case R.id.switch5:
			if (isChecked){
				CMDProcessor cmd = new CMDProcessor();
		    		cmd.su.runWaitFor("busybox echo '"+value+"' > /sys/module/cpu_tegra/parameters/cpu_user_cap");
				Toast.makeText(this,"CPU User Cap @" +value+"Hz" ,Toast.LENGTH_SHORT).show();
				
			}
			else {
				CMDProcessor cmd = new CMDProcessor();
		    		cmd.su.runWaitFor("busybox echo '1200000' > /sys/module/cpu_tegra/parameters/cpu_user_cap");
				Toast.makeText(this, "CPU User Cap back to 1.2GHz " , Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.switch6:
			if (isChecked){
				CMDProcessor cmd = new CMDProcessor();
		    	   cmd.su.runWaitFor("busybox echo '204000' > /sys/module/snd_soc_tlv320aic3008/parameters/audio_min_freq");
				Toast.makeText(this, "Audio Min freq. 204MHz " , Toast.LENGTH_SHORT).show();
			}
			else {
				CMDProcessor cmd = new CMDProcessor();
		    	   cmd.su.runWaitFor("busybox echo '102000' > /sys/module/snd_soc_tlv320aic3008/parameters/audio_min_freq");
				Toast.makeText(this, "Audio Min freq. 102MHz!! " , Toast.LENGTH_SHORT).show();
			};
		break;
			
		}
			
		
	}
		// TODO Auto-generated method stub
	

}
