package hashcode2017.entities

class Cache(val id: Number, private val size: Int) {
    val videos: MutableSet<Video> = mutableSetOf()
    private var placeOccupied: Int = 0
    get

    fun addVideo(video: Video) {
        videos.add(video)
        placeOccupied += video.size
        if (placeOccupied > size) {
            throw RuntimeException("Place exceeded")
        }
    }

    fun getPlaceRemained(): Int {
        return size - placeOccupied
    }

    fun hasPlaceFor(video: Video): Boolean {
        return getPlaceRemained() >= video.size
    }

    fun hasVideo(video: Video): Boolean {
        return video in videos
    }
}