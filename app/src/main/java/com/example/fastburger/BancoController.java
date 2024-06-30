package com.example.fastburger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
   private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

// inclusão de dados
    //inclusão de dados na tabela de contatos
    public String insereDados(String txtNome, String txtEmail) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", txtNome);
        valores.put("email", txtEmail);

        resultado = db.insert("contatos", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    //inclusão de dados na tabela de Usuarios
    public String insereDadosUsuario(String txtNome, String txtCpf, String txtEmail, String txtSenha) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", txtNome);
        valores.put("cpf", txtCpf);
        valores.put("email", txtEmail);
        valores.put("senha", txtSenha);

        resultado = db.insert("usuarios", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir os dados do usuário!";
        else
            return "Dados Cadastrados com sucesso!";
    }

    //inclusão de dados na tabela de Pedidos
    public String insereDadosPedido(String _email, int _qtdHamburger, int _qtdBatata,
                     int _qtdRefrigerante, float _valorHamburger, float _valorBatata,
                     float _valorRefrigerante, float _totalPedido){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();  // abrir banco de dados para gravação

        valores = new ContentValues();
        // campo da tabela, variável com conteúdo para armazenar
        valores.put("email", _email);
        valores.put("qtdHamburger", _qtdHamburger);
        valores.put("qtdBatata", _qtdBatata);
        valores.put("qtdRefrigerante", _qtdRefrigerante);
        valores.put("valorHamburger", _valorHamburger);
        valores.put("valorBatata", _valorBatata);
        valores.put("valorRefrigerante", _valorRefrigerante);
        valores.put("totalGeral", _totalPedido);

        resultado = db.insert("pedidos", null, valores); //gravar dados
        db.close();   // fechar banco de dados

        if (resultado == -1)
            return "Erro ao gravar o pedido!";
        else
            return "Pedido Gravado com Sucesso!";
    }

// Consulta da Dados
    //Consulta dados de contatos filtrando pelo idContato (codigo)
    public Cursor carregaDadosPeloCodigo(int id) {
        Cursor cursor;
        String[] campos = { "codigo", "nome", "email" };
        String where = "codigo=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query("contatos", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    // consulta dados para login / senha
    public Cursor carregaDadosPeloEmailSenha(String email,String senha){
        Cursor cursor;
        //SELECT idUser, nome, cpf, email, senha FROM usuarios WHERE email = 'digitado' and senha = 'digitada'
        String[] campos = { "idUser", "nome", "cpf", "email", "senha" };
        String where = "email = '" + email + "' and senha = '" + senha + "' ";
        db = banco.getReadableDatabase();
        cursor = db.query("usuarios", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    // consulta meus dados
    public Cursor consultaUsuario(String email) {
        Cursor cursor;
        //SELECT idUser, nome, cpf, email, senha FROM usuarios WHERE email = 'digitado'
        String[] campos = { "idUser", "nome", "cpf", "email", "senha" };
        String where = "email = '" + email + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("usuarios", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    // alteracao de dados
    public String alteraDados(int id, String nome, String email){

        String msg = "Dados alterados com sucesso!!!" ;

        db = banco.getReadableDatabase();

        ContentValues valores = new ContentValues() ;
        valores.put("nome" , nome ) ;
        valores.put("email", email) ;

        String condicao = "codigo =" + id;

        int linha ;
        linha = db.update("contatos", valores, condicao, null) ;

        if (linha < 1){
            msg = "Erro ao alterar os dados" ;
        }

        db.close();
        return msg;
    }

    public String alteraDadosUsuario(int idUser, String _nome, String _cpf,
                                     String _email, String _senha) {
        String msg = "Dados alterados com sucesso!!!" ;

        db = banco.getReadableDatabase();

        ContentValues valores = new ContentValues() ;
        valores.put("nome" , _nome ) ;
        valores.put("cpf", _cpf) ;
        valores.put("email", _email) ;
        valores.put("senha", _senha) ;

        String condicao = "idUser =" + idUser;

        int linha ;
        linha = db.update("usuarios", valores, condicao, null) ;

        if (linha < 1){
            msg = "Erro ao alterar os dados" ;
        }

        db.close();
        return msg;
    }

// exclusão de dados
    public String excluirDados(int id){
        String msg = "Registro Excluído" ;

        db = banco.getReadableDatabase();

        String condicao = "codigo = " + id ;

        int linhas ;
        linhas = db.delete("contatos", condicao, null) ;

        if ( linhas < 1) {
            msg = "Erro ao Excluir" ;
        }

        db.close();
        return msg;
    }



}

