package br.edu.ifpr.persistencia;

import br.edu.ifpr.modelo.PessoaJurídica;

import java.util.ArrayList;
import java.util.List;

public class PessoaJurídicaPersistencia {
    private List<PessoaJurídica> pessoaJurídica;


    public PessoaJurídicaPersistencia() {
        this.pessoaJurídica = new ArrayList<>();
    }

    public int insert(PessoaJurídica pessoa) {
        for (PessoaJurídica x : pessoaJurídica) {
            if (x.getCnpj().equals(pessoa.getCnpj())) {
                return 0;
            }
        }
        pessoaJurídica.add(pessoa);
        return 1;
    }

    public int update(PessoaJurídica pessoa) {

        boolean removed = pessoaJurídica.removeIf(u -> u.getCnpj().equals(pessoa.getCnpj()));
        if (removed) {
            pessoaJurídica.add(pessoa);
            return 1;
        }
        return 0;
    }

    public PessoaJurídica select(String cnpj) {
        for (PessoaJurídica x : pessoaJurídica) {
            if (x.getCnpj().equals(cnpj)) {
                return x;
            }
        }
        return null;
    }

    public int delete(String cnpj) {
        boolean removed = pessoaJurídica.removeIf(u -> u.getCnpj().equals(cnpj));
        return removed ? 1 : 0;
    }

    public int delete() {
        int total = pessoaJurídica.size();
        pessoaJurídica.clear();
        return total;
    }

    public ArrayList<PessoaJurídica> select() {
        return new ArrayList<>(pessoaJurídica);
    }
}
