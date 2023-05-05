package br.com.mystore.domain.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mystore.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

	@Query("select max(dataAtualizacao) from formas_pagamentos")
	OffsetDateTime getDataUltimaAtualizacao();

	@Query("select dataAtualizacao from formas_pagamentos where id = :formaPagamentoId")
	OffsetDateTime getDataAtualizacaoById(Long formaPagamentoId);
}
