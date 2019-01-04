package by.home.fileSorter.service.impl.error;

import by.home.fileSorter.service.IFileMover;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Class realise method which move JSON files
 */
@Service
@Slf4j
public class ErrorFileMover implements IFileMover {

    @Value("${error.sftp.valid.folder.path}")
    private String validToFolder;

    @Value("${error.sftp.not.valid.folder.path}")
    private String notValidToFolder;

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

    @Value("${input.folder.path}")
    private String fromFolder;

    private JSch jsch = new JSch();
    private Session session = null;
    private ChannelSftp sftpChannel = new ChannelSftp();


    @Override
    public boolean moveFile(File file, boolean isValid) {
        log.info("Move file {}", file.getName());
        log.debug("Move file from {} isValid to {}, not valid to {} path", fromFolder, validToFolder,
                notValidToFolder);
        return isValid ? move(file, validToFolder) : move(file, notValidToFolder);
    }

    private boolean move(File file, String jsonRemoteFolderPath) {
        try {
            log.info("Connecting to server");
            ChannelSftp sftpChannel = configSftpConnection();
            sftpChannel.put(fromFolder + file.getName(), jsonRemoteFolderPath + file.getName());
            boolean resultOfDeleting = deleteOldFiles(file);
            log.debug("Old file are deleted - {}", resultOfDeleting);
            return true;
        } catch (JSchException | SftpException e) {
            log.error("SFTP Connection exception {}", e.getMessage());
            return false;
        } catch (NullPointerException nul) {
            log.error("Connection session is NULL", nul.getMessage());
            return false;
        } finally {
            log.info("Close server connection");
            sftpChannel.exit();
            if (session != null) session.disconnect();
            else log.error("Connection session is NULL");
        }
    }

    private ChannelSftp configSftpConnection() throws JSchException {
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
