package com.android.vending.licensing;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public abstract class e extends Binder implements ILicensingService {
    public e() {
        attachInterface(this, "com.android.vending.licensing.ILicensingService");
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        n kVar;
        switch (i) {
            case 1:
                parcel.enforceInterface("com.android.vending.licensing.ILicensingService");
                long readLong = parcel.readLong();
                String readString = parcel.readString();
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    kVar = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.android.vending.licensing.ILicenseResultListener");
                    kVar = (queryLocalInterface == null || !(queryLocalInterface instanceof n)) ? new k(readStrongBinder) : (n) queryLocalInterface;
                }
                a(readLong, readString, kVar);
                return true;
            case 1598968902:
                parcel2.writeString("com.android.vending.licensing.ILicensingService");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
