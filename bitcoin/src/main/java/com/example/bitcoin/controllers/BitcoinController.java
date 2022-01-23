package com.example.bitcoin.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.bitcoin.entities.Bitcoin;
import com.example.bitcoin.entities.History;
import com.example.bitcoin.entities.Response;
import com.example.bitcoin.services.BitcoinService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class BitcoinController {

    // private BitcoinService bitcoinService = new BitcoinService();
    @Autowired
    BitcoinService bitcoinService;

    @RequestMapping(value = "/", method=RequestMethod.GET)
    @ResponseBody
    public Response getBitcoin() {
        List<Bitcoin> listBitcoin = bitcoinService.getAllBitcoins();
        // System.out.println("get bitcoin");
        return new Response(HttpStatus.OK.value(), "Success", listBitcoin);
    }

    @RequestMapping(value = "/history", method=RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Response getHistory(@Valid @RequestBody History history, HttpServletResponse response) {
        String startDateStr = history.getStartDatetime();
        String endDateStr = history.getEndDatetime();
        List<Bitcoin> listHistory = new ArrayList<Bitcoin>();

        try {
            DateTime startDate = new DateTime(startDateStr);
            DateTime endDate = new DateTime(endDateStr);

            if (String.valueOf(startDate.getYear()).equals(startDateStr) || String.valueOf(endDate.getYear()).equals(endDateStr)) {
                // System.out.println("startDate and/or endDate is invalid");
                throw new IllegalArgumentException();
            }

            listHistory = bitcoinService.getHistory(history);

        } catch (IllegalArgumentException ex) {
            // System.out.println("ill: " + ex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new Response(HttpStatus.BAD_REQUEST.value(), "Request is invalid");
        } catch (Exception ex) {
            // System.out.println("ex: " + ex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new Response(HttpStatus.BAD_REQUEST.value(), "Request is invalid");
        }

        response.setStatus(HttpServletResponse.SC_OK);
        return new Response(HttpStatus.OK.value(), "Success", listHistory);
    }

    @RequestMapping(value = "/bitcoin", method=RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Response addBitcoin(@Valid @RequestBody Bitcoin bitcoin, HttpServletResponse response) {
        // System.out.println("add bitcoin");
        String dateStr = bitcoin.getDatetime();
        
        try {
            DateTime date = new DateTime(dateStr);
            if (String.valueOf(date.getYear()).equals(dateStr)) {
                // System.out.println("Datetime is invalid");
                throw new IllegalArgumentException();
            }

            String setDate = bitcoinService.convertDateToString(date);
            // String[] dateFormat = date.toString().split("[.+]"); //yyyy-MM-dd'T'HH:mm:ss .SSS timezone
            // String setDate = dateFormat[0] + "+" + dateFormat[2];
            bitcoin.setDatetime(setDate);
            bitcoinService.addBitcoin(bitcoin);

        } catch (IllegalArgumentException ex) {
            // System.out.println("ill: " + ex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new Response(HttpStatus.BAD_REQUEST.value(), "Request is invalid");
        } catch (Exception ex) {
            // System.out.println("ex: " + ex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new Response(HttpStatus.BAD_REQUEST.value(), "Request is invalid");
        }

        response.setStatus(HttpServletResponse.SC_CREATED);
        return new Response(HttpStatus.CREATED.value(), "Created.");
    }

}