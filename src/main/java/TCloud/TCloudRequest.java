package TCloud;

import org.apache.commons.codec.digest.HmacUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;

public class TCloudRequest {
    protected HashMap<String, String> requestArgs;
    private String secId;
    private String secKey;

    public TCloudRequest() {

    }

    public TCloudRequest(String secId, String secKey) {
        this.secId = secId;
        this.secKey = secKey;

        //init public request args
        requestArgs = new HashMap<>();
        //pending to add an action
        requestArgs.put("Action", "");
        //        requestArgs.put("Region", "");
        long timeStamp = System.currentTimeMillis() / 1000L;
        requestArgs.put("Timestamp", String.valueOf(timeStamp));
        requestArgs.put("Nonce", String.valueOf(Math.abs((new Random().nextInt()))));
        requestArgs.put("SecretId", secId);
        //tbd
//        requestArgs.put("Signature", "");

    }

    public void setAction(String action) {
        requestArgs.put("Action", action);
    }

    public String genRequestUrl() {
        StringBuilder url = new StringBuilder();
        url.append("cns.api.qcloud.com/v2/index.php?");
        var keys = requestArgs.keySet().toArray();
        Arrays.sort(keys);
        for (int i=0; i<keys.length; i++) {
            if (i != 0){
                url.append('&');
            }
            if (requestArgs.get(keys[i]) != null) {
                url.append(URLEncoder.encode(keys[i].toString(), StandardCharsets.UTF_8));
                url.append('=');
                url.append(URLEncoder.encode(requestArgs.get(keys[i]), StandardCharsets.UTF_8));
            }
        }
        return url.toString();
    }

    public String genRequestUrl4Signature() {
        StringBuilder url = new StringBuilder();
        url.append("cns.api.qcloud.com/v2/index.php?");
        var keys = requestArgs.keySet().toArray();
        Arrays.sort(keys);
        for (int i=0; i<keys.length; i++) {
            if (i != 0){
                url.append('&');
            }
            if (requestArgs.get(keys[i]) != null) {
                url.append(keys[i]);
                url.append('=');
                url.append(requestArgs.get(keys[i]));
            }
        }
        return url.toString();
    }

    public String getSignature(String requestUrl) throws Exception {
        String msg = "GET" + requestUrl;
        String signature = null;

        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            byte[] hash;
            SecretKeySpec spec = new SecretKeySpec(this.secKey.getBytes(StandardCharsets.UTF_8), mac.getAlgorithm());
            mac.init(spec);
            hash = mac.doFinal(msg.getBytes(StandardCharsets.UTF_8));
            signature = Base64.getEncoder().encodeToString(hash);
            return signature;
//            System.out.println(sig);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("HmacSHA1 is not supported." + e.getMessage());
        }
    }

    public String generateFinalRequestUrl() throws Exception {
        String url = genRequestUrl();
        String url4Signature = genRequestUrl4Signature();
        String signature = getSignature(url4Signature);
        return url + "&Signature=" + URLEncoder.encode(signature, StandardCharsets.UTF_8);
    }

    public String doRequest() throws Exception {
        String url = generateFinalRequestUrl();
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://" + url))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception ex) {
            return "";
//            throw new Exception("request failed.");
        }
    }
}
