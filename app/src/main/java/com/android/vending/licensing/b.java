package com.android.vending.licensing;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/* loaded from: classes.dex */
public final class b implements ServiceConnection {
    private static final SecureRandom a = new SecureRandom();
    private ILicensingService b;
    private PublicKey c;
    private final Context d;
    private final u e;
    private Handler f;
    private final String g;
    private final String h;
    private final Set i = new HashSet();
    private final Queue j = new LinkedList();
    private boolean k = false;

    public b(Context context, u uVar, String str) {
        this.d = context;
        this.e = uVar;
        this.c = a(str);
        this.g = this.d.getPackageName();
        this.h = a(context, this.g);
        HandlerThread handlerThread = new HandlerThread("background thread");
        handlerThread.start();
        this.f = new Handler(handlerThread.getLooper());
    }

    private static String a(Context context, String str) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("LicenseChecker", "Package not found. could not get version code.");
            return "";
        }
    }

    private static PublicKey a(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(com.android.vending.licensing.a.b.a(str)));
        } catch (com.android.vending.licensing.a.a e) {
            Log.e("LicenseChecker", "Could not decode from Base64.");
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        } catch (InvalidKeySpecException e3) {
            Log.e("LicenseChecker", "Invalid key specification.");
            throw new IllegalArgumentException(e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(c cVar) {
        this.i.remove(cVar);
        if (this.i.isEmpty()) {
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(c cVar) {
        this.e.a(f.RETRY, null);
        if (this.e.a()) {
            cVar.a().a();
        } else {
            cVar.a().b();
        }
    }

    private void c() {
        while (true) {
            c cVar = (c) this.j.poll();
            if (cVar == null) {
                return;
            }
            try {
                Log.i("LicenseChecker", "Calling checkLicense on service for " + cVar.c());
                this.b.a(cVar.b(), cVar.c(), new r(this, cVar));
                this.i.add(cVar);
            } catch (RemoteException e) {
                Log.w("LicenseChecker", "RemoteException in checkLicense call.", e);
                b(cVar);
            }
        }
    }

    private void d() {
        if (this.b != null) {
            try {
                this.d.unbindService(this);
                this.k = true;
            } catch (IllegalArgumentException e) {
                Log.e("LicenseChecker", "Unable to unbind from licensing service (already unbound)");
            }
            this.b = null;
        }
    }

    public final void a() {
        if (this.k) {
            return;
        }
        try {
            this.d.unbindService(this);
        } catch (IllegalArgumentException e) {
            Log.w("LicenseChecker", "Unable to force-unbind from licensing service (already unbound)");
        }
        this.b = null;
    }

    public final synchronized void a(t tVar) {
        if (this.e.a()) {
            Log.i("LicenseChecker", "Using cached license response");
            tVar.a();
        } else {
            c cVar = new c(this.e, new h(), tVar, a.nextInt(), this.g, this.h);
            if (this.b == null) {
                Log.i("LicenseChecker", "Binding to licensing service.");
                try {
                    if (this.d.bindService(new Intent(ILicensingService.class.getName()), this, 1)) {
                        this.j.offer(cVar);
                    } else {
                        Log.e("LicenseChecker", "Could not bind to service.");
                        b(cVar);
                    }
                } catch (SecurityException e) {
                    tVar.a(j.MISSING_PERMISSION);
                }
            } else {
                this.j.offer(cVar);
                c();
            }
        }
    }

    public final synchronized void b() {
        d();
        this.f.getLooper().quit();
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ILicensingService qVar;
        if (iBinder == null) {
            qVar = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.android.vending.licensing.ILicensingService");
            qVar = (queryLocalInterface == null || !(queryLocalInterface instanceof ILicensingService)) ? new q(iBinder) : (ILicensingService) queryLocalInterface;
        }
        this.b = qVar;
        c();
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        Log.w("LicenseChecker", "Service unexpectedly disconnected.");
        this.b = null;
    }
}
