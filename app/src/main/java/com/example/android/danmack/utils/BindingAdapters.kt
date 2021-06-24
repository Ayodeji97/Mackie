import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.danmack.model.searchmodel.AutoComplete
import com.example.android.danmack.model.searchmodel.HitsData
import com.example.android.danmack.model.searchmodel.SearchData
import com.example.android.danmack.model.searchmodel.TermData
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

//@BindingAdapter("searchTerm")
//fun TextView.setAutoComplete (item : TermData) {
//   text = item.term
//}
