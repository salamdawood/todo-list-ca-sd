package com.example.todolistcasd

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class TodoItemRoomDatabase : RoomDatabase() {

    abstract fun todoItemDao(): TodoItemDao

    /**
     * Here we use the Singleton pattern to create a single INSTANCE of a Room Database
     * @Volatile is used to indicate that the INSTANCE variable can have its value changed
     * from multiple threads. This annotation is used to ensure the value is always up to date.
     */
    companion object {
        @Volatile
        private var INSTANCE: TodoItemRoomDatabase? = null

        /**
         * If the INSTANCE is not null, then return it
         * if it is, then create the database
         */
        fun getDatabase(context: Context): TodoItemRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoItemRoomDatabase::class.java,
                    "todo_item_database"
                ).build()
                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}