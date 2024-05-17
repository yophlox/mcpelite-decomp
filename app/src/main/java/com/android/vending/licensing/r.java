package com.android.vending.licensing;

import android.os.Handler;
import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class r extends a {
    final /* synthetic */ b a;
    private final c b;
    private Runnable c = new s(this);

    public r(b bVar, c cVar) {
        Handler handler;
        this.a = bVar;
        this.b = cVar;
        Log.i("LicenseChecker", "Start monitoring timeout.");
        handler = this.a.f;
        handler.postDelayed(this.c, 10000L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(r rVar) {
        Handler handler;
        Log.i("LicenseChecker", "Clearing timeout.");
        handler = rVar.a.f;
        handler.removeCallbacks(rVar.c);
    }

    @Override // com.android.vending.licensing.n
    public final void a(int i, String str, String str2) {
        Handler handler;
        handler = this.a.f;
        handler.post(new v(this, i, str, str2));
    }
}
