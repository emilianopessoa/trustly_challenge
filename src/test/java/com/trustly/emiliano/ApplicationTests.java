package com.trustly.emiliano;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.trustly.emiliano.controller.RepositoryFileController;
import com.trustly.emiliano.model.RepositoryFile;
import com.trustly.emiliano.service.GithubService;

@SpringBootTest
class ApplicationTests {

	/**
	 * Attribute to log messages.
	 */
	Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

	/**
	 * {@link GithubService} implementation.
	 */
	@Autowired
	private RepositoryFileController repositoryFileController;

	@Test
	void contextLoads() {
		// Message formatted with magenta color.
		String msg = "\u001B[35m" + "%s" + "\u001B[35m";

		RepositoryFile rf = repositoryFileController.loadGithubInfo("https://github.com/scrapinghub/sample-projects", false);
		logger.info(String.format(msg, "Testing 'RepositoryFileController.loadGithubInfo'."));
		logger.info(String.format(msg, "Getting 'https://github.com/scrapinghub/sample-projects' information."));
		logger.info(String.format(msg, "Total lines: " + rf.getTotalLines() + "."));
		logger.info(String.format(msg, "Total bytes: " + rf.getTotalBytes() + "."));
		logger.info(String.format(msg, "End of 'RepositoryFileController.loadGithubInfo' Test."));
	}

}
