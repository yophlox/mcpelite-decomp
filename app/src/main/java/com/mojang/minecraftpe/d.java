package com.mojang.minecraftpe;

import android.app.AlertDialog;
import android.view.View;

/* loaded from: classes.dex */
final class d implements View.OnClickListener {
    private /* synthetic */ f a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(f fVar) {
        this.a = fVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        AlertDialog alertDialog;
        AlertDialog alertDialog2;
        MainActivity.b(this.a.a);
    }
}
