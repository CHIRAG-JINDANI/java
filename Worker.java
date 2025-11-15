public class Worker {
    String workerName;
    String workerContact;
    String workerEmail;
    String workerPassword;

    public Worker(String workerName, String workerContact, String workerEmail, String workerPassword) {
        this.workerName = workerName;
        this.workerContact = workerContact;
        this.workerEmail = workerEmail;
        this.workerPassword = workerPassword;
    }

    public String getWorkerEmail() {
        return this.workerEmail;
    }

    public String getWorkerContact() {
        return this.workerContact;
    }

    public String getEmail() {
        return workerEmail;
    }

    public String getPassword() {
        return workerPassword;
    }

}