package com.mojang.minecraftpe;

import android.content.DialogInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i implements DialogInterface.OnClickListener {
    private /* synthetic */ MainActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        MainActivity.b(this.a);
    }
}
