package com.mojang.minecraftpe;

import android.app.AlertDialog;
import android.view.View;

/* loaded from: classes.dex */
final class e implements View.OnClickListener {
    private /* synthetic */ f a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(f fVar) {
        this.a = fVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        AlertDialog alertDialog;
        AlertDialog alertDialog2;
        alertDialog = this.a.a.g;
        if (alertDialog != null) {
            alertDialog2 = this.a.a.g;
            alertDialog2.cancel();
        }
        this.a.a.d = 0;
    }
}
