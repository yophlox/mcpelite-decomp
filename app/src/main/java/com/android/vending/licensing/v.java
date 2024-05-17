package com.android.vending.licensing;

import android.util.Log;
import java.security.PublicKey;
import java.util.Set;

/* loaded from: classes.dex */
final class v implements Runnable {
    private /* synthetic */ r a;
    private final /* synthetic */ int b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(r rVar, int i, String str, String str2) {
        this.a = rVar;
        this.b = i;
        this.c = str;
        this.d = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Set set;
        c cVar;
        c cVar2;
        PublicKey publicKey;
        c cVar3;
        Log.i("LicenseChecker", "Received response " + this.b);
        set = this.a.a.i;
        cVar = this.a.b;
        if (set.contains(cVar)) {
            r.b(this.a);
            cVar2 = this.a.b;
            publicKey = this.a.a.c;
            cVar2.a(publicKey, this.b, this.c, this.d);
            b bVar = this.a.a;
            cVar3 = this.a.b;
            bVar.a(cVar3);
        }
    }
}
