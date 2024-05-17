package com.android.vending.licensing;

import android.content.SharedPreferences;
import android.util.Log;

/* loaded from: classes.dex */
public final class l {
    private final SharedPreferences a;
    private final d b;
    private SharedPreferences.Editor c = null;

    public l(SharedPreferences sharedPreferences, d dVar) {
        this.a = sharedPreferences;
        this.b = dVar;
    }

    public final void a() {
        if (this.c != null) {
            this.c.commit();
            this.c = null;
        }
    }

    public final void a(String str, String str2) {
        if (this.c == null) {
            this.c = this.a.edit();
        }
        this.c.putString(str, this.b.a(str2));
    }

    public final String b(String str, String str2) {
        String string = this.a.getString(str, null);
        if (string != null) {
            try {
                return this.b.b(string);
            } catch (o e) {
                Log.w("PreferenceObfuscator", "Validation error while reading preference: " + str);
            }
        }
        return str2;
    }
}
