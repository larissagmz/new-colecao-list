package br.edu.ifpr.persistencia;
import br.edu.ifpr.modelo.PessoaJuridica;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PessoaJuridicaPersistencia {
    private List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();

    public int insert(PessoaJuridica pessoa) {
        if (pessoa == null || pessoa.getCnpj() == null) {
            throw new IllegalArgumentException("Pessoa ou CNPJ inválido.");
        }
        for (PessoaJuridica x : pessoasJuridicas) {
            if (x.getCnpj().equals(pessoa.getCnpj())) {
                return 0;
            }
        }
        pessoasJuridicas.add(pessoa);
        return 1;
    }

    public int update(PessoaJuridica pessoa) {
        if (pessoa == null || pessoa.getCnpj() == null) {
            throw new IllegalArgumentException("Pessoa ou CNPJ inválido.");
        }
        boolean removed = pessoasJuridicas.removeIf(u -> u.getCnpj().equals(pessoa.getCnpj()));
        if (removed) {
            pessoasJuridicas.add(pessoa);
            return 1;
        }
        return 0;
    }

    public PessoaJuridica selectByCnpj(String cnpj) {
        if (cnpj == null) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo.");
        }

        return pessoasJuridicas.stream().filter(element -> element.getCnpj().equals((cnpj)))
                .findFirst().orElseThrow(()-> new NoSuchElementException("Pessoa juridica com cnpj: " + cnpj + " nao encontrado"));

//        for (PessoaJuridica x : pessoasJuridicas) {
//            if (x.getCnpj().equals(cnpj)) {
//                return x;
//            }
//        }
//        return null;
    }

    public int delete(String cnpj) {
        if (cnpj == null) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo.");
        }
        boolean removed = pessoasJuridicas.removeIf(u -> u.getCnpj().equals(cnpj));
        return removed ? 1 : 0;
    }

    public int delete() {
        int total = pessoasJuridicas.size();
        pessoasJuridicas.clear();
        return total;
    }

    public List<PessoaJuridica> select() {
        return new ArrayList<>(pessoasJuridicas);
    }
}
