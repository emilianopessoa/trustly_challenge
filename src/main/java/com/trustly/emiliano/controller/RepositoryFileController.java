package com.trustly.emiliano.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.trustly.emiliano.commons.ControllerException;
import com.trustly.emiliano.model.RepositoryFile;
import com.trustly.emiliano.service.GithubService;
import com.trustly.emiliano.service.IRepositoryFileService;

/**
 * 
 * Control class for {@link RepositoryFile} maintenance.
 * 
 * @author Emiliano Pessôa
 *
 */
@RestController
public class RepositoryFileController {

	/**
	 * Attribute to log messages.
	 */
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * {@link GithubService} implementation.
	 */
	@Autowired
	private IRepositoryFileService githubService;

	/**
	 * {@link GithubService} implementation.
	 */
	@Autowired
	private IRepositoryFileService bitbucketService;

	/**
	 * API method that retrieves information from a gitlab repository.
	 * 
	 * @param url     Gitlab url repository.
	 * @param grouped <code>true</code> the values should be ordered by file
	 *                extension.<br>
	 *                <code>false</code> All files in the repository will be
	 *                displayed.
	 * @return A {@link RepositoryFile} containing the repository files.
	 */
	@GetMapping(value = "/api/github", produces = "application/json")
	public RepositoryFile loadGithubInfo(@RequestParam String url, @RequestParam(required = false, defaultValue = "true") boolean grouped) {
		LocalDateTime begin = LocalDateTime.now();
		try {
			if (grouped) {
				return githubService.loadRepositoryFilesGroupdByExtension(url);
			} else {
				return githubService.loadRepositoryFile(url);
			}
		} catch (ControllerException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} finally {
			logger.info("Execution time:" + begin.until(LocalDateTime.now(), ChronoUnit.SECONDS) + " seconds.");
		}
	}

	/**
	 * API method that retrieves information from a bitbucket repository.
	 * 
	 * @param url Bitbucket url repository.
	 * 
	 * @return A {@link RepositoryFile} containing the repository files.
	 */
	@GetMapping(value = "/api/bitbucket", produces = "application/json")
	public RepositoryFile loadBitbucketInfo(@RequestParam String url) {
		LocalDateTime begin = LocalDateTime.now();
		try {
			return bitbucketService.loadRepositoryFile(url);
		} catch (ControllerException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} finally {
			logger.info("Execution time:" + begin.until(LocalDateTime.now(), ChronoUnit.SECONDS) + " seconds.");
		}
	}

}
