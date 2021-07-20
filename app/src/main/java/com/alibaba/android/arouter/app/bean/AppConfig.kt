package com.alibaba.android.arouter.app.bean

data class AppConfig(
    val base: Base,
    val locale: Locale,
    val redirect: Redirect,
    val testing: Testing
)

data class Base(
    val appParams: List<AppParam>,
    val dataSource: List<DataSource>,
    val services: List<Service>
)

data class Locale(
    val country: Country,
    val language: Language
)

data class Redirect(
    val devices: List<Device>
)

data class Testing(
    val enabled: Boolean,
    val group: List<Group>
)

data class AppParam(
    val enabled: Boolean,
    val nlid: String,
    val params: Params,
    val url: String
)

data class DataSource(
    val enabled: Boolean,
    val nlid: String,
    val params: ParamsX,
    val url: String
)

data class Service(
    val nlid: String,
    val params: ParamsXX,
    val url: String
)

data class Params(
    val NBAStoreURL_iPad: String,
    val NBAStoreURL_iPhone: String,
    val activeSKU: String,
    val adroit: String,
    val appId: String,
    val authorization: String,
    val backupGeo: String,
    val base64EncodedPublicKey: String,
    val cameraFeeds: List<CameraFeed>,
    val categoryId: String,
    val challengeWebView: String,
    val countryCodeUrl: String,
    val debugLog: Boolean,
    val dfpAdUnitID_CenterCourt: String,
    val dfpAdUnitID_Games: String,
    val dfpAdUnitID_Latest: String,
    val dfpAdUnitID_Standings: String,
    val dfpAdUnitID_Stats: String,
    val dfpAdUnitID_Videos: String,
    val dfpVideoTagX: String,
    val email: Email,
    val faqURL: String,
    val feedServer2012URL: String,
    val feedServerURL: String,
    val forceUpgrade: Boolean,
    val gaa: String,
    val gameTicketsByGameUrl: String,
    val gameTicketsUrl: String,
    val gamestreamWebView: String,
    val geoFence: Boolean,
    val iabSKUPrefix: String,
    val insidersWebView: String,
    val lanuchVerify: Boolean,
    val locImage: String,
    val locImage_ORIG: String,
    val locStream: String,
    val maxPrice: MaxPrice,
    val message: String,
    val name: String,
    val nlurl: String,
    val noUpsellMessage: String,
    val num1: String,
    val packages: List<Package>,
    val packagesFeedUrl: String,
    val playoffsURL_iPad: String,
    val playoffsURL_iPhone: String,
    val productDemoURL_iPad: String,
    val productDemoURL_iPhone: String,
    val refreshTimeInterval: String,
    val risingStarChallengeGameIds: String,
    val saturdayNightCameras: String,
    val saturdayNightId: String,
    val scheduleDisplayInterval: Int,
    val scheduleEndDate: String,
    val scheduleInitialDate_RENAME: String,
    val scheduleStartDate: String,
    val season: String,
    val showPlayoffs: Boolean,
    val showSingleGamePurchases: Boolean,
    val showTicketLink: Boolean,
    val statisticsSeasonType: String,
    val title: String,
    val tosURL: String,
    val upgradeUrl: String,
    val videoSearchServer: String
)

data class CameraFeed(
    val bit: String,
    val displayName: String
)

data class Email(
    val enable: String,
    val subject: String,
    val to: String
)

data class MaxPrice(
    val CAD: String,
    val EUR: String,
    val USD: String
)

data class Package(
    val begins: String,
    val desc: String,
    val expires: String,
    val season: String,
    val sku: String,
    val title: String
)

data class ParamsX(
    val domainIdentifier: String,
    val highlightQuery: String,
    val languageCode: String,
    val linkServerURL: String,
    val newsCountries: List<Any>,
    val nextToday: String,
    val preToday: String,
    val storageLocation: String,
    val urlArray: List<UrlArray>
)

data class UrlArray(
    val name: String,
    val url: String
)

data class ParamsXX(
    val appId: String,
    val comment: String,
    val comments: String,
    val config: Int,
    val decoder: String,
    val default: Int,
    val dl: Int,
    val enable: Boolean,
    val epg: Int,
    val games: Int,
    val geoCache: Int,
    val playoffs: Int,
    val productID: String,
    val sampleInterval: String,
    val schedule: Int,
    val sdk: Int,
    val sessionPoll: Int,
    val singleGameAccessCache: Int,
    val siteID: String,
    val tickets: Int,
    val useChromecast: Boolean,
    val widget: Int
)

class Country(
)

class Language(
)

data class Device(
    val deviceId: DeviceId,
    val url: String
)

data class DeviceId(
    val neulion_alan_iphone4s: String,
    val neulion_jay_iphone5s: String
)

data class Group(
    val items: List<Item>,
    val name: String
)

data class Item(
    val items: List<ItemX>,
    val name: String
)

data class ItemX(
    val nlid: String,
    val url: String
)