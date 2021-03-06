package ru.SnowVolf.convertx.ui.fragments.extended.hash

import android.os.Bundle
import android.support.v7.view.menu.MenuBuilder
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import ru.SnowVolf.convertx.R
import ru.SnowVolf.convertx.other.Extras
import ru.SnowVolf.convertx.settings.Preferences
import ru.SnowVolf.convertx.ui.Toasty
import ru.SnowVolf.convertx.ui.fragments.base.BaseFragment
import ru.SnowVolf.convertx.utils.StringUtils.copyToClipboard
import java.util.zip.Adler32

/**
 * Created by Snow Volf on 14.02.2017.
 */

class Adler : BaseFragment() {
    lateinit var data: EditText
    lateinit var dataOut: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.fragment_ex, container, false)
        data = rootView?.findViewById(R.id.exData) as EditText
        data.setHint(R.string.hint_adler32)
        data.isFocusable = true
        dataOut = rootView?.findViewById(R.id.exText) as TextView
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        TITLE = R.string.hint_adler32
        data.textSize = Preferences.getFontSize().toFloat()
        data.typeface = Mono
        dataOut.typeface = Mono
        data.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                val source = data.text.toString()
                //Преобразуем текст в массив байт
                val bytes = source.toByteArray()
                /**
                 * Вызываем объект Checksum и передаем ему Adler32 в качестве входного параметра
                 */
                val checksum = Adler32()
                checksum.update(bytes, 0, bytes.size)

                val adlerSum = checksum.value
                /**
                 * Т.к. adlerSum представяет собой Long, то для преобразования его в String
                 * достаточно прикрепить к нему пустую строку
                 */
                //фикс предупреждения Lint: [do not concentrate text displayed with setText. Use resources string with placeholders] в Android Studio
                dataOut.text = StringBuilder().append("").append(adlerSum).toString()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        var menu = menu
        if (menu != null) {
            menu.clear()
        } else
            menu = MenuBuilder(context)
        //добавляем пункты меню
        menu.add(R.string.copy2clipboard)
                .setIcon(R.drawable.ic_menu_copy)
                .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
                .setOnMenuItemClickListener { menuItem ->
                    copyToClipboard(context, dataOut.text.toString())
                    Extras.showToast(context, getString(R.string.copied2clipboard))
                    true
                }
        menu.add(R.string.clear_all)
                .setIcon(R.drawable.ic_menu_clear_all)
                .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
                .setOnMenuItemClickListener { menuItem ->
                    clearAllText()
                    true
                }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            activity.finish()
        return true
    }

    fun clearAllText() {
        data.setText("")
        dataOut.text = ""
        Toasty.success(context, getString(R.string.cleared), Toast.LENGTH_SHORT, true).show()
    }
}
