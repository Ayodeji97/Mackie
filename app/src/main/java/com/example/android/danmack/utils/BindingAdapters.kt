import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.danmack.model.songmodel.SongData
import com.example.android.danmack.model.songmodel.Track


@BindingAdapter("songTitle")
fun TextView.setSongTitle (item : Track) {
    text = item.title

}

@BindingAdapter("authorName")
fun TextView.setSongAuthorName (item: SongData) {

    text = item.tracks.first().title
}
