package by.home.fileSorter.service.impl.json;

import by.home.fileSorter.service.IFileMover;
import by.home.fileSorter.utils.PropertiesUtils;
import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

/**
 * Class realise method which move JSON file from local folder to sftp server
 */
public class JsonFileMover implements IFileMover {

    @Autowired
    private PropertiesUtils propertiesUtils;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileMover.class);

    private String notSortedLocalFolderPath;
    private String ftpUsername;
    private String ftpHost;
    private String ftpConfigV1;
    private String ftpConfigV2;
    private String ftpPassword;
    private String ftpChanelType;
    private Integer ftpPort;

    @Override
    public void moveFiles(List<File> validFileList, List<File> notValidFileList) {
        LOGGER.info("Move files to path from property");
        String jsonRemoteValidFolderPath = propertiesUtils.getJsonRemoteValidFolderPath();
        String jsonRemoteNotValidFolderPath = propertiesUtils.getJsonNotValidFolderPath();
        this.notSortedLocalFolderPath = propertiesUtils.getNotSortedFolderPath();
        LOGGER.debug("Move files from {} valid to {}, not valid to {} path", notSortedLocalFolderPath, jsonRemoteValidFolderPath, jsonRemoteNotValidFolderPath);
        move(validFileList, jsonRemoteValidFolderPath);
        move(notValidFileList, jsonRemoteNotValidFolderPath);
    }

    private void move(List<File> fileList, String jsonRemoteFolderPath) {
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp sftpChannel = new ChannelSftp();
        getSftpConfig();
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
                sftpChannel.put(notSortedLocalFolderPath + file.getName(), jsonRemoteFolderPath + file.getName());
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
            if (session != null) {
                session.disconnect();
            } else LOGGER.debug("Connection session is NULL");
        }
    }

    private void getSftpConfig() {
        LOGGER.info("Get sftp server config");
        this.ftpUsername = propertiesUtils.getFtpUsername();
        this.ftpHost = propertiesUtils.getFtpHost();
        this.ftpConfigV1 = propertiesUtils.getFtpConfigV1();
        this.ftpConfigV2 = propertiesUtils.getFtpConfigV2();
        this.ftpPassword = propertiesUtils.getFtpPassword();
        this.ftpChanelType = propertiesUtils.getFtpChanelType();
        this.ftpPort = propertiesUtils.getFtpPort();
    }

    private boolean deleteOldFiles(List<File> fileList) {
        LOGGER.info("Deleting old local files");
        for (File file : fileList) {
            if (file.exists()) file.delete();
        }
        return true;
    }
}
