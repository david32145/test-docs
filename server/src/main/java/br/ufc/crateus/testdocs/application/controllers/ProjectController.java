package br.ufc.crateus.testdocs.application.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.crateus.testdocs.application.dto.CreateMemberDTO;
import br.ufc.crateus.testdocs.application.services.StorageService;
import br.ufc.crateus.testdocs.data.ProjectListDTO;
import br.ufc.crateus.testdocs.data.dao.ProjectDao;
import br.ufc.crateus.testdocs.domain.entities.Project;
import br.ufc.crateus.testdocs.domain.entities.User;
import br.ufc.crateus.testdocs.domain.erros.DomainError;
import br.ufc.crateus.testdocs.domain.erros.Either;
import br.ufc.crateus.testdocs.domain.services.ProjectService;

@RestController
@CrossOrigin()
@RequestMapping("/api/projects")
public class ProjectController {

	private ProjectService projectService;

	private ProjectDao projectDao;

	private StorageService storageService;

	public ProjectController(ProjectService projectService,
			ProjectDao projectDao, StorageService storageService) {
		this.projectService = projectService;
		this.projectDao = projectDao;
		this.storageService = storageService;
	}

	@GetMapping
	public Object getMyProjects(@RequestHeader("Authorization") long loggedUserId) {
		List<ProjectListDTO> projectListDTOs = new ArrayList<ProjectListDTO>();
		for(String[] s : projectDao.listProject(loggedUserId)) {
			projectListDTOs.add(new ProjectListDTO(Long.parseLong(s[0]),s[1], Long.parseLong(s[2]), s[3], s[4]));
		}
		return projectListDTOs;
	}

	@PostMapping
	public ResponseEntity<?> createProject(@RequestBody Project project,
			@RequestHeader("Authorization") long loggedUserId) {
		Either<Project, DomainError> createProjectResult = projectService
				.createProduct(project, loggedUserId);
		if (createProjectResult.hasData()) {
			return new ResponseEntity<Project>(createProjectResult.getData(),
					HttpStatus.CREATED);
		}
		return new ResponseEntity<DomainError>(createProjectResult.getError(),
				HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{projectId}")
	public ResponseEntity<?> updateProject(@RequestBody Project project,
			@PathVariable("projectId") long projectId) {
		Either<Project, DomainError> updateProjectResult = projectService
				.updateProject(project, projectId);
		if (updateProjectResult.hasData()) {
			return new ResponseEntity<Project>(updateProjectResult.getData(),
					HttpStatus.OK);
		}
		return new ResponseEntity<DomainError>(updateProjectResult.getError(),
				HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<?> findById(
			@PathVariable("projectId") long projectId) {
		Either<Project, DomainError> findProjectByIdResult = projectService
				.findById(projectId);
		if (findProjectByIdResult.hasData()) {
			return ResponseEntity.ok(findProjectByIdResult.getData());
		}
		return new ResponseEntity<DomainError>(findProjectByIdResult.getError(),
				HttpStatus.NOT_FOUND);
	}

	@PostMapping("/{projectId}/members")
	public ResponseEntity<?> addMember(
			@PathVariable("projectId") long projectId,
			@RequestBody CreateMemberDTO requestBody) {
		Either<User, DomainError> addMemberResult = projectService
				.addMember(projectId, requestBody.getUserEmail());
		if (addMemberResult.hasData()) {
			return ResponseEntity.ok(addMemberResult.getData());
		}
		return ResponseEntity.badRequest().body(addMemberResult.getError());
	}

	@GetMapping("/{projectId}/members")
	public ResponseEntity<?> findAllMembers(
			@PathVariable("projectId") long projectId) {
		Either<List<User>, DomainError> findMembersResult = projectService
				.findUsersByProjectId(projectId);
		if (findMembersResult.hasData()) {
			return ResponseEntity.ok(findMembersResult.getData());
		}
		return ResponseEntity.badRequest().body(findMembersResult.getError());
	}

	@DeleteMapping("/{projectId}/members/{memberId}")
	public ResponseEntity<?> deleteMember(@PathVariable("projectId") long projectId, @PathVariable("memberId")long userId) {
		Either<String, DomainError> deleteMemberResult = projectService
				.deleteMember(projectId, userId);
		if (deleteMemberResult.hasData()) {
			return ResponseEntity.ok(deleteMemberResult.getData());
		}
		return ResponseEntity.badRequest().body(deleteMemberResult.getError());
	}
	
	@PostMapping("/{projectId}/imageUri")
	@ResponseBody
	public ResponseEntity<?> changeImage(
			@PathVariable("projectId") long projectId,
			@RequestParam("image") MultipartFile file) throws IOException {
		String fileName = storageService.upload(file.getInputStream(), file.getOriginalFilename());
		Either<String, DomainError> deleteMemberResult = projectService
				.updateImage(projectId, fileName);
		System.out.println(deleteMemberResult.getData());
		if (deleteMemberResult.hasData()) {
			return ResponseEntity.ok(deleteMemberResult.getData());
		}
		return ResponseEntity.badRequest().body(deleteMemberResult.getError());
	
	}
}
