package com.example.todokpb.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todokpb.model.TodoDatabase

val DB_NAME = "tododb"
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 not null")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("INSERT INTO todo(title,notes,priority) VALUES('Study Hard', 'Play Harder', 3)")
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 not null")
    }
}

//fun buildDb(context: Context):TodoDatabase {
//    val db = Room.databaseBuilder(context,
//        TodoDatabase::class.java, DB_NAME).build()
//
//    return db
//}

fun buildDb(context: Context):TodoDatabase {
    val db = Room.databaseBuilder(context,
        TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
        .build()
    return db
}
