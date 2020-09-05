package farees.hussain.routes

import farees.hussain.data.collections.Note
import farees.hussain.data.getNotesForUser
import farees.hussain.data.saveNote
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Conflict
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.noteRoutes(){
    route("/getNotes"){
        authenticate {
            get {
                val email = call.principal<UserIdPrincipal>()!!.name
                val notes = getNotesForUser(email)
                call.respond(OK,notes)
            }
        }
    }
    route("/addNote"){
        authenticate {
            post {
                val note = try {
                    call.receive<Note>()
                }catch (e:ContentTransformationException){
                    call.respond(BadRequest)
                    return@post
                }
                if(saveNote(note)){
                    call.respond(OK)
                }else {
                    call.respond(Conflict)
                }
            }
        }
    }
}