package com.android.contact.android.crypto.change.core.internal.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface KDao<in T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T): Long

    @Update
    fun update(t: T)

    @Delete
    fun delete(t: T)
}