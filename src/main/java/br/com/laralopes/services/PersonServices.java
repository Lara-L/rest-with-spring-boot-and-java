package br.com.laralopes.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.laralopes.exceptions.ResourceNotFoundException;
import br.com.laralopes.model.Person;
import br.com.laralopes.repositories.PersonRepository;

@Service //serve para que o spring boot encare essa classe com um objeto que vai ser injetado em runtime em outras classes da aplicação
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll() {
		
		logger.info("buscando todas as pessoas!");
		
		
		return repository.findAll();
	}
	
	public Person findById(Long id) {
		logger.info("buscando uma pessoa!");

		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("sem servico para esse Id"));
	}
	
	public Person create(Person person) {
		logger.info("criando uma pessoa!");
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("atualizando dados de uma pessoa!");
		
		var entity  = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("sem servico para esse Id"));
		
		entity.setPrimeiroNome(person.getPrimeiroNome());
		entity.setUltimoNome(person.getUltimoNome());
		entity.setEndereco(person.getEndereco());
		entity.setGenero(person.getGenero());
		
		return repository.save(person);
	}
	
	public void delete(Long id) {
		logger.info("deletando dados de uma pessoa!");
		
		var entity  = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("sem servico para esse Id"));
		repository.delete(entity);
	}
}
