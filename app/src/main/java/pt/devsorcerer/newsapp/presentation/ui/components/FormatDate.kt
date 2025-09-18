package pt.devsorcerer.newsapp.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

/**
 * A composable function that takes a date string (like "2025-09-18T18:31:00Z"),
 * parses it, and formats it into a more readable format (like "Sep 18, 2025").
 *
 * It uses `remember` to avoid re-calculating the format on every recomposition.
 *
 * @param publishedAt The raw date string from the API.
 * @return A formatted, human-readable date string.
 */
@Composable
fun formatDate(publishedAt: String): String {
    val formattedDate = remember(publishedAt) {
        try {
            // Parse the ISO 8601 date-time string
            val odt = OffsetDateTime.parse(publishedAt)
            // Define the desired output format (e.g., "Sep 18, 2025")
            val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.getDefault())
            // Apply the formatter
            odt.format(formatter)
        } catch (e: DateTimeParseException) {
            // If the date string is in an unexpected format,
            // return the original string to avoid a crash.
            publishedAt
        }
    }
    return formattedDate
}
