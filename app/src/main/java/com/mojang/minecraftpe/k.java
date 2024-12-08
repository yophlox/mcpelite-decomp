package com.mojang.minecraftpe;

import android.app.Activity;
import android.content.Context;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.tasks.Task;

public class k {
    private ReviewManager reviewManager;
    private Activity activity;

    public k(Activity activity) {
        this.activity = activity; 
        reviewManager = ReviewManagerFactory.create(activity); 
    }

    public void checkLicensing() {
        Task<ReviewInfo> flow = reviewManager.requestReviewFlow();

        flow.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ReviewInfo reviewInfo = task.getResult();  
                Task<Void> launchFlow = reviewManager.launchReviewFlow(activity, reviewInfo); 

                launchFlow.addOnCompleteListener(launchTask -> {
                    if (launchTask.isSuccessful()) {
                    } else {
                        Exception error = launchTask.getException();
                    }
                });
            } else {
                Exception error = task.getException();
            }
        });
    }
}
