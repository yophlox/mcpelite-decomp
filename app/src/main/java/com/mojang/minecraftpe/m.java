package com.mojang.minecraftpe;

import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import java.util.ArrayList;

/* loaded from: classes.dex */
final class m {
    private PreferenceActivity a;
    private ArrayList b = new ArrayList();

    public m(PreferenceActivity preferenceActivity) {
        this.a = preferenceActivity;
    }

    private Preference a(String str) {
        return this.a.findPreference(str);
    }

    public final void a() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.b.size()) {
                return;
            }
            ((c) this.b.get(i2)).a.removePreference(((c) this.b.get(i2)).b);
            i = i2 + 1;
        }
    }

    public final void a(SharedPreferences sharedPreferences, String str) {
        Preference a = a(str);
        if (a instanceof CheckBoxPreference) {
            if (str.equals("gfx_lowquality")) {
                boolean z = sharedPreferences.getBoolean("gfx_lowquality", false);
                CheckBoxPreference checkBoxPreference = (CheckBoxPreference) a("gfx_fancygraphics");
                if (checkBoxPreference != null) {
                    checkBoxPreference.setEnabled(!z);
                    if (z) {
                        checkBoxPreference.setChecked(false);
                    }
                }
            }
            if (str.equals("gfx_fancygraphics")) {
                CheckBoxPreference checkBoxPreference2 = (CheckBoxPreference) a;
                if (MainActivity.a()) {
                    checkBoxPreference2.setSummary("Experimental on this device!");
                }
            }
            if (a.getKey().equals("ctrl_usetouchscreen")) {
                boolean b = MainActivity.b();
                if (!b) {
                    this.b.add(new c((PreferenceCategory) a("category_graphics"), a));
                }
                a.setEnabled(b);
                a.setDefaultValue(Boolean.valueOf(!b));
                if (b) {
                    ((CheckBoxPreference) a("ctrl_usetouchjoypad")).setEnabled(((CheckBoxPreference) a).isChecked());
                }
            }
        }
    }

    public final void a(Preference preference) {
        a(PreferenceManager.getDefaultSharedPreferences(this.a), preference.getKey());
    }
}
