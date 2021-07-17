import java.time.LocalDateTime

enum class Direction {
    BUY, SELL
}

enum class Trend {
    BULLISH, LOWBULLISH, UNCLEAR, LOWBEARISH, BEARISH
}

class Signal(date: LocalDateTime, direction: Direction) {}

fun initializeSignals() {}

fun marketTrendFromSignals() {}

fun updateSettings(signal: String) {}

fun main(args: Array<String>) {
    var timeInterval: Int = 60 // Time interval of the signals in minutes
    var signals: List<Signal>?
    var trend: String?
    // Init last signals

    // Evaluate trend

    // Update config from trend
}
