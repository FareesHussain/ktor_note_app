package farees.hussain.data

import farees.hussain.data.collections.Note
import farees.hussain.data.collections.User
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

private val client = KMongo.createClient().coroutine
private val database = client.getDatabase("NotesDatabase")
private val users = database.getCollection<User>()
private val notes = database.getCollection<Note>()

suspend fun registerUser(user: User) : Boolean {
    return users.insertOne(user).wasAcknowledged()
}
/*to avoid multiple users with same email addresss*/
suspend fun checkIfUserExists(email:String) = users.findOne(User::email eq(email))!=null

suspend fun addNotes(note: Note) = notes.insertOne(note).wasAcknowledged()