package farees.hussain.routes

import farees.hussain.data.checkPasswordForEmail
import farees.hussain.data.requests.AccountRequest
import farees.hussain.data.response.SimpleResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.loginRoute(){
    route("/login"){
        post {
            val request = try {
                call.receive<AccountRequest>()
            }catch (e:ContentTransformationException){
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            val isPasswordCorrect = checkPasswordForEmail(request.email,request.password)
            if (isPasswordCorrect) {
                call.respond(OK,SimpleResponse(true,"You are logged in successfully"))
            }else{
                call.respond(OK,SimpleResponse(true,"Email or Password is in Correct"))
            }
        }
    }
}