package br.com.fiap.mobile.sprint3.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.fiap.mobile.sprint3.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class CadastroPlantacaoActivity : Activity() {

    private val URLBASE = "https://mobile-cboris-default-rtdb.firebaseio.com"

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.cadastro_plantacao)

        val client = OkHttpClient()

        val buttonVoltar = findViewById<Button>(R.id.btnVoltar)
        val buttonCadastrar = findViewById<Button>(R.id.btnCadastrar)

        val edtCnpj = findViewById<EditText>(R.id.edtCnpj)
        val edtRegiao = findViewById<EditText>(R.id.edtRegiao)
        val edtCultivo = findViewById<EditText>(R.id.edtCultivo)

        buttonCadastrar.setOnClickListener{
            val plantacaoJson = """
                {
                    "cnpj":"${edtCnpj.text}",
                    "regiao":"${edtRegiao.text}",
                    "cultivo":"${edtCultivo.text}"
                }
            """.trimIndent()

            val request = Request.Builder()
                .url("${URLBASE}/plantacoes.json")
                .post(
                    plantacaoJson.toRequestBody(
                        "application/json".toMediaType()
                    )
                )
                .build()

            val response = object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(
                            this@CadastroPlantacaoActivity,
                            "Erro ao cadastrar plantacao",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    runOnUiThread {
                        Toast.makeText(
                            this@CadastroPlantacaoActivity,
                            "Contato gravado com sucesso",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            client.newCall(request).enqueue(response)
        }

        buttonVoltar.setOnClickListener{
            finish()
        }
    }


}