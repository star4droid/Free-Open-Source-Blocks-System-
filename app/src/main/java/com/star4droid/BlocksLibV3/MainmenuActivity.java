package com.star4droid.BlocksLibV3;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import com.google.gson.Gson;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;
import android.Manifest;
import android.content.pm.PackageManager;

public class MainmenuActivity extends Activity {
	
	private String collections_path = "";
	private HashMap<String, Object> map = new HashMap<>();
	
	private LinearLayout linear1;
	private TextView textview1;
	private EditText cp;
	private TextView textview2;
	private EditText pp;
	private TextView textview3;
	private EditText bp;
	private TextView textview4;
	private EditText collections_p;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	
	private Intent i = new Intent();
	private AlertDialog.Builder d;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.mainmenu);
		initialize(_savedInstanceState);
		
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
			||checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
			} else {
				initializeLogic();
			}
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		cp = findViewById(R.id.cp);
		textview2 = findViewById(R.id.textview2);
		pp = findViewById(R.id.pp);
		textview3 = findViewById(R.id.textview3);
		bp = findViewById(R.id.bp);
		textview4 = findViewById(R.id.textview4);
		collections_p = findViewById(R.id.collections_p);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		button3 = findViewById(R.id.button3);
		button4 = findViewById(R.id.button4);
		d = new AlertDialog.Builder(this);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.putExtra("pallets_path", pp.getText().toString());
				i.setClass(getApplicationContext(), ManagerActivity.class);
				startActivity(i);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				map = new HashMap<>();
				ArrayList<String> list = new ArrayList<>();
				list.add("View1");
				list.add("View2");
				list.add("View3");
				list.add("View4");
				list.add("View5");
				list.add("View6");
				list.add("View7");
				list.add("View8");
				map.put("View", new Gson().toJson(list));
				list.clear();
				list.add("TextView1");
				list.add("TextView2");
				list.add("TextView3");
				list.add("TextView4");
				list.add("TextView5");
				list.add("TextView6");
				list.add("TextView7");
				list.add("TextView8");
				map.put("TextView", new Gson().toJson(list));
				i.putExtra("pallets_path", pp.getText().toString());
				i.putExtra("code_save_path", cp.getText().toString());
				i.putExtra("blocks_save_path", bp.getText().toString());
				i.putExtra("collections_path", collections_p.getText().toString());
				i.putExtra("list_keys", new Gson().toJson(map));
				i.setClass(getApplicationContext(), MainActivity.class);
				startActivity(i);
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d.setTitle("Code");
				d.setMessage(FileUtil.readFile(cp.getText().toString()));
				d.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.create().show();
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				FileUtil.deleteFile(bp.getText().toString());
			}
		});
	}
	
	private void initializeLogic() {
		bp.setText(FileUtil.getExternalStorageDir().concat("/.test/blocks.json"));
		pp.setText(FileUtil.getExternalStorageDir().concat("/.test/pallets.json"));
		cp.setText(FileUtil.getExternalStorageDir().concat("/.test/code.json"));
		collections_p.setText(FileUtil.getExternalStorageDir().concat("/.test/collections.json"));
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}