import TCloud.Config;
import com.alibaba.fastjson.JSON;

import java.net.Inet6Address;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Enumeration;
import java.util.NoSuchElementException;

public class SystemUtil {
    public static String getLocalIPv6Addr(String nicName) throws Exception {
        Enumeration<NetworkInterface> nics;
        try {
            nics = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException ex) {
            throw new Exception("Cannot find any NIC.");
        }
        var nicList = Collections.list(nics);
        var nic = nicList.stream()
                .filter(i -> i.getName().equals(nicName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("NIC not exist."));
        var ipAddr = nic.getInterfaceAddresses().stream()
                .filter(ip -> ip.getAddress().getClass() == Inet6Address.class)
                .filter(ip -> !ip.getAddress().isAnyLocalAddress())
                .filter(ip -> !ip.getAddress().isLoopbackAddress())
                .filter(ip -> !ip.getAddress().isLinkLocalAddress())
                .filter(ip -> !ip.getAddress().isMulticastAddress())
                .filter(ip -> !ip.getAddress().isSiteLocalAddress())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Cannot find any IPv6 address."))
                .getAddress();
        return ipAddr.getHostAddress();
    }

    public static void printAllNic() {
        Enumeration<NetworkInterface> nics;
        try {
            nics = NetworkInterface.getNetworkInterfaces();
        } catch(SocketException ex) {
            System.out.println("Cannot find any NIC.");
            return;
        }

        var nicList = Collections.list(nics);
        for (NetworkInterface nif : nicList) {
            System.out.println(nif);
        }
    }

    /*
    create an config file in home folder
     */
    public static void createConfigFile() {
        var config = new Config();
        config.setDomain("youdomain.yourdomain");
        config.setSubDomain("yoursubdomain");
        config.setSecId("your secId");
        config.setSecKey("your sedKey");
        config.setNif("your nif like eth0");

        try{
            Path path = getConfigFilePath();

            Files.createFile(path);
            String configJson = JSON.toJSONString(config);
            Files.writeString(path, (CharSequence) configJson, Charset.forName("UTF-8"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static Path getConfigFilePath() {
        String homeFolder = System.getProperty("user.home");
        Path path = Paths.get(homeFolder + "/.tddnsConfig.json");

        return path;
    }

    public static Config getConfig() throws Exception {
        String configFile = Files.readString(SystemUtil.getConfigFilePath());
        var config = JSON.parseObject(configFile, Config.class);
        return config;
    }
}
