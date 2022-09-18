package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AuditDaoImpl implements AuditDao {
	
    private final String AUDIT_FILE;
    //Default constructor
    //Contractor for testing
    public AuditDaoImpl(String auditFile) {
        this.AUDIT_FILE = auditFile;
    }
    public AuditDaoImpl() {
        this.AUDIT_FILE = "Audit.txt";
    }

    @SuppressWarnings("resource")
	public void writeAuditEntry(String entry) throws DataPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new DataPersistenceException("Could not persist audit information.", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();

    }

}
