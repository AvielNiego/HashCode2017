package hashcode2017

import hashcode2017.entities.*
import java.io.File
import java.util.*

class FileParser(private val filePath: String) {
    lateinit var s: Scanner
    var vCount: Int = 0
    var eCount: Int = 0
    var rCount: Int = 0
    var cCount: Int = 0
    var cacheSize: Int = 0

    public var caches: List<Cache> = emptyList()
    public var videos: List<Video> = emptyList()
    public var endpoints: List<Endpoint> = emptyList()
    public var requests: List<Requests> = emptyList()

    fun parseFile() {
        s = Scanner(File(filePath))
        readCountersLine()
        caches = (0 until cCount).map { Cache(it, cacheSize) }
        videos = (0 until vCount).map{ Video(it, nextLine()) }
        endpoints = readEndpoints()
        requests = readRequests()
    }

    private fun readRequests(): List<Requests> {
        return (0 until rCount).map { Requests(videos[nextLine()], endpoints[nextLine()], nextLine()) }
    }

    private fun readEndpoints(): List<Endpoint> {
        return (0 until eCount).map { Endpoint(it, nextLine(), createCacheConnections(nextLine())) }
    }

    private fun createCacheConnections(connectionsCount: Int): List<CacheConnection> {
        return (0 until connectionsCount).map { CacheConnection(caches[nextLine()], nextLine()) }
    }

    private fun readCountersLine() {
        vCount = nextLine()
        eCount =nextLine()
        rCount = nextLine()
        cCount = nextLine()
        cacheSize = nextLine()
    }

    private fun nextLine(): Int {
        return s.next().toInt()
    }
}