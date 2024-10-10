package br.edu.ifpr.persistencia;

import br.edu.ifpr.modelo.PessoaFisica;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PessoaFisicaPersistencia {
    private List<PessoaFisica> pessoasFisicas = new ArrayList<>();

    public int insert(PessoaFisica pessoa) {
        if (pessoa == null || pessoa.getCpf() == null) {
            throw new IllegalArgumentException("Pessoa ou CNPJ inválido.");
        }
        for (PessoaFisica x : pessoasFisicas) {
            if (x.getCpf().equals(pessoa.getCpf())) {
                return 0;
            }
        }
        pessoasFisicas.add(pessoa);
        return 1;
    }

    public int update(PessoaFisica pessoa) {
        if (pessoa == null || pessoa.getCpf() == null) {
            throw new IllegalArgumentException("Pessoa ou CNPJ inválido.");
        }
        boolean removed = pessoasFisicas.removeIf(u -> u.getCpf().equals(pessoa.getCpf()));
        if (removed) {
            pessoasFisicas.add(pessoa);
            return 1;
        }
        return 0;
    }

    public PessoaFisica selectByCnpj(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }

        return pessoasFisicas.stream().filter(element -> element.getCpf().equals((cpf)))
                .findFirst().orElseThrow(()-> new NoSuchElementException("Pessoa juridica com cnpj: " + cpf + " nao encontrado"));

//        for (PessoaJuridica x : pessoasJuridicas) {
//            if (x.getCnpj().equals(cnpj)) {
//                return x;
//            }
//        }
//        return null;
    }

    public int delete(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }
        boolean removed = pessoasFisicas.removeIf(u -> u.getCpf().equals(cpf));
        return removed ? 1 : 0;
    }

    public int delete() {
        int total = pessoasFisicas.size();
        pessoasFisicas.clear();
        return total;
    }

    public List<PessoaFisica> select() {
        return new ArrayList<>(pessoasFisicas);
    }
}
