package es.iessaladillo.pedrojoya.pr05_trivial.ui.main

import android.app.Dialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.ui.about.AboutFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.confirmation.ConfirmationExitDialogFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game.GameFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.rules.RulesFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.settings.SettingsFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.title.TitleFragment
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT"

class MainActivity : AppCompatActivity() {

    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAppBar()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    private fun setupAppBar() {
        setSupportActionBar(toolbar)
    }


    // Depending of the actual fragment it does different actions.
    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.frgContainer) is GameFragment && settings.getBoolean(getString(R.string.switchPreferenceCompat_key), true)){
            // If we are in the middle of the game and the Show Confirmation Dialog setting is active, it shows a confirmation dialog.
            ConfirmationExitDialogFragment().show(supportFragmentManager, TAG_DETAIL_FRAGMENT)
        } else if (supportFragmentManager.findFragmentById(R.id.frgContainer) is TitleFragment){
            // If we are at the title screen it closes the app.
            finishAffinity()
        } else {
            // Anywhere else it goes back to the title screen.
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frgContainer, TitleFragment(), TAG_DETAIL_FRAGMENT)
                .commit()
        }
    }

    // Selecting the three menus on the toolbar redirect us to the selected fragment.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuRules -> {
                navigateToRules()
            }
            R.id.mnuAbout -> {
                navigateToAbout()
            }
            R.id.mnuSettings -> {
                navigateToSettings()
            }
            android.R.id.home -> {
                onBackPressed()
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun navigateToSettings() {
        val settingsFragment = SettingsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frgContainer, settingsFragment, TAG_DETAIL_FRAGMENT)
            .commit()
    }

    private fun navigateToAbout() {
        val aboutFragment =
            AboutFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frgContainer, aboutFragment, TAG_DETAIL_FRAGMENT)
            .commit()
    }

    private fun navigateToRules() {
        val rulesFragment =
            RulesFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frgContainer, rulesFragment, TAG_DETAIL_FRAGMENT)
            .commit()
    }

}