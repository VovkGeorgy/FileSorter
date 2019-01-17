package by.home.fileSorter.service.file.impl;

import by.home.fileSorter.service.file.IFileService;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    private Session session = null;
    private ChannelSftp sftpChannel = new ChannelSftp();

    @PreDestroy
    public void connectionTeardown() {
        log.info("Close server connection");
        sftpChannel.exit();
        if (session != null) session.disconnect();
        else log.error("Connection session is NULL");
    }

    @Override
    public boolean moveFile(File file, String inputFolderPath, String outputFolderPath) {
        try {
            log.info("Connecting to server");
            ChannelSftp sftpChannel = (session == null) ? configSftpConnection() : this.sftpChannel;
            sftpChannel.put(inputFolderPath, outputFolderPath);
            log.debug("Move file from {} to {} path", inputFolderPath, outputFolderPath);
            boolean resultOfDeleting = deleteOldFiles(file);
            log.debug("Result of old files deleting is - ", resultOfDeleting);
            return true;
        } catch (JSchException | SftpException e) {
            log.error("SFTP Connection exception {}", e.getMessage());
            return false;
        } catch (NullPointerException nul) {
            log.error("Connection session is NULL", nul.getMessage());
            return false;
        }
    }

    private ChannelSftp configSftpConnection() throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(ftpUsername, ftpHost, ftpPort);
        session.setConfig(ftpConfigV1, ftpConfigV2);
        session.setPassword(ftpPassword);
        session.connect();
        Channel channel = session.openChannel(ftpChanelType);
        channel.connect();
        sftpChannel = (ChannelSftp) channel;
        log.debug("Connecting to server is established");
        return sftpChannel;
    }

    private boolean deleteOldFiles(File file) {
        return file.exists() && file.delete();
    }
}
