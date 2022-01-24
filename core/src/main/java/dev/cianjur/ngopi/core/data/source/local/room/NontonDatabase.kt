package dev.cianjur.ngopi.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.cianjur.ngopi.core.data.source.local.entity.MovieEntity
import dev.cianjur.ngopi.core.data.source.local.entity.TvShowEntity

@Database(
    entities = [
        MovieEntity::class,
        TvShowEntity::class,
    ],
    version = 1, exportSchema = false
)
abstract class NontonDatabase : RoomDatabase() {

    abstract fun nontonDao(): NontonDao
}
