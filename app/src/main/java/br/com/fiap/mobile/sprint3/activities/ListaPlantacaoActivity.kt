package br.com.fiap.mobile.sprint3.activities

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import br.com.fiap.agroplus.models.Plantacao
import br.com.fiap.mobile.sprint3.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ListaPlantacaoActivity : Activity() {
    private val client = OkHttpClient()
    private val gson = Gson()
    private lateinit var sharedPreferences: SharedPreferences

    val URLBASE = "https://mobile-cboris-default-rtdb.firebaseio.com"

    override fun onCreate(bundle : Bundle?){
        super.onCreate(bundle)
        setContentView(R.layout.listagem_plantacoes)

        sharedPreferences = getSharedPreferences("PlantacaoPrefs", Context.MODE_PRIVATE)

        val getCnpj = findViewById<EditText>(R.id.getCnpj)
        val getRegiao = findViewById<EditText>(R.id.getRegiao)
        val getTipoCultivo = findViewById<EditText>(R.id.getCultivo)

        val savedCnpj = sharedPreferences.getString("CNPJ", "")
        val savedRegiao = sharedPreferences.getString("Regiao", "")
        val savedTipoCultivo = sharedPreferences.getString("Cultivo", "")

        getCnpj.setText(savedCnpj)
        getRegiao.setText(savedRegiao)
        getTipoCultivo.setText(savedTipoCultivo)

        val request = Request.Builder()
            .url("${URLBASE}/plantacoes.json")
            .get()
            .build()

        val response = object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("PLANTACOES", "Erro: ${e.message} ao acessar o Firebase")
            }

            override fun onResponse(call: Call, response: Response) {
                val textoJson = response.body?.string()

                val tipo = object :
                    TypeToken<HashMap<String?, Plantacao?>>(){}.type
                val jsonMap : HashMap<String?, Plantacao?> =
                    gson.fromJson(textoJson, tipo)

                for(plantacao in jsonMap.values){
                    if(plantacao != null){
                        runOnUiThread{
                            getCnpj.setText(plantacao.cnpj)
                            getRegiao.setText(plantacao.regiao)
                            getTipoCultivo.setText(plantacao.cultivo)

                            sharedPreferences.edit().apply {
                                putString("CNPJ", plantacao.cnpj)
                                putString("Regiao", plantacao.regiao)
                                putString("Cultivo", plantacao.cultivo)
                                apply()
                            }
                        }
                    }
                }
            }
        }
        client.newCall(request).enqueue(response)

        val buttonVoltar = findViewById<Button>(R.id.btnVoltar)
        buttonVoltar.setOnClickListener{
            finish()
        }
    }
}