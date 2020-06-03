import TCloud.AddRecord.AddRecordRequest;
import TCloud.Config;
import TCloud.DeleteRecord.DeleteRecordRequest;
import TCloud.EditRecord.EditRecordRequest;
import TCloud.RecordList.DnsRecord;
import TCloud.RecordList.GetRecordListRequest;
import TCloud.RecordList.RecordListResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class DdnsApplication {

    public static void main(String[] args) {
        var argList = Arrays.asList(args);
        Config config;
        RecordListResponse recordListResponse;
        String ipv6Address;
        SystemUtil.printAllNic();

        try {
            config = SystemUtil.getConfig();
        } catch (Exception ex) {
            System.out.println(SystemUtil.getConfigFilePath().toString() + " config file error or not exist.");
            System.out.println("Please delete it and use -generate to generate a config file.");
            System.out.println("All NICs in the system：");
            SystemUtil.printAllNic();
            return;
        }

        //get record lists
        try {
            var request = new GetRecordListRequest(
                    config.getSecId(),
                    config.getSecKey(),
                    config.getDomain(),
                    config.getSubDomain(),
                    "AAAA");
            recordListResponse = request.getRecordList();
        } catch (Exception ex) {
            System.out.println("get records failed: " + ex.toString());
            return;
        }
        //get local ipv6 address
        try {
            ipv6Address = SystemUtil.getLocalIPv6Addr(config.getNif());
        } catch (Exception ex) {
            System.out.println("Cannot get ipv6 address: " + ex.toString());
            return;
        }

        if (recordListResponse.getRecords().size() == 0) {
            System.out.println(String.format("No record found for %s.%s", config.getSubDomain(), config.getDomain()));
            return;
        } else if ( recordListResponse.getRecords().size() > 1) {
            System.out.println(String.format("More than 1 records found for %s.%s", config.getSubDomain(), config.getDomain()));
            return;
        }

//        var record = recordListResponse.getRecords().get(0);
//        try {
//            var req = new EditRecordRequest(config.getSecId(), config.getSecKey(), config.getDomain(), record.getId(),
//                    config.getSubDomain(), "AAAA", ipv6Address);
//            req.editRecord();
//        } catch (Exception ex) {
//            System.out.println("Error while edit record: " + ex.toString());
//        }

        var recordsToBeRemoved = recordListResponse.getRecords().stream()
                .filter(r -> !r.getValue().equals(ipv6Address))
                .collect(Collectors.toList());

        if (recordsToBeRemoved.size() == recordListResponse.getRecords().size()) {
            //add address to tcloud
            try {
                var req = new AddRecordRequest(config.getSecId(), config.getSecKey(),
                        config.getDomain(), config.getSubDomain(), "AAAA", ipv6Address);
                System.out.println("Adding record: " + req.genRequestUrl());
                req.addRecord();
            } catch (Exception ex) {
                System.out.println("Error while create record: " + ex.toString());
//                return;
            }
        }

        //remove records
        for (DnsRecord r: recordsToBeRemoved){
            try {
                var req = new DeleteRecordRequest(config.getSecId(), config.getSecKey(), config.getDomain(), r.getId());
                System.out.println("Removing record: " + r.toString());
                req.deleteRecord();
            } catch (Exception ex) {
                System.out.println("Error while delete record: " + ex.toString());
            }
        }



    }


}
