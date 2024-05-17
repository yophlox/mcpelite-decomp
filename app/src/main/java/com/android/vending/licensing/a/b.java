package com.android.vending.licensing.a;

/* loaded from: classes.dex */
public class b {
    private static final byte[] a;
    private static final byte[] b;
    private static final byte[] c;
    private static final byte[] d;
    private static /* synthetic */ boolean e;

    static {
        e = !b.class.desiredAssertionStatus();
        a = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        b = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        c = new byte[]{-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9};
        d = new byte[]{-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9};
    }

    private b() {
    }

    private static int a(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        if (bArr[2] == 61) {
            bArr2[i] = (byte) ((((bArr3[bArr[0]] << 24) >>> 6) | ((bArr3[bArr[1]] << 24) >>> 12)) >>> 16);
            return 1;
        }
        if (bArr[3] == 61) {
            int i2 = ((bArr3[bArr[0]] << 24) >>> 6) | ((bArr3[bArr[1]] << 24) >>> 12) | ((bArr3[bArr[2]] << 24) >>> 18);
            bArr2[i] = (byte) (i2 >>> 16);
            bArr2[i + 1] = (byte) (i2 >>> 8);
            return 2;
        }
        int i3 = ((bArr3[bArr[0]] << 24) >>> 6) | ((bArr3[bArr[1]] << 24) >>> 12) | ((bArr3[bArr[2]] << 24) >>> 18) | ((bArr3[bArr[3]] << 24) >>> 24);
        bArr2[i] = (byte) (i3 >> 16);
        bArr2[i + 1] = (byte) (i3 >> 8);
        bArr2[i + 2] = (byte) i3;
        return 3;
    }

    public static String a(byte[] bArr) {
        int i;
        int i2;
        int length = bArr.length;
        byte[] bArr2 = a;
        int i3 = ((length + 2) / 3) * 4;
        byte[] bArr3 = new byte[i3 + (i3 / Integer.MAX_VALUE)];
        int i4 = length - 2;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i7 < i4) {
            int i8 = ((bArr[i7 + 0] << 24) >>> 8) | ((bArr[(i7 + 1) + 0] << 24) >>> 16) | ((bArr[(i7 + 2) + 0] << 24) >>> 24);
            bArr3[i6] = bArr2[i8 >>> 18];
            bArr3[i6 + 1] = bArr2[(i8 >>> 12) & 63];
            bArr3[i6 + 2] = bArr2[(i8 >>> 6) & 63];
            bArr3[i6 + 3] = bArr2[i8 & 63];
            i5 += 4;
            if (i5 == Integer.MAX_VALUE) {
                bArr3[i6 + 4] = 10;
                i6++;
                i5 = 0;
            }
            i7 += 3;
            i6 += 4;
        }
        if (i7 < length) {
            int i9 = i7 + 0;
            int i10 = length - i7;
            int i11 = (i10 > 2 ? (bArr[i9 + 2] << 24) >>> 24 : 0) | (i10 > 0 ? (bArr[i9] << 24) >>> 8 : 0) | (i10 > 1 ? (bArr[i9 + 1] << 24) >>> 16 : 0);
            switch (i10) {
                case 1:
                    bArr3[i6] = bArr2[i11 >>> 18];
                    bArr3[i6 + 1] = bArr2[(i11 >>> 12) & 63];
                    bArr3[i6 + 2] = 61;
                    bArr3[i6 + 3] = 61;
                    break;
                case 2:
                    bArr3[i6] = bArr2[i11 >>> 18];
                    bArr3[i6 + 1] = bArr2[(i11 >>> 12) & 63];
                    bArr3[i6 + 2] = bArr2[(i11 >>> 6) & 63];
                    bArr3[i6 + 3] = 61;
                    break;
                case 3:
                    bArr3[i6] = bArr2[i11 >>> 18];
                    bArr3[i6 + 1] = bArr2[(i11 >>> 12) & 63];
                    bArr3[i6 + 2] = bArr2[(i11 >>> 6) & 63];
                    bArr3[i6 + 3] = bArr2[i11 & 63];
                    break;
            }
            if (i5 + 4 == Integer.MAX_VALUE) {
                bArr3[i6 + 4] = 10;
                i2 = i6 + 1;
            } else {
                i2 = i6;
            }
            i = i2 + 4;
        } else {
            i = i6;
        }
        if (e || i == bArr3.length) {
            return new String(bArr3, 0, bArr3.length);
        }
        throw new AssertionError();
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x001b, code lost:
    
        if (r6 == 0) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x001d, code lost:
    
        if (r6 != 1) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0034, code lost:
    
        throw new com.android.vending.licensing.a.a("single trailing character at offset " + (r1 - 1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d1, code lost:
    
        r4[r6] = 61;
        r0 = a(r4, r3, r7, r2) + r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00d8, code lost:
    
        r1 = new byte[r0];
        java.lang.System.arraycopy(r3, 0, r1, 0, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00dd, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00de, code lost:
    
        r0 = r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] a(java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vending.licensing.a.b.a(java.lang.String):byte[]");
    }
}
