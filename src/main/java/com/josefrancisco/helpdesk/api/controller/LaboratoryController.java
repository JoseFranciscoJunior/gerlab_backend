package com.josefrancisco.helpdesk.api.controller;

import com.josefrancisco.helpdesk.api.response.Response;
import com.josefrancisco.helpdesk.api.security.entity.Laboratory;
import com.josefrancisco.helpdesk.api.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/laboratory")
@CrossOrigin(origins = "*")
public class LaboratoryController {
	
	@Autowired
	private LaboratoryService laboratoryService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Laboratory>> create(HttpServletRequest request, @RequestBody Laboratory laboratory,
			BindingResult result) {
		Response<Laboratory> response = new Response<Laboratory>();
		try {
			validateCreateLaboratory(laboratory, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			laboratory.setPassword(passwordEncoder.encode(laboratory.getPassword()));
			Laboratory laboratoryPersisted = (Laboratory) laboratoryService.createOrUpdate(laboratory);
			response.setData(laboratoryPersisted);
		} catch (DuplicateKeyException dE) {
			response.getErrors().add("E-mail already registered !");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateCreateLaboratory(Laboratory laboratory, BindingResult result) {
		if (laboratory.getEmail() == null) {
			result.addError(new ObjectError("Laboratory", "Email no information"));
			return;
		}
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Laboratory>> update(HttpServletRequest request, @RequestBody Laboratory laboratory,
			BindingResult result) {
		Response<Laboratory> response = new Response<Laboratory>();
		try {
			validateUpdate(laboratory, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			laboratory.setPassword(passwordEncoder.encode(laboratory.getPassword()));
			Laboratory laboratoryPersisted = (Laboratory) laboratoryService.createOrUpdate(laboratory);
			response.setData(laboratoryPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateUpdate(Laboratory laboratory, BindingResult result) {
		if (laboratory.getId() == null) {
			result.addError(new ObjectError("Laboratory", "Id no information"));
			return;
		}
		if (laboratory.getEmail() == null) {
			result.addError(new ObjectError("Laboratory", "Email no information"));
			return;
		}
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Laboratory>> findById(@PathVariable("id") String id) {
		Response<Laboratory> response = new Response<Laboratory>();
		Laboratory laboratory = laboratoryService.findById(id);
		if (laboratory == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(laboratory);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		Laboratory laboratory = laboratoryService.findById(id);
		if (laboratory == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		laboratoryService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
    public  ResponseEntity<Response<Page<Laboratory>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<Laboratory>> response = new Response<Page<Laboratory>>();
		Page<Laboratory> laboratorys = laboratoryService.findAll(page, count);
		response.setData(laboratorys);
		return ResponseEntity.ok(response);
    }
	
}
