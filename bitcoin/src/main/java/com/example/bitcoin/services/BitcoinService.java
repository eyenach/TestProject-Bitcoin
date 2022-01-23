package com.example.bitcoin.services;

import java.util.ArrayList;
import java.util.List;

import com.example.bitcoin.entities.Bitcoin;
import com.example.bitcoin.entities.History;
import com.example.bitcoin.repositories.BitcoinRepository;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitcoinService {

    @Autowired
	private BitcoinRepository bitcoinRepository;

    public List<Bitcoin> getAllBitcoins(){
        // System.out.println("service get all sort: ");
		for (Bitcoin b : bitcoinRepository.findAllOrder()) {
            System.out.println(b.getDatetime() + " = " + b.getAmount());
        }
		return bitcoinRepository.findAllOrder();
	}
	
	public List<Bitcoin> getHistory(History history){
        // System.out.println("service get all between: ");
		
		DateTime startDate = DateTime.parse(history.getStartDatetime()).withMinuteOfHour(0).withSecondOfMinute(0);
		DateTime endDate = DateTime.parse(history.getEndDatetime()).withMinuteOfHour(0).withSecondOfMinute(0);
		DateTimeZone zone = startDate.getZone();
		int range = endDate.getHourOfDay() - startDate.getHourOfDay();
		System.out.println(startDate + " timezone=" + startDate.getZone() + " range=" + range);

		List<String> listDatetime = new ArrayList<String>();
		DateTime tempDate = startDate;
		for (int i=0; i<=range; i++) {
			if (i == 0) {
				listDatetime.add(convertDateToString(startDate));
			} else if (i == range) {
				listDatetime.add(convertDateToString(endDate));
			} else {
				LocalDateTime local = new LocalDateTime(tempDate.toInstant(), zone).plusHours(1);
				tempDate = local.toDateTime(zone);
				listDatetime.add(convertDateToString(tempDate));
			}
		}
		// System.out.println("listDatetime=" + listDatetime);

		List<Bitcoin> listHistory = new ArrayList<Bitcoin>();
		float amount = 1000;
		for (String date: listDatetime) {
			DateTime start = new DateTime(date);
			DateTime end = new DateTime(date).withMinuteOfHour(59).withSecondOfMinute(59);
			// System.out.println("query: " + start + " => " + end);

			String startStr = convertDateToString(start);
			String endStr = convertDateToString(end);

			List<Bitcoin> listAll = new ArrayList<Bitcoin>();
			listAll = bitcoinRepository.findAllBetween(startStr, endStr);
			for (Bitcoin bitcoin: listAll) {
				amount += bitcoin.getAmount();
			}

			Bitcoin bitcoin = new Bitcoin(date, amount);
			listHistory.add(bitcoin);
		}

		return listHistory;
	}

	public void addBitcoin(Bitcoin bitcoin){
		// System.out.println("service add");
		bitcoinRepository.save(bitcoin);
	}

	public String convertDateToString(DateTime date) {
		String[] dateFormat = date.toString().split("[.+]");
        String dateStr = dateFormat[0] + "+" + dateFormat[2];
		return dateStr;
	}
    
}
