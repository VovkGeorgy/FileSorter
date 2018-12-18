package by.home.fileSorter.service.impl.json;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.service.IFileMover;
import com.jcraft.jsch.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class realise method which move JSON file from local folder to sftp server
 */
@Service
public class JsonFileMover implements IFileMover<List<ErrorMessage>> {


    @Value("${json.sftp.valid.folder.path}")
    private String jsonRemoveValidFolderPath;

    @Value("${json.sftp.not.valid.folder.path}")
    private String jsonNotValidFolderPath;

    @Value("${ftp.username}")
    private String ftpUsername;

    @Value("${ftp.password}")
    private String ftpPassword;

    @Value("${ftp.host}")
    private String ftpHost;

    @Value("${ftp.port}")
    private Integer ftpPort;

    @Value("${ftp.config.v1}")
    private String ftpConfigV1;

    @Value("${ftp.config.v2}")
    private String ftpConfigV2;

    @Value("${ftp.chanel.type}")
    private String ftpChanelType;

    @Value("${not.sorted.folder.path}")
    private String notSortedFolderPath;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileMover.class);

    @Override
    public void moveFiles(List<ErrorMessage> validObjectsList, List<File> notValidFileList) {
        LOGGER.info("Move files to path from property");
        LOGGER.debug("Move files from {} valid to {}, not valid to {} path", notSortedFolderPath, jsonRemoveValidFolderPath,
                jsonNotValidFolderPath);
        move(getFilesByObject(validObjectsList), jsonRemoveValidFolderPath);
        move(notValidFileList, jsonNotValidFolderPath);
    }

    private void move(List<File> fileList, String jsonRemoteFolderPath) {
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp sftpChannel = new ChannelSftp();
        try {
            LOGGER.info("Try to connect to server");
            session = jsch.getSession(ftpUsername, ftpHost, ftpPort);
            session.setConfig(ftpConfigV1, ftpConfigV2);
            session.setPassword(ftpPassword);
            LOGGER.debug("Try to connect to session {}", session);
            session.connect();
            Channel channel = session.openChannel(ftpChanelType);
            LOGGER.debug("Try to connect to chanel {}", channel);
            channel.connect();
            sftpChannel = (ChannelSftp) channel;
            LOGGER.debug("Connecting to server is established");
            LOGGER.info("Upload files to server");
            for (File file : fileList) {
                sftpChannel.put(notSortedFolderPath + file.getName(), jsonRemoteFolderPath + file.getName());
            }
            boolean resuldOfDeleting = deleteOldFiles(fileList);
            LOGGER.debug("Old file are deleted - {}", resuldOfDeleting);
        } catch (JSchException | SftpException e) {
            LOGGER.error("SFTP Connection exception {}", e.getMessage());
        } catch (NullPointerException nul) {
            LOGGER.debug("Connection session is NULL", nul.getMessage());
        } finally {
            LOGGER.info("Close server connection");
            sftpChannel.exit();
            if (session != null) session.disconnect();
            else LOGGER.debug("Connection session is NULL");
        }
    }

    private List<File> getFilesByObject(List<ErrorMessage> validObjectsList) {
        ArrayList<File> validFileList = new ArrayList<>();
        validObjectsList.forEach(errorMessage -> validFileList.add(FileUtils.getFile(notSortedFolderPath, errorMessage.getFileName())));
        return validFileList;
    }

    private boolean deleteOldFiles(List<File> fileList) {
        LOGGER.info("Deleting old local files");
        fileList.stream().filter(File::exists).forEach(File::delete);
        return true;
    }
}
