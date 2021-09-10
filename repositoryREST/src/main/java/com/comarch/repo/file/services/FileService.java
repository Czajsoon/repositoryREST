package com.comarch.repo.file.services;

import com.comarch.repo.file.domain.FileConfig;
import com.comarch.repo.file.domain.File;
import com.comarch.repo.file.repository.InMemoryRepositoryFileInterface;
import com.comarch.repo.file.utils.CreateFileConfig;
import com.comarch.repo.file.utils.ReadFileConfig;
import com.comarch.repo.file.utils.SearchFile;
import com.comarch.repo.user.utils.ReadPaths;
import com.comarch.repo.utils.Ids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService implements FileServiceInterface {

    public final Path root;

    @Autowired
    InMemoryRepositoryFileInterface inMemoryRepositoryFile;

    @Override
    public Resource loadFileAsResource(String id){
        try{
            Path filePath = inMemoryRepositoryFile.getFilePath(id);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists())
                return resource;
            else
                return null;
        }
        catch(MalformedURLException ex){
            System.out.println("error getfileDownload");
            return null;
        }
    }

    @Autowired
    public FileService() {
        this.root = Paths.get("/uploads/repo")
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.root);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<File> getAllFiles(){
        List<File> filesList = new ArrayList<>(inMemoryRepositoryFile.getAllFilesList().values());
        return filesList;
    }

    @Override
    public List<File> getAllFilesUser(String userID){
        List<File> fileList = new ArrayList<>();
        for(File file : getAllFiles()){
            if(file.getPermissionList() != null)
                for(String id: file.getPermissionList())
                    if(id.equals(userID))
                        fileList.add(file);
            if(file.getUserID().equals(userID))
                fileList.add(file);
        }
        return fileList;
    }

    @Override
    public File getFile(String id){
        return inMemoryRepositoryFile.getFile(id);
    }

    @Override
    public File addOrRemoveAccess(String fileID, String userID) throws IOException {
       List<String> userId = getFile(fileID).getPermissionList().stream().filter(user -> user.equals(userID)).collect(Collectors.toList());

       if(userId.size() > 0) getFile(fileID).removePermission(userID);
       else getFile(fileID).putPermissionUser(userID);

       return getFile(fileID);
    }

    public List<File> getFilesFromDirectory(String ownerID,String dir) throws IOException {
        List<File> filesList = new ArrayList<>();
        if(dir != null) {
            for (Path dirIT : ReadPaths.readUserPathDir(dir))
                filesList.add(ReadFileConfig.readConfigFile(dirIT));
            return filesList.stream().filter(file -> file.getUserID().equals(ownerID)).collect(Collectors.toList());
        }
        else return null;
    }

    @Override
    public boolean givePermissionForFolder(String dir,String userID,String ownerID) throws IOException {
        boolean userInPermissionList = false;
        for(File file : getFilesFromDirectory(ownerID,dir)){
            for(String userPermitted : file.getPermissionList())
                if(userPermitted.equals(userID)){
                    userInPermissionList = true;
                    file.getPermissionList().add(userID);
                }
        }
        return userInPermissionList;
    }

    @Override
    public File storeFile(MultipartFile file, FileConfig fileConfig) {
        String fileNamePath = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            String id = Ids.generateId();
            fileConfig.setId(id);
            Path idPath = Paths.get(this.root +"/" + fileConfig.getUserID() +"/" + id + "/").toAbsolutePath().normalize();
            Path targetLocation;
            if(fileConfig.getAdditionalPath().equals("undefined"))
                targetLocation = Files.createDirectory(idPath).resolve(fileNamePath);
            else {
                Path newTargetLocation = Paths.get(this.root + "/" + fileConfig.getAdditionalPath() + "/").toAbsolutePath().normalize();
                Path newTargetLocationFile = Paths.get(this.root + "/"  + Paths.get(fileNamePath));
                if(Files.exists(newTargetLocationFile) && file.getOriginalFilename().equals(newTargetLocationFile.getFileName().toString())){
                    fileNamePath = id + "___" + fileNamePath;
                    targetLocation = Files.createDirectories(newTargetLocation).resolve(fileNamePath);
                }
                else
                    targetLocation = Files.createDirectories(newTargetLocation).resolve(fileNamePath);
            }
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            fileConfig.setTargetLocation(targetLocation);
            fileConfig.setFileSize(String.valueOf(file.getSize()));
            CreateFileConfig.createConfigOfFile(fileConfig);
            System.out.println(fileConfig);
            File returnFile = inMemoryRepositoryFile.addFile(File.builder()
                            .id(id)
                            .fileName(fileConfig.getFileName())
                            .description(fileConfig.getDescription())
                            .fileSize(file.getSize())
                            .fileName(fileNamePath)
                            .path(targetLocation)
                            .fileType(fileConfig.getFormat())
                            .firstNameUser(fileConfig.getFirstName())
                            .lastNameUser(fileConfig.getLastName())
                            .userID(fileConfig.getUserID())
                            .permissionList(new ArrayList<>())
                            .build());
            System.out.println(returnFile);
            return returnFile;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
