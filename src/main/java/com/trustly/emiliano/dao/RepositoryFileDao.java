package com.trustly.emiliano.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.trustly.emiliano.model.RepositoryFile;

/**
 * 
 * DAO used to persist a {@link RepositoryFile}.
 * 
 * @author Emiliano Pessôa
 *
 */
public interface RepositoryFileDao extends JpaRepository<RepositoryFile, String> {

	/**
	 * 
	 * Find all {@link RepositoryFile} grouped by extension.
	 * 
	 * @param url Repository url.
	 * @return A list of {@link RepositoryFile}.
	 */
	@Query("select new com.trustly.emiliano.model.RepositoryFile(extension, sum(totalLines), sum(totalBytes)) from RepositoryFile where parent.url = :url group by extension")
	public List<RepositoryFile> findAllGroupByExtension(String url);

}