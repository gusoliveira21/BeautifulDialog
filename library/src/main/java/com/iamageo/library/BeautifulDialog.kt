package com.iamageo.library

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.beautiful_dialog.*

class BeautifulDialog {

    /***
     * Positions For Alert Dialog
     * */
    enum class POSITIONS {
        CENTER, BOTTOM, TOP
    }

    companion object {

        private lateinit var layoutInflater: LayoutInflater

        /***
         * core method For Alert Dialog
         * */
        fun build(
            context: Activity
        ): AlertDialog {
            layoutInflater = LayoutInflater.from(context)
            val alertDialog =
                AlertDialog.Builder(
                    context, R.style.beautiful_dialog
                )
                    .setView(R.layout.beautiful_dialog)
            val alert: AlertDialog = alertDialog.create()
            alert.show()
            return alert
        }
    }
}

/***
 * Title Properties For Alert Dialog
 * */
fun AlertDialog.title(
    title: String,
    fontStyle: Typeface? = null,
    titleColor: Int = 0
): AlertDialog {
    this.title.text = title.trim()
    if (fontStyle != null) {
        this.title.typeface = fontStyle
    }
    if (titleColor != 0) {
        this.title.setTextColor(titleColor)
    }
    this.title.show()
    return this
}

/***
 * Dialog Background properties For Alert Dialog
 * */
fun AlertDialog.background(
    dialogBackgroundColor: Int? = null
): AlertDialog {
    if (dialogBackgroundColor != null) {
        this.mainLayout.setBackgroundResource(dialogBackgroundColor)
    }
    return this
}

/***
 * Positions of Alert Dialog
 * */
fun AlertDialog.position(
    position: BeautifulDialog.POSITIONS = BeautifulDialog.POSITIONS.BOTTOM
): AlertDialog {
    val layoutParams = mainLayout.layoutParams as RelativeLayout.LayoutParams
    if (position == BeautifulDialog.POSITIONS.CENTER) {
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
    } else if (position == BeautifulDialog.POSITIONS.BOTTOM) {
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
    } else if (position == BeautifulDialog.POSITIONS.TOP) {
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)
    }
    mainLayout!!.layoutParams = layoutParams
    return this
}

/***
 * Sub Title or Body of Alert Dialog
 * */
fun AlertDialog.body(
    body: String,
    fontStyle: Typeface? = null,
    color: Int = 0
): AlertDialog {
    this.subHeading.text = body.trim()
    this.subHeading.show()
    if (fontStyle != null) {
        this.subHeading.typeface = fontStyle
    }
    if (color != 0) {
        this.subHeading.setTextColor(color)
    }
    return this
}

///***
// * Icon of  Alert Dialog
// * */
//fun AlertDialog.icon(
//    icon: Int,
//    animateIcon: Boolean = false
//): AlertDialog {
//    this.image.setImageResource(icon)
//    this.image.show()
//    // Pulse Animation for Icon
//    if (animateIcon) {
//        val pulseAnimation = AnimationUtils.loadAnimation(context, R.anim.pulse)
//        image.startAnimation(pulseAnimation)
//    }
//    return this
//}

/***
 * onPositive Button Properties For Alert Dialog
 *
 * No Need to call dismiss(). It is calling already
 * */
fun AlertDialog.onPositive(
    text: String,
    buttonBackgroundColor: Int? = null,
    textColor: Int? = null,
    action: (() -> Unit)? = null
): AlertDialog {
    this.yesButton.show()
    if (buttonBackgroundColor != null) {
        this.yesButton.setBackgroundResource(buttonBackgroundColor)
    }
    if (textColor != null) {
        this.yesButton.setTextColor(textColor)
    }
    this.yesButton.text = text.trim()
    this.yesButton.setOnClickListener {
        action?.invoke()
        dismiss()
    }
    return this
}

/***
 * onNegative Button Properties For Alert Dialog
 *
 * No Need to call dismiss(). It is calling already
 * */
fun AlertDialog.onNegative(
    text: String,
    buttonBackgroundColor: Int? = null,
    textColor: Int? = null,
    action: (() -> Unit)? = null
): AlertDialog {
    this.noButton.show()
    this.noButton.text = text.trim()
    if (textColor != null) {
        this.noButton.setTextColor(textColor)
    }
    if (buttonBackgroundColor != null) {
        this.noButton.setBackgroundResource(buttonBackgroundColor)
    }
    this.noButton.setOnClickListener {
        action?.invoke()
        dismiss()
    }
    return this
}

private fun View.show() {
    this.visibility = View.VISIBLE
}