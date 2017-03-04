package hashcode2017

import hashcode2017.entities.Cache
import hashcode2017.entities.Requests

fun main(args : Array<String>) {

    val filePath = "C:\\Develop\\GamingInvest\\SpringDefaultProj\\HashCode2017\\src\\main\\java\\hashcode2017\\videos_worth_spreading.in"
    val f = FileParser(filePath)
    f.parseFile()
    println("Finish reading file")
    val requests: List<Requests> = f.requests
    val caches: List<Cache> = f.caches

    var i = 0
    var sortedRequests: MutableList<Requests> = requests.sortedBy { it.rank }.toMutableList()
    while (sortedRequests.isNotEmpty()) {
        if (!sortedRequests.last().isValid){
            sortedRequests = sortedRequests.filter { it.calcRank() ; it.rank > 0 }.sortedBy { it.rank }.toMutableList()
        }
        val bestRequests = sortedRequests.last()
        sortedRequests.removeAt(sortedRequests.lastIndex)
        bestRequests.bestCache.addVideo(bestRequests.video)
        sortedRequests.forEach { it.invalidate(bestRequests.bestCache, bestRequests.video) }
        println("${i++} requests size ${sortedRequests.size}, $filePath")
        if (i % 100 == 0) {
            FileWriter(filePath, caches).writeLines()
        }
    }

    FileWriter(filePath, caches).writeLines()
}