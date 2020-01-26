package es.iessaladillo.pedrojoya.pr05_trivial.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.ui.about.AboutFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.rules.RulesFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.settings.SettingsFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.title.TitleFragment
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT"

class MainActivity : AppCompatActivity() {

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


    override fun onBackPressed() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frgContainer, TitleFragment(), TAG_DETAIL_FRAGMENT)
            .commit()
    }

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