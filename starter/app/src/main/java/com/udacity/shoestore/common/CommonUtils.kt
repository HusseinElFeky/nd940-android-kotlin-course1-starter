package com.udacity.shoestore.common

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import com.udacity.shoestore.R

fun isFilled(textInputLayout: TextInputLayout): Boolean {
    return if (textInputLayout.editText!!.text.isNullOrEmpty()) {
        textInputLayout.error = textInputLayout.context.getString(R.string.error_field_required)
        false
    } else {
        textInputLayout.error = null
        true
    }
}

fun isEverythingFilled(textInputLayouts: List<TextInputLayout>): Boolean {
    var noErrors = true
    for (textInputLayout in textInputLayouts) {
        val isFilled = isFilled(textInputLayout)
        if (noErrors) {
            noErrors = isFilled
        }
    }
    return noErrors
}

fun TextInputLayout.addFilledTextWatcher() {
    editText!!.addTextChangedListener {
        isFilled(this)
    }
}

fun TextView.setClickableText(
    clickableText: String,
    wholeText: String,
    onClickAction: () -> Unit
) {
    val spannableString = SpannableString(wholeText)
    val startIndex = spannableString.indexOf(clickableText)

    val clickableSpan = object : ClickableSpan() {
        override fun onClick(textView: View) {
            onClickAction.invoke()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
        }
    }

    spannableString.setSpan(
        clickableSpan,
        startIndex,
        startIndex + clickableText.length,
        Spanned.SPAN_INCLUSIVE_EXCLUSIVE
    )

    text = spannableString
    movementMethod = LinkMovementMethod.getInstance()
    highlightColor = Color.TRANSPARENT
}
