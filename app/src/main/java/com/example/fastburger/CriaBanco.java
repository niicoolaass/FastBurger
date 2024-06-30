package com.example.fastburger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco_exemplo.db";
    private static final int VERSAO = 3;
    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE contatos ("
                + "codigo integer primary key autoincrement,"
                + "nome text,"
                + "email text)";
        db.execSQL(sql);
        sql = "CREATE TABLE usuarios ("
                + "idUser integer primary key autoincrement,"
                + "nome text,"
                + "cpf text,"
                + "email text,"
                + "senha text)";
        db.execSQL(sql);
        sql = "CREATE TABLE pedidos ("
                + "idPedido integer primary key autoincrement,"
                + "email text,"
                + "qtdHamburger int,"
                + "qtdBatata int,"
                + "qtdRefrigerante int,"
                + "valorHamburger float,"
                + "valorBatata float,"
                + "valorRefrigerante float,"
                + "totalGeral float)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contatos");
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS pedidos");
        onCreate(db);
    }
}