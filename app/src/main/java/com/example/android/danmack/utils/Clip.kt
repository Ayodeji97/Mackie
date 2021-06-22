import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast

object Clip {

    fun clipReferral(text: String, activity: Activity) {
        val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.setPrimaryClip(clip)

        Toast.makeText(
            activity, "Copied",
            Toast.LENGTH_SHORT
        ).show()
    }


}