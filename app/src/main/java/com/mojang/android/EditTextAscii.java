package com.mojang.android;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/* loaded from: classes.dex */
public class EditTextAscii extends EditText implements TextWatcher {
    public EditTextAscii(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        addTextChangedListener(this);
    }

    public EditTextAscii(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        addTextChangedListener(this);
    }

    public static String a(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt < 128) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        String editable2 = editable.toString();
        String a = a(editable2);
        if (editable2.equals(a)) {
            return;
        }
        editable.replace(0, editable.length(), a);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.widget.TextView, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
