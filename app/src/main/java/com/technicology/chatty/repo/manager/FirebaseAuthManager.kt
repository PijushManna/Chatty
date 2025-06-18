package com.technicology.chatty.repo.manager

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthManager {
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    fun signUp(email: String, password: String): Boolean {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).isSuccessful
    }

    fun login(email: String, password: String): Boolean {
        return firebaseAuth.signInWithEmailAndPassword(email, password).isSuccessful
    }

    fun logout(){
        firebaseAuth.signOut()
    }

    fun isUserLoggedIn() = firebaseAuth.currentUser != null
}