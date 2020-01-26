package es.iessaladillo.pedrojoya.pr05_trivial.ui.confirmation

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.ui.title.TitleFragment

class ConfirmationExitDialogFragment : DialogFragment() {

    private var myContext: FragmentActivity? = null

    @Suppress("DEPRECATION")
    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        super.onAttach(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    // Creates the confirmation exit dialog when trying to exit the current game.
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.main_confirmation))
            .setMessage(getString(R.string.main_quit_game))
            .setPositiveButton(
                getString(R.string.main_yes)
            ) { _, _ ->
                val titleFragment = TitleFragment()
                myContext!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frgContainer, titleFragment)
                    .commit()
            }
            .setNegativeButton(
                getString(R.string.main_no)
            ) { _, _ ->
            }.create()


}
