package com.example.android.preferencepractice;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.pref_main_textviews);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Preference preference = getPreferenceScreen().findPreference(key);

        if(key.equals(getString(R.string.list_key))){

            ListPreference listPreference = (ListPreference) preference;

            int idx = listPreference.findIndexOfValue(listPreference.getValue());

            String label = (String) listPreference.getEntries()[idx];

            preference.setSummary(label);

        }

        else if(key.equals(getString(R.string.edit_text_key))) {

                EditTextPreference editTextPreference = (EditTextPreference) preference;

                String label = editTextPreference.getText();

                preference.setSummary(label);

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        setupPreferenceSummary();

    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

    }


    private void setupPreferenceSummary() {

        int count = getPreferenceScreen().getPreferenceCount();

        for(int i = 0; i < count; i ++) {

            Preference preference = getPreferenceScreen().getPreference(i);

            if(preference instanceof ListPreference) {

                ListPreference listPreference = (ListPreference) preference;

                String key = listPreference.getKey();

                int idx = listPreference.findIndexOfValue(listPreference.getValue());

                String label = (String) listPreference.getEntries()[idx];

                preference.setSummary(label);

            }

            else if(preference instanceof EditTextPreference) {

                EditTextPreference editTextPreference = (EditTextPreference) preference;

                String label = editTextPreference.getText();

                preference.setSummary(label);

            }

        }

    }

}
