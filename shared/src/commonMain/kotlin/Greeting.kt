import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    fun countToTen(): Flow<String> = flow {
        (1..10).forEach {
            emit("Counter value= $it")
            delay(1000)
        }
    }
}