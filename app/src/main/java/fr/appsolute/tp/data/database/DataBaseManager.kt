package fr.appsolute.tp.data.database

import android.app.Application
import androidx.room.*
import fr.appsolute.tp.RickAndMortyApplication
import fr.appsolute.tp.data.model.Episode

@Database(
    entities = [Episode::class],
    version = 1,
    exportSchema = false
)
abstract class RickAndMortyDataBase: RoomDatabase() {
    abstract val episodeDao: EpisodeDao
}

private class DataBaseManagerImpl(applicationContext: RickAndMortyApplication): DataBaseManager {
    override val database: RickAndMortyDataBase =
        Room.databaseBuilder(applicationContext, RickAndMortyDataBase::class.java, applicationContext.packageName.toString()).build()

}

interface DataBaseManager {
    val database: RickAndMortyDataBase

    companion object {
        fun newInstance( applicationContext: RickAndMortyApplication): DataBaseManager =
            DataBaseManagerImpl(applicationContext)
    }
}

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM episode")
    fun selectAll(): List<Episode>

    @Insert
    fun insertAll(entities: List<Episode>)
}