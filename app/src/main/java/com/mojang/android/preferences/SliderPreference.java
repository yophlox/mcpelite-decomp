package com.mojang.android.preferences;

import android.content.Context;
import android.content.res.Resources;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/* loaded from: classes.dex */
public class SliderPreference extends DialogPreference implements SeekBar.OnSeekBarChangeListener {
    private Context a;
    private TextView b;
    private SeekBar c;
    private String d;
    private int e;
    private int f;
    private int g;
    private int h;

    public SliderPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        String attributeValue;
        this.a = context;
        Resources resources = getContext().getResources();
        int attributeResourceValue = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "text", 0);
        if (attributeResourceValue != 0) {
            attributeValue = resources.getString(attributeResourceValue);
        } else {
            attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "text");
            if (attributeValue == null) {
                attributeValue = "";
            }
        }
        this.d = attributeValue;
        this.e = a(attributeSet, "http://schemas.android.com/apk/res/android", "defaultValue", 0);
        this.f = a(attributeSet, "http://schemas.android.com/apk/res/android", "max", 100);
        this.h = a(attributeSet, null, "min", 0);
        setDefaultValue(Integer.valueOf(this.e));
    }

    private int a(AttributeSet attributeSet, String str, String str2, int i) {
        Resources resources = getContext().getResources();
        int attributeResourceValue = attributeSet.getAttributeResourceValue(str, str2, 0);
        return attributeResourceValue != 0 ? resources.getInteger(attributeResourceValue) : attributeSet.getAttributeIntValue(str, str2, i);
    }

    @Override // android.preference.DialogPreference
    protected View onCreateDialogView() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setPadding(6, 6, 6, 6);
        this.b = new TextView(this.a);
        this.b.setGravity(1);
        this.b.setTextSize(32.0f);
        linearLayout.addView(this.b, new LinearLayout.LayoutParams(-1, -2));
        this.c = new SeekBar(this.a);
        this.c.setOnSeekBarChangeListener(this);
        linearLayout.addView(this.c, new LinearLayout.LayoutParams(-1, -2));
        if (shouldPersist()) {
            this.g = getPersistedInt(this.e);
        }
        this.c.setMax(this.f);
        this.c.setProgress(this.g);
        return linearLayout;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.g = this.h + i;
        this.b.setText(String.valueOf(this.g).concat(this.d));
        if (shouldPersist()) {
            persistInt(this.h + i);
        }
        callChangeListener(new Integer(this.g));
    }

    @Override // android.preference.Preference
    protected void onSetInitialValue(boolean z, Object obj) {
        super.onSetInitialValue(z, obj);
        if (z) {
            this.g = shouldPersist() ? getPersistedInt(this.e) : 0;
        } else {
            this.g = ((Integer) obj).intValue();
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
