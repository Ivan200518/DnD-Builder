package com.example.dndbuilder

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.math.floor

class StatsTableFragment : Fragment(R.layout.fragment_states_table) {

    private val stats = listOf(
        Stat("POWER"),
        Stat("DEXTERITY"),
        Stat("CONCENT"),
        Stat("INITIATIVE"),
        Stat("WISDOM"),
        Stat("CHARISMA")
    )

    private lateinit var statsTable: TableLayout
    private var currentBackground: String = "Acolyte" // пример, должен приходить извне

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        statsTable = view.findViewById(R.id.statsTable)


        setupBackgroundModPermissions(currentBackground)
        populateTable()


    }

    private fun setupBackgroundModPermissions(background: String) {
        // Примерная логика: Acolyte может редактировать WISDOM и CHARISMA
        stats.forEach {
            it.editableMod = when (background) {
                "Acolyte" -> it.name in listOf("WISDOM", "CHARISMA")
                "Soldier" -> it.name in listOf("POWER", "INITIATIVE")
                else -> false
            }
        }
    }

    private fun populateTable() {
        statsTable.removeAllViews()

        // Заголовок
        val header = TableRow(requireContext())
        listOf("Stat", "Base", "Race", "Other", "Total", "Mod").forEach {
            header.addView(makeTextView(it, true))
        }
        statsTable.addView(header)

        // Строки
        stats.forEach { stat ->
            val row = TableRow(requireContext())

            val baseInput = makeEditText(stat.base.toString())
            val raceInput = makeEditText(stat.race.toString())
            val otherInput = makeEditText(stat.other.toString())
            val totalView = makeTextView("0")
            val modView = if (stat.editableMod) {
                makeEditText(stat.mod.toString())
            } else {
                makeTextView("0")
            }

            // обновление total/mod при изменении
            val watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val base = baseInput.text.toString().toIntOrNull() ?: 0
                    val race = raceInput.text.toString().toIntOrNull() ?: 0
                    val other = otherInput.text.toString().toIntOrNull() ?: 0
                    val total = base + race + other
                    val mod = floor((total - 10) / 2.0).toInt()

                    totalView.text = total.toString()
                    if (!stat.editableMod && modView is TextView) {
                        modView.text = if (mod >= 0) "+$mod" else "$mod"
                    }

                    // сохраняем в модель
                    stat.base = base
                    stat.race = race
                    stat.other = other
                    stat.mod = mod
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            }

            baseInput.addTextChangedListener(watcher)
            raceInput.addTextChangedListener(watcher)
            otherInput.addTextChangedListener(watcher)

            row.addView(makeTextView(stat.name))
            row.addView(baseInput)
            row.addView(raceInput)
            row.addView(otherInput)
            row.addView(totalView)
            row.addView(modView)

            statsTable.addView(row)
        }
    }

    private fun makeTextView(text: String, bold: Boolean = false): TextView {
        return TextView(requireContext()).apply {
            setText(text)
            setTextColor(Color.WHITE)
            setPadding(8, 8, 8, 8)
            gravity = Gravity.CENTER
            if (bold) setTypeface(null, Typeface.BOLD)
        }
    }

    private fun makeEditText(default: String): EditText {
        return EditText(requireContext()).apply {
            setText(default)
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED
            setBackgroundColor(Color.parseColor("#D7CCC8"))
            gravity = Gravity.CENTER
            layoutParams = TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
        }
    }
}
