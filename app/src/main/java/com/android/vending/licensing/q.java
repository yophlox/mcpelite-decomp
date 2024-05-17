package com.android.vending.licensing;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
final class q implements ILicensingService {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // com.android.vending.licensing.ILicensingService
    public final void a(long j, String str, n nVar) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.vending.licensing.ILicensingService");
            obtain.writeLong(j);
            obtain.writeString(str);
            obtain.writeStrongBinder(nVar != null ? nVar.asBinder() : null);
            this.a.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.a;
    }
}
