package com.example.job_app.feature_feedback.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.job_app.feature_feedback.repository.ChatRepository
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {
    private val chatRepository = ChatRepository()

    var application by mutableStateOf("")

    fun onApplicationChange(string: String) {
        this.application = string
    }

    fun createChatCompletion(navController: NavController) {
        viewModelScope.launch {
            //val message = application
            val message = message
            chatRepository.createChatCompletion(message, object : ChatRepository.ChatResponseListener {
                override fun onSuccess(responseMessage: String) {
                    navController.navigate("response/$responseMessage")
                }
                override fun onError(errorMessage: String) {
                    navController.navigate("response/${"ChatGPT API error: $errorMessage"}")
                }
            })
        }
    }
}



// Besked til at teste API'en
const val message = "Jobansøgning til stilling som Marketingkoordinator" +
        "Kære [Virksomhedens navn]," +
        "Jeg skriver for at ansøge om stillingen som marketingkoordinator hos [Virksomhedens navn], som " +
        "opslået på [hvor du fandt jobopslaget]. Med en bachelorgrad i Marketing og tre års erfaring inden for " +
        "digital markedsføring, tror jeg på, at jeg kan bidrage positivt til jeres team." +
        "I min nuværende stilling hos [Nuværende arbejdsgiver] har jeg haft ansvaret for at udvikle og " +
        "implementere online kampagner, der har øget vores webtrafik med 25% over det sidste år. Jeg har " +
        "erfaring med SEO, Google Analytics, og sociale medieplatforme som Facebook, Instagram og " +
        "LinkedIn. Jeg er også vant til at arbejde med budgettering og analyse af kampagneperformance for " +
        "at optimere vores markedsføringsindsats." +
        "Jeg tiltrækkes af denne stilling, fordi jeg beundrer [Virksomhedens navn]’s innovative tilgang til " +
        "markedsføring og jeres engagement i bæredygtighed. Jeg ser frem til muligheden for at bidrage " +
        "med mine færdigheder og lære af et talentfuldt team." +
        "Jeg ser frem til muligheden for at uddybe, hvordan jeg kan bidrage til jeres team. Vedlagt finder I mit " +
        "CV for yderligere detaljer om min erfaring og kvalifikationer. Tak for jeres overvejelse." +
        "Med venlig hilsen," +
        "[Dit navn]" +
        "[Telefonnummer]" +
        "[E-mailadresse]"
