package com.example.events

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.view.View.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.CompoundButton.*
import android.widget.SeekBar.*
import android.widget.TextView.*
import android.text.TextWatcher

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AlertDialog
import android.content.DialogInterface.*

class Quiz : Runnable {
  private var duration : Int? = null
  private var noFlags : Int? = null

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


  constructor(duration : Int, noFlags : Int) {
    this.duration = duration
    this.noFlags = noFlags
  }

  override fun run() {
    println("Quiz thread is running.")
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
  }
}