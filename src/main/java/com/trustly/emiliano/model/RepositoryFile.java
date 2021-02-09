package com.trustly.emiliano.model;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.trustly.emiliano.commons.FileUtils;

/**
 * 
 * Entity that represents the files of a repository hierarchically.
 * 
 * @author Emiliano Pessôa
 *
 */
@Entity
public class RepositoryFile implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Repository url and key of the entity. <br>
	 * The root file contains the repository url while the childrens {@link #files}
	 * url are formed by concatenating the url with the file name.
	 * 
	 */
	@Id
	@JsonIgnore
	private String url;

	/**
	 * The file name.
	 */
	@JsonInclude(Include.NON_DEFAULT)
	private String fileName;

	/**
	 * The parent {@link RepositoryFile}.
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private RepositoryFile parent;

	/**
	 * The children {@link RepositoryFile} list.
	 */
	@JsonInclude(Include.NON_EMPTY)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	private List<RepositoryFile> files;

	/**
	 * Number of lines in this file. <br>
	 * The root object has the sum of all childrens lines.
	 */
	@JsonInclude(Include.NON_DEFAULT)
	private long totalLines;

	/**
	 * Number of bytes of this file. <br>
	 * The root object has the sum of all childrens bytes.
	 */
	@JsonInclude(Include.NON_DEFAULT)
	private double totalBytes;

	/**
	 * Last commit hash. Used to control the update of {@link RepositoryFile}.
	 */
	@JsonIgnore
	private String lastCommit;

	/**
	 * Date of the last update of this file.
	 */
	@JsonIgnore
	private Date lastModifiedDate;

	/**
	 * Extension of this file. If it has no extension then 'other' will be inserted.
	 * will be inserted
	 */
	@JsonInclude(Include.NON_DEFAULT)
	private String extension;

	/**
	 * Constructor
	 */
	public RepositoryFile() {

	}

	/**
	 * Constructor
	 * 
	 * @param extension  {@link #extension}
	 * @param totalLines {@link #totalLines}
	 * @param totalBytes {@link #totalBytes}
	 */
	public RepositoryFile(String extension, long totalLines, double totalBytes) {
		this.extension = extension;
		this.totalLines = totalLines;
		this.totalBytes = totalBytes;
	}

	/**
	 * Constructor
	 * 
	 * @param url    {@link #url}
	 * @param zipUrl Zipped file url whose files will be unzipped inside this
	 *               object.
	 * 
	 * @throws IOExceptio            - Case an I/O exception occurs.
	 * @throws MalformedURLException - If the url is invalid.
	 * @throws Exception             - If an exception occurs.
	 */
	public RepositoryFile(String url, String lastCommit, String zipUrl) throws MalformedURLException, IOException {
		ZipInputStream zis = new ZipInputStream(new URL(zipUrl).openStream());
		ZipEntry ze = zis.getNextEntry();
		while (ze != null) {
			// Root file
			if (this.url == null) {
				this.url = url;
				this.lastCommit = lastCommit;
				this.fileName = ze.getName().replace("/", "");
				this.lastModifiedDate = new Date(ze.getTime());
				this.files = new ArrayList<>();
			}
			// Other files
			if (!ze.isDirectory()) {
				RepositoryFile child = new RepositoryFile();
				String name = ze.getName();
				child.setUrl(url + name);
				child.setLastCommit(this.lastCommit);
				child.setFileName(name);
				child.setLastModifiedDate(new Date(ze.getTime()));
				child.setTotalBytes((ze.getSize() / 1024d));
				child.setTotalLines(FileUtils.countLines(zis));
				child.setParent(this);
				child.setExtension(name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : "other");
				this.totalLines += child.getTotalLines();
				this.totalBytes += child.getTotalBytes();
				this.getFiles().add(child);
			}
			ze = zis.getNextEntry();
		}
		zis.close();
	}

	/**
	 * @return the {@link #url}.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url Set {@link #url} value.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the {@link #fileName}.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName Set {@link #fileName} value.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the {@link #parent}.
	 */
	public RepositoryFile getParent() {
		return parent;
	}

	/**
	 * @param parent Set {@link #parent} value.
	 */
	public void setParent(RepositoryFile parent) {
		this.parent = parent;
	}

	/**
	 * @return the {@link #files}.
	 */
	public List<RepositoryFile> getFiles() {
		return files;
	}

	/**
	 * @param files Set {@link #files} value.
	 */
	public void setFiles(List<RepositoryFile> files) {
		this.files = files;
	}

	/**
	 * @return the {@link #totalLines}.
	 */
	public long getTotalLines() {
		return totalLines;
	}

	/**
	 * @param totalLines Set {@link #totalLines} value.
	 */
	public void setTotalLines(long totalLines) {
		this.totalLines = totalLines;
	}

	/**
	 * @return the {@link #totalBytes}.
	 */
	public double getTotalBytes() {
		return totalBytes;
	}

	/**
	 * @param totalBytes Set {@link #totalBytes} value.
	 */
	public void setTotalBytes(double totalBytes) {
		this.totalBytes = totalBytes;
	}

	/**
	 * @return the {@link #lastCommit}.
	 */
	public String getLastCommit() {
		return lastCommit;
	}

	/**
	 * @param lastCommit Set {@link #lastCommit} value.
	 */
	public void setLastCommit(String lastCommit) {
		this.lastCommit = lastCommit;
	}

	/**
	 * @return the {@link #lastModifiedDate}.
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate Set {@link #lastModifiedDate} value.
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the {@link #extension}.
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension Set {@link #extension} value.
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

}
