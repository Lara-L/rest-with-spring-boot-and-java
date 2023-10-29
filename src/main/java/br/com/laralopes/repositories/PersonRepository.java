package br.com.laralopes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.laralopes.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{}
