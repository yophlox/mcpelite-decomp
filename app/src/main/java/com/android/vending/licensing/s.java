package com.android.vending.licensing;

import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class s implements Runnable {
    private /* synthetic */ r a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(r rVar) {
        this.a = rVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        c cVar;
        c cVar2;
        Log.i("LicenseChecker", "Check timed out.");
        b bVar = this.a.a;
        cVar = this.a.b;
        bVar.b(cVar);
        b bVar2 = this.a.a;
        cVar2 = this.a.b;
        bVar2.a(cVar2);
    }
}
