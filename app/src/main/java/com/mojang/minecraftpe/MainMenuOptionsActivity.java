package com.mojang.minecraftpe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import com.mojang.android.EditTextAscii;

/* loaded from: classes.dex */
public class MainMenuOptionsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    m a;
    private String b;

    private void a(PreferenceGroup preferenceGroup, a aVar) {
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceGroup.getPreference(i);
            if (preference instanceof PreferenceGroup) {
                a((PreferenceGroup) preference, aVar);
            } else {
                aVar.a(preference);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Preference preference) {
        if (preference != null && (preference instanceof EditTextPreference)) {
            preference.setSummary("'" + ((EditTextPreference) preference).getText() + "'");
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(getIntent().getExtras().getInt("preferenceId"));
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (PreferenceManager.getDefaultSharedPreferences(this).contains("mp_username")) {
            this.b = PreferenceManager.getDefaultSharedPreferences(this).getString("mp_username", null);
        }
        this.a = new m(this);
        a(preferenceScreen, new l(this));
        this.a.a();
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        Preference findPreference = findPreference(str);
        this.a.a(sharedPreferences, str);
        if (findPreference instanceof EditTextPreference) {
            EditTextPreference editTextPreference = (EditTextPreference) findPreference;
            SharedPreferences.Editor edit = sharedPreferences.edit();
            String text = editTextPreference.getText();
            String trim = EditTextAscii.a(text).trim();
            if (((str.equals("mp_username") && trim == null) || trim.length() == 0) && ((trim = this.b) == null || trim.equals(""))) {
                trim = "Steve";
                this.b = "Steve";
            }
            if (!text.equals(trim)) {
                edit.putString(str, trim);
                edit.commit();
                editTextPreference.setText(trim);
            }
        }
        b(findPreference);
    }
}
