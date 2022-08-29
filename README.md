# aotter-trek-android-kotlin-demo
Trek SDK Example App Demo

## Trek SDK Document
https://trek.gitbook.io/aottertrek-sdk-doc/android/new-android-aottertrek-trek-sdk

## Trek SDK release change log
- 2022/08/04 release - SDK `4.7.2` (Recommend)
  - TrekBannerAdView new feature
    - preload
    - auto refresh
  - TrekNativeAd object new feature
     - `images` object provider `drawable` 、`uri`
     -  remove unnecessary parameter
  - Breaking change:  
     **we had been changed trek and mediation dependencies path**       
     - trek : `implementation 'com.aotter.android:trek-ads:4.7.2'`     
     - trek admob mediation : `implementation 'com.aotter.android:trek-admob-mediation:4.7.2'`    
     **we had been changed mediation custom class path**    
     - Native Ad : `com.aotter.trek.admob.mediation.ads.TrekAdmobCustomEventNative`    
     - Banner Ad : `com.aotter.trek.admob.mediation.ads.TrekAdmobCustomEventBanner`    
         - Detail refer [Migration Guide](https://trek.gitbook.io/aottertrek-sdk-doc/android/migration-guide)
         - Detail refer [Trek installation](https://trek.gitbook.io/aottertrek-sdk-doc/android/sdk-integration/installation)
         - Detail refer [Admob mediation installation](https://trek.gitbook.io/aottertrek-sdk-doc/android/admob-mediation/installation)
- 2022/06/22 release - SDK `4.6.1`
     - New Sensor
     -  `TrekAdLoader` instead of `TrekAd`
     - The `TrekAdLoader.loadAds()` method sends a request for multiple ads (up to 5)
     - `TrekNativeAd` instead of `AdData`
     - `TrekAds.initialize() method` instead of  `AotterService.initialize() method`
     - `TrekBannerAdView` instead of `TrekBannerView `
     - New `TrekAdViewBinder` object (Using the object register ad layout)
     - `TrekJsonObject` instead of `JsonObject`
     - Kotlinx-serialization instead of Gson
     - Updating ExoPlayer version to `2.17.1`
     - Updating Kotlin version to `1.6.21`
- 2022/06/22 release - SDK `4.5.0`
     - new TrekNativeAdImage 
     - Support vertical slide
     - Open browser with chrome
     - imp tool optimization
     - TrekMediaView optimization
     - Log optimization
- 2022/04/15 release - SDK `4.4.5`
     - Optimized implementation
     - The TrekAdmobAdViewBinder class has been removed, and the admob mediation binding view flow has been integrated into the mediation adapter.
     - Update admob mediation custom adapter ( [Migrate to SDK v21](https://developers.google.com/admob/android/migration) )
- 2022/03/28 release - SDK `4.4.2`
     - Add `setContentUrl()` & `setContentTitle()`
     - OMSDK obstruction minor adjustment
     - Add OMSDK contentUrl and customRefencData
     - Mapping Admob mediation `hasVideoContent` parameter
- 2022/03/22 release - SDK `4.4.0`
     - change domain
     - support om json tag
     - support om js string dynamic update
     - support webview slide
     - improve BackgroundHolder setting
     - update ExoPlayer
- 2021/12/23 release - SDK `4.3.4`
     - use Activity page (instead of DialogFragment page) when context comes from the application
- 2021/12/01 release - SDK `4.3.2`
     - support android 12
     - support kotlin version 1.5.31
     - update exoplayer
     - adjust impression tool
     - adjust third click event flow
     - fix banner ad、supr.ad impression event
- 2021/09/27 release - SDK `4.3.1`
     - Adjust om impressionType
- 2021/09/13 release - SDK `4.3.0`
     - Optimize impression/VTR/CTR
     - Adjust TrekMediaView lifecycle
     - Adjsut TrekMediaView play video when visibility is 50% or more
     - New TrekBannerView
- 2021/08/31 release - SDK `4.2.6`
     - remove unneeded permission 
- 2021/08/30 release - SDK `4.2.5`
     - remove supr.ad third imp
- 2021/08/24 release - SDK `4.2.4`
     - TrekMediaView default height
     - Defindes key of jsonObject
     - Add sdkVersion
     - Add mediationVersion
     - Optimization proguard
- 2021/07/29 release - SDK `4.2.1`
    - Add isExpired method
    - Add isVideoAd method
- 2021/07/16 release - SDK `4.2.0`
    - Optimize adData
    - Adjust TrekMediaView lifecycle
## Demo release change log
- 2022/07/19
    - update sample code
- 2022/04/15
    - update sample code
- 2022/03/28
    - update sample code
- 2021/07/01 
    - Add Native Ad sample code 
    - Add Supr.Ad sample code
    - Add Banner Ad sample code


