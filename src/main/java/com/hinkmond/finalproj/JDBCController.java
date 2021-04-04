package com.hinkmond.finalproj;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;


@RestController
public class JDBCController {
    private final static String KEYFILEPATH = "./keyFile.key";

    @CrossOrigin
    @RequestMapping(value = "/mobilepayment", method = RequestMethod.GET)
    public String printCryptTest() {
        AESUtils aesUtils = new AESUtils();

        String encryptedStr = aesUtils.encrypt("Mobile Payment!", KEYFILEPATH);
        return ("Decrypt = " + aesUtils.decrypt(encryptedStr, KEYFILEPATH));
    }

    @CrossOrigin
    @RequestMapping(value = "/listAllTrans", method = RequestMethod.GET)
    public String listAllTrans() {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();

        String queryStr = "SELECT * from trans_info;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("trans_id")).append(", ")
                    .append(sqlRowSet.getString("first_name")).append(", ")
                    .append(sqlRowSet.getString("last_name")).append(", ")
                    .append(sqlRowSet.getString("pos_location")).append(", ")
                    .append(sqlRowSet.getString("issuer_bank")).append(", ")
                    .append(sqlRowSet.getString("price")).append(", ")
                    .append(sqlRowSet.getString("created_at"))
                    .append("\n");
        }
        return ("SELECT * from trans_info:\n" + resultStr);
    }

    @CrossOrigin
    @RequestMapping(value = "/addTran", method = RequestMethod.POST)
    public String addTran(@RequestBody AddTranData addTranData) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String queryStr = "INSERT INTO trans_info (first_name, last_name, pos_location, issuer_bank, price) " +
                "VALUES (" +
                "'" + addTranData.getFirstName() + "'," +
                "'" + addTranData.getLastName() + "'," +
                "'" + addTranData.getPosLocation() + "'," +
                "'" + addTranData.getIssuerBank() + "'," +
                "'" + addTranData.getPrice() + "'" +
                ");";
        int rowsUpdated = jdbcTemplate.update(queryStr);
        return ("Rows updated: " + rowsUpdated);
    }

    @CrossOrigin
    @RequestMapping(value = "/printAllAccts", method = RequestMethod.GET)
    public String printAllAccts() {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();

        String queryStr = "SELECT * from acct_info;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("trans_id")).append(", ")
                    .append(sqlRowSet.getString("acct_num")).append(", ")
                    .append(sqlRowSet.getString("balance"))
                    .append("\n");
        }
        return ("SELECT * from acct_info:\n" + resultStr);
    }
}
