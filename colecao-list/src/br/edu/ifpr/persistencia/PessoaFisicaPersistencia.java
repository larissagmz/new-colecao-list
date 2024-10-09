package br.edu.ifpr.persistencia;
import br.edu.ifpr.modelo.PessoaFísica;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaPersistencia {
    private List<PessoaFísica> pessoaFísicas;

    public PessoaFisicaPersistencia(ArrayList<PessoaFísica> listaPessoaFisica) {
        this.pessoaFísicas = new ArrayList<>(listaPessoaFisica);
    }

    public int insertPessoaFisica(PessoaFísica pessoa) {
        for (PessoaFísica x : pessoaFísicas) {
            if (x.getCpf().equals(pessoa.getCpf())) {
                return 0;
            }
        }
        pessoaFísicas.add(pessoa);
        return 1;
    }

    public int updatePessoaFisica(PessoaFísica pessoa) {
        boolean removed = pessoaFísicas.removeIf(u -> u.getCpf().equals(pessoa.getCpf()));
        if (removed) {
            pessoaFísicas.add(pessoa);
            return 1;
        }
        return 0;
    }

    public PessoaFísica selectPessoaFísica(String cpf) {
        for (PessoaFísica x : pessoaFísicas) {
            if (x.getCpf().equals(cpf)) {
                return x;
            }
        }
        return null;
    }

    public int deletePessoaFísica(String cpf) {
        boolean removed = pessoaFísicas.removeIf(u -> u.getCpf().equals(cpf));
        return removed ? 1 : 0;
    }

    public int delete() {
        int total = pessoaFísicas.size();
        pessoaFísicas.clear();
        return total;
    }

    public ArrayList<PessoaFísica> selectTodasPessoaFísica() {
        return new ArrayList<>(pessoaFísicas);
    }
}
