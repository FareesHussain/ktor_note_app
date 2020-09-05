package farees.hussain

import farees.hussain.data.collections.User
import farees.hussain.data.registerUser
import farees.hussain.routes.loginRoute
import farees.hussain.routes.registerRoute
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    /* Routing -> to define url endpoints */
    install(Routing){
        registerRoute()
        loginRoute()
    }
    /* ContentNegotiation -> for validation purpose using gson for responding to json content */
    install(ContentNegotiation){
        gson {
            setPrettyPrinting()
        }
    }
}

