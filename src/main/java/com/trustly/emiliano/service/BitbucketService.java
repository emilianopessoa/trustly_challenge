package com.trustly.emiliano.service;

import org.springframework.stereotype.Service;
import com.trustly.emiliano.commons.ControllerException;
import com.trustly.emiliano.model.RepositoryFile;

/**
 * 
 * Class responsible for implementing the bitbucket repository loading service.
 * 
 * @author Emiliano Pessôa
 *
 */
@Service
public class BitbucketService implements IRepositoryFileService {

	// TODO: Class to be implemented in the near future. =)

	@Override
	public RepositoryFile loadRepositoryFile(String url) throws ControllerException {
		throw new ControllerException("Under development!");
	}

	@Override
	public RepositoryFile loadRepositoryFilesGroupdByExtension(String url) throws ControllerException {
		throw new ControllerException("Under development!");
	}

}
