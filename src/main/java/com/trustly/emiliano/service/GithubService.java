package com.trustly.emiliano.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trustly.emiliano.commons.HtmlUtils;
import com.trustly.emiliano.commons.ControllerException;
import com.trustly.emiliano.dao.RepositoryFileDao;
import com.trustly.emiliano.model.RepositoryFile;

/**
 * 
 * Class responsible for implementing the bitbucket repository loading service.
 * 
 * @author Emiliano Pessôa
 *
 */
@Service
public class GithubService implements IRepositoryFileService {

	/**
	 * DAO to persist {@link RepositoryFile}.
	 */
	@Autowired
	RepositoryFileDao repositoryInfoDao;

	@Override
	public RepositoryFile loadRepositoryFile(String url) throws ControllerException {
		try {
			/* (1) Get url page html. */
			String html = HtmlUtils.getHtmlFromUrl(url);

			/* (2) Get zip file url. */
			URI uri = new URI(url);
			String domain = uri.getScheme() + "://" + uri.getHost();
			String zipUrl = domain + HtmlUtils.getTagAttributeValue(HtmlUtils.getHtmlTagByContentValue(html, "DOWNLOAD_ZIP"), "href");

			/* (3) Get last commit. */
			String boxHeader = HtmlUtils.getHtmlTagByContentValue(html, "Box-header");
			String lastCommit = HtmlUtils.getTagAttributeValue(HtmlUtils.getHtmlTagByContentValue(boxHeader, "include-fragment"), "src");

			/* (4) Check if the repository exists in our DB. */
			RepositoryFile info = repositoryInfoDao.findById(url).orElse(null);

			/* (5) Check if the repository is up to date. */
			if (info == null || !info.getLastCommit().equals(lastCommit)) {
				if (info != null) {
					repositoryInfoDao.delete(info);
				}
				info = new RepositoryFile(url, lastCommit, zipUrl);
				repositoryInfoDao.save(info);
			}
			return info;
		} catch (IOException e) {
			throw new ControllerException("Invalid URL");
		} catch (URISyntaxException e) {
			throw new ControllerException("Zip file not found", e);
		} catch (Exception e) {
			throw new ControllerException("An unknown error has occurred", e);
		}

	}

	@Override
	public RepositoryFile loadRepositoryFilesGroupdByExtension(String url) throws ControllerException {
		RepositoryFile info = this.loadRepositoryFile(url);
		info.setFiles(repositoryInfoDao.findAllGroupByExtension(info.getUrl()));
		return info;
	}

}
