package com.android.vending.licensing;

import android.content.Context;
import android.util.Log;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

/* loaded from: classes.dex */
public final class m implements u {
    private long a;
    private long b;
    private long c;
    private long d;
    private long e = 0;
    private f f;
    private l g;

    public m(Context context, d dVar) {
        this.g = new l(context.getSharedPreferences("com.android.vending.licensing.OneTimePolicy.c0", 0), dVar);
        this.f = f.valueOf(this.g.b("lastResponse", f.RETRY.toString()));
        this.a = Long.parseLong(this.g.b("validityTimestamp", "0"));
        this.b = Long.parseLong(this.g.b("retryUntil", "0"));
        this.c = Long.parseLong(this.g.b("maxRetries", "0"));
        this.d = Long.parseLong(this.g.b("retryCount", "0"));
    }

    private void a(long j) {
        this.d = j;
        this.g.a("retryCount", Long.toString(j));
    }

    private void a(String str) {
        Long valueOf;
        String l;
        try {
            valueOf = Long.valueOf(Long.parseLong(str));
            l = str;
        } catch (NumberFormatException e) {
            Log.w("OneTimePolicy", "License validity timestamp (VT) missing, caching for a minute");
            valueOf = Long.valueOf(System.currentTimeMillis() + 60000);
            l = Long.toString(valueOf.longValue());
        }
        this.a = valueOf.longValue();
        this.g.a("validityTimestamp", l);
    }

    private void b(String str) {
        String str2;
        Long l;
        try {
            l = Long.valueOf(Long.parseLong(str));
            str2 = str;
        } catch (NumberFormatException e) {
            Log.w("OneTimePolicy", "License retry timestamp (GT) missing, grace period disabled");
            str2 = "0";
            l = 0L;
        }
        this.b = l.longValue();
        this.g.a("retryUntil", str2);
    }

    private void c(String str) {
        String str2;
        Long l;
        try {
            l = Long.valueOf(Long.parseLong(str));
            str2 = str;
        } catch (NumberFormatException e) {
            Log.w("OneTimePolicy", "Licence retry count (GR) missing, grace period disabled");
            str2 = "0";
            l = 0L;
        }
        this.c = l.longValue();
        this.g.a("maxRetries", str2);
    }

    private static Map d(String str) {
        HashMap hashMap = new HashMap();
        try {
            for (NameValuePair nameValuePair : URLEncodedUtils.parse(new URI("?" + str), "UTF-8")) {
                hashMap.put(nameValuePair.getName(), nameValuePair.getValue());
            }
        } catch (URISyntaxException e) {
            Log.w("OneTimePolicy", "Invalid syntax error while decoding extras data from server.");
        }
        return hashMap;
    }

    @Override // com.android.vending.licensing.u
    public final void a(f fVar, i iVar) {
        if (fVar != f.RETRY) {
            a(0L);
        } else {
            a(this.d + 1);
        }
        if (fVar == f.LICENSED) {
            Map d = d(iVar.g);
            this.f = fVar;
            a((String) d.get("VT"));
            b((String) d.get("GT"));
            c((String) d.get("GR"));
        } else if (fVar == f.NOT_LICENSED) {
            a("0");
            b("0");
            c("0");
        }
        this.e = System.currentTimeMillis();
        this.f = fVar;
        this.g.a("lastResponse", fVar.toString());
        this.g.a();
    }

    @Override // com.android.vending.licensing.u
    public final boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f == f.LICENSED) {
            return true;
        }
        if (this.f != f.RETRY || currentTimeMillis >= this.e + 60000) {
            return false;
        }
        return currentTimeMillis <= this.b || this.d <= this.c;
    }
}
