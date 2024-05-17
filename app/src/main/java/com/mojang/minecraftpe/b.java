package com.mojang.minecraftpe;

import android.widget.TextView;
import com.mojang.android.StringValue;

/* loaded from: classes.dex */
final class b implements StringValue {
    private TextView a;

    public b(TextView textView) {
        this.a = textView;
    }

    @Override // com.mojang.android.StringValue
    public final String a() {
        return this.a.getText().toString();
    }
}
