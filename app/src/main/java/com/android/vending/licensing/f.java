package com.android.vending.licensing;

/* loaded from: classes.dex */
public enum f {
    LICENSED,
    NOT_LICENSED,
    RETRY;

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static f[] valuesCustom() {
        f[] valuesCustom = values();
        int length = valuesCustom.length;
        f[] fVarArr = new f[length];
        System.arraycopy(valuesCustom, 0, fVarArr, 0, length);
        return fVarArr;
    }
}
