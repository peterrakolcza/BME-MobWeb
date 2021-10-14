package hu.bme.aut.simpledrawer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import hu.bme.aut.simpledrawer.databinding.ActivityDrawingBinding
import hu.bme.aut.simpledrawer.sqlite.PersistentDataHelper
import hu.bme.aut.simpledrawer.view.DrawingView

class DrawingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDrawingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataHelper = PersistentDataHelper(this)
        dataHelper.open()
        restorePersistedObjects()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val toolbarMenu: Menu = binding.toolbar.menu
        menuInflater.inflate(R.menu.menu_toolbar, toolbarMenu)
        for (i in 0 until toolbarMenu.size()) {
            val menuItem: MenuItem = toolbarMenu.getItem(i)
            menuItem.setOnMenuItemClickListener { item -> onOptionsItemSelected(item) }
            if (menuItem.hasSubMenu()) {
                val subMenu: SubMenu = menuItem.subMenu
                for (j in 0 until subMenu.size()) {
                    subMenu.getItem(j)
                        .setOnMenuItemClickListener { item -> onOptionsItemSelected(item) }
                }
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_style_line -> {
                binding.canvas.currentDrawingStyle = DrawingView.DRAWING_STYLE_LINE
                item.isChecked = true
                true
            }
            R.id.menu_style_point -> {
                binding.canvas.currentDrawingStyle = DrawingView.DRAWING_STYLE_POINT
                item.isChecked = true
                true
            }
            R.id.menu_delete -> {
                binding.canvas.lines.clear()
                binding.canvas.points.clear()
                dataHelper.clearLines()
                dataHelper.clearPoints()

                binding.canvas.invalidate()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private lateinit var dataHelper: PersistentDataHelper

    override fun onResume() {
        super.onResume()
        dataHelper.open()
    }

    override fun onPause() {
        dataHelper.close()
        super.onPause()
    }

    private fun restorePersistedObjects() {
        binding.canvas.restoreObjects(dataHelper.restorePoints(), dataHelper.restoreLines())
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage(R.string.are_you_sure_want_to_exit)
            .setPositiveButton(R.string.ok) { _, _ -> onExit() }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun onExit() {
        dataHelper.persistPoints(binding.canvas.points)
        dataHelper.persistLines(binding.canvas.lines)
        dataHelper.close()
        finish()
    }

}