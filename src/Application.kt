package farees.hussain

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    /**
     * Features for the project
     */
    install(DefaultHeaders)     // default headers -> responses from our server
    /*default header will attach useful extra information such as the current date to response headers*/
    install(CallLogging)        // callLogging -> log of all HTTP requests that come to server and responses
    install(Routing)            // Routing -> to define url endpoints
    /* ContentNegotiation -> for validation purpose using gson for responding to json content */
    install(ContentNegotiation){
        gson {
            setPrettyPrinting()
        }
    }

}

