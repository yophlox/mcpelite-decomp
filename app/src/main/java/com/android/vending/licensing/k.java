package com.android.vending.licensing;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
final class k implements n {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // com.android.vending.licensing.n
    public final void a(int i, String str, String str2) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.vending.licensing.ILicenseResultListener");
            obtain.writeInt(i);
            obtain.writeString(str);
            obtain.writeString(str2);
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
