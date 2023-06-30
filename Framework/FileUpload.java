package etu1917.framework;



public class FileUpload {
    String name;
    byte[] file;
    String path;


    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }
}

//String filePath = "/path/to/file";

// file to byte[], Path
//byte[] bytes = Files.readAllBytes(Paths.get(filePath));

// file to byte[], File -> Path
//File file = new File(filePath);
//byte[] bytes = Files.readAllBytes(file.toPath());