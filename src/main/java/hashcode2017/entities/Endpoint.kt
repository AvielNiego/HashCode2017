package hashcode2017.entities

class Endpoint(private val id: Int, private val dsLatency: Int, private val connections: List<CacheConnection>) {
    private val dsConnection = CacheConnection(Cache(-1, Int.MAX_VALUE), dsLatency)

    fun getMinLatencyCacheConnection(video: Video): CacheConnection {
        return connections.filter { it.cache.hasPlaceFor(video) }.minBy { it.latency } ?: dsConnection
    }

    fun getMinLatencyCacheConnectionWithVideo(video: Video): CacheConnection {
        return connections.filter { it.cache.hasVideo(video) }.minBy { it.latency } ?: dsConnection
    }

    fun getConnections(): List<CacheConnection> {
        return connections
    }
}

