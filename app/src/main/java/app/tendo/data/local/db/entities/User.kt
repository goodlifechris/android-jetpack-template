package app.tendo.data.local.db.entities
/**
 * Created by goodlife on 26,August,2021
 */
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @NonNull
    @PrimaryKey
    val id: Int,
    val name: String?,
    val phone: String?,
    val shares: Double,
)