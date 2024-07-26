package com.qg24.softwareplatform.util;

import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectRequest;
import lombok.Data;

import java.io.File;

@Data
public class HuaweiOBSUtils {

    private  String ak;
    private  String sk;
    private  String endPoint;
    private  String bucketName;
    private  ObsClient obsClient;

    public HuaweiOBSUtils(String ak, String sk, String endPoint, String bucketName) {
        this.ak = ak;
        this.sk = sk;
        this.endPoint = endPoint;
        this.bucketName = bucketName;
        this.obsClient = new ObsClient(ak, sk, endPoint);
    }

    public void uploadToOBS(File file, String fileName) {
        PutObjectRequest request = new PutObjectRequest();
        request.setBucketName("qg23onnx");
        request.setObjectKey("Upload/" + fileName);
        request.setFile(file);
        obsClient.putObject(request);
    }

    public String generateOBSUrl(String uniqueFilename) {
        return "https://" + bucketName + "." + endPoint.substring(("https://").length()) + "/Upload/" + uniqueFilename;
    }

    public String generateOBSUrl(String endPoint, String bucketName, String uniqueFilename) {
        return "https://" + bucketName + "." + endPoint.substring(("https://").length()) + "/Upload/" + uniqueFilename;
    }


}
