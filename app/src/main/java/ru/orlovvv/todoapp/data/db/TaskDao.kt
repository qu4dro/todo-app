package ru.orlovvv.todoapp.data.db

import androidx.room.*
import com.google.android.play.core.tasks.Task
import kotlinx.coroutines.flow.Flow
import ru.orlovvv.todoapp.data.db.entities.TaskItem
import ru.orlovvv.todoapp.ui.tasks.SortOrder

@Dao
interface TaskDao {

    fun getAllTasks(query: String, sortOrder: SortOrder, hideCompleted: Boolean): Flow<List<TaskItem>> {
        return when(sortOrder) {
            SortOrder.BY_DATE -> getAllTasksSortedByDateCreated(query, hideCompleted)
            SortOrder.BY_NAME -> getAllTasksSortedByName(query, hideCompleted)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskItem)

    @Update
    suspend fun update(task: TaskItem)

    @Delete
    suspend fun delete(task: TaskItem)

    @Query("SELECT * FROM tasks_table WHERE (is_completed != :hideCompleted OR is_completed = 0) AND task_name LIKE '%' || :searchQuery || '%' ORDER BY is_important DESC, task_name")
    fun getAllTasksSortedByName(searchQuery: String, hideCompleted: Boolean): Flow<List<TaskItem>>

    @Query("SELECT * FROM tasks_table WHERE (is_completed != :hideCompleted OR is_completed = 0) AND task_name LIKE '%' || :searchQuery || '%' ORDER BY is_important DESC, date_created")
    fun getAllTasksSortedByDateCreated(searchQuery: String, hideCompleted: Boolean): Flow<List<TaskItem>>
}