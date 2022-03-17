package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editnome;
    EditText editemail;
    EditText editcurso;
    Button btnenviar;
    Button btnmostrar;
    pessoasBD db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Cadastro");
        editnome = (EditText) findViewById(R.id.EditNome);
        editemail = (EditText) findViewById(R.id.EditEmail);
        editcurso = (EditText) findViewById(R.id.EditCurso);
        btnenviar = (Button) findViewById(R.id.btnEnviar);
        btnmostrar = (Button) findViewById(R.id.btnBuscaTodos);
        db = new pessoasBD(this);
        btnenviar.setOnClickListener(this);
        btnmostrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==btnenviar.getId()){
            pessoa p = new pessoa();
            p.setNome(editnome.getText().toString().trim());
            p.setCurso(editcurso.getText().toString().trim());
            p.setEmail(editemail.getText().toString().trim());
            db.save(p);
        }
        if(view.getId()==btnmostrar.getId()){
            List<pessoa> pessoas = db.findAll();
            for(int i=0; i<pessoas.size();i++){
                System.out.print(pessoas.get(i).getId()+"");
                System.out.print(pessoas.get(i).getNome()+"");
                System.out.print(pessoas.get(i).getEmail()+"");
                System.out.println(pessoas.get(i).getCurso()+"");
            }
            Intent intent = new Intent(this,cadastrados.class);
            intent.putExtra("objList",(Serializable) pessoas);
            startActivity(intent);
        }
    }
}