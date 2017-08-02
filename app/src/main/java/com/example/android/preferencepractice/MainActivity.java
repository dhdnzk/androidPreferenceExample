package com.example.android.preferencepractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    TextView checkboxTextView;
    TextView listTextView;
    TextView editTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        checkboxTextView = (TextView) findViewById(R.id.tv_checkbox_display);

        listTextView = (TextView) findViewById(R.id.tv_list_display);

        editTextView = (TextView) findViewById(R.id.tv_edit_text_display);

        setupSharedPreference();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.action_setting:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if(key.equals(getString(R.string.check_box_key))){

            checkboxTextView.setText(String.valueOf(sharedPreferences.getBoolean(key, true)));

        }
        else if(key.equals(getString(R.string.list_key))){

            listTextView.setText(sharedPreferences.getString(key, ""));

        }
        else if(key.equals(getString(R.string.edit_text_key))) {

            editTextView.setText(sharedPreferences.getString(key, "default text"));

        }

    }

    private void setupSharedPreference() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        checkboxTextView.setText(String.valueOf(sharedPreferences.getBoolean(getString(R.string.check_box_key), true)));

        listTextView.setText(sharedPreferences.getString(getString(R.string.list_key), getString(R.string.list_key)));

        editTextView.setText(sharedPreferences.getString(getString(R.string.edit_text_key), "default key"));

    }
}
