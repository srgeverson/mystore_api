package br.com.mystore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mystore.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}
