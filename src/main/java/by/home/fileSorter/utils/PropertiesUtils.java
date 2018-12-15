package by.home.fileSorter.utils;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Class get file properties from system
 */
public class PropertiesUtils {

    @Value("${not.sorted.folder.path}")
    private String NOT_SORTED_FOLDER_PATH;

    @Value("${txt.valid.folder.path}")
    private String TXT_FOLDER_PATH;

    @Value("${json.local.folder.path}")
    private String JSON_LOCAL_FOLDER_PATH;

    @Value("${json.file.template}")
    private String JSON_FILE_TEMPLATE;

    @Value("${txt.file.template}")
    private String TXT_FILE_TEMPLATE;

    @Value("${json.sftp.valid.folder.path}")
    private String JSON_REMOVE_VALID_FOLDER_PATH;

    @Value("${scan.delay}")
    private int SCAN_DELAY;

    @Value("${ftp.username}")
    private String FTP_USERNAME;

    @Value("${ftp.password}")
    private String FTP_PASSWORD;

    @Value("${ftp.host}")
    private String FTP_HOST;

    @Value("${ftp.port}")
    private Integer FTP_PORT;

    @Value("${ftp.config.v1}")
    private String FTP_CONFIG_V1;

    @Value("${ftp.config.v2}")
    private String FTP_CONFIG_V2;

    @Value("${ftp.chanel.type}")
    private String FTP_CHANEL_TYPE;

    @Value("${txt.not.valid.folder.path}")
    private String TXT_NOT_VALID_FOLDER_PATH;

    @Value("${json.sftp.not.valid.folder.path}")
    private String JSON_NOT_VALID_FOLDER_PATH;

    private List<String> templates = new ArrayList<>();

    @PostConstruct
    void postConstruct() {
        templates.add(TXT_FILE_TEMPLATE);
        templates.add(JSON_FILE_TEMPLATE);
    }

    public int getScanDelay() {
        return SCAN_DELAY;
    }

    public List<String> getTemplates() {
        return templates;
    }

    public String getTxtFileTemplate() {
        return TXT_FILE_TEMPLATE;
    }

    public String getJsonFileTemplate() {
        return JSON_FILE_TEMPLATE;
    }

    public String getNotSortedFolderPath() {
        return NOT_SORTED_FOLDER_PATH;
    }

    public String getTxtFolderPath() {
        return TXT_FOLDER_PATH;
    }

    public String getJsonLocalFolderPath() {
        return JSON_LOCAL_FOLDER_PATH;
    }

    public String getJsonRemoteValidFolderPath() {
        return JSON_REMOVE_VALID_FOLDER_PATH;
    }

    public String getFtpUsername() {
        return FTP_USERNAME;
    }

    public String getFtpPassword() {
        return FTP_PASSWORD;
    }

    public String getFtpHost() {
        return FTP_HOST;
    }

    public Integer getFtpPort() {
        return FTP_PORT;
    }

    public String getFtpConfigV1() {
        return FTP_CONFIG_V1;
    }

    public String getFtpConfigV2() {
        return FTP_CONFIG_V2;
    }

    public String getFtpChanelType() {
        return FTP_CHANEL_TYPE;
    }

    public String getTxtNotValidFolderPath() {
        return TXT_NOT_VALID_FOLDER_PATH;
    }

    public String getJsonNotValidFolderPath() {
        return JSON_NOT_VALID_FOLDER_PATH;
    }
}
