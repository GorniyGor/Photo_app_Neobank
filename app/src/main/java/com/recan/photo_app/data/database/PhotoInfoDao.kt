package com.recan.photo_app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.recan.photo_app.data.model.PhotoDbEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PhotoInfoDao {

    @Insert
    fun insertPhoto(photo: PhotoDbEntity): Completable

    @Query("select * from Photo")
    fun getPhotos(): Observable<List<PhotoDbEntity>>
}