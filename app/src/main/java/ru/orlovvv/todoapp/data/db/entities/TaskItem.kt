package ru.orlovvv.todoapp.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "tasks_table")
@Parcelize
data class TaskItem(
        @ColumnInfo(name = "task_name")
        val name: String,
        @ColumnInfo(name = "is_important")
        val isImportant: Boolean = false,
        @ColumnInfo(name = "is_completed")
        val isCompleted: Boolean = false,
        @ColumnInfo(name = "date_created")
        val dateCreated: Long = System.currentTimeMillis(),
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
    val dateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(dateCreated)
}