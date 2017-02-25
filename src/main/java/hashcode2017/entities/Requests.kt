package hashcode2017.entities

class Requests(val video: Video, private val endpoint: Endpoint, private val count: Int) {
    var rank: Int = 0
    get() = field

    lateinit var bestCache: Cache
    get

    var isValid = false
    private val caches = endpoint.getConnections().map { it.cache }

    init {
        calcRank()
    }

    fun calcRank() {
        val bestCacheConnection = endpoint.getMinLatencyCacheConnection(video)
        val currentBestConnectionToVideo = endpoint.getMinLatenctCacheConnectionWithVideo(video)
        rank = count * (currentBestConnectionToVideo.latency - bestCacheConnection.latency)
        bestCache = bestCacheConnection.cache
        isValid = true
    }

    fun invalidate(bestCache: Cache, bestVideo: Video) {
        if (!isValid) return
        isValid = !(bestCache == this.bestCache || (this.video == bestVideo && bestCache in caches))
    }
}