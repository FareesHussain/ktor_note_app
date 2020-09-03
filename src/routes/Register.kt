package farees.hussain.routes

import farees.hussain.data.checkIfUserExists
import farees.hussain.data.collections.User
import farees.hussain.data.registerUser
import farees.hussain.data.requests.AccountRequest
import farees.hussain.data.response.SimpleResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.registerRoute(){
    route("/register"){
        post {
            val request = try {
                call.receive<AccountRequest>()
            }catch (e: ContentTransformationException){
                call.respond(BadRequest)
                return@post
            }
            val userExists = checkIfUserExists(request.email)
            if(!userExists){
                if(registerUser(User(request.email,request.password))){
                    call.respond(OK,SimpleResponse(true,"Successfully created a account"))
                } else{
                    call.respond(OK,SimpleResponse(false,"Unable to created a account"))
                }
            }else{
                call.respond(OK,SimpleResponse(false,"Email already exists"))
            }
        }
    }
}