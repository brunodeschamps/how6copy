package com.marcelobarbacovi.how6gerenciarong.alunos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.marcelobarbacovi.how6gerenciarong.R;
import com.marcelobarbacovi.how6gerenciarong.database.DataBaseHelper;


public class AlunosFragmentAdcionar extends Fragment {

   // variaveis para os campos utilizados dentro dos leiaut dos fragments
    TextInputEditText adcionarNomeAluno, adicionarResponsavelAluno, adicionarEnderecoAluno, adcionarObservacaoAluno;
    EditText adicionarTelefoneAluno;



    public AlunosFragmentAdcionar() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.alunos_fragment_adcionar, container, false);

        /* recupera os campos do fragment adicionar alunos
        para dentro das variaveis criadas logo acima
         */

        adcionarNomeAluno = v.findViewById(R.id.Adicionar_Nome_aluno);
        adicionarResponsavelAluno= v.findViewById(R.id.Adcionar_Responsavel_Aluno); ;
        adicionarEnderecoAluno = v.findViewById(R.id.Adcionar_Endereco_Aluno);
        adcionarObservacaoAluno = v.findViewById(R.id.Adicionar_observacao_Aluno);
        adicionarTelefoneAluno = v.findViewById(R.id.Adcionar_telefone_Aluno);

        // metodo  para chamar adicionar alunos, quando clicado no button

        Button btnAdcionarAluno = v.findViewById(R.id.button_salvar_aluno);
        btnAdcionarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();


            }
        });


        return  v;
    }

    /* metodo adcionar alunos, primeiro valida os campos fazios, obrigando o preenchimento


     */
    private void adicionar(){
        if (adcionarNomeAluno.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do Aluno", Toast.LENGTH_LONG).show();
        } else if (adicionarResponsavelAluno.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o Responsavel do Aluno", Toast.LENGTH_LONG).show();
        } else if (adicionarTelefoneAluno.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o telefone do Aluno", Toast.LENGTH_LONG).show();
        } else if (adicionarEnderecoAluno.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o endereço do Aluno", Toast.LENGTH_LONG).show();
// aqui armazena os dados dentro da variavel da classe aluno
        } else {
            DataBaseHelper databaseHelper = new DataBaseHelper(getActivity());
            Aluno a = new Aluno();
            a.setNome(adcionarNomeAluno.getText().toString());
            a.setResponsavel(adicionarResponsavelAluno.getText().toString());
            a.setTelefone(adicionarTelefoneAluno.getText().toString());
            a.setEndereco(adicionarEnderecoAluno.getText().toString());
            a.setObservao(adcionarObservacaoAluno.getText().toString());

           // chama o metodo de inserção no banco de dados SQLLite com a classe DataBaseHelper
            databaseHelper.createAluno(a);
            Toast.makeText(getActivity(), "Aluno salvo", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_alunos, new AlunosFragmentListar()).commit();
        }
    }
}