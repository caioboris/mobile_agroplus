package br.com.fiap.mobile.sprint3.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import br.com.fiap.mobile.sprint3.R

class SobreActivity : Activity(){

    override fun onCreate(bundle : Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.sobre)

        val buttonVoltar = findViewById<Button>(R.id.btnVoltar3)

        buttonVoltar.setOnClickListener{
            finish()
        }

    }
}