package com.semirsuljevic.ui.api.common

import android.graphics.Typeface
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun HechimHtmlText(
    html: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(
        color = HechimTheme.colors.textDefault,
        fontSize = 14.sp
    ),
    hyperlinkStyle: TextStyle = TextStyle(
        color = HechimTheme.colors.textDefault,
        fontSize = 13.sp,
        textDecoration = TextDecoration.Underline
    ),
    softWrap: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onHyperlinkClick: (uri: String) -> Unit = {}
) {
    val spanned = remember(html) {
        HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY, null, null)
    }

    val annotatedText = remember(spanned, hyperlinkStyle) {
        buildAnnotatedString {
            append(spanned.toString())

            spanned.getSpans(0, spanned.length, Any::class.java).forEach { span ->
                val startIndex = spanned.getSpanStart(span)
                val endIndex = spanned.getSpanEnd(span)

                when (span) {
                    is StyleSpan -> {

                        span.toSpanStyle()?.let {
                            addStyle(
                                style = it,
                                start = startIndex,
                                end = endIndex
                            )
                        }
                    }
                    is UnderlineSpan -> {
                        addStyle(
                            SpanStyle(textDecoration = TextDecoration.Underline),
                            start = startIndex,
                            end = endIndex
                        )
                    }
                    is URLSpan -> {
                        addStyle(
                            hyperlinkStyle.toSpanStyle(),
                            start = startIndex,
                            end = endIndex
                        )
                        addStringAnnotation(
                            Tag.Hyperlink.name,
                            annotation = span.url,
                            start = startIndex,
                            end = endIndex
                        )
                    }
                }
            }
        }
    }

    ClickableText(
        annotatedText,
        modifier = modifier,
        style = style,
        softWrap = softWrap,
        overflow = overflow,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    ) {
        annotatedText.getStringAnnotations(tag = Tag.Hyperlink.name, start = it, end = it).firstOrNull()?.let {link ->
            onHyperlinkClick(link.item)
        }
    }
}

private fun StyleSpan.toSpanStyle(): SpanStyle? {
    return when (style) {
        Typeface.BOLD -> SpanStyle(fontWeight = FontWeight.Bold)
        Typeface.ITALIC -> SpanStyle(fontStyle = FontStyle.Italic)
        Typeface.BOLD_ITALIC -> SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
        else -> null
    }
}

private enum class Tag {
    Hyperlink
}

@Preview
@Composable
fun HechimHtmlTextPreview() {
    HechimHtmlText(
        html = "By continuing, you accept our <b><a href=\\\"https://www.facebook.com/\\\">Mobile App " +
            "License Agreement</a></b> & <b><a href=\\\"https://www.facebook.com/\\\">Privacy Policy</a></b>."
    )
}


