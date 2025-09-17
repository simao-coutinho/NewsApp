package pt.devsorcerer.newsapp.data.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Upsert

/**
 * Interface with the basic methods that must implement the application of the application to make
 * INSERT, UPDATE AND DELETE. All @query ("select ..") remaining must be declared in the
 * Daughters interfaces.
 *
 * It is advisable to write down with @transaction all the methods @query that have a select sentence
 * In the following cases:
 *
 * 1. The result of the Query is great.
 * 2. The result of the Query is a pojo with @relation fields. In this way we ensure that
 * Each @query ("select ...") independent (for each @relation) is executed in the same
 * Transaction and the results are consistent between Queries
 *
 * [T] type of entity with which the DAO will work
 */
interface RoomDao<T> {

    /**
     * Stores the [entity] in database and returns the lowId of the new inserted entity.
     * If the entity is a "Table RowId" (its primaryKey is an integer), the rowId corresponds to
     * Your assigned ID
     */
    @Insert
    fun insert(entity: T): Long

    /**
     * Same behavior that [insert] unless returning a list with the rowId of all
     * New insertions
     */
    @Insert
    fun insert(entities: List<T>): List<Long>

    @Upsert
    fun upsert(entity: T): Long

    @Upsert
    fun upsert(entities: List<T>): List<Long>

    /**
     * Update on [entity] database and return the number of affected records
     */
    @Update
    fun update(entity: T): Int

    /**
     * Update on the database the list of [entities] and returns the number of records
     * affected
     */
    @Update
    fun update(entities: List<T>): Int

    /**
     * Database eliminates the [entity] and returns the number of affected records
     */
    @Delete
    fun delete(entity: T): Int

    /**
     * Databases the [entities] list and returns the number of affected records
     */
    @Delete
    fun delete(entities: List<T>): Int

}