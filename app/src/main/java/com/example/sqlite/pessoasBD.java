package com.example.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class pessoasBD extends SQLiteOpenHelper {
    private static final String TAG ="sql";
    private static final String NOME_BANCO = "pessoa.sqlite";
    private static final int VERSAO = 1;
    public pessoasBD(Context context) {
        super(context,NOME_BANCO, null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG,"criando a tabela pessoa");
        sqLiteDatabase.execSQL("create table if not exists pessoa (_id integer primary key autoincrement, nome text, email text, curso text);");
        Log.d(TAG,"Tabela Criada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long save(pessoa pessoa){
    long id = pessoa.getId();
    SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome",pessoa.getNome());
            values.put("email",pessoa.getEmail());
            values.put("curso",pessoa.getCurso());
            if(id!=0){
                String _id = String.valueOf(pessoa.getId());
                String[] whereArgs = new String[]{_id};
                int count = db.update("pessoa",values,"_id=?",whereArgs);
                return count;
            }
            else{
                id= db.insert("pessoa","",values);
                return id;
            }
        } finally {
            db.close();
        }
    }
    public int delete(pessoa pessoa){
        SQLiteDatabase db = getWritableDatabase();
        try {
            int count = db.delete("pessoa","_id=?",new String[]{String.valueOf(pessoa.getId())});
            Log.d(TAG,"Deletou ["+ count + "] registro.");
            return count;
        } finally {
            db.close();
        }
    }
    public int deletebycurso(String curso){
        SQLiteDatabase db = getWritableDatabase();
        try {
            int count = db.delete("pessoa","curso=?",new String[]{curso});
            Log.d(TAG,"Deletou ["+ count +"] registros.");
            return count;
        } finally {
            db.close();
        }
    }
    public List<pessoa> findAll(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.query("pessoa",null,null,null,null,null,null,null);
        return toList(c);
        }finally {
        db.close();
        }
    }

    @SuppressLint("Range")
    private List<pessoa> toList(Cursor c) {
        List<pessoa> pessoas = new ArrayList<pessoa>();
        if(c.moveToFirst()){
            do{
             pessoa p = new pessoa();
             pessoas.add(p);
             p.setId(c.getInt(c.getColumnIndex("_id")));
             p.setNome(c.getString(c.getColumnIndex("nome")));
             p.setCurso(c.getString(c.getColumnIndex("curso")));
            }while(c.moveToNext());
        }
        return pessoas;
    }
}
