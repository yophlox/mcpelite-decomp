package com.android.vending.licensing;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public final class p implements d {
    private static final byte[] a = {16, 74, 71, -80, 32, 101, -47, 72, 117, -14, 0, -29, 70, 65, -12, 74};
    private Cipher b;
    private Cipher c;

    public p(byte[] bArr, String str, String str2) {
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWITHSHAAND256BITAES-CBC-BC");
            PBEKeySpec pBEKeySpec = new PBEKeySpec((String.valueOf(str) + str2).toCharArray(), bArr, 512, 56);
            System.out.println("GENERATING KEY >>>");
            SecretKey generateSecret = secretKeyFactory.generateSecret(pBEKeySpec);
            System.out.println("<-- Finished generating key");
            SecretKeySpec secretKeySpec = new SecretKeySpec(generateSecret.getEncoded(), "AES");
            this.b = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.b.init(1, secretKeySpec, new IvParameterSpec(a));
            this.c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.c.init(2, secretKeySpec, new IvParameterSpec(a));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Invalid environment", e);
        }
    }

    @Override // com.android.vending.licensing.d
    public final String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return com.android.vending.licensing.a.b.a(this.b.doFinal(("com.android.vending.licensing.AESObfuscator-1|" + str).getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Invalid environment", e);
        } catch (GeneralSecurityException e2) {
            throw new RuntimeException("Invalid environment", e2);
        }
    }

    @Override // com.android.vending.licensing.d
    public final String b(String str) {
        if (str == null) {
            return null;
        }
        try {
            String str2 = new String(this.c.doFinal(com.android.vending.licensing.a.b.a(str)), "UTF-8");
            if (str2.indexOf("com.android.vending.licensing.AESObfuscator-1|") != 0) {
                throw new o("Header not found (invalid data or key):" + str);
            }
            return str2.substring("com.android.vending.licensing.AESObfuscator-1|".length(), str2.length());
        } catch (com.android.vending.licensing.a.a e) {
            throw new o(String.valueOf(e.getMessage()) + ":" + str);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Invalid environment", e2);
        } catch (BadPaddingException e3) {
            throw new o(String.valueOf(e3.getMessage()) + ":" + str);
        } catch (IllegalBlockSizeException e4) {
            throw new o(String.valueOf(e4.getMessage()) + ":" + str);
        }
    }
}
