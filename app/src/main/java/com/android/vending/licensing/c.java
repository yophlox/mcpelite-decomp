package com.android.vending.licensing;

import android.text.TextUtils;
import android.util.Log;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Iterator;
import java.util.regex.Pattern;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c {
    private final u a;
    private final t b;
    private final int c;
    private final String d;
    private final String e;
    private final g f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(u uVar, g gVar, t tVar, int i, String str, String str2) {
        this.a = uVar;
        this.f = gVar;
        this.b = tVar;
        this.c = i;
        this.d = str;
        this.e = str2;
    }

    private void a(f fVar, i iVar) {
        this.a.a(fVar, iVar);
        if (this.a.a()) {
            this.b.a();
        } else {
            this.b.b();
        }
    }

    private void a(j jVar) {
        this.b.a(jVar);
    }

    private void d() {
        this.b.b();
    }

    public final t a() {
        return this.b;
    }

    public final void a(PublicKey publicKey, int i, String str, String str2) {
        i iVar = null;
        if (i == 0 || i == 1 || i == 2) {
            try {
                Signature signature = Signature.getInstance("SHA1withRSA");
                signature.initVerify(publicKey);
                signature.update(str.getBytes());
                if (!signature.verify(com.android.vending.licensing.a.b.a(str2))) {
                    Log.e("LicenseValidator", "Signature verification failed.");
                    d();
                    return;
                }
                try {
                    TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
                    simpleStringSplitter.setString(str);
                    Iterator it = simpleStringSplitter.iterator();
                    if (!it.hasNext()) {
                        throw new IllegalArgumentException("Blank response.");
                    }
                    String str3 = (String) it.next();
                    String str4 = it.hasNext() ? (String) it.next() : "";
                    String[] split = TextUtils.split(str3, Pattern.quote("|"));
                    if (split.length < 6) {
                        throw new IllegalArgumentException("Wrong number of fields.");
                    }
                    i iVar2 = new i();
                    iVar2.g = str4;
                    iVar2.a = Integer.parseInt(split[0]);
                    iVar2.b = Integer.parseInt(split[1]);
                    iVar2.c = split[2];
                    iVar2.d = split[3];
                    iVar2.e = split[4];
                    iVar2.f = Long.parseLong(split[5]);
                    if (iVar2.a != i) {
                        Log.e("LicenseValidator", "Response codes don't match.");
                        d();
                        return;
                    }
                    if (iVar2.b != this.c) {
                        Log.e("LicenseValidator", "Nonce doesn't match.");
                        d();
                        return;
                    }
                    if (!iVar2.c.equals(this.d)) {
                        Log.e("LicenseValidator", "Package name doesn't match.");
                        d();
                        return;
                    } else if (!iVar2.d.equals(this.e)) {
                        Log.e("LicenseValidator", "Version codes don't match.");
                        d();
                        return;
                    } else {
                        if (TextUtils.isEmpty(iVar2.e)) {
                            Log.e("LicenseValidator", "User identifier is empty.");
                            d();
                            return;
                        }
                        iVar = iVar2;
                    }
                } catch (IllegalArgumentException e) {
                    Log.e("LicenseValidator", "Could not parse response.");
                    d();
                    return;
                }
            } catch (com.android.vending.licensing.a.a e2) {
                Log.e("LicenseValidator", "Could not Base64-decode signature.");
                d();
                return;
            } catch (InvalidKeyException e3) {
                a(j.INVALID_PUBLIC_KEY);
                return;
            } catch (NoSuchAlgorithmException e4) {
                throw new RuntimeException(e4);
            } catch (SignatureException e5) {
                throw new RuntimeException(e5);
            }
        }
        switch (i) {
            case 0:
            case 2:
                a(this.f.a(), iVar);
                return;
            case 1:
                a(f.NOT_LICENSED, iVar);
                return;
            case 3:
                a(j.NOT_MARKET_MANAGED);
                return;
            case 4:
                Log.w("LicenseValidator", "An error has occurred on the licensing server.");
                a(f.RETRY, iVar);
                return;
            case 5:
                Log.w("LicenseValidator", "Licensing server is refusing to talk to this device, over quota.");
                a(f.RETRY, iVar);
                return;
            case 257:
                Log.w("LicenseValidator", "Error contacting licensing server.");
                a(f.RETRY, iVar);
                return;
            case 258:
                a(j.INVALID_PACKAGE_NAME);
                return;
            case 259:
                a(j.NON_MATCHING_UID);
                return;
            default:
                Log.e("LicenseValidator", "Unknown response code for license check.");
                d();
                return;
        }
    }

    public final int b() {
        return this.c;
    }

    public final String c() {
        return this.d;
    }
}
