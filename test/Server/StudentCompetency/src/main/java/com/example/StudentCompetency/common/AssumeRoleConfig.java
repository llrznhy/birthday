package com.example.StudentCompetency.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
@Component
public class AssumeRoleConfig {

    private static String accessKeyId;
    private static String secretAccessKey;
    private static String sessionToken;
    private static Logger logger = LoggerFactory.getLogger(AssumeRoleConfig.class);

    @Autowired
    public AssumeRoleConfig(Environment env) {
        AssumeRoleConfig.accessKeyId = env.getProperty("aws.access_key_id");
        AssumeRoleConfig.secretAccessKey = env.getProperty("aws.secret_access_key");
        AssumeRoleConfig.sessionToken = env.getProperty("aws.session_token");
    }
    public static AwsSessionCredentials assumeRoleAndGetCredentials(String roleArn, String roleSessionName) {
        logger.info("acc:"+accessKeyId+"sec:"+secretAccessKey);
        AwsSessionCredentials awsCreds = AwsSessionCredentials.create(accessKeyId,secretAccessKey,sessionToken);

        StaticCredentialsProvider credsProvider = StaticCredentialsProvider.create(awsCreds);

        StsClient stsClient = StsClient.builder()
                .region(Region.CA_CENTRAL_1)
                .credentialsProvider(credsProvider)
                .build();

        AssumeRoleRequest assumeRoleRequest = AssumeRoleRequest.builder()
                .roleArn(roleArn)
                .roleSessionName(roleSessionName)
                .build();

        AssumeRoleResponse assumeRoleResponse = stsClient.assumeRole(assumeRoleRequest);

        String accessKeyId = assumeRoleResponse.credentials().accessKeyId();
        String secretAccessKey = assumeRoleResponse.credentials().secretAccessKey();
        String sessionToken = assumeRoleResponse.credentials().sessionToken();
        logger.info("access key:"+accessKeyId+", secret access key:"+secretAccessKey+"token:"+sessionToken);
        stsClient.close();

        return AwsSessionCredentials.create(accessKeyId, secretAccessKey, sessionToken);
    }
}
