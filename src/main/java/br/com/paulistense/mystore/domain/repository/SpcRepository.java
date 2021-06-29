package br.com.paulistense.mystore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.paulistense.mystore.domain.model.Spc;
import br.com.paulistense.mystore.domain.model.SpcId;

@Repository
public interface SpcRepository extends JpaRepository<Spc, SpcId> {
	
}