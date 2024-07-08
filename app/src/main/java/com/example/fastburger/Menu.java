package com.example.fastburger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    TextView txtMENUser;
    ImageButton btMENContatos, btMENPedidos, btMENMeusDados;
    String _email = "";
    String _nome = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intencao = getIntent();
        Bundle paramertros = intencao.getExtras();
        _email = paramertros.getString("email");
        _nome = paramertros.getString("nome");


        txtMENUser = (TextView) findViewById(R.id.txtMENUser);
        btMENContatos = (ImageButton) findViewById(R.id.btMENContatos);
        btMENPedidos = (ImageButton) findViewById(R.id.btMENPedidos);
        btMENMeusDados = (ImageButton) findViewById(R.id.btMENMeusDados);

        txtMENUser.setText("Usuário: " + _nome);
        btMENContatos.setOnClickListener(this);
        btMENPedidos.setOnClickListener(this);
        btMENMeusDados.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btMENContatos){
            Intent tela = new Intent(this, MainActivity.class);
            startActivity(tela);
        }
        if (view.getId()==R.id.btMENPedidos){
            Intent tela = new Intent(this, Pedidos.class);

            Bundle parametros = new Bundle();
            parametros.putString("email",_email);
            tela.putExtras(parametros);

            startActivity(tela);
        }
        if (view.getId()==R.id.btMENMeusDados){
            Intent tela = new Intent(this, MeusDados.class);

            Bundle parametros = new Bundle();
            parametros.putString("email",_email);
            tela.putExtras(parametros);

            startActivity(tela);
        }

    }
}