package com.mojang.minecraftpe;

import android.app.AlertDialog;
import android.app.NativeActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;
import com.mojang.android.StringValue;
import com.mojang.minecraftpe.demo.R;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/* loaded from: classes.dex */
public class MainActivity extends NativeActivity {
    private static boolean c = false;
    private AlertDialog g;
    private boolean a = true;
    private int b = 2;
    private int d = -1;
    private String[] e = null;
    private ArrayList f = new ArrayList();
    private final DateFormat h = new SimpleDateFormat();

    static {
        System.loadLibrary("minecraftpe");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(MainActivity mainActivity, boolean z, boolean z2, boolean z3) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle("");
        if (z3) {
            builder.setCancelable(false);
        }
        builder.setOnCancelListener(new j(mainActivity));
        if (z) {
            builder.setPositiveButton("Ok", new i(mainActivity));
        }
        if (z2) {
            builder.setNegativeButton("Cancel", new h(mainActivity));
        }
        mainActivity.g = builder.create();
        mainActivity.g.setOwnerActivity(mainActivity);
    }

    public static boolean a() {
        return c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(MainActivity mainActivity) {
        int size = mainActivity.f.size();
        mainActivity.e = new String[size];
        for (int i = 0; i < size; i++) {
            mainActivity.e[i] = ((StringValue) mainActivity.f.get(i)).a();
        }
        for (String str : mainActivity.e) {
            System.out.println("js: " + str);
        }
        mainActivity.d = 1;
    }

    public static boolean b() {
        for (String str : new String[]{Build.MODEL, Build.DEVICE, Build.PRODUCT}) {
            String lowerCase = str.toLowerCase();
            if (lowerCase.indexOf("r800") < 0 && lowerCase.indexOf("so-01d") < 0) {
                if (lowerCase.indexOf("xperia") >= 0 && lowerCase.indexOf("play") >= 0) {
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    public static void saveScreenshot(String str, int i, int i2, int[] iArr) {
        Bitmap createBitmap = Bitmap.createBitmap(iArr, i, i2, Bitmap.Config.ARGB_8888);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            createBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
            try {
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (FileNotFoundException e3) {
            System.err.println("Couldn't create file: " + str);
            e3.printStackTrace();
        }
    }

    public void buyGame() {
    }

    public int checkLicense() {
        return 0;
    }

    public void displayDialog(int i) {
        int[] iArr;
        int i2;
        MainActivity mainActivity;
        int i3;
        int i4;
        boolean z = false;
        if (i == 1) {
            int[] iArr2 = {R.id.editText_worldName, R.id.editText_worldSeed, R.id.button_gameMode};
            i3 = R.id.button_createworld_create;
            i4 = R.id.button_createworld_cancel;
            iArr = iArr2;
            i2 = R.layout.create_new_world;
            mainActivity = this;
        } else if (i == 3) {
            Intent intent = new Intent(this, (Class<?>) MainMenuOptionsActivity.class);
            intent.putExtra("preferenceId", R.xml.preferences);
            startActivityForResult(intent, i);
            return;
        } else {
            if (i != 4) {
                return;
            }
            iArr = new int[]{R.id.editText_worldNameRename};
            i2 = R.layout.rename_mp_world;
            mainActivity = this;
            i3 = 0;
            z = true;
            i4 = -1;
        }
        mainActivity.f.clear();
        mainActivity.runOnUiThread(new f(mainActivity, i3, i4, z, i2, iArr));
    }

    public String getDateString(int i) {
        return this.h.format(new Date(i * 1000));
    }

    public int[] getImageData(String str) {
        AssetManager assets = getAssets();
        try {
            assets.list("images");
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(assets.open(str));
                int width = decodeStream.getWidth();
                int height = decodeStream.getHeight();
                int[] iArr = new int[(width * height) + 2];
                iArr[0] = width;
                iArr[1] = height;
                decodeStream.getPixels(iArr, 2, width, 0, 0, width, height);
                return iArr;
            } catch (IOException e) {
                System.err.println("getImageData: Could not open image " + str);
                return null;
            }
        } catch (IOException e2) {
            System.err.println("getImageData: Could not list directory");
            return null;
        }
    }

    public String[] getOptionStrings() {
        String str;
        Map<String, ?> all = PreferenceManager.getDefaultSharedPreferences(this).getAll();
        String[] strArr = new String[all.size() * 2];
        int i = 0;
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            String obj = entry.getValue().toString();
            if (key.equals("ctrl_usetouchscreen")) {
                this.a = !b() || ((Boolean) entry.getValue()).booleanValue();
            }
            if (key.equals("gfx_lowquality")) {
                this.b = ((Boolean) entry.getValue()).booleanValue() ? 3 : 2;
            }
            if (key.equals("ctrl_sensitivity")) {
                str = new Double(0.01d * Integer.parseInt(obj)).toString();
                int i2 = i + 1;
                strArr[i] = key;
                strArr[i2] = str;
                i = i2 + 1;
            }
            str = obj;
            int i22 = i + 1;
            strArr[i] = key;
            strArr[i22] = str;
            i = i22 + 1;
        }
        String[] strArr2 = new String[i];
        for (int i3 = 0; i3 < i; i3++) {
            strArr2[i3] = strArr[i3];
        }
        return strArr2;
    }

    public float getPixelsPerMillimeter() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return ((displayMetrics.ydpi + displayMetrics.xdpi) * 0.5f) / 25.4f;
    }

    public int getScreenHeight() {
        Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        int min = Math.min(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        System.out.println("getheight: " + min);
        return min;
    }

    public int getScreenWidth() {
        Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        int max = Math.max(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        System.out.println("getwidth: " + max);
        return max;
    }

    public int getUserInputStatus() {
        return this.d;
    }

    public String[] getUserInputString() {
        return this.e;
    }

    public boolean hasBuyButtonWhenInvalidLicense() {
        return true;
    }

    public void initiateUserInput(int i) {
        this.e = null;
        this.d = -1;
    }

    public boolean isTouchscreen() {
        return this.a;
    }

    native void nativeRegisterThis();

    native void nativeUnregisterThis();

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 3) {
            this.d = 1;
        }
    }

    @Override // android.app.NativeActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        getOptionStrings();
        setVolumeControlStream(3);
        super.onCreate(bundle);
        nativeRegisterThis();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.NativeActivity, android.app.Activity
    public void onDestroy() {
        System.out.println("onDestroy");
        nativeUnregisterThis();
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.NativeActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.NativeActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.NativeActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // android.app.NativeActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    @Override // android.app.NativeActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public void postScreenshotToFacebook(String str, int i, int i2, int[] iArr) {
    }

    public void quit() {
        runOnUiThread(new g(this));
    }

    public void setIsPowerVR(boolean z) {
        c = z;
    }

    public void tick() {
    }

    public void vibrate(int i) {
        ((Vibrator) getSystemService("vibrator")).vibrate(i);
    }
}
