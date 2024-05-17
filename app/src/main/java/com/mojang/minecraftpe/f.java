package com.mojang.minecraftpe;

import android.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mojang.android.StringValue;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f implements Runnable {
    final /* synthetic */ MainActivity a;
    private final /* synthetic */ int b;
    private final /* synthetic */ int c;
    private final /* synthetic */ boolean d;
    private final /* synthetic */ int e;
    private final /* synthetic */ int[] f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(MainActivity mainActivity, int i, int i2, boolean z, int i3, int[] iArr) {
        this.a = mainActivity;
        this.b = i;
        this.c = i2;
        this.d = z;
        this.e = i3;
        this.f = iArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AlertDialog alertDialog;
        AlertDialog alertDialog2;
        AlertDialog alertDialog3;
        AlertDialog alertDialog4;
        ArrayList arrayList;
        ArrayList arrayList2;
        View findViewById;
        View findViewById2;
        MainActivity.a(this.a, this.b == 0, this.c == 0, this.d);
        try {
            View inflate = LayoutInflater.from(this.a).inflate(this.e, (ViewGroup) null);
            if (this.b != 0 && this.b != -1 && (findViewById2 = inflate.findViewById(this.b)) != null) {
                findViewById2.setOnClickListener(new d(this));
            }
            if (this.c != 0 && this.c != -1 && (findViewById = inflate.findViewById(this.c)) != null) {
                findViewById.setOnClickListener(new e(this));
            }
            alertDialog4 = this.a.g;
            alertDialog4.setView(inflate);
            if (this.f != null) {
                for (int i : this.f) {
                    KeyEvent.Callback findViewById3 = inflate.findViewById(i);
                    if (findViewById3 instanceof StringValue) {
                        arrayList2 = this.a.f;
                        arrayList2.add((StringValue) findViewById3);
                    } else if (findViewById3 instanceof TextView) {
                        arrayList = this.a.f;
                        arrayList.add(new b((TextView) findViewById3));
                    }
                }
            }
        } catch (Error e) {
            e.printStackTrace();
        }
        alertDialog = this.a.g;
        alertDialog.show();
        alertDialog2 = this.a.g;
        alertDialog2.getWindow().setFlags(1024, 1024);
        alertDialog3 = this.a.g;
        alertDialog3.getWindow().setLayout(-1, -1);
    }
}
