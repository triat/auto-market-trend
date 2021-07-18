import java.time.LocalDateTime

enum class Direction {
    BUY, SELL
}

enum class MarketTrend {
    BULLISH, LOWBULLISH, UNCLEAR, LOWBEARISH, BEARISH
}

class Signal(private val date: LocalDateTime, val direction: Direction) {

    fun simplifiedDate(): LocalDateTime {
        return LocalDateTime.of(date.year, date.month, date.dayOfMonth, date.hour, date.minute)
    }
}

class Market {
    private val signals: MutableList<Signal> = mutableListOf()
    var trend: MarketTrend? = null

    fun addSignal(signal: Signal) {
        if (signals.size <= 2) signals.drop(1)
        signals.add(signal)
        println("New signal added")
    }

    fun currentTrend(): MarketTrend? {
        /*
        Check the direction of the signals to know if they follow the same trend or not.
        Then check if they happened at 1 interval of time (time between each possible signal) meaning
        that the market is in a strong direction or not
         */
        trend = if (signals[0].direction == signals[1].direction) {
            if (signals[1].direction == Direction.BUY) {
                if (signals[0].simplifiedDate() == signals[1].simplifiedDate().minusMinutes(timeframe)) {
                    MarketTrend.BULLISH
                } else {
                    MarketTrend.LOWBULLISH
                }
            } else {
                if (signals[0].simplifiedDate() == signals[1].simplifiedDate().minusMinutes(timeframe)) {
                    MarketTrend.BEARISH
                } else {
                    MarketTrend.LOWBEARISH
                }
            }
        } else {
            MarketTrend.UNCLEAR
        }
        return trend
    }
}

fun initializeSignals() {}

fun marketTrendFromSignals() {}

fun updateSettings() {}

const val timeframe: Long = 60 // Time between two possible signals expressed in minutes

fun main(args: Array<String>) {
    val market = Market()

    // Init last signals
    val s1 = Signal(LocalDateTime.now().minusHours(2), Direction.BUY)
    val s2 = Signal(LocalDateTime.now(), Direction.BUY)
    market.addSignal((s1))
    market.addSignal((s2))
    // Evaluate trend
    println(market.currentTrend())
    // Update config from trend
}
