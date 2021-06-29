package br.com.paulistense.mystore.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.paulistense.mystore.domain.model.Grafico;

@Repository
public interface GraficoRepository extends JpaRepository<Grafico, Long> {

	Optional<Grafico> findByDescricao(String descricao);

}