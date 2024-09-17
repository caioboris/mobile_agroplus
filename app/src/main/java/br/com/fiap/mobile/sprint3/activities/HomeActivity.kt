package br.com.fiap.mobile.sprint3.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import br.com.fiap.mobile.sprint3.R

class HomeActivity : Activity() {

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        setContentView(R.layout.home)

        val buttonSobre = findViewById<Button>(R.id.btnSobre)
        val buttonCadastroPlantacao = findViewById<Button>(R.id.btnCadastroPlantacao)
        val buttonMinhaPlantacao = findViewById<Button>(R.id.btnMinhaPlantacao)


        buttonSobre.setOnClickListener{
            val intent = Intent(this, SobreActivity::class.java)
            startActivity(intent)
        }

        buttonCadastroPlantacao.setOnClickListener{
            val intent = Intent(this, CadastroPlantacaoActivity::class.java)
            startActivity(intent)
        }

        buttonMinhaPlantacao.setOnClickListener{
            val intent = Intent(this, ListaPlantacaoActivity::class.java)
            startActivity(intent)
        }
    }
}