package com.stylo.batterymonitor.ads

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

object AdMobManager {
    private var interstitialAd: InterstitialAd? = null
    private var rewardedAd: RewardedAd? = null

    private const val TEST_INTERSTITIAL = "ca-app-pub-3940256099942544/1033173712"
    private const val TEST_REWARDED = "ca-app-pub-3940256099942544/5224354917"

    fun preloadInterstitial(context: Context) {
        if (interstitialAd != null) return
        InterstitialAd.load(context, TEST_INTERSTITIAL, AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) { interstitialAd = ad }
                override fun onAdFailedToLoad(error: LoadAdError) { interstitialAd = null }
            })
    }

    fun preloadRewarded(context: Context) {
        if (rewardedAd != null) return
        RewardedAd.load(context, TEST_REWARDED, AdRequest.Builder().build(),
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) { rewardedAd = ad }
                override fun onAdFailedToLoad(error: LoadAdError) { rewardedAd = null }
            })
    }

    fun showInterstitialIfReady(activity: Activity) {
        interstitialAd?.let { ad ->
            ad.show(activity)
            interstitialAd = null
            preloadInterstitial(activity)
        }
    }

    fun showRewardedIfReady(activity: Activity, onReward: () -> Unit) {
        rewardedAd?.let { ad ->
            ad.show(activity) { onReward() }
            rewardedAd = null
            preloadRewarded(activity)
        }
    }
}
