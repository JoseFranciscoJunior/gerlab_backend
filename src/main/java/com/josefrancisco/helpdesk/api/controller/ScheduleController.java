package com.josefrancisco.helpdesk.api.controller;

import com.josefrancisco.helpdesk.api.response.Response;
import com.josefrancisco.helpdesk.api.security.entity.Reserve;
import com.josefrancisco.helpdesk.api.service.ReserveService;
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
@RequestMapping("/api/reserve")
@CrossOrigin(origins = "*")
public class ScheduleController {
	
	@Autowired
	private ReserveService reserveService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Reserve>> create(HttpServletRequest request, @RequestBody Reserve reserve,
			BindingResult result) {
		Response<Reserve> response = new Response<Reserve>();
		try {
			validateCreateReserve(reserve, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			reserve.setPassword(passwordEncoder.encode(reserve.getPassword()));
			Reserve reservePersisted = (Reserve) reserveService.createOrUpdate(reserve);
			response.setData(reservePersisted);
		} catch (DuplicateKeyException dE) {
			response.getErrors().add("E-mail already registered !");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateCreateReserve(Reserve reserve, BindingResult result) {
		if (reserve.getEmail() == null) {
			result.addError(new ObjectError("Reserve", "Email no information"));
			return;
		}
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Reserve>> update(HttpServletRequest request, @RequestBody Reserve reserve,
			BindingResult result) {
		Response<Reserve> response = new Response<Reserve>();
		try {
			validateUpdate(reserve, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			reserve.setPassword(passwordEncoder.encode(reserve.getPassword()));
			Reserve reservePersisted = (Reserve) reserveService.createOrUpdate(reserve);
			response.setData(reservePersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateUpdate(Reserve reserve, BindingResult result) {
		if (reserve.getId() == null) {
			result.addError(new ObjectError("Reserve", "Id no information"));
			return;
		}
		if (reserve.getEmail() == null) {
			result.addError(new ObjectError("Reserve", "Email no information"));
			return;
		}
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Reserve>> findById(@PathVariable("id") String id) {
		Response<Reserve> response = new Response<Reserve>();
		Reserve reserve = reserveService.findById(id);
		if (reserve == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(reserve);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		Reserve reserve = reserveService.findById(id);
		if (reserve == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		reserveService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
    public  ResponseEntity<Response<Page<Reserve>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<Reserve>> response = new Response<Page<Reserve>>();
		Page<Reserve> reserves = reserveService.findAll(page, count);
		response.setData(reserves);
		return ResponseEntity.ok(response);
    }
	
}
