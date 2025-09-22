package pt.devsorcerer.newsapp.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlin.math.ceil

/**
 * Calculates the estimated reading time for a given piece of content.
 * Uses `remember` to avoid re-calculating on every recomposition.
 *
 * @param content The text content of the article.
 * @return A formatted string like "5 min read". Defaults to "1 min read".
 */
@Composable
fun calculateReadingTime(content: String?): String {
    return remember(content) {
        val wpm = 225 // Average reading speed in words per minute

        // Count words by splitting on any whitespace
        val wordCount = content?.split(Regex("\\s+"))?.size ?: 0
        if (wordCount == 0) {
            return@remember "1 min\nread"
        }

        // Calculate time and round up to the nearest minute
        val readingTime = ceil(wordCount.toDouble() / wpm).toInt()

        // Ensure the minimum reading time is 1 minute
        val finalTime = maxOf(1, readingTime)

        "$finalTime min\nread"
    }
}