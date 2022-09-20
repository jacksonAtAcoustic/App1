package com.example.events

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class QuizHandler : Runnable {
  private var filename : String? = null
  private var text : String? = null
  private var activity : MainActivity? = null

  constructor(activity : MainActivity) {
    this.activity = activity

    // starters
    this.filename = "PLACEHOLDER"
    this.text = "What flag is this?"
  }

  constructor(filename : String, text : String) {
    this.filename = filename
    this.text = text
  }

  override fun run() {
    println("QuizHandler is running.")

    var imgV = activity?.findViewById<ImageView>(R.id.imageView)
    var txtV = activity?.findViewById<TextView>(R.id.text)

    var imgLocation = "@drawable/" + filename

    var imageResource = activity?.resources?.getIdentifier(imgLocation, "drawable", activity?.packageName)
    imgV?.setImageResource(imageResource!!)
  }

  public fun updateText() {
    var txtV = activity?.findViewById<TextView>(R.id.text)
    txtV?.setText("What flag is this?")
  }
}

class Quiz : Thread {
  private var duration : Int? = null
  private var noFlags : Int? = null
  private var count : Int = 0
  private var quizH : QuizHandler? = null

  // all flags
  private var files = arrayOf("afghanistan.bmp", "albania.bmp", "algeria.bmp", "american_samoa.bmp", "andorra.bmp", "angola.bmp", "anguilla.bmp",
    "antigua_and_barbuda.bmp", "argentina.bmp","armenia.bmp", "aruba.bmp","australia.bmp", "austria.bmp","azerbaijan.bmp",
    "bahamas.bmp", "bahrain.bmp", "bangladesh.bmp", "barbados.bmp", "belarus.bmp", "belgium.bmp", "belize.bmp", "benin.bmp",
    "bermuda.bmp", "bhutan.bmp", "bolivia.bmp", "bosnia.bmp", "botswana.bmp", "brazil.bmp", "british_virgin_islands.bmp",
    "brunei.bmp", "bulgaria.bmp", "burkina_faso.bmp", "burundi.bmp", "cambodia.bmp", "cameroon.bmp", "canada.bmp", "cape_verde.bmp",
    "cayman_islands.bmp", "central_african_republic.bmp", "chad.bmp", "chile.bmp", "china.bmp","christmas_island.bmp",
    "colombia.bmp","comoros.bmp","cook_islands.bmp","costa_rica.bmp","croatia.bmp","cuba.bmp","cyprus.bmp","cyprus_northern.bmp",
    "czech_republic.bmp","cte_divoire.bmp","democratic_republic_of_the_congo.bmp","denmark.bmp","djibouti.bmp","dominica.bmp",
    "dominican_republic.bmp","ecuador.bmp","egypt.bmp","el_salvador.bmp","equatorial_guinea.bmp","eritrea.bmp","estonia.bmp",
    "ethiopia.bmp","falkland_islands.bmp","faroe_islands.bmp","fiji.bmp","finland.bmp","france.bmp","french_polynesia.bmp",
    "gabon.bmp","gambia.bmp","georgia.bmp","germany.bmp","ghana.bmp","gibraltar.bmp","greece.bmp","greenland.bmp","grenada.bmp",
    "guam.bmp","guatemala.bmp","guinea.bmp","guinea_bissau.bmp","guyana.bmp","haiti.bmp","honduras.bmp","hong_kong.bmp","hungary.bmp",
    "iceland.bmp", "india.bmp","indonesia.bmp","iran.bmp","iraq.bmp", "ireland.bmp","israel.bmp","italy.bmp","jamaica.bmp","japan.bmp",
    "jordan.bmp", "kazakhstan.bmp","kenya.bmp","kiribati.bmp","kuwait.bmp","kyrgyzstan.bmp","laos.bmp","latvia.bmp","lebanon.bmp",
    "lesotho.bmp","liberia.bmp","libya.bmp","liechtenstein.bmp","lithuania.bmp","luxembourg.bmp","macao.bmp","macedonia.bmp",
    "madagascar.bmp","malawi.bmp","malaysia.bmp","maldives.bmp","mali.bmp","malta.bmp","marshall_islands.bmp","martinique.bmp",
    "mauritania.bmp","mauritius.bmp","mexico.bmp","micronesia.bmp","moldova.bmp","monaco.bmp","mongolia.bmp","montserrat.bmp",
    "morocco.bmp","mozambique.bmp","myanmar.bmp","namibia.bmp","nauru.bmp","nepal.bmp","netherlands.bmp","netherlands_antilles.bmp",
    "new_zealand.bmp", "nicaragua.bmp","niger.bmp","nigeria.bmp","niue.bmp","norfolk_island.bmp","north_korea.bmp","norway.bmp",
    "oman.bmp","pakistan.bmp","palau.bmp","panama.bmp","papua_new_guinea.bmp","paraguay.bmp","peru.bmp","philippines.bmp",
    "pitcairn_islands.bmp","poland.bmp","portugal.bmp","puerto_rico.bmp","qatar.bmp","republic_of_the_congo.bmp","romania.bmp",
    "russian_federation.bmp","rwanda.bmp","saint_kitts_and_nevis.bmp","saint_lucia.bmp","saint_pierre.bmp","saint_vicent_and_the_grenadines.bmp",
    "samoa.bmp","san_marino.bmp","sao_tom_and_prncipe.bmp","saudi_arabia.bmp","senegal.bmp","serbia_and_montenegro.bmp",
    "seychelles.bmp", "sierra_leone.bmp", "singapore.bmp","slovakia.bmp","slovenia.bmp","soloman_islands.bmp","somalia.bmp",
    "south_Africa.bmp","south_georgia.bmp","south_korea.bmp","soviet_union.bmp","spain.bmp","sri_lanka.bmp","sudan.bmp","suriname.bmp",
    "swaziland.bmp","sweden.bmp","switzerland.bmp","syria.bmp", "taiwan.bmp","tajikistan.bmp","tanzania.bmp","thailand.bmp",
    "tibet.bmp", "timor_Leste.bmp", "togo.bmp","tonga.bmp","trinidad_and_tobago.bmp","tunisia.bmp", "turkey.bmp","turkmenistan.bmp",
    "turks_and_caicos_islands.bmp", "tuvalu.bmp","uae.bmp","uganda.bmp","ukraine.bmp","united_kingdom.bmp","united_states_of_america.bmp",
    "uruguay.bmp","us_virgin_islands.bmp","uzbekistan.bmp","vanuatu.bmp","vatican_city.bmp","venezuela.bmp","vietnam.bmp",
    "wallis_and_futuna.bmp","yemen.bmp","zambia.bmp","zimbabwe.bmp")
  // country names
  private var countries = arrayOf("Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
    "Antigua and Barbuda", "Argentina","Armenia", "Aruba","Australia", "Austria","Azerbaijan",
    "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin",
    "Bermuda", "Bhutan", "Bolivia", "Bosnia", "Botswana", "Brazil", "British Virgin Islands",
    "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
    "Cayman Islands", "Central African Republic", "Chad", "Chile", "China","Christmas Island",
    "Colombia","Comoros","Cook Islands","Costa Rica","Croatia","Cuba","Cyprus","Cyprus Northern",
    "Czech Republic","Cte dIvoire","Democratic Republic of the Congo","Denmark","Djibouti","Dominica",
    "Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia",
    "Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia",
    "Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada",
    "Guam","Guatemala","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary",
    "Iceland", "India","Indonesia","Iran","Iraq", "Ireland","Israel","Italy","Jamaica","Japan",
    "Jordan", "Kazakhstan","Kenya","Kiribati","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon",
    "Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macao","Macedonia",
    "Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Martinique",
    "Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montserrat",
    "Morocco","Mozambique","Myanmar","Namibia","Nauru","Nepal","Netherlands","Netherlands Antilles",
    "New Zealand", "Nicaragua","Niger","Nigeria","Niue","Norfolk Island","North Korea","Norway",
    "Oman","Pakistan","Palau","Panama","Papua New Guinea","Paraguay","Peru","Philippines",
    "Pitcairn Islands","Poland","Portugal","Puerto Rico","Qatar","Republic of the Congo","Romania",
    "Russian Federation","Rwanda","Saint Kitts and Nevis","Saint Lucia","Saint Pierre","Saint Vicent and the Grenadines",
    "Samoa","San Marino","Sao Tom and Prncipe","Saudi Arabia","Senegal","Serbia and Montenegro",
    "Seychelles", "Sierra Leone", "Singapore","Slovakia","Slovenia","Soloman Islands","Somalia",
    "South Africa","South Georgia","South Korea","Soviet Union","Spain","Sri Lanka","Sudan","Suriname",
    "Swaziland","Sweden","Switzerland","Syria", "Taiwan","Tajikistan","Tanzania","Thailand",
    "Tibet", "Timor-Leste", "Togo","Tonga","Trinidad and Tobago","Tunisia", "Turkey","Turkmenistan",
    "Turks and Caicos Islands", "Tuvalu","UAE","Uganda","Ukraine","United Kingdom","United States of America",
    "Uruguay","US Virgin Islands","Uzbekistan","Vanuatu","Vatican City","Venezuela","Vietnam",
    "Wallis and Futuna","Yemen","Zambia","Zimbabwe")

  constructor(duration: Int, noFlags: Int, quizH: QuizHandler) {
    this.duration = duration
    this.noFlags = noFlags
    this.quizH = quizH
  }

  override fun run() {
    println("Quiz thread is running.")

    while (count != noFlags) {
      var index = Random.nextInt(0, countries.size)
      var ctrl = QuizHandler(files[index], countries[index])
      // "What flag is this?
      ctrl.updateText()
      // Sleep mess
      duration?.times(1000)?.let { Thread.sleep(it.toLong()) }
      // execute handler
      quizH?.run()
      // add to count
      count++
    }
  }
}

class MainActivity : AppCompatActivity()
{
  companion object
  {
    private var instance : MainActivity? = null

    fun getInstance() : MainActivity
    {
      // makes sure this instance is not null (NPE)
      return instance!!
    }
  }

  override fun onCreate(savedInstanceState: Bundle?)
  {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    instance = this

    // instance of Main activity
    var activity = MainActivity.getInstance()

    var handler = QuizHandler(activity)
    activity.runOnUiThread(handler)

    var quiz : Quiz = Quiz(5, 10, handler)

    quiz.start()
  }
}