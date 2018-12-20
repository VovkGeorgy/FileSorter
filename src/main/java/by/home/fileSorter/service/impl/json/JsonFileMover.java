package by.home.fileSorter.service.impl.json;

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
public class JsonFileMover implements IFileMover {

    @Value("${json.sftp.valid.folder.path}")
    private String validToFolder;

    @Value("${json.sftp.not.valid.folder.path}")
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

    @Value("${not.sorted.folder.path}")
    private String fromFolder;

    @Override
    public boolean moveFile(boolean isValid, File file) {
        log.info("Move files to path from property");
        log.debug("Move files from {} isValid to {}, not valid to {} path", fromFolder, validToFolder,
                notValidToFolder);
        return isValid ? move(file, validToFolder) : move(file, notValidToFolder);
    }

    private boolean move(File file, String jsonRemoteFolderPath) {
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp sftpChannel = new ChannelSftp();
        try {
            log.info("Try to connect to server");
            session = jsch.getSession(ftpUsername, ftpHost, ftpPort);
            session.setConfig(ftpConfigV1, ftpConfigV2);
            session.setPassword(ftpPassword);
            log.debug("Try to connect to session {}", session);
            session.connect();
            Channel channel = session.openChannel(ftpChanelType);
            log.debug("Try to connect to chanel {}", channel);
            channel.connect();
            sftpChannel = (ChannelSftp) channel;
            log.debug("Connecting to server is established");
            log.info("Upload files to server");
            sftpChannel.put(fromFolder + file.getName(), jsonRemoteFolderPath + file.getName());
            boolean resultOfDeleting = deleteOldFiles(file);
            log.debug("Old file are deleted - {}", resultOfDeleting);
            return true;
        } catch (JSchException | SftpException e) {
            log.error("SFTP Connection exception {}", e.getMessage());
            return false;
        } catch (NullPointerException nul) {
            log.debug("Connection session is NULL", nul.getMessage());
            return false;
        } finally {
            log.info("Close server connection");
            sftpChannel.exit();
            if (session != null) session.disconnect();
            else log.debug("Connection session is NULL");
        }
    }

    private boolean deleteOldFiles(File file) {
        log.info("Deleting old local files");
        return file.exists() && file.delete();
    }
}
