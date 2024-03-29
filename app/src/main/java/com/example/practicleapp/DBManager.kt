package com.example.practicleapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase

class DBManager(private val context: Context) {
    private var dbHelper: DatabaseHelper? = null
    private var database: SQLiteDatabase? = null

    @Throws(SQLException::class)
    fun open(): DBManager {
        dbHelper = DatabaseHelper(context)
        database = dbHelper!!.writableDatabase
        return this
    }

    fun close() {
        dbHelper!!.close()
    }

    fun insert(name: String?, email: String?, address: String?, phone: String?) {
        val contentValue = ContentValues()
        contentValue.put(DatabaseHelper.SUBJECT, name)
        contentValue.put(DatabaseHelper.EMAIL, email)
        contentValue.put(DatabaseHelper.ADDRESS, address)
        contentValue.put(DatabaseHelper.PHONE, phone)
        database!!.insert(DatabaseHelper.TABLE_NAME, null, contentValue)
    }

    fun fetch(): Cursor? {
        val columns = arrayOf(DatabaseHelper._ID, DatabaseHelper.SUBJECT, DatabaseHelper.EMAIL, DatabaseHelper.ADDRESS
        , DatabaseHelper.PHONE)
        val cursor =
            database!!.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null)
        cursor?.moveToFirst()
        return cursor
    }

    fun update(_id: Long, name: String?, email: String?, address: String?, phone: String?): Int {
        val contentValues = ContentValues()
        contentValues.put(DatabaseHelper.SUBJECT, name)
        contentValues.put(DatabaseHelper.EMAIL, email)
        contentValues.put(DatabaseHelper.ADDRESS, address)
        contentValues.put(DatabaseHelper.PHONE, phone)
        return database!!.update(
            DatabaseHelper.TABLE_NAME,
            contentValues,
            DatabaseHelper._ID + " = " + _id,
            null
        )
    }

    fun delete(_id: Long) {
        database!!.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null)
    }
}