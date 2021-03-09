package ru.orlovvv.todoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.orlovvv.todoapp.data.db.entities.TaskItem
import ru.orlovvv.todoapp.di.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [TaskItem::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

    class Callback @Inject constructor(private val database: Provider<TaskDatabase>, @ApplicationScope val applicationScope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().getTaskDao()

            applicationScope.launch {
                dao.insert(TaskItem("Buy a car"))
                dao.insert(TaskItem("Buy a PC", isImportant = true))
                dao.insert(TaskItem("Exam learning", isCompleted = true))
                dao.insert(TaskItem("Buy a car"))
            }
        }
    }
}