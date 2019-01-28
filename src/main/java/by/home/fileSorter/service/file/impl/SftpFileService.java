package by.home.fileSorter.service.file.impl;

import by.home.fileSorter.service.file.IFileService;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;

/**
 * Class realise method which move files to sftp server
 */
@Slf4j
@Service
public class SftpFileService implements IFileService {

    @Value("${sftp.username}")
    private String ftpUsername;

    @Value("${sftp.password}")
    private String ftpPassword;

    @Value("${sftp.host}")
    private String ftpHost;

    @Value("${sftp.port}")
    private Integer ftpPort;

    @Value("${sftp.config.v1}")
    private String ftpConfigV1;

    @Value("${sftp.config.v2}")
    private String ftpConfigV2;

    @Value("${sftp.chanel.type}")
    private String ftpChanelType;

    private Session session;
    private ChannelSftp sftpChannel;

    @PostConstruct
    private void connectionInit() {
        try {
            log.info("Connecting to server");
            JSch jsch = new JSch();
            session = jsch.getSession(ftpUsername, ftpHost, ftpPort);
            session.setConfig(ftpConfigV1, ftpConfigV2);
            session.setPassword(ftpPassword);
            session.connect();
            Channel channel = session.openChannel(ftpChanelType);
            channel.connect();
            sftpChannel = (ChannelSftp) channel;
            log.debug("Connecting to server is established");
        } catch (JSchException e) {
            sftpChannel = null;
            session = null;
            log.error("SFTP Connection exception {}", e.getMessage());
        }
    }

    @PreDestroy
    public void connectionTeardown() {
        log.info("Close server connection");
        if (session != null & sftpChannel != null) {
            sftpChannel.exit();
            session.disconnect();
            return;
        }
        log.warn("Connection statements is NULL");
    }

    @Override
    public boolean moveFile(File file, String outputFolderPath) {
        try {
            outputFolderPath += file.getName();
            String inputFolderPath = file.getPath();
            sftpChannel.put(inputFolderPath, outputFolderPath);
            log.info("Move file from {} to {} path", inputFolderPath, outputFolderPath);
            boolean resultOfDeleting = deleteLocalFiles(file);
            log.debug("Result of old files is {} deleted is", resultOfDeleting ? " " : "NOT");
            return true;
        } catch (SftpException | NullPointerException e) {
            log.error("SFTP Connection exception {}", e.getMessage());
            connectionTeardown();
            connectionInit();
            return false;
        }
    }

    private boolean deleteLocalFiles(File file) {
        return file.exists() && file.delete();
    }
}
