package hashcode2017

import hashcode2017.entities.Cache
import java.io.File

class FileWriter(filePath: String, caches: List<Cache>) {
    val caches = caches.filter { it.videos.isNotEmpty() }
    val filePath = filePath.replace(".in", ".out")

    fun writeLines(){
        val lines :MutableList<String> = mutableListOf(caches.size.toString())
        caches.forEach { lines.add(it.id.toString() + " " + getVideosIdsString(it)) }
        File(filePath).writeText(lines.joinToString("\n"))
    }

    private fun getVideosIdsString(cache: Cache) = cache.videos.map { it.id.toString() }.joinToString(" ")
}