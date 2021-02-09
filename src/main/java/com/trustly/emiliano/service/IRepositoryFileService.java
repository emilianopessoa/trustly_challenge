package com.trustly.emiliano.service;

import com.trustly.emiliano.commons.ControllerException;
import com.trustly.emiliano.model.RepositoryFile;

/**
 * 
 * Service used to load repositories informations.
 * 
 * @author Emiliano Pessôa
 *
 */
public interface IRepositoryFileService {

	/**
	 * Service used to load a RepositoryFile given the url of a repository.
	 * 
	 * @param url Repository URL.
	 * @return A {@link RepositoryFile} containing the repository files.
	 * @throws ControllerException - If a controller exception occurs
	 */
	RepositoryFile loadRepositoryFile(String url) throws ControllerException;

	/**
	 * 
	 * Service used to load a RepositoryFile group by file extension given the url
	 * of a repository.
	 * 
	 * @param url Repository URL.
	 * @return A {@link RepositoryFile} containing the repository files.
	 * @throws ControllerException - If a controller exception occurs
	 */
	RepositoryFile loadRepositoryFilesGroupdByExtension(String url) throws ControllerException;

}
