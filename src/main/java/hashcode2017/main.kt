package hashcode2017

import hashcode2017.entities.Cache
import hashcode2017.entities.Endpoint
import hashcode2017.entities.Requests
import hashcode2017.entities.Video

fun main(args : Array<String>) {

    val filePath = "C:\\Develop\\GamingInvest\\SpringDefaultProj\\HashCode2017\\src\\main\\java\\hashcode2017\\example.in"
    val f = FileParser(filePath)
    f.parseFile()

    val videos: List<Video> = f.videos
    val endpoint: List<Endpoint> = f.endpoints
    val requests: List<Requests> = f.requests
    val caches: List<Cache> = f.caches

    val sortedRequests: MutableList<Requests> = requests.sortedBy { it.rank }.toMutableList()
    while (sortedRequests.isNotEmpty()) {
        if (!sortedRequests.last().isValid)
            sortedRequests.forEach { it.calcRank() }
        val bestRequests = sortedRequests.last()
        bestRequests.bestCache.addVideo(bestRequests.video)
        sortedRequests.removeAt(sortedRequests.lastIndex)
        sortedRequests.forEach { it.invalidate(bestRequests.bestCache, bestRequests.video) }
    }

    FileWriter(filePath, caches).writeLines()
}