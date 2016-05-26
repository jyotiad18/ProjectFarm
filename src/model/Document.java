package model;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import model.exception.InvalidDataException;

public class Document implements Serializable {

	private static final long serialVersionUID = 3404763898326246494L;
	
	private String documentPath;
	private Date added;
	private int docid;
	
	private String documentname;
	
	
	

	public Document(String documentPath , int docid) throws InvalidDataException {
		setDocumentPath(documentPath);
		setDocid(docid);
		setAdded(new Date());
	}

	public int getDocid() {
		return docid;
	}

	public void setDocid(int docid) {
		this.docid = docid;
	}

	public String getDocumentPath() {
		return documentPath;
	}
	public String getDocumentname() {
		Path p = Paths.get(documentPath);
		documentname = p.getFileName().toString();
		return documentname ;
	}

	public void setDocumentPath(String documentPath) throws InvalidDataException {
		
		Path p = Paths.get(documentPath);
		File file = new File(documentPath);
		
		if(!file.exists()) {
			throw new InvalidDataException("File " + p.getFileName() + " does not exists");
		}
		
		if(!file.isFile()) {
			throw new InvalidDataException("Path " + p.getFileName() + " does not point to a file");
		}
		
		this.documentPath = documentPath;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

}
