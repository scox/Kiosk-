	package com.kiosk.service;
	
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.net.Socket;
	import java.net.UnknownHostException;
	
	import org.springframework.stereotype.Service;
	
	/**
	 * Author: Sam Cox
	 * Date: 06/01/2012
	 * EmergencyStopService.Java:  This class provides business logic layer to send an emergency
	 *  packet to the service.
	 */
	
	@Service
	public class EmergencyServiceImpl implements EmergencyService {
	
		@Override
		public boolean triggerEmergencyStop() {
	
			boolean status = false;
	
			Socket Socket = null;
			PrintWriter out = null;
			BufferedReader in = null;
	
			try {
				//initiate connection with server
				Socket = new Socket("localhost", 4444);
				out = new PrintWriter(Socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(
						Socket.getInputStream()));
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host: localhost");
				System.exit(1);
			} catch (IOException e) {
				System.err
						.println("Couldn't get I/O for the connection to: localhost.");
				System.exit(1);
			}
	
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(
					System.in));
			String fromServer;
	
			try {
	
				// Server response. If server doesnt send ack send emergency packet
				while ((fromServer = in.readLine()) != null) {
	
					if (fromServer.equals("5")) {
					
						status = true;
						out.print("STOP");
						out.close();
						in.close();
						stdIn.close();
						Socket.close();
						break;
					} 
					
					//send packet to server with opt code 4
					else {
						System.out.println("Client: constructed packet ");
						out.println("4");
					}
				}
	
			}
	
			catch (Exception e) {
				System.out.println("Couldnt send request");
				status = true;
			}
	
			return status;
	
		}
	
	}