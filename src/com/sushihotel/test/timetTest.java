package com.sushihotel.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sushihotel.reservation.Reservation;
import com.sushihotel.reservation.ReservationMgr;

public class timetTest {
    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	public static void main(String[] args) throws ParseException {
		ReservationMgr rMgr = new ReservationMgr();
		//List reservationList;
		//Reservation reservation;
		Date timeCheck = new Date();
		timeCheck.setHours(13);
		timeCheck.setMinutes(00);
		timeCheck.setSeconds(00);
		long initialDelay = new Date(timeCheck.getTime()-System.currentTimeMillis()).getTime();
		if (initialDelay <= 0 ) {
			initialDelay = initialDelay + 86400000L;
		}
		
		ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
		execService.scheduleAtFixedRate(()->{
			//rMgr.updateExpiredReservation();
		}, initialDelay, 86400000L, TimeUnit.MILLISECONDS);
		execService.

//		System.out.println(timeCheck );
//		System.out.println(initialDelay);
//		System.out.println(timeCheck.getTime()-System.currentTimeMillis());


	}

	public void changeReserveStatus() {
		
	}
		
	
}
