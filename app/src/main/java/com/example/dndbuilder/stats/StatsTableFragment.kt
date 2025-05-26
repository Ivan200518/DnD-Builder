package com.example.dndbuilder.stats

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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.dndbuilder.R
import com.example.dndbuilder.character.CharacterApi
import com.example.dndbuilder.character.CharacterRequest
import com.example.dndbuilder.character.CharacterViewModel
import com.example.dndbuilder.utils.Ability
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.floor


class StatsTableFragment : Fragment(R.layout.fragment_states_table) {
    private val viewModel: CharacterViewModel by activityViewModels()
    private lateinit var statsTable: TableLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statsTable = view.findViewById(R.id.statsTable)

        setupBackgroundModifiers()
        populateTable()
    }


    private fun setupBackgroundModifiers() {
        val background = viewModel.background.value ?: "Acolyte"
        val updatedStats = viewModel.stats.value?.map { stat ->
            stat.copy(editableMod = when (background) {
                "Acolyte" -> stat.name in listOf("WISDOM", "CHARISMA")
                "Soldier" -> stat.name in listOf("STRENGTH", "CONSTITUTION")
                else -> false
            })
        }
        viewModel.stats.value = updatedStats
    }

    private fun populateTable() {
        statsTable.removeAllViews()

        val header = TableRow(requireContext()).apply {
            addView(makeTextView("Stat", true))
            addView(makeTextView("Base", true))
            addView(makeTextView("Race", true))
            addView(makeTextView("Other", true))
            addView(makeTextView("Total", true))
            addView(makeTextView("Mod", true))
        }
        statsTable.addView(header)

        val stats = viewModel.stats.value ?: return

        stats.forEachIndexed { index, stat ->
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

            val watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    updateStatValues(index, baseInput, raceInput, otherInput, totalView, modView)
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            }

            baseInput.addTextChangedListener(watcher)
            raceInput.addTextChangedListener(watcher)
            otherInput.addTextChangedListener(watcher)

            row.apply {
                addView(makeTextView(stat.name))
                addView(baseInput)
                addView(raceInput)
                addView(otherInput)
                addView(totalView)
                addView(modView)
            }
            statsTable.addView(row)
        }
    }

    private fun updateStatValues(
        index: Int,
        baseInput: EditText,
        raceInput: EditText,
        otherInput: EditText,
        totalView: TextView,
        modView: View
    ) {
        val base = baseInput.text.toString().toIntOrNull() ?: 0
        val race = raceInput.text.toString().toIntOrNull() ?: 0
        val other = otherInput.text.toString().toIntOrNull() ?: 0

        val total = base + race + other
        val mod = floor((total - 10) / 2.0).toInt()

        totalView.text = total.toString()
        when (modView) {
            is EditText -> modView.setText(mod.toString())
            is TextView -> modView.text = if (mod >= 0) "+$mod" else "$mod"
        }

        val stats = viewModel.stats.value?.toMutableList() ?: return
        val stat = stats[index]
        stats[index] = stat.copy(base = base, race = race, other = other, mod = mod)
        viewModel.stats.value = stats
    }

    private fun makeTextView(text: String, bold: Boolean = false): TextView {
        return TextView(requireContext()).apply {
            this.text = text
            setTextColor(Color.WHITE)
            setPadding(8, 8, 8, 8)
            gravity = Gravity.CENTER
            if (bold) setTypeface(null, Typeface.BOLD)
            layoutParams = TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
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
