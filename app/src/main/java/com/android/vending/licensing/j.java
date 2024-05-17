package com.android.vending.licensing;

/* loaded from: classes.dex */
public enum j {
    INVALID_PACKAGE_NAME,
    NON_MATCHING_UID,
    NOT_MARKET_MANAGED,
    CHECK_IN_PROGRESS,
    INVALID_PUBLIC_KEY,
    MISSING_PERMISSION;

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static j[] valuesCustom() {
        j[] valuesCustom = values();
        int length = valuesCustom.length;
        j[] jVarArr = new j[length];
        System.arraycopy(valuesCustom, 0, jVarArr, 0, length);
        return jVarArr;
    }
}
