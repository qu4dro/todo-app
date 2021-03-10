package ru.orlovvv.todoapp.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.orlovvv.todoapp.data.db.entities.TaskItem

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskItem)

    @Update
    suspend fun update(task: TaskItem)

    @Delete
    suspend fun delete(task: TaskItem)

    @Query("SELECT * FROM tasks_table WHERE task_name LIKE '%' || :searchQuery || '%' ORDER BY is_important")
    fun getAllTasks(searchQuery: String): Flow<List<TaskItem>>
}