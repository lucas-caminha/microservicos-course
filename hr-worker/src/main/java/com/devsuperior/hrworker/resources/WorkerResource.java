package com.devsuperior.hrworker.resources;

import java.util.List;

import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(WorkerResource.class);
	
	@Value("${test.config}")
	private String testConfig;
	
	@Autowired
	private Environment env;
	@Autowired
	private WorkerRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {
		List<Worker> workers = repository.findAll();
		return ResponseEntity.ok(workers);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable("id") Long id) {		
		logger.info("Port: " + env.getProperty("local.server.port"));
		Worker worker = repository.findById(id).get();
		return ResponseEntity.ok(worker);
	}
	
	@GetMapping("/configs")
	public ResponseEntity<Void> getConfigs() {
		logger.info("Config: " + testConfig);
		return ResponseEntity.noContent().build();
	}

}
